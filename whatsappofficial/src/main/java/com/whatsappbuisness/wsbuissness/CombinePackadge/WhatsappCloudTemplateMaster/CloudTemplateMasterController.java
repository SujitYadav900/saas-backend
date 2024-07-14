package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cloudtemplatemaster")
public class CloudTemplateMasterController {

    @Autowired
    CloudTemplateMasterService cloudTemplateMasterService;

    @Autowired
    SessionRetrievalService sessionRetrievalService;

    private final Logger logger = LoggerFactory.getLogger(CloudTemplateMasterController.class);

    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PostMapping("/")
    CloudTemplateMasterDao insert(@RequestParam(value = "file", required = false, name = "file" ) MultipartFile file, @RequestParam("cloudTemplateMasterDao") String cloudTemplateMasterDaoStr, Authentication authentication){
        logger.info("the value of cloudTemplateMasterDao is "+cloudTemplateMasterDaoStr);
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        CloudTemplateMasterDao cloudTemplateMasterDao = new Gson().fromJson(cloudTemplateMasterDaoStr, CloudTemplateMasterDao.class);
        cloudTemplateMasterDao.setAccountId(usermasterDao.getId());
        cloudTemplateMasterDao.setCreateDate(DateTiming.getDateRedable());
        cloudTemplateMasterDao.setUpdateDate(DateTiming.getDateRedable());
        cloudTemplateMasterDao.setDateFilter(DateTiming.getDateFilterDate());
        if(file != null){
            cloudTemplateMasterDao.setMultipartFile(file);
        }
        return cloudTemplateMasterService.insert(cloudTemplateMasterDao);
    }

    @PostMapping("/update")
    CloudTemplateMasterDao update(@RequestBody CloudTemplateMasterDao cloudTemplateMasterDao, Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        cloudTemplateMasterDao.setUpdateDate(DateTiming.getDateRedable());
        return cloudTemplateMasterService.update(cloudTemplateMasterDao);
    }
    @GetMapping("/getbytemplateid")
    CloudTemplateMasterDao getById(@RequestParam("templateId") String templateId, Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return cloudTemplateMasterService.getById(templateId, usermasterDao.getId());
    }
    @GetMapping("/getall")
    public List<CloudTemplateMasterDao> getAllActiveTemplateByAccountId(Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return cloudTemplateMasterService.getActiveCloudTemplatesByAccountId(usermasterDao.getId());
    }
    @GetMapping("/delete")
    public void disableCloudTemplate(@RequestParam("templateId") String templateId, Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        cloudTemplateMasterService.disableTemplate(templateId, usermasterDao.getId());
    }

}
