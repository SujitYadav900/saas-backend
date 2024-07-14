package com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.GroupQueryResultDao;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserOrBuisnessServiceTest {
    @Autowired
    UserOrBuisnessService userOrBuisnessService;
    private static final Logger logger= LoggerFactory.getLogger(UserOrBuisnessServiceTest.class);

    @Test
    void checkSession() {
        CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao,StatusDaoUsrOrBsn> userOrBuisnessIntiatedDao= userOrBuisnessService.checkSession("919999420593","91",1, ChatSide.User,1);
       // logger.info("User buissness {}",userOrBuisnessIntiatedDao);
    }

    @Test
    void updateMessageCount() {
    }

    @Test
    void groupByQuery() {
        List<GroupQueryResultDao<String,String,String,String,String>>  data= userOrBuisnessService.groupByQuery(20004,20230121,20230121,TypeOfReport.ACTIVESESSIONS);
        //logger.info("Data {}",data);
    }

    @Test
    void getTransaction() {
        List<UserOrBuisnessIntiatedDao> userOrBuisnessIntiatedDaos=userOrBuisnessService.getTransaction(1,20101010,20221010);
        //logger.info("Data {}",userOrBuisnessIntiatedDaos);
    }
}