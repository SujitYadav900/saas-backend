package com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWisePricePk;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWisePriceService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWiseRateRetDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.UserOrBsnsSessionRedis.UserOrBsnsRedisService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.GroupQueryResultDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserOrBuissnessServiceImpl implements UserOrBuisnessService {
    @Autowired
    UserOrBuisnessRepo userOrBuisnessRepo;
    @Autowired
    CounterGenerationService counterGenerationService;
    @Autowired
    UserOrBsnsRedisService userOrBsnsRedisService;


    @Autowired
    DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(UserOrBuissnessServiceImpl.class);


    @Override
    public CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> checkSessionWithCatagory(String phn, String code, long accountId, ChatSide chatSide, double ded, String category) {
        long dateFilterLong = DateTiming.getDateFilterDateLong();
        boolean isNew = false;
        CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> data;
        UserOrBuisnessIntiatedDao userOrBuisnessIntiatedDao = userOrBuisnessRepo.findTopByDstAndCountryCodeAndAccountIdAndCategoryOrderByIdDesc(phn, code, accountId, category);
        logger.info("Previous was userOrBuisnessIntiatedDao {}",userOrBuisnessIntiatedDao);
        if (userOrBuisnessIntiatedDao != null) {
            if (userOrBuisnessIntiatedDao.getExpiryLong() > dateFilterLong) {
                if(userOrBuisnessIntiatedDao.getCategory().equalsIgnoreCase(category)){
                    isNew = false;
                    updateMessageCount(chatSide, userOrBuisnessIntiatedDao.getId(),category);
                }else{
                    isNew = true;
                }
            }else {
                    isNew = true;
            }
        } else {
            isNew = true;
        }
        if (isNew) {
            userOrBuisnessIntiatedDao = new UserOrBuisnessIntiatedDao();
            userOrBuisnessIntiatedDao.setDeduction(ded);
            userOrBuisnessIntiatedDao.setAccountId(accountId);
            userOrBuisnessIntiatedDao.setDst(phn);
            userOrBuisnessIntiatedDao.setDateFilter(DateTiming.dateFilterDate());
            userOrBuisnessIntiatedDao.setCountryCode(code);
            userOrBuisnessIntiatedDao.setRefId(counterGenerationService.generateUid());
            userOrBuisnessIntiatedDao.setExpiryLong(dateFilterLong);
            userOrBuisnessIntiatedDao.setCategory(category);
            String[] timeArr = DateTiming.getCurrentAndNextDate();
            userOrBuisnessIntiatedDao.setSessionStartTime(timeArr[0]);
            userOrBuisnessIntiatedDao.setSessionEndTime(timeArr[1]);
            userOrBuisnessIntiatedDao.setExpiryLong(Long.parseLong(timeArr[2]));
            if (chatSide == ChatSide.Client) {
                userOrBuisnessIntiatedDao.setSessionType(SessionType.USER);
                userOrBuisnessIntiatedDao.setUsrMessageCount(1);
            } else {
//                userOrBuisnessIntiatedDao.setSessionType(SessionType.BUISSNESS);
//                userOrBuisnessIntiatedDao.setBsnsMessageCount(1);
                if(category.equalsIgnoreCase("MARKETING")){
                    userOrBuisnessIntiatedDao.setSessionType(SessionType.MARKETING);
                    userOrBuisnessIntiatedDao.setMarketingMessageCount(1);

                }else if(category.equalsIgnoreCase("UTILITY")){
                    userOrBuisnessIntiatedDao.setSessionType(SessionType.UTILITY);
                    userOrBuisnessIntiatedDao.setUtiltityMessageCount(1);

                }else if(category.equalsIgnoreCase("AUTHENTICATION")){
                    userOrBuisnessIntiatedDao.setSessionType(SessionType.AUTHENTICATION);
                    userOrBuisnessIntiatedDao.setAuthenticationMessageCount(1);
                }
            }
            userOrBuisnessIntiatedDao = userOrBuisnessRepo.save(userOrBuisnessIntiatedDao);
            logger.info("The value of userOrBuisnessIntiatedDao {}", userOrBuisnessIntiatedDao);
        }
        if (isNew) {
            data = new CommonClassReturnWithStatus<>(userOrBuisnessIntiatedDao, StatusDaoUsrOrBsn.NEW);
        } else {
            data = new CommonClassReturnWithStatus<>(userOrBuisnessIntiatedDao, StatusDaoUsrOrBsn.EXISTS);
        }
        userOrBuisnessIntiatedDao.setNew(isNew);
        logger.info("After Checking User or Buissness {}", userOrBuisnessIntiatedDao);
        return data;
    }

    @Override
    public CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> checkSessionWithCatagoryRedis(String phn, String code, long accountId, ChatSide chatSide, double ded, String category) {
        long dateFilterLong = DateTiming.getDateFilterDateLong();
        boolean isNew = false;
        CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> data;
        CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao,Boolean> sessionRedis = userOrBsnsRedisService.checkSession(phn, code, accountId, chatSide,category,1440);
        logger.info("The value of CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao,Boolean> is {}", sessionRedis);
        UserOrBuisnessIntiatedDao  userOrBuisnessIntiatedDao=sessionRedis.getData();
        if (sessionRedis.getStatus()) {

            updateMessageCount(chatSide,sessionRedis.getData().getId(),category);
        } else {
            isNew = true;
        }
        if (isNew) {
            userOrBuisnessIntiatedDao = new UserOrBuisnessIntiatedDao();
            userOrBuisnessIntiatedDao.setDeduction(ded);
            userOrBuisnessIntiatedDao.setAccountId(accountId);
            userOrBuisnessIntiatedDao.setDst(phn);
            userOrBuisnessIntiatedDao.setDateFilter(DateTiming.dateFilterDate());
            userOrBuisnessIntiatedDao.setCountryCode(code);
            userOrBuisnessIntiatedDao.setRefId(counterGenerationService.generateUid());
            userOrBuisnessIntiatedDao.setExpiryLong(dateFilterLong);
            userOrBuisnessIntiatedDao.setCategory(category);
            String[] timeArr = DateTiming.getCurrentAndNextDate();
            userOrBuisnessIntiatedDao.setSessionStartTime(timeArr[0]);
            userOrBuisnessIntiatedDao.setSessionEndTime(timeArr[1]);
            userOrBuisnessIntiatedDao.setExpiryLong(Long.parseLong(timeArr[2]));
            if (chatSide == ChatSide.Client) {
                userOrBuisnessIntiatedDao.setSessionType(SessionType.USER);
                userOrBuisnessIntiatedDao.setUsrMessageCount(1);
            } else {
//                userOrBuisnessIntiatedDao.setSessionType(SessionType.BUISSNESS);
//                userOrBuisnessIntiatedDao.setBsnsMessageCount(1);
                if(category.equalsIgnoreCase("MARKETING")){
                    userOrBuisnessIntiatedDao.setSessionType(SessionType.MARKETING);
                    userOrBuisnessIntiatedDao.setMarketingMessageCount(1);

                }else if(category.equalsIgnoreCase("UTILITY")){
                    userOrBuisnessIntiatedDao.setSessionType(SessionType.UTILITY);
                    userOrBuisnessIntiatedDao.setUtiltityMessageCount(1);

                }else if(category.equalsIgnoreCase("AUTHENTICATION")){
                    userOrBuisnessIntiatedDao.setSessionType(SessionType.AUTHENTICATION);
                    userOrBuisnessIntiatedDao.setAuthenticationMessageCount(1);
                }
            }
            userOrBuisnessIntiatedDao = userOrBuisnessRepo.save(userOrBuisnessIntiatedDao);
            userOrBsnsRedisService.insert(phn, code, accountId, chatSide,category,1440,userOrBuisnessIntiatedDao);

        }
        if (isNew) {
            data = new CommonClassReturnWithStatus<>(userOrBuisnessIntiatedDao, StatusDaoUsrOrBsn.NEW);
        } else {
            data = new CommonClassReturnWithStatus<>(userOrBuisnessIntiatedDao, StatusDaoUsrOrBsn.EXISTS);
        }

        return data;
    }


    @Override
    public CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> checkSession(String phn, String code, long accountId, ChatSide chatSide, double ded) {
        long dateFilterLong = DateTiming.getDateFilterDateLong();
        boolean isNew = false;
        CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> data;
        UserOrBuisnessIntiatedDao userOrBuisnessIntiatedDao = userOrBuisnessRepo.findTopByDstAndCountryCodeAndAccountIdOrderByIdDesc(phn, code, accountId);
        //logger.info("Previous was {}",userOrBuisnessIntiatedDao);
        if (userOrBuisnessIntiatedDao != null) {
            if (userOrBuisnessIntiatedDao.getExpiryLong() > dateFilterLong) {
                isNew = false;
                updateMessageCount(chatSide, userOrBuisnessIntiatedDao.getId(), userOrBuisnessIntiatedDao.getCategory());
            } else {
                isNew = true;
            }
        } else {
            isNew = true;
        }
        if (isNew) {
            logger.info("If session is new ");
            userOrBuisnessIntiatedDao = new UserOrBuisnessIntiatedDao();
            userOrBuisnessIntiatedDao.setDeduction(ded);
            userOrBuisnessIntiatedDao.setAccountId(accountId);
            userOrBuisnessIntiatedDao.setDst(phn);
            userOrBuisnessIntiatedDao.setDateFilter(DateTiming.dateFilterDate());
            userOrBuisnessIntiatedDao.setCountryCode(code);
            userOrBuisnessIntiatedDao.setRefId(counterGenerationService.generateUid());
            userOrBuisnessIntiatedDao.setExpiryLong(dateFilterLong);
            String[] timeArr = DateTiming.getCurrentAndNextDate();
            userOrBuisnessIntiatedDao.setSessionStartTime(timeArr[0]);
            userOrBuisnessIntiatedDao.setSessionEndTime(timeArr[1]);
            userOrBuisnessIntiatedDao.setExpiryLong(Long.parseLong(timeArr[2]));
            if (chatSide == ChatSide.Client) {
                userOrBuisnessIntiatedDao.setSessionType(SessionType.USER);
                userOrBuisnessIntiatedDao.setUsrMessageCount(1);
            } else {
                userOrBuisnessIntiatedDao.setSessionType(SessionType.BUISSNESS);
                userOrBuisnessIntiatedDao.setBsnsMessageCount(1);
            }
            userOrBuisnessIntiatedDao = userOrBuisnessRepo.save(userOrBuisnessIntiatedDao);

        }
        if (isNew) {
            data = new CommonClassReturnWithStatus<>(userOrBuisnessIntiatedDao, StatusDaoUsrOrBsn.NEW);
        } else {
            logger.info("If session is not new new {}", userOrBuisnessIntiatedDao);
            data = new CommonClassReturnWithStatus<>(userOrBuisnessIntiatedDao, StatusDaoUsrOrBsn.EXISTS);
        }
        userOrBuisnessIntiatedDao.setNew(isNew);
        //logger.info("After Checking User or Buissness {}", userOrBuisnessIntiatedDao);
        return data;
    }

    @Override
    public void updateMessageCount(ChatSide chatSide, long id, String catagory) {
        Connection con = null;
//        String query = "update Tbl_Bs_Usr_Session set bsnsMessageCount=bsnsMessageCount+1 where id=? ;";
        String query = "";
        if (chatSide == ChatSide.Client) {
            query = "update Tbl_Bs_Usr_Session set usrMessageCount=usrMessageCount+1 where id=? ;";
        }else{
            if(catagory.equalsIgnoreCase("MARKETING")){
                query = "update Tbl_Bs_Usr_Session set marketingMessageCount=marketingMessageCount+1 where id=? ;";
            }else if(catagory.equalsIgnoreCase("UTILITY")){
                query = "update Tbl_Bs_Usr_Session set utiltityMessageCount=utiltityMessageCount+1 where id=? ;";
            }else if(catagory.equalsIgnoreCase("AUTHENTICATION")){
                query = "update Tbl_Bs_Usr_Session set authenticationMessageCount=authenticationMessageCount+1 where id=? ;";
            }
        }


        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, id);
            logger.info("Will update MessageCount {}", stmt);
            stmt.executeUpdate();

        } catch (Exception ew) {

        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public List<GroupQueryResultDao<String,String,String,String,String>> groupByQuery(long accountId, int startDate, int enddate, TypeOfReport typeOfReport) {
        Connection con = null;
        List<GroupQueryResultDao<String,String,String,String,String>> al = new ArrayList<>();
        GroupQueryResultDao<String,String,String,String,String> data;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = generateQueryOnTheBasisOfType(typeOfReport, con, accountId, startDate, enddate);
            logger.info("Quey {}",stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                data = new GroupQueryResultDao<>();
                data.setName(rs.getString(1));
                data.setCount(rs.getString(2));
                data.setSessionStartTime(rs.getString(3));
                data.setSessionEndTime(rs.getString(4));

                al.add(data);

            }
        } catch (Exception ew) {
            logger.error("Error Occured {}", ew.getMessage());
            logger.error("Error {}", ew);

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return al;
    }

    @Override
    public List<UserOrBuisnessIntiatedDao> getTransaction(long accountId, int startdate, int enddate) {
        return userOrBuisnessRepo.findAllByAccountIdAndDateFilterGreaterThanEqualAndDateFilterLessThanEqual(accountId, startdate, enddate);
    }

    @Override
    public UserOrBuisnessIntiatedDao getSessionByPhoneNumberAndCodeAndAccountId(String phoneNumber, String code, long accountId) {
        UserOrBuisnessIntiatedDao userOrBuisnessIntiatedDao = userOrBuisnessIntiatedDao= userOrBuisnessRepo.findTopByDstAndCountryCodeAndAccountIdOrderByIdDesc(phoneNumber,code,accountId);
        logger.info("getSessionByPhoneNumberAndCodeAndAccountId for : {} : {} :{}",phoneNumber,accountId,userOrBuisnessIntiatedDao);

        return userOrBuisnessIntiatedDao;
    }

    @Override
    public UserOrBuisnessIntiatedDao getSessionByPhoneNumberAndCodeAndAccountIdAndCategory(String phoneNumber, String code, long accountId, String category) {
        return userOrBuisnessRepo.findTopByDstAndCountryCodeAndAccountIdAndCategoryOrderByIdDesc(phoneNumber,code,accountId,category);
    }

    @Override
    public void deleteUserOrBuisnessIntiatedDaoById(long id) {
        userOrBuisnessRepo.deleteById(id);
    }

    /**
     * @param typeOfReport
     * @param con
     * @param accountId
     * @param startDate
     * @param enddate
     * @return will return Prepared Statement Object
     * @throws Exception
     */
    private PreparedStatement generateQueryOnTheBasisOfType(TypeOfReport typeOfReport, Connection con, long accountId, int startDate, int enddate) throws Exception {
        PreparedStatement stmt = null;
        long dateTimeLong = DateTiming.getDateFilterDateLong();

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        Date newDate = new Date(date.getTime() + TimeUnit.HOURS.toMillis(-24));
        long diffeDates= Long.parseLong(dateFormat.format(newDate));

        switch (typeOfReport) {
            case COUNTRYWISE:

                stmt = con.prepareStatement("select concat(countryCode,'-',(case when sessionType=0 then 'USER' when sessionType=3 then 'UTILITY' when sessionType=2 then 'AUTHENTICATION' when sessionType=4 then 'MARKETING' else 'BUSINESS' end)),count(countryCode),0,0 from Tbl_Bs_Usr_Session where accountId=? and ( dateFilter>=? and dateFilter<=? ) group by countryCode,sessionType ;");
                stmt.setLong(1, accountId);
                stmt.setInt(2, startDate);
                stmt.setInt(3, enddate);

                break;
            case ACTIVESESSIONS:
               // stmt = con.prepareStatement("select 'Active Session',count(*) from Tbl_Bs_Usr_Session where accountId=? and ( dateFilter>=? and dateFilter<=? ) and  expiryLong > ? union all select 'Closed Session',count(*) from Tbl_Bs_Usr_Session where accountId=? and ( dateFilter>=? and dateFilter<=? ) and  expiryLong < ?");

//                stmt = con.prepareStatement(" SELECT dst,sessionType,sessionStartTime,sessionEndTime FROM WSBUISSNES.Tbl_Bs_Usr_Session WHERE accountId=?  and ( dateFilter>=? and dateFilter<=? ) and expiryLong > ?;");
                stmt = con.prepareStatement(" SELECT dst,sessionType,sessionStartTime,sessionEndTime FROM WSBUISSNES.Tbl_Bs_Usr_Session WHERE accountId=?  and ( dateFilter<=? ) and expiryLong > ?;");

                stmt.setLong(1, accountId);
//                stmt.setInt(2, startDate);
                stmt.setInt(2, enddate);
                stmt.setLong(3, dateTimeLong);

//                stmt.setLong(5, accountId);
//                stmt.setInt(6, startDate);
//                stmt.setInt(7, enddate);
//                stmt.setLong(8, dateTimeLong);

                break;
            case USERORBUISSNESS:
//                stmt = con.prepareStatement("select sessionType,count(sessionType),0,0 from Tbl_Bs_Usr_Session where accountId=? and ( dateFilter>=? and dateFilter<=? ) group by sessionType ;");
                stmt = con.prepareStatement("select sessionType,count(sessionType),0,0 from Tbl_Bs_Usr_Session where accountId=? and ( dateFilter>=? and dateFilter<=? ) group by sessionType ;");
                stmt.setLong(1, accountId);
                stmt.setInt(2, startDate);
                stmt.setInt(3, enddate);

                break;


        }
        return stmt;

    }

//    public static void main(String[] args) {
//        UserOrBuissnessServiceImpl user=new UserOrBuissnessServiceImpl();
//        long dateTimeLong = DateTiming.getDateFilterDateLong();
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//         Date date = new Date();
//
//        Date newDate = new Date(date.getTime() + TimeUnit.MINUTES.toMillis(-1440));
//        long newd= Long.parseLong(dateFormat.format(newDate));
//        System.out.println("dateTimeLong "+dateTimeLong);
//        System.out.println("newDate "+newd);
//    }
}
