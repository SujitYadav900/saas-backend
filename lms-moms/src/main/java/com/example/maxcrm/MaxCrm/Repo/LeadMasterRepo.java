package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadMasterDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadMasterRepo extends CrudRepository<LeadMasterDao,Long> {


        LeadMasterDao findByPhonenumber(String phonenumber);
        LeadMasterDao findByMbopsChildId(String mbopsChildId);
        boolean  existsByPhonenumber(String phonenumber);



}
