package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.MailObjectDao;
import com.example.maxcrm.MaxCrm.Service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);



    @Override
    public String sendMail(MailObjectDao mailObjectDao) {

        logger.info("Sending mail to {}",mailObjectDao.toString());
        return null;
    }
}
