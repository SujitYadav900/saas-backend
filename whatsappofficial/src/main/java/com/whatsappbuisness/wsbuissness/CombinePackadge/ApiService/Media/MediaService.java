package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media;
/*
 Author=Supreet Singh
 Date= 11/02/21 10:34 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import org.springframework.http.ResponseEntity;

public interface MediaService {
    MediaDao uploadFile(byte[] file, SubscriptionDao accountMasterDao,String filename) throws Exception;

    byte[] getFile(String id, SubscriptionDao accountMasterDao) throws Exception;

    byte[] getFileAmeyo(String id, SubscriptionDao accountMasterDao) throws Exception;

    String deleteMedia(String id, SubscriptionDao accountMasterDao) throws Exception;
    MediaDao encodeAttachment(byte[] fileArray);

    MediaDao shareableLinkToBase64(String shareableUrl,String fileName);

    String getFileExtension(String fileName) throws Exception;

    MediaDao uploadFileByCloudAPI(byte[] fileArray, SubscriptionDao subscriptionDao, String originalFilename) throws  Exception;

    ResponseEntity getFileByCloudAPI(String id, SubscriptionDao subscriptionDao,String ext) throws Exception;

    String getMultipartFileByCloudAPI(String id, SubscriptionDao subscriptionDao, String mimeType) throws  Exception;

    String getMultipartFileByAmeyoServer(String id, SubscriptionDao subscriptionDao, String mimeType);

    String getMultipartFileByKarixServer(String id, SubscriptionDao subscriptionDao, String mimeType);

}

