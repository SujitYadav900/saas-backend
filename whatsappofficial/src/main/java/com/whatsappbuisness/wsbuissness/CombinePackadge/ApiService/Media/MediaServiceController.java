package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MediaDb.MediaDbDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MediaDb.MediaDbRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Crud.MessageRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Gateway;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;


@RestController
@RequestMapping("/media")
public class MediaServiceController {
    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    MediaService mediaService;
    private static final Logger logger= LoggerFactory.getLogger(MediaServiceController.class);
    @Autowired
    MediaDbRepo mediaDbRepo;
    @Autowired
    private MessageRepo messageRepo;

    @PostMapping("/clientsideuploadmedia")
    public MediaDao clientsideuploadmedia(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws Exception {
        TokenStoreDao tokenStoreDao = sessionRetrievalService.validate(httpServletRequest);
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(tokenStoreDao.getAccountId());
        MediaDbDao mediaDbDao = new MediaDbDao();
        byte[] fileArray = file.getBytes();
        if(subscriptionDao.getGateway() == Gateway.CLOUDAPI){
            MediaDao mediaDao= mediaService.uploadFileByCloudAPI(fileArray, subscriptionDao,file.getOriginalFilename());
            mediaDbDao.setId(mediaDao.getMedia().get(0).getId());
            mediaDbDao.setDate(DateTiming.getDateRedable());
            mediaDbDao.setDateFilter(DateTiming.getDateFilterDate());
            mediaDbDao.setFilename(file.getOriginalFilename());
            logger.info("upload file successfully to server of  {}",mediaDao);
            mediaDbRepo.save(mediaDbDao);
            return mediaDao;
        }
        return null;
    }

    @PostMapping("/")
    public MediaDao upload(@RequestParam("file") MultipartFile file, Authentication authentication) throws Exception {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        String contentType=file.getContentType();

        SubscriptionDao subscriptionDao = subscriptionService.getByActive(usermasterDao.getAccountId());

        byte[] fileArray = file.getBytes();
        MediaDbDao mediaDbDao=new MediaDbDao() ;
        logger.info("Will upload file to server of accountId {}",subscriptionDao.getAccountId());
        if(subscriptionDao.getGateway() == Gateway.KARIX){
            MediaDao mediaDao= mediaService.encodeAttachment(fileArray);
            String mimeType = mediaService.getFileExtension(file.getOriginalFilename());
            mediaDao.getMedia().get(0).setMimeType(mimeType);
            mediaDbDao.setId("0");
            mediaDbDao.setDate(DateTiming.getDateRedable());
            mediaDbDao.setDateFilter(DateTiming.getDateFilterDate());
            mediaDbDao.setFilename(file.getOriginalFilename());
            mediaDbRepo.save(mediaDbDao);
            return mediaDao;
        }else if(subscriptionDao.getGateway() == Gateway.CLOUDAPI){
            MediaDao mediaDao= mediaService.uploadFileByCloudAPI(fileArray, subscriptionDao,file.getOriginalFilename());
            mediaDbDao.setId(mediaDao.getMedia().get(0).getId());
            mediaDbDao.setDate(DateTiming.getDateRedable());
            mediaDbDao.setDateFilter(DateTiming.getDateFilterDate());
            mediaDbDao.setFilename(file.getOriginalFilename());
            logger.info("upload file successfully to server of  {}",mediaDao);
            mediaDbRepo.save(mediaDbDao);
            return mediaDao;
        }else{
            MediaDao mediaDao= mediaService.uploadFile(fileArray, subscriptionDao,file.getOriginalFilename());
            mediaDbDao.setId(mediaDao.getMedia().get(0).getId());
            mediaDbDao.setDate(DateTiming.getDateRedable());
            mediaDbDao.setDateFilter(DateTiming.getDateFilterDate());
            mediaDbDao.setFilename(file.getOriginalFilename());
           // logger.info("upload file successfully to server of  {}",mediaDao);
            mediaDbRepo.save(mediaDbDao);
            return mediaDao;
        }
    }

    @GetMapping("/external/{fileId}")
    public ResponseEntity<Resource> downloadFileExternal(@PathVariable("fileId") String id, @RequestParam("extensionType") String ext, Authentication authentication, HttpServletRequest httpServletRequest) throws Exception {

        TokenStoreDao tokenStoreDao =  sessionRetrievalService.validate(httpServletRequest);

        SubscriptionDao subscriptionDao = subscriptionService.getByActive(tokenStoreDao.getAccountId());
        byte[] arr = mediaService.
                getFile(id, subscriptionDao);
        MediaDbDao mediaDbDao=null;
        String filename="file";
        try {
            mediaDbDao = mediaDbRepo.findById(id).get();
            filename=mediaDbDao.getFilename();
        }catch (Exception ew)
        {
            filename="file";
        }

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(arr));
        HttpHeaders headers = new HttpHeaders();
        if(!StringUtils.isEmpty(ext))
        {
            String fileExtension = MimeTypeUtils.parseMimeType(ext).getSubtype();
            filename=filename+"."+fileExtension;
        }


        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Access-Control-Expose-Headers","Content-Disposition");


        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(arr.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
//    @GetMapping("/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String id, @RequestParam("extensionType") String ext, Authentication authentication) throws Exception {
//
//        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
//        SubscriptionDao subscriptionDao = subscriptionService.getByActive(usermasterDao.getAccountId());
//        byte[] arr = mediaService.getFile(id, subscriptionDao);
//        MediaDbDao mediaDbDao=null;
//        String filename="file";
//        try {
//             mediaDbDao = mediaDbRepo.findById(id).get();
//            filename=mediaDbDao.getFilename();
//        }catch (Exception ew)
//        {
//            filename="file";
//
//
//        }
//
//        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(arr));
//        HttpHeaders headers = new HttpHeaders();
//        if(!StringUtils.isEmpty(ext))
//        {
//            String fileExtension = MimeTypeUtils.parseMimeType(ext).getSubtype();
//            filename=filename+"."+fileExtension;
//        }
//
//
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        headers.add("Access-Control-Expose-Headers","Content-Disposition");
//
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(arr.length)
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
//    }

    //  Author=Ritu Redhu ----->
//@GetMapping("/{fileId}")
//public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String id, @RequestParam("extensionType") String ext, Authentication authentication) throws Exception {
//
//    UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
//    SubscriptionDao subscriptionDao = subscriptionService.getByActive(usermasterDao.getAccountId());
//    if (subscriptionDao.getGateway()== Gateway.KARIX) {
//        logger.info("Karix user" );
////        byte[] arr = mediaService.getFile(id, subscriptionDao);
////        MediaDbDao mediaDbDao = null;
////        String filename = "file";
////        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(arr));
////        HttpHeaders headers = new HttpHeaders();
////            String fileExtension = MimeTypeUtils.parseMimeType(ext).getSubtype();
////            filename = filename + "." + fileExtension;
////
////        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
////        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
////        headers.add("Pragma", "no-cache");
////        headers.add("Expires", "0");
////        headers.add("Access-Control-Expose-Headers", "Content-Disposition");
////
////
////        return ResponseEntity.ok()
////                .headers(headers)
////                .contentLength(arr.length)
////                .contentType(MediaType.APPLICATION_OCTET_STREAM)
////                .body(resource);
//        return  null;
//    }
//    else
//    {
//        byte[] arr = mediaService.getFileAmeyo(id, subscriptionDao);
//        MediaDbDao mediaDbDao = null;
//        String filename = "file";
//    try {
//        mediaDbDao = mediaDbRepo.findById(id).get();
//        filename=mediaDbDao.getFilename();
//    }catch (Exception ew)
//    {
//        filename="file";
//
//
//    }
//
//        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(arr));
//        HttpHeaders headers = new HttpHeaders();
//        if (!StringUtils.isEmpty(ext)) {
//            String fileExtension = MimeTypeUtils.parseMimeType(ext).getSubtype();
//            filename = filename + "." + fileExtension;
//        }
//
//
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        headers.add("Access-Control-Expose-Headers", "Content-Disposition");
//
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(arr.length)
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
//    }
//}
//    // -----> Author=Ritu Redhu

    @GetMapping("/karix")
    public ResponseEntity<Resource> downloadFilekarix( @RequestParam("extensionType") String ext, @RequestParam("id") String id ,Authentication authentication) throws Exception {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(usermasterDao.getAccountId());
        logger.info("subscription subscriptionDao {}",subscriptionDao);
        if (subscriptionDao.getGateway()== Gateway.KARIX) {
            logger.info("Karix user");
            byte[] arr = mediaService.getFile(id, subscriptionDao);

            MediaDbDao mediaDbDao = null;
            String filename = "file";
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(arr));
            HttpHeaders headers = new HttpHeaders();
            if (!StringUtils.isEmpty(ext)) {
                String fileExtension = MimeTypeUtils.parseMimeType(ext).getSubtype();
                filename = filename + "." + fileExtension;
            }
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Access-Control-Expose-Headers", "Content-Disposition");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(arr.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        }
        else if(subscriptionDao.getGateway()== Gateway.CLOUDAPI){
            return mediaService.getFileByCloudAPI(id, subscriptionDao, ext);
        }
        else {
            byte[] arr = mediaService.getFileAmeyo(id, subscriptionDao);
            MediaDbDao mediaDbDao=null;
            String filename="file";
            try {
                mediaDbDao = mediaDbRepo.findById(id).get();
                filename=mediaDbDao.getFilename();
            }catch (Exception ew)
            {
                filename="file";
            }
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(arr));
            HttpHeaders headers = new HttpHeaders();
            if(!StringUtils.isEmpty(ext))
            {
                String fileExtension = MimeTypeUtils.parseMimeType(ext).getSubtype();
                filename=filename+"."+fileExtension;
            }
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Access-Control-Expose-Headers","Content-Disposition");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(arr.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        }
    }
//     -----> Author=Ritu Redhu

}
