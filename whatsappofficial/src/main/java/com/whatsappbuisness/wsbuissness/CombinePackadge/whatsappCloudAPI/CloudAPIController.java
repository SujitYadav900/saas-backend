package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao.KarixMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cloudapi")
public class CloudAPIController {

    @Autowired
    CloudAPIService cloudAPIService;

    @PostMapping("/")
    MessageDao sendMessageByCloudAPI(@RequestBody CloudAPIDao cloudAPIDao){
        MessageDao messageDao = new MessageDao();
        return cloudAPIService.sendMessageByCloudAPI(cloudAPIDao,messageDao);
    }

}
