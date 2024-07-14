package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.ResponseServiceDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook.CloudAPIIncomingDao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CloudTemplateMasterService {
    CloudTemplateMasterDao insert(CloudTemplateMasterDao cloudTemplateMasterDao);
    CloudTemplateMasterDao update(CloudTemplateMasterDao cloudTemplateMasterDao);

    CloudTemplateMasterDao getById(String templateId, long acountId);
    List<CloudTemplateMasterDao> getActiveCloudTemplatesByAccountId(long accountId);
    void disableTemplate(String templateId,long accountId);

    void updateCloudTemplateStatus(CloudAPIIncomingDao cloudAPIIncomingDao,long accountId );
}
