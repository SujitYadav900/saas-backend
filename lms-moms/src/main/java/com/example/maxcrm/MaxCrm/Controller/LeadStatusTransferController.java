package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadStatusTransferDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.LeadStatusTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/statustranfer")
public class LeadStatusTransferController {
    Logger logger = LoggerFactory.getLogger(LeadStatusTransferController.class);
    @Autowired
    LeadStatusTransferService leadStatusTransferService;

    @GetMapping("/get")
    Iterable<LeadStatusTransferDao> getAll(@RequestParam("id") long id) {
        return leadStatusTransferService.findAllByLeadId(id);
    }
    @PostMapping("/insert")
    LeadStatusTransferDao insert(@RequestBody LeadStatusTransferDao leadStatusTransferDao, Authentication authentication) throws SQLException {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        leadStatusTransferDao.setCreateBy(u.getUsername());
        logger.info("Inserting {}",leadStatusTransferDao);

        return leadStatusTransferService.insert(leadStatusTransferDao);
    }



}
