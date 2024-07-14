package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadStageDocument;
import com.example.maxcrm.MaxCrm.Dao.StageOptionDocument;
import com.example.maxcrm.MaxCrm.Repo.LeadStageRepo;
import com.example.maxcrm.MaxCrm.Service.LeadStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LeadStageServiceImpl implements LeadStageService {
    @Autowired
    LeadStageRepo leadStageRepo;
    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public LeadStageDocument insert(LeadStageDocument leadStageDocument) {
        return leadStageRepo.insert(leadStageDocument);
    }

    @Override
    public List<LeadStageDocument> insertAll(List<LeadStageDocument> al) {
        return leadStageRepo.insert(al);
    }

    @Override
    public LeadStageDocument update(LeadStageDocument leadStageDocument) {
        return leadStageRepo.save(leadStageDocument);
    }

    @Override
    public LeadStageDocument findAllByName(String name) {
        return leadStageRepo.findByName(name).get(0);
    }

    @Override
    public List<LeadStageDocument> findAll() {
        return leadStageRepo.findAll();
    }

    @Override
    public StageOptionDocument getStatusByStage(String stage, String status) {
        System.out.println("Stage is "+stage+" and Status is "+status);

        LeadStageDocument leadStageDocument=findAllByName(stage);
        for(int i=0;i<leadStageDocument.getOption().size();i++)
        {
            if(leadStageDocument.getOption().get(i).getValue().equalsIgnoreCase(status))
            {
                return leadStageDocument.getOption().get(i);
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        leadStageRepo.deleteById(id);

    }

    @Override
    public boolean leadStageExist(String name) {
        return leadStageRepo.existsLeadStageDocumentByStage(name);
    }

    @Override
    public boolean leadStatusExists(String name) {

        Query query=new Query();

        query.addCriteria(Criteria.where("option.value").is(name));
        return  mongoTemplate.exists(query,LeadStageDocument.class);

    }


}
