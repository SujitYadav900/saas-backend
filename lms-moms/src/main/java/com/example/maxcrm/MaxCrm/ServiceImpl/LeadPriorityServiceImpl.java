package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadPriorityDao;
import com.example.maxcrm.MaxCrm.Repo.LeadPriorityRepository;
import com.example.maxcrm.MaxCrm.Service.LeadPriorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LeadPriorityServiceImpl implements LeadPriorityService {
    Logger logger = LoggerFactory.getLogger(LeadPriorityServiceImpl.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    LeadPriorityRepository leadPriorityRepository;

    @Override
    public LeadPriorityDao insert(LeadPriorityDao dao) throws Exception {
        logger.info("Inserting {}",dao);
        try {
            dao = leadPriorityRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public LeadPriorityDao update(LeadPriorityDao dao) throws Exception {
        logger.info("Updating {}",dao);
        try {
            dao = leadPriorityRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public int findRandomUser(String type) {
        Connection con=null;
        int userId=0;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT Tbl_LeadPriority_user.user_id FROM Tbl_LeadPriority_user INNER JOIN Tbl_LeadPriority ON Tbl_LeadPriority_user.lead_priority_id = Tbl_LeadPriority.id INNER JOIN Tbl_UserMaster ON Tbl_UserMaster.id = Tbl_LeadPriority_user.user_id WHERE Tbl_LeadPriority.name = ? AND Tbl_UserMaster.status='Active' ORDER BY RAND() LIMIT 1;");
            stmt.setString(1,type);
            ResultSet rs=stmt.executeQuery();
            if(rs.next())
            {
                userId= rs.getInt(1);
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
    public void delete(int id) {
        logger.info("Deleting {}", id);
        leadPriorityRepository.deleteById(id);
    }

    @Override
    public Iterable<LeadPriorityDao> findAll() {

        return leadPriorityRepository.findAll();
    }

    @Override
    public Iterable<LeadPriorityDao> findActive() {
        return leadPriorityRepository.findActive();
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqleadtype]")) {
            retmsg = "Lead Priority Already Exists!!";

        } else {
            retmsg = "Lead Priority Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
}
