package com.whatsappbuisness.wsbuissness.CombinePackadge.DriveService;

import com.google.gson.Gson;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private static final String driveBaseUrl = "https://wa.chatmybot.in/drive/drive/v1/drive/";
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(5, 120, TimeUnit.SECONDS))
            .build();
    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    @Override
    public FileUploadResponseDao save(FileUploadDao fileUploadDao) {
        FileUploadResponseDao fileUploadResponseDao = new FileUploadResponseDao();
        //logger.info("Will Upload To server {}", fileUploadDao);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileUploadDao.getFileName(),
                        RequestBody.create(fileUploadDao.getFile(), MediaType.parse(fileUploadDao.getMediaType())))
                .build();

        Request request = new Request.Builder()
                .url(driveBaseUrl + "?id=" + fileUploadDao.getAccountId())
                .post(requestBody)
                .build();
        Response response = null;
        try {

            response = client.newCall(request).execute();
            String responseStr = response.body().string();
            if (response.isSuccessful()) {

                //logger.info("After Uploading File {}", responseStr);
                fileUploadResponseDao = new Gson().fromJson(responseStr, FileUploadResponseDao.class);
                fileUploadResponseDao.setFileUrl(driveBaseUrl + fileUploadResponseDao.getId());
            } else {
                logger.error("Failed Uploading File {}", responseStr);
            }
        } catch (Exception ew) {
            logger.error("Failed Uploading File Catch {}", ew.getMessage());
            logger.error("Error After Uploading {}",ew);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return fileUploadResponseDao;
    }
}
