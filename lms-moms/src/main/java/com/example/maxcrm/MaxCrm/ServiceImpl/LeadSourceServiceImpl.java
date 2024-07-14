package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadSourceDao;
import com.example.maxcrm.MaxCrm.Repo.LeadSourceRepository;
import com.example.maxcrm.MaxCrm.Service.LeadSourceService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Service
public class LeadSourceServiceImpl implements LeadSourceService {
    Logger logger = LoggerFactory.getLogger(LeadSourceServiceImpl.class);
    @Autowired
    LeadSourceRepository leadSourceRepository;

    @Autowired
    DataSource dataSource;

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqleadsource]")) {
            retmsg = "Lead Source Already Exists!!";

        } else {
            retmsg = "Lead Source Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }

    @Override
    public LeadSourceDao insert(LeadSourceDao dao) throws Exception {


        logger.info("Inserting {}", dao);
        try {
            dao = leadSourceRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        updateLeadSourceMemory();
        return dao;
    }

    @Override
    public LeadSourceDao update(LeadSourceDao dao) throws Exception {
        logger.info("Updating {}", dao);
        try {
            dao = leadSourceRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        updateLeadSourceMemory();
        return dao;
    }

    @Override
    public LeadSourceDao findByName(String sourceName) {
        logger.info("Finding lead source with name :: {}",sourceName);
        return leadSourceRepository.findBySourceName(sourceName);
    }

    @Override
    public void delete(int id) {
        logger.info("Deleting {}", id);
        leadSourceRepository.deleteById(id);
    }

    @Override
    public Iterable<LeadSourceDao> findAll() {

        return leadSourceRepository.findAll();
    }

    @Override
    public Iterable<LeadSourceDao> findActive() {
        return leadSourceRepository.findActive();
    }

    @Override
    public int findRandomUserId(int leadsource) throws SQLException {
        Connection con=null;
        int userId=0;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select Tbl_LeadSource_user.user_id from Tbl_LeadSource_user inner join Tbl_UserMaster on Tbl_LeadSource_user.user_id=Tbl_UserMaster.id where Tbl_LeadSource_user.lead_source_id=? and Tbl_UserMaster.status='active' ORDER BY RAND() limit 1 ;");
            stmt.setInt(1,leadsource);
            ResultSet rs=stmt.executeQuery();
            while (rs.next())
            {
                userId=rs.getInt(1);
            }
        }catch (SQLException sql)
        {
            throw sql;
        }finally {
            con.close();
        }
        return userId;
    }

    @Override
    public int findRanDomUserId(String leadsource) {
        Connection con=null;
        int userId=0;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select Tbl_LeadSource_user.user_id from Tbl_LeadSource_user inner join Tbl_LeadSource on Tbl_LeadSource_user.lead_source_id=Tbl_LeadSource.id inner join Tbl_UserMaster on Tbl_LeadSource_user.user_id=Tbl_UserMaster.id where Tbl_LeadSource.name=? and Tbl_UserMaster.status='Active' order by rand() limit 1;");
            stmt.setString(1,leadsource);
            ResultSet rs=stmt.executeQuery();
            while (rs.next())
            {
                userId=rs.getInt(1);
            }
        }catch (SQLException sql)
        {

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userId;
    }

    @Override
    public LeadSourceDao findLeadByLeadSource(String leadSource) {
        return leadSourceRepository.findBySourceName(leadSource);
    }

    @Override
    public void updateLeadSourceMemory() {
        Iterable<LeadSourceDao> al=leadSourceRepository.findActive();
        UtilityClass.alleadsourcedao=new HashMap<>();
        for (LeadSourceDao leadSourceDao : al) {
            UtilityClass.alleadsourcedao.put(leadSourceDao.getName(),leadSourceDao);
        }
    }

    @Override
    public HashMap<String,String> getUser( byte type) throws SQLException {
        String query="";
        HashMap<String,String> hashMap=new HashMap<>();
        if(type==1)
        {
            query="select Tbl_LeadSource.name,ifnull(group_concat(Tbl_LeadSource_user.user_id),0) as userlist  from Tbl_LeadSource_user inner join Tbl_LeadSource on Tbl_LeadSource_user.lead_source_id=Tbl_LeadSource.id group by Tbl_LeadSource.name;";
        }
        else if(type==2)
        {
            query="select Tbl_ProductMaster.name,ifnull(group_concat(Tbl_Product_user.user_id),0)  as userlist from Tbl_Product_user inner join Tbl_ProductMaster on Tbl_Product_user.product_id=Tbl_ProductMaster.id group by Tbl_ProductMaster.name;" ;
        }
        else if(type==3)
        {
            query="select Tbl_LeadType.name,ifnull(group_concat(Tbl_LeadType_user.user_id),0) as userlist from Tbl_LeadType_user inner join Tbl_LeadType on Tbl_LeadType_user.lead_type_id=Tbl_LeadType.id group by Tbl_LeadType.name;" ;

        }
        Connection con=null;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement(query);

            ResultSet rs=stmt.executeQuery();
            while (rs.next())
            {
                hashMap.put(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException sql)
        {
            logger.error("Error Occured {}",sql);
        }finally {
        con.close();

        }
        return hashMap;
    }
}
