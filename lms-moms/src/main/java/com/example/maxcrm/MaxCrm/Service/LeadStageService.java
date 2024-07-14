package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadStageDocument;
import com.example.maxcrm.MaxCrm.Dao.StageOptionDocument;

import java.util.List;

public interface LeadStageService {
    LeadStageDocument insert(LeadStageDocument leadStageDocument);
    List<LeadStageDocument> insertAll(List<LeadStageDocument> al);
    LeadStageDocument update(LeadStageDocument leadStageDocument);
    LeadStageDocument findAllByName(String name);
    List<LeadStageDocument> findAll();
    StageOptionDocument getStatusByStage(String stage,String status);
    void delete(String id);
    boolean leadStageExist(String name);

    boolean leadStatusExists(String name);
}
