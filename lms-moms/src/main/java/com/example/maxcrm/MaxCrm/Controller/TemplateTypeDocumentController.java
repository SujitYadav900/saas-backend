package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.TemplateTypeDocument;
import com.example.maxcrm.MaxCrm.Service.TemplateTypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templatetype")
public class TemplateTypeDocumentController {
    @Autowired
    TemplateTypeDocumentService templateTypeDocumentService;

    @GetMapping("/getall")
    public List<TemplateTypeDocument> getAll() {
        return templateTypeDocumentService.findAll();
    }

    @GetMapping("/getactive")
    public List<TemplateTypeDocument> getActive() {
        return templateTypeDocumentService.findAllActive();
    }

    @PostMapping("/insert")
    TemplateTypeDocument insert(@RequestBody TemplateTypeDocument templateTypeDocument) {
        return templateTypeDocumentService.insert(templateTypeDocument);
    }

    @PostMapping("/update")
    TemplateTypeDocument update(@RequestBody TemplateTypeDocument templateTypeDocument) {
        return templateTypeDocumentService.update(templateTypeDocument);
    }

    @DeleteMapping("/delete")
    void delete(String id) {
        templateTypeDocumentService.delete(id);
    }

}
