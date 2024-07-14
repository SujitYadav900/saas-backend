package com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWiseRateRetDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.GroupQueryResultDao;

import java.util.List;

public interface UserOrBuisnessService {
    /**
     * will check if session found if not found then will create one and will also will update number of
     * message (User/Buisness)
     * @param phn
     * @param code
     * @param accountId
     * @param chatSide
     * @param ded deduction price amount

     * @return check enum for status first
     */
    CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao,StatusDaoUsrOrBsn> checkSession(String phn, String code, long accountId, ChatSide chatSide, double ded);
    /**
     *(*** Mysql ***) will check if session found if not found then will create one and will also will update number of
     * message (User/Buisness)
     * @param phn
     * @param code
     * @param accountId
     * @param chatSide
     * @param ded deduction price amount

     * @return check enum for status first
     */
    CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao,StatusDaoUsrOrBsn> checkSessionWithCatagory(String phn, String code, long accountId, ChatSide chatSide, double ded, String category);

    /**
     * (*** Redis ***)will check if session found if not found then will create one and will also will update number of
     * message (User/Buisness)
     * @param phn
     * @param code
     * @param accountId
     * @param chatSide
     * @param ded deduction price amount

     * @return check enum for status first
     */
    CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao,StatusDaoUsrOrBsn> checkSessionWithCatagoryRedis(String phn, String code, long accountId, ChatSide chatSide, double ded, String category);

    /**
     * Will update chat message count on the basis of Chat Side
     * @param chatSide
    * @param id pk of the table
     */
    void updateMessageCount(ChatSide chatSide,long id, String category);


    /**
     *
     * @param accountId AccountId of Account
     * @param startDate
     * @param enddate
     * @param typeOfReport Type of report Enum
     * @return
     */
    List<GroupQueryResultDao<String,String,String,String,String>> groupByQuery(long accountId,int startDate,int enddate,TypeOfReport typeOfReport);

    /**
     *
     * @param accountId
     * @param startdate
     * @param enddate
     * @return This will return active session history between date
     */
    List<UserOrBuisnessIntiatedDao> getTransaction(long accountId,int startdate,int enddate);

    UserOrBuisnessIntiatedDao getSessionByPhoneNumberAndCodeAndAccountId(String phoneNumber,String code,long accountId);
    UserOrBuisnessIntiatedDao getSessionByPhoneNumberAndCodeAndAccountIdAndCategory(String phoneNumber,String code,long accountId, String category);
    void deleteUserOrBuisnessIntiatedDaoById(long id);


}
