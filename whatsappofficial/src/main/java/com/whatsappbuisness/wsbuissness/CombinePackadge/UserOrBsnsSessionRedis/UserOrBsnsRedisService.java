package com.whatsappbuisness.wsbuissness.CombinePackadge.UserOrBsnsSessionRedis;


import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.UserOrBuisnessIntiatedDao;

public interface UserOrBsnsRedisService {

        /**
         *This method is used for checking session
         * id would be generated by concating string in pattern phn + "_" + code + "_" + accountId + "_" + chatSide.toString() + "_" + category
         * @param phn
         * @param code
         * @param accountId
         * @param chatSide
         * @param category
         * @return will return status=true when session is created
         */
        CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao,Boolean> checkSession(String phn, String code, long accountId, ChatSide chatSide, String category, int expiryInMins);

        /**
         * Will Delele all session so use carefully
         */
        /**
         * This method is used to create a new session which will auto expire after a given time
         * @param phn
         * @param code
         * @param accountId
         * @param chatSide
         * @param category
         * @param expiryInMins
         * @param userOrBuisnessIntiatedDao
         * @return
         */
        UserOrBuisnessIntiatedDao insert(String phn, String code, long accountId, ChatSide chatSide, String category, int expiryInMins, UserOrBuisnessIntiatedDao userOrBuisnessIntiatedDao);
        void deleteSessionByKey(String phn, String code, long accountId, ChatSide chatSide, String category);
}