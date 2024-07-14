package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media;
/*
 Author=Supreet Singh
 Date= 11/02/21 10:35 PM
*/


import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs.TokenService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MediaDb.MediaDbDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MediaDb.MediaDbRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Crud.MessageRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Mime.MimeTypeDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Mime.MimeTypeService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import javassist.bytecode.ByteArray;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.random.RandomGenerator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MediaServiceImpl implements MediaService {

    private static final String driveBaseUrl = "https://wa.chatmybot.in/drive/drive/v1/drive/";

    @Autowired
    TokenService tokenService;
    @Autowired
    MediaService mediaService;
    @Autowired
    MediaDbRepo mediaDbRepo;

    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);
    @Autowired
    RabbitMqqueService rabbitMqqueService;
    @Autowired
    MimeTypeService mimeTypeService;

    @Override
    public String getFileExtension(String fileName) throws Exception {

        String[] part = fileName.split("\\.");
        try {
            MimeTypeDao mimeTypeDao = mimeTypeService.findById(part[part.length - 1]);
            return mimeTypeDao.getMimeType();
        } catch (Exception ew) {
            throw new Exception("File Format not supported!!");
        }
    }

    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(200, TimeUnit.SECONDS)
            .writeTimeout(200, TimeUnit.SECONDS)
            .connectTimeout(200, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(80, 200, TimeUnit.SECONDS))
            .build();
    @Autowired
    private MessageRepo messageRepo;

    @Override
    public MediaDao uploadFile(byte[] file, SubscriptionDao accountMasterDao, String filename) throws Exception {

        if (file.length > 20971520) {
            throw new Exception("Cannot Upload File Greater then 20 MB");
        }

        String mimeType = getFileExtension(filename);
        logger.info("Will Upload File To Server with length {} of account {} filename is {} Mime type is", file.length, accountMasterDao.getAccountId(), filename, mimeType);


        RequestBody body = RequestBody.create(file, MediaType.parse(mimeType));
        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl() + "v1/media")
                .method("POST", body)
                .addHeader("Content-Type", mimeType)
                .addHeader("Content-Disposition", "attachment; filename=\"test\"")
                .addHeader("filename", filename)
                .addHeader("Authorization", "Bearer " + tokenService.getToken(accountMasterDao.getAccountId()))
                .build();
        String response = client.newCall(request).execute().body().string();
        logger.info("Response after uploading file  is {}",response);
        return new MediaDao().toObject(response);

    }

    @Override
    public MediaDao uploadFileByCloudAPI(byte[] fileArray, SubscriptionDao subscriptionDao, String originalFilename) throws Exception {
        if (fileArray.length > 20971520) {
            throw new Exception("Cannot Upload File Greater then 20 MB");
        }
        String mimeType = getFileExtension(originalFilename);
        logger.info("Will Upload File To Server with length {} of account {} filename is {} Mime type is", fileArray.length, subscriptionDao.getAccountId(), originalFilename, mimeType);
        RequestBody body = RequestBody.create(fileArray,MediaType.parse(mimeType));
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("type",mimeType)
                .addFormDataPart("messaging_product","whatsapp")
                .addFormDataPart("file",originalFilename,body)
//        RequestBody.create(MediaType.parse("application/octet-stream"),
//                    new File("/home/ajay/Downloads/sample.pdf")))
                .build();
         Request request = new Request.Builder()
                 .url("https://graph.facebook.com/v15.0/"+ subscriptionDao.getCloudAPIPhoneNumberID() + "/media")
                 .method("POST",requestBody)
                 .addHeader("Authorization", "Bearer " + subscriptionDao.getCloudAPIToken())
                 .build();
        String strResponse= new OkHttpClient().newCall(request).execute().body().string();
        MediaInnerDao mediaInnerDao = new Gson().fromJson(strResponse, MediaInnerDao.class);
        MediaDao mediaDao = new MediaDao();
        List<MediaInnerDao> mediaInnerDaoList = new ArrayList<>();
        mediaInnerDaoList.add(mediaInnerDao);
        mediaDao.setMedia(mediaInnerDaoList);
        return mediaDao;
    }

    @Override
    public ResponseEntity getFileByCloudAPI(String id, SubscriptionDao subscriptionDao,String ext) throws Exception {
        // For getting mediaUrlLink with the media Id......
        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v16.0/"+ id +"/")
                .method("GET",null)
                .addHeader("Authorization","Bearer "+ subscriptionDao.getCloudAPIToken())
                .build();

            Response response = client.newCall(request).execute();
                // Now hit that response url to get media...
                String resString = response.body().string();
                JSONObject jsonObject = new JSONObject(resString);
                String downloadMediaUrl = jsonObject.getString("url");
                logger.info("The download url is "+ downloadMediaUrl);
                URL urlObj = new URL(downloadMediaUrl);
                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", "Bearer "+ subscriptionDao.getCloudAPIToken());
                InputStream inputStream = connection.getInputStream();
//                String mediaType = connection.getContentType();
//                String mediaExt = mediaType.substring(mediaType.lastIndexOf("/") + 1);
                int contentLength = connection.getContentLength();

            InputStreamResource resource = new InputStreamResource(inputStream);
            String filename="file";
            HttpHeaders headers = new HttpHeaders();
            if(!StringUtils.isEmpty(ext))
            {
                String fileExtension = MimeTypeUtils.parseMimeType(ext).getSubtype();
                filename = filename+"."+fileExtension;
            }
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Access-Control-Expose-Headers","Content-Disposition");
            logger.info("The method reched here");
//            connection.disconnect();
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(contentLength)
                    .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

    }

    @Override
    public String getMultipartFileByCloudAPI(String id, SubscriptionDao subscriptionDao, String mimeType) throws Exception{
        // For getting mediaUrlLink with the media Id......
        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v16.0/"+ id +"/")
                .method("GET",null)
                .addHeader("Authorization","Bearer "+ subscriptionDao.getCloudAPIToken())
                .build();

        Response response = client.newCall(request).execute();
        String resString = response.body().string();
        JSONObject jsonObject = new JSONObject(resString);
        String downloadMediaUrl = jsonObject.getString("url");
        URL urlObj = new URL(downloadMediaUrl);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer "+ subscriptionDao.getCloudAPIToken());
        InputStream inputStream = connection.getInputStream();

        String fileName= "file3";
        if(!StringUtils.isEmpty(mimeType))
        {
            String fileExtension = MimeTypeUtils.parseMimeType(mimeType).getSubtype();
            fileName = fileName+"."+fileExtension;
        }
//        connection.disconnect();
        MultipartFile multipartFile = inputStreamToMultipartFile(inputStream, fileName, mimeType);
        logger.info("The MultiPart Object is {}", multipartFile.toString());
        String sharableUrl = storeFileTODrive(multipartFile, subscriptionDao, fileName);
        return sharableUrl;
    }

    @Override
    public String getMultipartFileByAmeyoServer(String id, SubscriptionDao subscriptionDao, String mimeType) {
        byte[] arr = new byte[0];
        try {
            arr = mediaService.getFileAmeyo(id, subscriptionDao);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MediaDbDao mediaDbDao=null;
        String fileName="file";
        try {
            mediaDbDao = mediaDbRepo.findById(id).get();
            fileName=mediaDbDao.getFilename();
        }catch (Exception ew)
        {
            fileName="file";
        }
        if(!StringUtils.isEmpty(mimeType))
        {
            String fileExtension = MimeTypeUtils.parseMimeType(mimeType).getSubtype();
            fileName=fileName+"."+fileExtension;
        }
        MultipartFile multipartFile = byteArrayToMultipartFile(arr, fileName, mimeType);
        String sharableUrl = storeFileTODrive(multipartFile, subscriptionDao, fileName);
        return sharableUrl;
    }

    @Override
    public String getMultipartFileByKarixServer(String id, SubscriptionDao subscriptionDao, String mimeType) {

        byte[] arr = new byte[0];
        try {
            arr = mediaService.getFile(id, subscriptionDao);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MediaDbDao mediaDbDao=null;
        String fileName="file";
        try {
            mediaDbDao = mediaDbRepo.findById(id).get();
            fileName=mediaDbDao.getFilename();
        }catch (Exception ew)
        {
            fileName="file";
        }
        if(!StringUtils.isEmpty(mimeType))
        {
            String fileExtension = MimeTypeUtils.parseMimeType(mimeType).getSubtype();
            fileName=fileName+"."+fileExtension;
        }
        MultipartFile multipartFile = byteArrayToMultipartFile(arr, fileName, mimeType);
        String sharableUrl = storeFileTODrive(multipartFile, subscriptionDao, fileName);
        return sharableUrl;
    }

    private String storeFileTODrive(MultipartFile multipartFile, SubscriptionDao subscriptionDao, String fileName) {
        try {
         byte[] bytes = multipartFile.getBytes();
        RequestBody body = RequestBody.create(bytes,MediaType.parse(multipartFile.getContentType()));
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",fileName,body)
                .build();

            Request request = new Request.Builder()
                .url("https://wa.chatmybot.in/gateway/drive/v1/drive/?id="+subscriptionDao.getAccountId())
//                .url("https://wa.chatmybot.in/gateway/drive/v1/drive/?id=20004")
                .post(requestBody)
                .build();
        Response response = null;
            response = client.newCall(request).execute();
            String resString = response.body().string();
            logger.info("The response from Drive is "+ resString);
            JSONObject jsonObject = new JSONObject(resString);
            long id = jsonObject.getLong("id");
            String shareableLink = "https://wa.chatmybot.in/Shareablelinks/" +subscriptionDao.getAccountId()+"/" + id + '/' + fileName;
            return shareableLink;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public MultipartFile inputStreamToMultipartFile(InputStream in, String fileName, String contentType) {
        try {
            byte[] byteArray = IOUtils.toByteArray(in);
            return new MockMultipartFile(fileName, fileName, contentType, byteArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public MultipartFile byteArrayToMultipartFile(byte[] bytes, String fileName, String contentType) {
        try {
            return new MockMultipartFile(fileName, fileName, contentType, bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public byte[] getFileAmeyo(String id, SubscriptionDao accountMasterDao) throws Exception {
        logger.info("Will get File {} ", id);
        byte[] file = null;
        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl() + "v1/media/" + id)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + tokenService.getToken(accountMasterDao.getAccountId()))
                .build();
        try {

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                file = response.body().bytes();
            } else {
                throw new Exception("Failed to Download File from WA");
            }


        } catch (IOException e) {
            logger.error("FAiled to Get File {} ", e.getMessage());
            e.printStackTrace();
        }
        return file;
    }

    //  Author=Ritu Redhu ----->
    public byte[] getFile(String id, SubscriptionDao accountMasterDao) throws Exception {
        String karixToken = accountMasterDao.getKarixToken();
        logger.info("id from frontend {} ", id);
        String newId =id.replace("==","");
        String urlencoding =URLEncoder.encode(newId,  "UTF-8" );
        String newId2 =urlencoding.replace("+","%2B");
        String newId3 =newId2.replace("%252BA","%2BA");
        String newId4 =newId3.replace("%253D%253D","");
        logger.info("newId2 from frontend {} ", newId4);
        String createUrl = "https://rcmmedia.instaalerts.zone/services/media/download?filedata="+ newId4 + "%3D%3D";
        logger.info("createUrl {}" , createUrl);
        byte[] file = null;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(createUrl)
                .method("GET",null)
                .addHeader("Authentication", "Bearer "+karixToken)
                .build();
        Response response = client.newCall(request).execute();
        logger.info("response {}" , response.body());
        file = response.body().bytes();
//        logger.info("response bytes {}" , response.body().bytes());
        response.close();
        return file;
    }

    // -----> Author=Ritu Redhu
    @Override
    public String deleteMedia(String id, SubscriptionDao accountMasterDao) throws Exception {
        String response = "";
        logger.info("Delelting  File {} ", id);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{}", mediaType);
        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl() + "v1/media/" + id)
                .method("DELETE", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + tokenService.getToken(accountMasterDao.getAccountId()))
                .build();
        try {
            response = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            response = e.getMessage();
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public MediaDao encodeAttachment(byte[] fileArray) {
        MediaDao mediaDao = new MediaDao();
        List<MediaInnerDao> mediaInnerDaoList = new ArrayList<>();
        MediaInnerDao mediaInnerDao = new MediaInnerDao();
        mediaInnerDao.setAttachedFileBase64(Base64.getEncoder().encodeToString(fileArray));
        mediaInnerDaoList.add(mediaInnerDao);
        mediaDao.setMedia(mediaInnerDaoList);
        return mediaDao;
    }

    @Override
    public MediaDao shareableLinkToBase64(String shareableUrl,String fileName) {

        MediaDao mediaDao = null;
        try {
            URL url = new URL(shareableUrl);
            URLConnection connection = url.openConnection();
            String mimeType = connection.getContentType();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] byteArray = new byte[7096];
            int bytesRead;
            while ((bytesRead = inputStream.read(byteArray)) != -1) {
                byteArrayOutputStream.write(byteArray, 0, bytesRead);
            }
            byte[] fileBytes = byteArrayOutputStream.toByteArray();
            inputStream.close();
            byteArrayOutputStream.close();
            mediaDao = mediaService.encodeAttachment(fileBytes);
            mediaDao.getMedia().get(0).setMimeType(mimeType);
            logger.info("All ok");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mediaDao;
    }


//    @Override
//    public byte[] decodeAttachment(String fileArray) {
//        byte[] downloadfile = Base64.getDecoder().decode(fileArray);
//        logger.info("downloadfile " );
//        return downloadfile;
//    }

}
