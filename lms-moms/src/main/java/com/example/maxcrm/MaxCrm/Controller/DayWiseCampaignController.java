package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.DayWiseCampaignDao;
import com.example.maxcrm.MaxCrm.Service.DayWiseCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daywisecampaign")
public class DayWiseCampaignController {
    @Autowired
    DayWiseCampaignService dayWiseCampaignService;


    @GetMapping("/getall")
    public List<DayWiseCampaignDao> getAll()
    {
     return dayWiseCampaignService.getAll();
    }

    @PostMapping("/insert")
    public DayWiseCampaignDao insert(@RequestBody DayWiseCampaignDao dayWiseCampaignDao)
    {
        return dayWiseCampaignService.insert(dayWiseCampaignDao);
    }

    @PostMapping("/update")
    public DayWiseCampaignDao update(@RequestBody DayWiseCampaignDao dayWiseCampaignDao)
    {
        return dayWiseCampaignService.update(dayWiseCampaignDao);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id")String id)
    {
        dayWiseCampaignService.delete(id);
    }
}
