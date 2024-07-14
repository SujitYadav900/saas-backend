package com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing.CampaingDao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CampaignScheduleService {
    int checkScheduleTime();

    List<CampaingDao> getAllActivCampaign(long accountId);
    int deleteScheduledByCampaignId(long id);

}
