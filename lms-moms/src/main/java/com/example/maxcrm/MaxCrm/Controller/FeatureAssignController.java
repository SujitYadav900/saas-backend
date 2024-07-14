package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.FeatureAsssignDao;
import com.example.maxcrm.MaxCrm.Service.FeatureAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/featureassign")
public class FeatureAssignController {
    @Autowired
    FeatureAssignService featureAssignService;

    @PostMapping("/insert")
    public FeatureAsssignDao insert(@RequestBody FeatureAsssignDao featureAsssignDao) {
        return featureAssignService.insert(featureAsssignDao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") int id) {
        featureAssignService.delete(id);
    }

}
