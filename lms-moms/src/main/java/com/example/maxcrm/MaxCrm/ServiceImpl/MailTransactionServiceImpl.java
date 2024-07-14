package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.MailTransactionDao;
import com.example.maxcrm.MaxCrm.Repo.MailTransactionRepo;
import com.example.maxcrm.MaxCrm.Service.MailtransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MailTransactionServiceImpl implements MailtransactionService {
    @Autowired
    MailTransactionRepo mailTransactionRepo;
    Logger logger = LoggerFactory.getLogger(MailtransactionService.class);
    @Override
    public void insert(List<MailTransactionDao> al) {
        logger.info("Saving Into Mail TransactionDao {}",al);
        mailTransactionRepo.saveAll(al);
    }
}
