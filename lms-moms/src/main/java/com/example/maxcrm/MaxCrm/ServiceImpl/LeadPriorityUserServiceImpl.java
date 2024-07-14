package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadPriorityUserDao;
import com.example.maxcrm.MaxCrm.Repo.LeadPriorityUserRepository;
import com.example.maxcrm.MaxCrm.Service.LeadPriorityUserService;
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


@Service
public class LeadPriorityUserServiceImpl implements LeadPriorityUserService {

    Logger logger = LoggerFactory.getLogger(LeadPriorityUserServiceImpl.class);
    @Autowired
    LeadPriorityUserRepository repo;

    @Autowired
    DataSource dataSource;

    @Override
    public Iterable<LeadPriorityUserDao> getAll() {

        logger.info("Getting all LeadSource Users");
        return repo.findAll();
    }

    @Override
    public LeadPriorityUserDao insert(LeadPriorityUserDao priorityUserDao)throws Exception {
        logger.info("Insertig LeadPriority User {}",priorityUserDao);
        try{
             priorityUserDao = repo.save(priorityUserDao);
        }catch (Exception ex){
            logger.error("Error Occured!! {}", ex);
            String message = handleException(ex.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return priorityUserDao;
    }

    @Override
    public void delete(long id) {
        logger.info("deletig leadtypeuser by id {}",id);
        repo.deleteById(id);
    }


    public ArrayList<LeadPriorityUserDao> getAllByTypeId(long lead_priority_id) throws SQLException {

        logger.info("getting all leadPriorityuser by id {}",lead_priority_id);
        Connection con=null;
        ArrayList<LeadPriorityUserDao> list = null;
        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT Tbl_LeadPriority_user.id, Tbl_UserMaster.username FROM Tbl_LeadPriority_user inner join Tbl_UserMaster on  Tbl_LeadPriority_user.user_id = Tbl_UserMaster.id where Tbl_LeadPriority_user.lead_priority_id="+lead_priority_id);
            ResultSet rs  = stmt.executeQuery();
            list = new ArrayList<>();
           while(rs.next()){
               LeadPriorityUserDao user = new LeadPriorityUserDao();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                list.add(user);
           }


        }catch (SQLException sql)
        {
            sql.printStackTrace();
            logger.error("Error Occured!! {}", sql);
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Error Occured!! {}", e);
            }
        }
        return list;
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqleaduser]")) {
            retmsg = "User Already Exists in the List!!";

        } else {
            retmsg = "User Can Not Be Added!!Please Try Again Later";
        }
        return retmsg;
    }
}
