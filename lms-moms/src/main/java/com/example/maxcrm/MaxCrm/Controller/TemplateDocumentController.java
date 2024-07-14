package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiService;
import com.example.maxcrm.MaxCrm.Dao.TemplateDocument;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.TemplateDocumentService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/template")
public class TemplateDocumentController {
    @Autowired
    TemplateDocumentService templateDocumentService;
    @Autowired
    private WatiService watiService;

    @GetMapping("/getalltemplate")
    public List<TemplateDocument> getAll(Authentication authentication) {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        return templateDocumentService.getAll(u.getUsername());
    }
    @GetMapping("/getalltemplateapproved")
    public List<TemplateDocument> getalltemplateapproved(@RequestParam(defaultValue = "WATI") String templateSource, Authentication authentication) throws Exception {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();

        String WAServiceProvider = templateSource;//UtilityClass.propertyService.findProperty("Campaign", "WAServiceProvider");
        if(WAServiceProvider.equalsIgnoreCase("WATI")){
            return watiService.getAllTemplates();
        }else{
            return templateDocumentService.getAllApproved(u.getUsername());
        }


    }

    @GetMapping("/getalltemplateapproval")
    public List<TemplateDocument> getalltemplateapproval( Authentication authentication) {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        return templateDocumentService.getAll();
    }
    @PostMapping("/approveorejecttemplate")
    public void approveOrRejectTemplate(@RequestParam("id")String id,@RequestParam("value") String value,@RequestParam("type") String type,@RequestParam("sts")boolean sts)
    {
        if(type.equalsIgnoreCase("Mail"))
        {
        templateDocumentService.updateMailTemplateSts(id,sts);
        }
        if(type.equalsIgnoreCase("Message"))
        {
        templateDocumentService.updateMessageTemplateSts(id,value,sts);
        }
        if(type.equalsIgnoreCase("Whatsapp"))
        {
            templateDocumentService.updateMailTemplateSts(id,sts);
        }

    }




    @GetMapping("/getalltemplateactive")
    public List<TemplateDocument> getAllActive(Authentication authentication) throws Exception {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        String WAServiceProvider = UtilityClass.propertyService.findProperty("Campaign", "WAServiceProvider");
        if(WAServiceProvider.equalsIgnoreCase("WATI")){
            return watiService.getAllTemplates();
        }else{
            return templateDocumentService.getActive(u.getUsername());
        }



    }

    @GetMapping("/getallbysystem")
    public List<TemplateDocument> getAllActiveBySystem() {


        return templateDocumentService.getAll("System");

    }
    @PostMapping("/insert")
    public TemplateDocument insert(@RequestBody TemplateDocument templateDocument,Authentication authentication)
    {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        templateDocument.setCreateBy(u.getUsername());
        templateDocument.setApproved(false);
        return templateDocumentService.insert(templateDocument);
    }



    @PostMapping("/update")
    public TemplateDocument update(@RequestBody TemplateDocument templateDocument,Authentication authentication)
    {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        templateDocument.setApproved(false);
        templateDocument.setCreateBy(u.getUsername());
        return templateDocumentService.update(templateDocument);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id")String id)
    {
        templateDocumentService.delete(id);
    }

}
