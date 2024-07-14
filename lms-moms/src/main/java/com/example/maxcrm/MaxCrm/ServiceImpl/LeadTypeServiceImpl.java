package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadTypeDao;
import com.example.maxcrm.MaxCrm.Repo.LeadTypeRepository;
import com.example.maxcrm.MaxCrm.Service.LeadTypeService;
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
public class LeadTypeServiceImpl implements LeadTypeService {
    Logger logger = LoggerFactory.getLogger(LeadTypeServiceImpl.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    LeadTypeRepository leadTypeRepository;

    @Override
    public LeadTypeDao insert(LeadTypeDao dao) throws Exception {
        logger.info("Inserting {}",dao);
        try {
            dao = leadTypeRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public LeadTypeDao update(LeadTypeDao dao) throws Exception {
        logger.info("Updating {}",dao);
        try {
            dao = leadTypeRepository.save(dao);
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
            PreparedStatement stmt=con.prepareStatement("SELECT Tbl_LeadType_user.user_id FROM Tbl_LeadType_user INNER JOIN Tbl_LeadType ON Tbl_LeadType_user.lead_type_id = Tbl_LeadType.id INNER JOIN Tbl_UserMaster ON Tbl_UserMaster.id = Tbl_LeadType_user.user_id WHERE Tbl_LeadType.name = ? AND Tbl_UserMaster.status='Active' ORDER BY RAND() LIMIT 1;");
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
        leadTypeRepository.deleteById(id);
    }

    @Override
    public Iterable<LeadTypeDao> findAll() {

        return leadTypeRepository.findAll();
    }

    @Override
    public Iterable<LeadTypeDao> findActive() {
        return leadTypeRepository.findActive();
    }

    @Override
    public LeadTypeDao findByName(String name) {
        return leadTypeRepository.findByName(name);
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqleadtype]")) {
            retmsg = "Lead Type Already Exists!!";

        } else {
            retmsg = "Lead Type Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
}
