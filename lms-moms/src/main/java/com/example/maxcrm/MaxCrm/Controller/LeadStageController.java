package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadStageDocument;
import com.example.maxcrm.MaxCrm.Service.LeadStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/leadstage")
@RestController
public class LeadStageController {
    @Autowired
    LeadStageService leadStageService;
    @GetMapping("/getall")
    List<LeadStageDocument> findAll()
    {
        return leadStageService.findAll();
    }

    @GetMapping("/getbyname")
    LeadStageDocument findAll(@RequestParam("name") String name) {
        return leadStageService.findAllByName(name);
    }

    @PostMapping("/insert")
    LeadStageDocument insert(@RequestBody LeadStageDocument leadStageDocument)
    {
        return leadStageService.insert(leadStageDocument);
    }
    @PostMapping("/insertall")
    List<LeadStageDocument> insertall(@RequestBody List<LeadStageDocument> leadStageDocument)
    {
        return leadStageService.insertAll(leadStageDocument);
    }
    @PostMapping("/update")
    LeadStageDocument update(@RequestBody LeadStageDocument leadStageDocument)
    {
        return leadStageService.update(leadStageDocument);
    }
    @DeleteMapping("/delete")
    void delete(@RequestParam String id)
    {

        leadStageService.delete(id);
    }



}
