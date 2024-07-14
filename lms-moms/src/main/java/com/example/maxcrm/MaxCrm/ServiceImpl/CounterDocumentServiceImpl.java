package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.CounterDocument;
import com.example.maxcrm.MaxCrm.Repo.CounterDocumentRepo;
import com.example.maxcrm.MaxCrm.Service.CounterDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterDocumentServiceImpl implements CounterDocumentService {
    @Autowired
    CounterDocumentRepo counterDocumentRepo;
    @Override
    public CounterDocument save(CounterDocument counterDocument) {
        return counterDocumentRepo.insert(counterDocument);
    }
}
