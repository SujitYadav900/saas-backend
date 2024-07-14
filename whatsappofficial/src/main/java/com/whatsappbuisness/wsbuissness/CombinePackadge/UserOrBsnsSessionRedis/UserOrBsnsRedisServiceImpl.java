package com.whatsappbuisness.wsbuissness.CombinePackadge.UserOrBsnsSessionRedis;


import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.UserOrBuisnessIntiatedDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserOrBsnsRedisServiceImpl implements UserOrBsnsRedisService {

    Logger logger = LoggerFactory.getLogger(UserOrBsnsRedisServiceImpl.class);
    @Autowired
    private RedisTemplate<String, UserOrBuisnessIntiatedDao> redisTemplate;

    @Override
    public CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao,Boolean> checkSession(String phn, String code, long accountId, ChatSide chatSide, String category, int expiryInMin) {
        String id= phn + "_" + code + "_" + accountId + "_" + chatSide.toString() + "_" + category;
        logger.info("The id in redis check session is "+ id);
        UserOrBuisnessIntiatedDao userOrBuisnessIntiatedDao=redisTemplate.opsForValue().get(id);
        if(userOrBuisnessIntiatedDao==null)
        {

            return new CommonClassReturnWithStatus<>(null,false);
        }
        else
        {
            return new CommonClassReturnWithStatus<>(userOrBuisnessIntiatedDao,true);
        }

    }

    @Override
    public UserOrBuisnessIntiatedDao insert(String phn, String code, long accountId, ChatSide chatSide, String category, int expiryInMins,UserOrBuisnessIntiatedDao userOrBuisnessIntiatedDao) {
        String id= phn + "_" + code + "_" + accountId + "_" + chatSide.toString() + "_" + category;
        redisTemplate.opsForValue().set(id,userOrBuisnessIntiatedDao);
        redisTemplate.expire(id, expiryInMins, TimeUnit.MINUTES);
        return userOrBuisnessIntiatedDao;
    }

    @Override
    public void deleteSessionByKey(String phn, String code, long accountId, ChatSide chatSide, String category) {
        String id= phn + "_" + code + "_" + accountId + "_" + chatSide.toString() + "_" + category;
        redisTemplate.delete(id);
    }


}
