package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.FacebookIntegrationDao;
import com.example.maxcrm.MaxCrm.Service.FacebookService;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImp implements FacebookService {

    @Override
    public int integrateFacebook(FacebookIntegrationDao dao) {
        return 200;
    }
}
