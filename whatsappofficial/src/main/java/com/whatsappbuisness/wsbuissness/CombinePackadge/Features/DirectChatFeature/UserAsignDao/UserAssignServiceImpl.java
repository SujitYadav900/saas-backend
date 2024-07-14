package com.whatsappbuisness.wsbuissness.CombinePackadge.Features.DirectChatFeature.UserAsignDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserAssignServiceImpl implements UserAssignService {
    @Autowired
    UserAssignRepo userAssignRepo;


    @Override
    public List<UserAssignDao> getByAccountId(long accountId) {
        return userAssignRepo.getAllByAccountId(accountId);
    }

    @Override
    public UserAssignDao save(UserAssignDao userAssignDao) {
        return userAssignRepo.save(userAssignDao);
    }
}
