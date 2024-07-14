package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.FeaturesDao;
import com.example.maxcrm.MaxCrm.Service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureController {
    @Autowired
    FeatureService featureService;
    @GetMapping("/get")
    public Iterable<FeaturesDao> getAll()
    {
        return featureService.getAllFeature();
    }
    @PostMapping("/insert")
    public FeaturesDao insert(@RequestBody  FeaturesDao featuresDao){
        return featureService.insertFeatureDao(featuresDao);
    }
    @PostMapping("/update")
    public FeaturesDao update( @RequestBody FeaturesDao featuresDao)
    {
        return featureService.updateFeatureDao(featuresDao);
    }
    @GetMapping("/getByRoleId")
    public List<FeaturesDao> getByRole(@RequestParam("roleId")int roleId)
    {
        return featureService.getRoleByRoleId(roleId);
    }
}
