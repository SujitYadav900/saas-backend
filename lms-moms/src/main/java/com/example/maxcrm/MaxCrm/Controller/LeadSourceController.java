package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadSourceDao;
import com.example.maxcrm.MaxCrm.Dao.TemplateDocument;
import com.example.maxcrm.MaxCrm.Service.LeadSourceService;
import com.example.maxcrm.MaxCrm.Service.TemplateDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/leadsource")
public class LeadSourceController {
    @Autowired
    LeadSourceService leadSourceService;
    @Autowired
    TemplateDocumentService templateDocumentService;

    @GetMapping("/getall")
    public Iterable<LeadSourceDao> findAll() {
        return leadSourceService.findAll();
    }

    @GetMapping("/getactive")
    public Iterable<LeadSourceDao> findActive() {
        return leadSourceService.findActive();
    }

    @PostMapping("/insert")
    public LeadSourceDao insert(@RequestBody LeadSourceDao menuDao) throws Exception {
        System.out.println(menuDao);
        menuDao.setAccessToken(generateString());
        return leadSourceService.insert(menuDao);
    }
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
    @PostMapping("/update")
    public LeadSourceDao update(@RequestBody LeadSourceDao menuDao) throws Exception {
        TemplateDocument templateDocument = null;
        try{
            String templateId = menuDao.getTemplate().substring(6,24);
            templateDocument = templateDocumentService.findById(templateId);
        }catch(NoSuchElementException e){
            System.out.println("Exception Occurs");
            templateDocument = new TemplateDocument();
            templateDocument.setTemplateType("Whatsapp");
        }


        return leadSourceService.update(menuDao);
    }

    @DeleteMapping("/delete")
    public void update(@RequestParam("id") int id) {
        leadSourceService.delete(id);
    }

}
