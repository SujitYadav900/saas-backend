package com.example.maxcrm.MaxCrm.CombinePackage.Wati;

import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos.WatiTemplateMsgRequestDao;
import com.example.maxcrm.MaxCrm.Dao.TemplateDocument;

import java.io.IOException;
import java.util.List;

public interface WatiService {

    List<TemplateDocument> getAllTemplates() throws Exception;

    void sendTemplateMessages(WatiTemplateMsgRequestDao msgRequestDao) throws Exception;
}
