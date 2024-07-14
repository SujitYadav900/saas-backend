package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.CombinePackage.ServiceReview.ServiceReviewDao;
import com.example.maxcrm.MaxCrm.Dao.*;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LeadMasterService {
    LeadMasterDao findByPhonenumber(String phonenumber);
    LeadMasterDao insert(LeadMasterDao leadMasterDao) throws Exception;
    LeadMasterDao update(LeadMasterDao leadMasterDao,MaskingDao maskingDao) throws SQLException;
    LeadMasterDao update(LeadMasterDao leadMasterDao);
    Map<String,String> bulkInsert(List<LeadMasterDao> leadMasterDao, UserMasterDao userMasterDao) throws SQLException;
    void updateLeadStageAndStatus(long id,UserMasterDao userMasterDao,String leadStage,String leadStatus) throws SQLException;
    int bulkUpdateLeadStageAndStatus(List<LeadTransferDao> al,UserMasterDao userMasterDao,String leadStage,String leadStatus) throws SQLException;
    void updateLeadStatus(long id,String createBy,String leadStatus) throws SQLException;
    void convertLead(long id,String createBy,String createDate) throws SQLException;
    int findCountForCampaign(String whereQuery);
    List<CampaignObjectDao> findLeadsForCampaign(String whereQuery, boolean hasurl, CampaignDocument campaignDocument, int offset, int limit) throws SQLException;
    void updateUrnNumberOfLead(long id,String urnNumber,String createBy) throws SQLException;
    void delete(long id);
    PaginationDao getAllDynamicQuery(int limit, int offset, String whereQuery,MaskingDao maskingDao) throws SQLException;
    List<String> getLeadSourceInner() throws Exception;
    void updateLastUpdateLead(Connection con, String updateBy, int updateDate, String upddatestr, long id);
    HashMap<String,String> getUserByType(String type) throws SQLException;
    void updateLastUpdateLeadMultiple(Connection con, String updateBy, int updateDate, String upddatestr, String idList);
    void tranferLead(long id, int userId);
    LeadMasterDao findById(long id);
    LeadMasterDao findByMBOPSChildId(String mbopsChildId);
    void singleNotification(String templateId,LeadMasterDao leadMasterDao) throws Exception;
    void updateLeadRecordingUrl(String phonenumber,String recordingUrl) throws SQLException;
    void updateLeadCounterAndUpdateDate(long id,String leadSource);
    void updateLeadConterByPhonenumber(List<String> phonenumber,HashMap<Integer,Integer> hashMap,UserMasterDao userMasterDao);
    //String singleNotificationWithParameter(LeadMasterDao leadMasterDao, String templateId, HashMap<String,String> hashMap) throws Exception;
    String singleNotificationWithParameter(LeadMasterDao leadMasterDao, String templateId,String templateName, HashMap<String,String> hashMap) throws Exception;
    HashMap<Integer,Integer> findFreeUser(String stage,String status,String userId,String leadSource,byte type) throws SQLException;
    void sendBatchNotification(HashMap<Integer,Integer> hashMap,UserMasterDao userMasterDao,Connection con) throws SQLException;
    void updateC2cAttempts(long leadId);
    void setMBOPSParentId(long leadId,String parentId);
    void setMBOPSChildId(long leadId,String childId,String flag);

    String transferleadtocrm(String url,long leadid,String source, String token) throws IOException;
    int getAppointmentSlotCount(String timeSlot) throws SQLException;
    int updateLeadStageAndStatusByChildId(String mbopsChildId,String leadStage, String leadStatus,String assessmentNotes, boolean hasAssessmnetNotes);
    int updateLeadStageAndStatusByPhoneNumber(String phoneNumber,String leadStage, String leadStatus,String assessmentNotes, boolean hasAssessmnetNotes);

    LeadMasterDao updateMainstreamAssessmentForm(LeadMasterDao leadMasterDao);
    LeadMasterDao updatePreschoolAssessmentForm(LeadMasterDao leadMasterDao);
    List<ServiceReviewDao> getAll(String whereQuery);

    void bulkSourceChange(List<Long> leadIdList,String newSource,UserMasterDao user);
    void bulkProgramChange(List<Long> leadIdList,String newProgram,UserMasterDao user);

    LeadMasterDao upadatesAssessmentNotes(long id, String notes);

    ResponseDao optStatus(String phoneNumber, byte otpStatus);

    void updateLeadCounterAndUpdateTransferLead(long id, String leadSource);
}
