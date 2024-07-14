package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Profile;
/*
 Author=Supreet Singh
 Date= 11/02/21 9:51 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;

public interface ProfileService {

    String getProfileAbout(SubscriptionDao accountMasterDao) throws Exception;

    String getProfilePic(SubscriptionDao accountMasterDao) throws Exception;
    String setProfileAbout(ProfileDao profile,SubscriptionDao accountMasterDao) throws Exception;
    String setProfilePic(byte[] profilePic,SubscriptionDao accountMasterDao) throws Exception;

    String getBuissnessProfile(SubscriptionDao accountMasterDao) throws Exception;
    String setBuissnessProfile(ProfileBuisnessDao profile,SubscriptionDao accountMasterDao) throws Exception;
    void updateWebhook(long id,SubscriptionDao accountMasterDao);

    void updateKarixWebhook(long id,SubscriptionDao accountMasterDao);
}
