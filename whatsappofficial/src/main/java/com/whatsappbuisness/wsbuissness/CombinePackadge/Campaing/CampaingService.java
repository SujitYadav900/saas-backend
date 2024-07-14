package com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing;
/*
 Author=Supreet Singh
 Date= 16/03/21 12:24 PM
*/

import java.util.List;

public interface CampaingService {

    CampaingDao insert(CampaingDao campaingDao);
    List<CampaingDao> getAllByAccountId(long accountId);


    int updateScheuledStatus(long campaignId);
}
