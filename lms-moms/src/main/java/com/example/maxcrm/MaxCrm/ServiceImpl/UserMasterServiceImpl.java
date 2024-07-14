package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Repo.UserMasterRepo;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

@Service
public class UserMasterServiceImpl implements UserMasterService {
    Logger logger = LoggerFactory.getLogger(UserMasterService.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    UserMasterRepo userMasterRepo;
    @Autowired
    TextMessageService textMessageService;
    @Autowired
    WhatsappService whatsappService;
    @Autowired
    ReportingService reportingService;
    @Autowired
    MailObjectService mailObjectService;
    @Autowired
    TemplateDocumentService templateDocumentService;

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqusername]")) {
            retmsg = "Username Is Already Used!!Kindly Try Another Username";

        }
        else if (message.contains("[unqphonenumber]")) {
            retmsg = "Phone Number Is Already Used!!Kindly Try Another Phone Number";

        }
        else if (message.contains("[unqemail]")) {
            retmsg = "Email Is Already Used!!Kindly Try Another Email";

        }
        else {
            retmsg = "User Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }

    @Override
    public Iterable<UserMasterDao> findByDepartment(String department) {

        return userMasterRepo.getUserByDepartment(department);
    }

    @Override
    public Iterable<UserMasterDao> findByReportTo(String reportTo) {
        return userMasterRepo.getUserByReportsTo(reportTo);
    }

    @Override
    public List<UserMasterDao> findByReportList(String list)  {

        List<UserMasterDao> al=new ArrayList();
        Connection con=null;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT `Tbl_UserMaster`.`id`,`Tbl_UserMaster`.`username`,`Tbl_UserMaster`.`firstName`,`Tbl_UserMaster`.`lastName` FROM `Tbl_UserMaster` where Tbl_UserMaster.id in("+list+");");


            ResultSet rs=stmt.executeQuery();
            UserMasterDao userMasterDao=null;
            while (rs.next())
            {
                userMasterDao=new UserMasterDao();
                userMasterDao.setId(rs.getInt(1));
                userMasterDao.setUsername(rs.getString(2));
                userMasterDao.setFirstName(rs.getString(3));
                userMasterDao.setLastName(rs.getString(4));
                al.add(userMasterDao);
            }

       }catch (SQLException sql)
       {
           sql.printStackTrace();

       }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return al;


    }

    @Override
    public Iterable<UserMasterDao> getAllUsers() {
        List<UserMasterDao> al=new ArrayList();
        Connection con=null;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT userMaster.id, userMaster.address, " +
                    "userMaster.attachementList, userMaster.createBy, userMaster.createDate, " +
                    "userMaster.department, userMaster.dob, userMaster.email, userMaster.faceBookId," +
                    " userMaster.firstName, userMaster.gender, userMaster.lastName, userMaster.lastUpdate, " +
                    "userMaster.lastUpdateBy, userMaster.mobile, userMaster.password, userMaster.reportTo, " +
                    "userMaster.sendMailOnCreation, userMaster.status, userMaster.twoStepAuthentication," +
                    " userMaster.username, userMaster.companyName, userMaster.companySize, userMaster.demo, " +
                    "userMaster.demoPeriod, userMaster.emailSent, userMaster.msgSent, userMaster.remainingDemoPeriod," +
                    " userMaster.industry, subquery.reportList," +
                    " COUNT(CASE WHEN FIND_IN_SET(userMaster.id, subquery.reportList) > 0" +
                    " THEN leadMaster.lastForward END) AS leadCountOfReportTo, " +
                    "SUM(CASE WHEN reporting.reportTo = leadMaster.lastForward THEN 1 ELSE 0 END) AS leadCountOfUser" +
                    " FROM Tbl_Reporting AS reporting right JOIN Tbl_UserMaster AS userMaster ON" +
                    " reporting.reportTo = userMaster.id LEFT JOIN Tbl_LeadMaster AS leadMaster ON " +
                    "reporting.userId = leadMaster.lastForward JOIN ( SELECT reportTo," +
                    " GROUP_CONCAT(userId) AS reportList FROM Tbl_Reporting GROUP BY reportTo ) AS subquery ON " +
                    "reporting.reportTo = subquery.reportTo GROUP BY reporting.reportTo, userMaster.firstName, " +
                    "subquery.reportList;");



            ResultSet rs=stmt.executeQuery();
            UserMasterDao userMasterDao=null;
            while (rs.next())
            {
                userMasterDao=new UserMasterDao();
                userMasterDao.setId(rs.getInt(1));
                userMasterDao.setAddress(rs.getString(2));
                userMasterDao.setAttachementList(rs.getString(3));
                userMasterDao.setCreateBy(rs.getString(4));
                userMasterDao.setCreateDate(rs.getString(5));
                userMasterDao.setDepartment(rs.getString(6));
                userMasterDao.setDob(rs.getString(7));
                userMasterDao.setEmail(rs.getString(8));
                userMasterDao.setFaceBookId(rs.getString(9));
                userMasterDao.setFirstName(rs.getString(10));
                userMasterDao.setGender(rs.getString(11));
                userMasterDao.setLastName(rs.getString(12));
                userMasterDao.setLastUpdate(rs.getString(13));
                userMasterDao.setLastUpdateBy(rs.getString(14));
                userMasterDao.setMobile(rs.getString(15));
                userMasterDao.setPassword(rs.getString(16));
                userMasterDao.setReportTo(rs.getString(17));
                userMasterDao.setSendMailOnCreation(rs.getBoolean(18));
                userMasterDao.setStatus(rs.getString(19));
                userMasterDao.setTwoStepAuthentication(rs.getBoolean(20));
                userMasterDao.setUsername(rs.getString(21));
                userMasterDao.setCompanyName(rs.getString(22));
                userMasterDao.setCompanySize(rs.getInt(23));
                userMasterDao.setDemo(rs.getBoolean(24));
                userMasterDao.setDemoPeriod(rs.getInt(25));
                userMasterDao.setEmailSent(rs.getInt(26));
                userMasterDao.setMsgSent(rs.getInt(27));
                userMasterDao.setRemainingDemoPeriod(rs.getInt(28));
                userMasterDao.setIndustry(rs.getString(29));
                userMasterDao.setReportList(rs.getString(30));
                userMasterDao.setLeadCountOfReportTo(rs.getString(31));
                userMasterDao.setLeadCountOfUser(rs.getString(32));
                al.add(userMasterDao);
            }

        }catch (SQLException sql)
        {
            sql.printStackTrace();

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("usermaster1 dao:{}",al.get(0));
        return al;
//        return userMasterRepo.findAll();
    }

    @Override
    public Iterable<UserMasterDao> getAllActiveUsers() {
        return userMasterRepo.findAllActive();
    }

    @Override
    public UserMasterDao insert(UserMasterDao userMasterDao) throws Exception {
        logger.info("Inserting {}", userMasterDao);
        try {
            userMasterDao.setDemo(false);
            // inserting $ sign will throw exception
            userMasterDao.setPassword(userMasterDao.getPassword().replaceAll("\\$", Matcher.quoteReplacement("$")));
            userMasterDao = userMasterRepo.save(userMasterDao);

            if(userMasterDao.isSendMailOnCreation())
            {
                String userCreateTemplate=UtilityClass.propertyService.findProperty("Usermaster","UsercreateTemplate");
                sendNotification(userMasterDao,userCreateTemplate);
            }







        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);

        }

        try {
            ReportTo[] ar = new ReportTo().convertJsonToObject(userMasterDao.getReportTo());
            List<ReportingDao> al = new ArrayList<>();
            ReportingDao dao = null;
            for (int i = 0; i < ar.length; i++) {
                dao = new ReportingDao();
                dao.setUserId(userMasterDao.getId());
                dao.setReportTo(ar[i].getUserid());
                dao.setReportToName(ar[i].getUsername());
                al.add(dao);
            }
            dao=new ReportingDao();
            dao.setUserId(userMasterDao.getId());
            dao.setReportTo(userMasterDao.getId());
            dao.setReportToName(userMasterDao.getUsername());
            al.add(dao);
            reportingService.deleteAndInsertByUserId(userMasterDao.getId(), al);
            reportingService.updateUserIdReporting();

        } catch (Exception ew) {

            throw new Exception("User was successfully Created But Report To Failed So Rolling Back!!");

        }

        return userMasterDao;
    }


    
    private void sendNotification(UserMasterDao userMasterDao,String templateId)
    {

        logger.info("TemplateID :: {} ",templateId);
        logger.info("Sending notification of user :: {}",userMasterDao);
        TemplateDocument templateDocument=templateDocumentService.findById(templateId);
        logger.info("Sending Template {}",templateDocument);
        templateDocument.setTemplate(convertString(templateDocument.getTemplate(),userMasterDao));
        templateDocument.setTemplateSubject(convertString(templateDocument.getTemplateSubject(),userMasterDao));

        if (templateDocument.getTemplateType().equalsIgnoreCase("Mail")) {
            List<MailObjectDao> al = new ArrayList<>();
            MailObjectDao mailObjectDao = new MailObjectDao();
            MailObjectMessage mailObjectMessage = new MailObjectMessage();
            mailObjectMessage.setRecipient(userMasterDao.getEmail());
            mailObjectMessage.setSubject(templateDocument.getTemplateSubject());
            mailObjectMessage.setCustRef("ASdad");

            mailObjectDao.setMessage(mailObjectMessage);
            MailObjectTemplate mailObjectTemplate = new MailObjectTemplate();
            mailObjectTemplate.setTemplateId(templateDocument.getTemplateIdHidden());
            CampaignObjectDao campaignObjectDao=new CampaignObjectDao();
            campaignObjectDao.setFullname(userMasterDao.getFirstName()+" " +userMasterDao.getLastName());
            campaignObjectDao.setOtp(userMasterDao.getPassword());
            campaignObjectDao.setPhonenumber(userMasterDao.getMobile());
            campaignObjectDao.setEmail(userMasterDao.getEmail());
            mailObjectTemplate.setTemplateValues(campaignObjectDao);
            mailObjectDao.setTemplate(mailObjectTemplate);
            al.add(mailObjectDao);
            try {
                mailObjectService.bulkInsertMail(al);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (templateDocument.getTemplateType().equalsIgnoreCase("Message")) {
            TextMessageSimpleDao textMessageSimpleDao = new TextMessageSimpleDao();
            String content = templateDocument.getTemplate();
            content = convertString(content,userMasterDao);
            //textMessageSimpleDao.setContent(templateDocument.getTemplate());
            textMessageSimpleDao.setContent(content);
            textMessageSimpleDao.setDst(userMasterDao.getMobile());
            String senderId = UtilityClass.propertyService.findProperty("Usermaster", "UserSenderId");
            textMessageSimpleDao.setSenderId(senderId);
            try {
                //Hi @fullname Your Otp is @otp

                //===============
                TextMessage tm = new TextMessage();
                InnerTextMessageDao innerTextMessageDao = new InnerTextMessageDao();
                innerTextMessageDao.setMobileNo(textMessageSimpleDao.getDst());
                innerTextMessageDao.setTemplateName(templateDocument.getTemplateName());
                innerTextMessageDao.setTemplateParams(userMasterDao.getUsername()+"~"+userMasterDao.getPassword()+" ");

                List<InnerTextMessageDao> innerTextMessageDaoList = new ArrayList<>();
                innerTextMessageDaoList.add(innerTextMessageDao);

                tm.setSmsTemplateParams(innerTextMessageDaoList);
                tm.setSender(senderId);

                //textMessageService.sendTextMessage(tm);
                //===============

                //USE THIS METHOD FOR SENDING DATA VIA PARAMS
                textMessageService.sendTextMessageSingle(textMessageSimpleDao);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")) {

            List<WhatsappDao> al = new ArrayList<>();
            WhatsappDao whatsappDao = new WhatsappDao();
            whatsappDao.setContent(templateDocument.getTemplate());
            whatsappDao.setContentType(templateDocument.getContentType());
            whatsappDao.setFileName("asd");
            whatsappDao.setCaption(templateDocument.getTemplateSubject());
            whatsappDao.setPhone(getPhonenumber(userMasterDao.getMobile()));
            al.add(whatsappDao);
            whatsappService.sendMessageWhatsapp(al);


        }


    }
    private String convertString(String content, UserMasterDao userMasterDao) {
        logger.info("Converting string  content :: {} , usermasterdoa :: {}",content,userMasterDao);
        content = content.replaceAll("@email", userMasterDao.getEmail());
        content = content.replaceAll("@phonenumber", userMasterDao.getMobile());
        content = content.replaceAll("@fullname", userMasterDao.getFirstName()+ " "+userMasterDao.getLastName());
        content = content.replaceAll("@otp", userMasterDao.getPassword());


        return content;
    }
    public String getPhonenumber(String phonenumber) {
        String[] ar = phonenumber.split("-");
        if (ar.length > 1) {
            return phonenumber;
        } else {
            return "91-" + phonenumber;
        }

    }
    @Override
    public UserMasterDao getRandomUserDepartment(String department) {
        Connection con=null;
        UserMasterDao userMasterDao=null;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT Tbl_UserMaster.id,Tbl_UserMaster.username,Tbl_UserMaster.mobile FROM Tbl_UserMaster  where Tbl_UserMaster.department=?  and Tbl_UserMaster.status='Active' order by rand() limit 1;");
            stmt.setString(1,department);
            ResultSet rs=stmt.executeQuery();
            if (rs.next())
            {
                userMasterDao=new UserMasterDao();
                userMasterDao.setId(rs.getInt(1));
                userMasterDao.setUsername(rs.getString(2));
                userMasterDao.setMobile(rs.getString(3));

            }
        }catch (SQLException sql)
        {
            sql.printStackTrace();

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userMasterDao;
    }
    @Override
    public UserMasterDao update(UserMasterDao userMasterDao) throws Exception {
        logger.info("Updating {}", userMasterDao);
        try {
            // inserting $ sign will throw exception
            userMasterDao.setPassword(userMasterDao.getPassword().replaceAll("\\$", Matcher.quoteReplacement("$")));
            userMasterDao = userMasterRepo.save(userMasterDao);

                if(userMasterDao.isSendMailOnCreation())
                {
                    String userUpdateTemplate=UtilityClass.propertyService.findProperty("Usermaster","UserUpdateTemplate");

                    sendNotification(userMasterDao,userUpdateTemplate);
                }


        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);

        }
        try {
            ReportTo[] ar = new ReportTo().convertJsonToObject(userMasterDao.getReportTo());
            List<ReportingDao> al = new ArrayList<>();
            ReportingDao dao = null;
            for (int i = 0; i < ar.length; i++) {
                dao = new ReportingDao();
                dao.setUserId(userMasterDao.getId());
                dao.setReportTo(ar[i].getUserid());
                dao.setReportToName(ar[i].getUsername());
                al.add(dao);
            }
            dao=new ReportingDao();
            dao.setUserId(userMasterDao.getId());
            dao.setReportTo(userMasterDao.getId());
            dao.setReportToName(userMasterDao.getUsername());
            al.add(dao);
            reportingService.deleteAndInsertByUserId(userMasterDao.getId(), al);
            reportingService.updateUserIdReporting();

        } catch (Exception ew) {
       
            throw new Exception("User was successfully Created But Report To Failed So Rolling Back!!");

        }

        return userMasterDao;
    }
    public void updateUser(String password,int id)
    {
        Connection con=null;
      try{
          con=dataSource.getConnection();
          PreparedStatement stmt=con.prepareStatement("update Tbl_UserMaster set password=?,lastUpdate=? where id=?;");
          String updateTime = UtilityClass.getDateRedable();
          stmt.setString(1,password);
          stmt.setString(2,updateTime );
          stmt.setInt(3,id);
          logger.info("Recover Password >>>> {}", stmt);
          stmt.executeUpdate();
          con.close();
      }catch (SQLException sql)
      {
        sql.printStackTrace();
      }

    }

    @Override
    public void sendNotificationUser(String templateId, UserMasterDao userMasterDao) {
        sendNotification(userMasterDao,templateId);
    }

    @Override
    public String findListOfUserUpload(byte type, String typeValue) {
        StringBuilder sb=new StringBuilder();
        String retString="";
        if(type==1) // by source
        {
            sb.append("select ifnull(group_concat(Tbl_LeadSource_user.user_id),0) as userlist  from Tbl_LeadSource_user inner join Tbl_LeadSource on Tbl_LeadSource_user.lead_source_id=Tbl_LeadSource.id where Tbl_LeadSource.name=?;");
        }
        else if(type==2) //by product
        {
            sb.append(" select ifnull(group_concat(Tbl_Product_user.user_id),0)  as userlist from Tbl_Product_user inner join Tbl_ProductMaster on Tbl_Product_user.product_id=Tbl_ProductMaster.id where Tbl_ProductMaster.name=?;");

        }
        else{
            sb.append("select ifnull(group_concat(Tbl_LeadType_user.user_id),0) as userlist from Tbl_LeadType_user inner join Tbl_LeadType on Tbl_LeadType_user.lead_type_id=Tbl_LeadType.id where Tbl_LeadType.name=?;\n");

        }
        Connection con=null;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement(sb.toString());
            stmt.setString(1,typeValue);
            ResultSet rs=stmt.executeQuery();
            while (rs.next())
            {
                retString=rs.getString(1);
            }
        }catch (Exception ew)
        {
            logger.error("Error Occured While Finding User {}",ew);

        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return retString;
    }

    @Override
    public String findReportingUser(int userid) {
        logger.info("Finding all users who report to :: {} ",userid);
        String userList = null;
        Connection con=null;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select group_concat(userid) from Tbl_Reporting where reportTo=?");
            stmt.setInt(1,userid);
            ResultSet rs=stmt.executeQuery();
            while (rs.next())
            {
                userList=rs.getString(1);
            }

        }catch (SQLException sql)
        {
          logger.warn("{}",sql);
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        logger.info("List of users :: {}",userList);
        return userList;
    }

    @Override
    public UserMasterDao findByUsername(String username) {
        return userMasterRepo.findByUsername(username);
    }

    @Override
    public List<MenuDynamicDatabaseDao> loadMenu(String dep) throws Exception {
        Connection con=null;
        List<MenuDynamicDatabaseDao> al=new ArrayList<>();
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT CONCAT('[', GROUP_CONCAT(CONCAT('{pagename:\"', Tbl_UrlAccess.pageName, '\",pagedesc:\"', Tbl_UrlAccess.pageDesc, '\",rolename:\"', Tbl_UrlAccess.roleName, '\", url:\"', Tbl_UrlAccess.url, '\"}')), ']') list, Tbl_Menu.menuName, Tbl_Menu.icon FROM Tbl_UrlAccess INNER JOIN Tbl_RolePage ON Tbl_UrlAccess.id = Tbl_RolePage.urlId LEFT JOIN Tbl_Menu ON Tbl_UrlAccess.menuId = Tbl_Menu.id WHERE Tbl_UrlAccess.status = 1 AND Tbl_UrlAccess.showOnMenu = 1 AND Tbl_Menu.status = 1 AND Tbl_RolePage.roleId = (SELECT Tbl_Role.id FROM Tbl_Role WHERE Tbl_Role.roleName = ?) GROUP BY Tbl_Menu.menuName,Tbl_Menu.icon ORDER BY Tbl_Menu.menuName ASC");
            stmt.setString(1,dep);
            ResultSet rs=stmt.executeQuery();
            MenuDynamicDatabaseDao menuDynamicDatabaseDao=null;
            MenuInnerDynamic menuInnerDynamic= null;
            while (rs.next())
            {
                menuInnerDynamic=new MenuInnerDynamic();
                menuDynamicDatabaseDao=new MenuDynamicDatabaseDao();

                menuDynamicDatabaseDao.setListment( menuInnerDynamic.convertJsonToObject(rs.getString(1)));
                menuDynamicDatabaseDao.setMenuName(rs.getString(2));
                menuDynamicDatabaseDao.setMenuIcon(rs.getString(3));
                al.add(menuDynamicDatabaseDao);
            }
        }catch (SQLException sql)
        {
            throw new Exception("Faied To Load Menu!!");

        }finally {
            con.close();
        }
        return al;
    }

    @Override
    public UserMasterDao findById(int id) {
        return userMasterRepo.findById(id).get();
    }

    @Override
    public UserMasterDao findUserForRecovery(String value) {

        logger.info("finding user for password recovery username/email/mobile : {}",value);
        return userMasterRepo.findUserForRecovery(value);
    }


    private class ReportTo {
        private String username;
        private int userid;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        ReportTo[] convertJsonToObject(String json) {
            return new Gson().fromJson(json, ReportTo[].class);

        }

    }
//for demo user, set reportto assign
    private UserMasterDao setReportTo(UserMasterDao dao){

        Connection con = null;
        try{
            con = dataSource.getConnection();
            String reportTo = "[{\"department\":\""+dao.getDepartment()+" \",\"userid\":\""+dao.getId()+"\",\"username\":\""+dao.getUsername()+"\"}]";
            dao.setReportTo(reportTo);
            PreparedStatement stmt = con.prepareStatement("Update Tbl_UserMaster set reportTo=? where id=?");
            stmt.setString(1,reportTo);
            stmt.setLong(2,dao.getId());
            stmt.executeUpdate();
            setReportToForDefaultLeads(con,dao);
        }catch (SQLException sql){
            logger.info("Exception Occured {}",sql);
        }finally {
            try{
                con.close();
            }catch (Exception e){
                logger.info("Error Occured {}",e);
            }
        }
        return dao;
    }

    //for demo user, set reportto assign
    private void setReportToForDefaultLeads(Connection con,UserMasterDao dao){


        try{
            con = dataSource.getConnection();
           long salesUserId = Long.parseLong(UtilityClass.propertyService.findProperty("CRMDemo", "saleuserid"));
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `Tbl_Reporting` (`reportTo`,`reportToName`,`userId`) VALUES(?,?,?);");
            stmt.setLong(1,dao.getId());
            stmt.setString(2,dao.getUsername());
            stmt.setLong(3,salesUserId);
            logger.info("Assign Sales User ReportTo >>>> {}", stmt);
            stmt.execute();
        }catch (SQLException sql){
            logger.info("Exception Occured {}",sql);
        }
    }


}
