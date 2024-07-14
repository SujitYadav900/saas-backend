package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadTransferDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.LeadTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/leadtransfer")
public class LeadTransferController {

    @Autowired
    LeadTransferService leadTransferService;

    @GetMapping("/getbyid")
    Iterable<LeadTransferDao> getByLeadId(@RequestParam("id") long id) {
        return leadTransferService.getByLeadId(id);
    }

    @PostMapping("/insert")
    int tranferLead(@RequestBody List<LeadTransferDao> al, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        return leadTransferService.insertBulk(al,userMasterDao.getUsername(),true);
    }


}
