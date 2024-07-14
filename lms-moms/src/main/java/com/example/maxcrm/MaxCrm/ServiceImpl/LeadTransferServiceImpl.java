package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadTransferDao;
import com.example.maxcrm.MaxCrm.Dao.NotificationDao;
import com.example.maxcrm.MaxCrm.Repo.LeadTransferRepo;
import com.example.maxcrm.MaxCrm.Service.CounterService;
import com.example.maxcrm.MaxCrm.Service.LeadMasterService;
import com.example.maxcrm.MaxCrm.Service.LeadTransferService;
import com.example.maxcrm.MaxCrm.Service.NotificationService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LeadTransferServiceImpl implements LeadTransferService {
    @Autowired
    LeadTransferRepo leadTransferRepo;
    @Autowired
    DataSource dataSource;
    @Autowired
    LeadMasterService leadMasterService;
    @Autowired
    CounterService counterService;
    @Autowired
    NotificationService notificationService;

    Logger logger = LoggerFactory.getLogger(LeadTransferServiceImpl.class);
    @Override
    public Iterable<LeadTransferDao> getByLeadId(long id) {
        return leadTransferRepo.findByLeadId(id);
    }

    @Override
    public int insertBulk(List<LeadTransferDao> al,String username,boolean sendNotification) throws SQLException {
        int insert=0;
        Connection con=null;
        String createDate= UtilityClass.getDateRedable();
        try{
            List<NotificationDao> notificationDaos=new ArrayList<>();
            HashMap<Integer,Integer> perUserNotification=new HashMap<>();
            con=dataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt=con.prepareStatement("INSERT  INTO  `Tbl_LeadTransfer` ( `createBy`, `datetiming`, `fromusername`, `leadId`, `toId`, `tousername`) VALUES ( ?, ?, ?, ?, ?, ?);");
            LeadTransferDao model=null;
            StringBuilder sb=new StringBuilder();
            int alsize=al.size();
            long notificationCounter=0;
            NotificationDao notificationDao;
            if(sendNotification) {
                 notificationCounter=counterService.getCounter(1,alsize+1);
            }
            String textMessageOnNewLead=UtilityClass.propertyService.findProperty("Lead","Send_Text_Message_On_Lead");

            for(int i=0;i<alsize;i++)
            {
                insert++;
                model=al.get(i);
                stmt.setString(1,username);
                stmt.setString(2,createDate);
                stmt.setString(3,model.getFromusername());
                stmt.setLong(4,model.getLeadId());
                stmt.setInt(5,model.getToId());
                stmt.setString(6,model.getTousername());
                sb.append(model.getLeadId());
                sb.append(",");
                stmt.addBatch();
                if(sendNotification)
                {
                    try{
                        int userCount=perUserNotification.get(model.getToId());
                        perUserNotification.put(model.getToId(),userCount+1);

                    }catch (NullPointerException n)
                    {

                        perUserNotification.put(model.getToId(),1);
                    }


                }


            }
            logger.info("Hashmap {}",perUserNotification);
            stmt.executeBatch();

            logger.info("Inserting into Transfer Table {}",stmt);

            int lastForward=model.getToId();
            String idlist = sb.substring(0, sb.toString().lastIndexOf(","));
            stmt=con.prepareStatement("update Tbl_LeadMaster set Tbl_LeadMaster.lastForward=? where Tbl_LeadMaster.id in ("+idlist+");");
            stmt.setInt(1,lastForward);

            stmt.executeUpdate();

            int date=UtilityClass.dateFilterDate();
            String datefilterdate=date+"@"+date;
            long fulldate=UtilityClass.fullDateLong();
            if(sendNotification)
            {
                for (int userId : perUserNotification.keySet()) {
                    int count= perUserNotification.get(userId);
                    notificationDao=new NotificationDao();
                    notificationDao.setId(notificationCounter);
                    notificationDao.setTo(userId);
                    notificationDao.setCreateAt(createDate);
                    //notificationDao.setRedirectUrl("lead?datefiltertype=createdate&innersource=0&statusValue=0&leadSource=0&product=0&leadType=0&userFilter="+userId+"&datefilter=true&datevalue="+0+"&searchvalue=0&leadstage=0");
                    notificationDao.setRedirectUrl("lead?datefiltertype=createdate&innersource=0&statusValue=0&leadSource=0&product=0&leadType=0&userFilter="+userId+"&datefilter=true&datevalue="+datefilterdate+"&searchvalue=0&leadstage=0");
                    notificationDao.setInnerSubject("NOTIFY");
                    notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
                    notificationDao.setMessage(username+" Transferred You "+count+" Leads");
                    notificationDao.setLeadId(0);
                    notificationDao.setSubject("New Transferred Lead");
                    notificationDao.setCreateBy(username);
                    notificationDao.setSendMessage(textMessageOnNewLead.equalsIgnoreCase("1"));
                    notificationDao.setDatetiming(fulldate);
                    notificationDao.setAssignTo(userId);
                    notificationDao.setDatetimingnotification(fulldate);
                    notificationDaos.add(notificationDao);
                    notificationCounter++;
                }
                notificationService.bulkInsertNotification(notificationDaos,con);
            }
            logger.info("Updating Lead Master For Transfer {}",stmt);
            if(UtilityClass.propertyService.findProperty("Lead","UpdateLeadOnLeadTransfer").equalsIgnoreCase("1")) {
                leadMasterService.updateLastUpdateLeadMultiple(con, username, UtilityClass.dateFilterDate(), createDate, idlist);
            }

            logger.info("Checking if clientType is null or not");
            //IF TRANSFERDAO HAS LEADTYPE SET (NOT NULL) THEN ALSO CHANGE LEAD TYPE OF THE LEADS
            if(al.get(0).getClientType() != "" && al.get(0).getClientType() != null){
                logger.info("Lead Type is > {}",al.get(0).getClientType());
                stmt = con.prepareStatement("UPDATE Tbl_LeadMaster set Tbl_LeadMaster.clientType=? WHERE Tbl_LeadMaster.id IN ("+idlist+")");
                stmt.setString(1,al.get(0).getClientType());
                logger.info("Query for updating clientType >> {}",stmt);
                stmt.executeUpdate();
            }

           con.commit();
        }catch (SQLException sql)
        {
            con.rollback();
        }finally {
            con.close();
        }


        return insert;
    }
}
