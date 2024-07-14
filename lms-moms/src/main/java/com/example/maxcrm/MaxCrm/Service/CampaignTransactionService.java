package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.CampaignObjectDao;
import com.example.maxcrm.MaxCrm.Dao.PaginationMainDao;

import java.util.List;

public interface CampaignTransactionService {
    void saveAll(List<CampaignObjectDao> al);

    CampaignObjectDao updateClick(String id,String ip,String userAgent);
    void updateDlrStatus(String messageId,String status) throws Exception;
    void deleteTransactionBeforeDate(long datefilter);
    PaginationMainDao<CampaignObjectDao> finddata(String campaignId,int offset,int limit,int totalCount);


}
