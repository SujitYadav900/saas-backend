package com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing;
/*
 Author=Supreet Singh
 Date= 16/03/21 12:22 PM
*/

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CampaingServiceRepo extends CrudRepository<CampaingDao,Long> {
    List<CampaingDao> getAllByAccountIdOrderByIdDesc(long accountId);
    List<CampaingDao> getAllByAccountIdOrderByScheduledTimeDesc(long accountId);

}
