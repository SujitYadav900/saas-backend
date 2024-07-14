package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.CampaignDocument;

import java.util.List;

public interface CampaignDocumentService {
    List<CampaignDocument> findAllByCreateBy(String createBy);


    CampaignDocument insert(CampaignDocument campaignDocument);
    void deleteOldCampaignData(int date);


    void delete(String Id);


    String updateClick(String cmpTransId,String ip,String userAgent) throws Exception;
    void increaseStatusCount(String campaignId,String dlrStatus);
    void updatestatus(String id,byte status);


}
