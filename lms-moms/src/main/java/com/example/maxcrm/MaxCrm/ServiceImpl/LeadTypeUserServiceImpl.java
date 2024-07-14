package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadTypeUserDao;
import com.example.maxcrm.MaxCrm.Repo.LeadTypeUserRepository;
import com.example.maxcrm.MaxCrm.Service.LeadTypeUserService;
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
public class LeadTypeUserServiceImpl implements LeadTypeUserService {

    Logger logger = LoggerFactory.getLogger(LeadTypeUserServiceImpl.class);
    @Autowired
    LeadTypeUserRepository repo;

    @Autowired
    DataSource dataSource;

    @Override
    public Iterable<LeadTypeUserDao> getAll() {


        logger.info("Getting all LeadSource Users");
        return repo.findAll();
    }

    @Override
    public LeadTypeUserDao insert(LeadTypeUserDao leadTypeUser)throws Exception {
        logger.info("Insertig LeadType User {}",leadTypeUser);
        try{
            leadTypeUser = repo.save(leadTypeUser);
        }catch (Exception ex){
            logger.error("Error Occured!! {}", ex);
            String message = handleException(ex.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return leadTypeUser;
    }

    @Override
    public void delete(long id) {
        logger.info("deletig leadtypeuser by id {}",id);
        repo.deleteById(id);
    }


    public ArrayList<LeadTypeUserDao> getAllByTypeId(long lead_type_id) throws SQLException {

        logger.info("getting all leadtypeuser by id {}",lead_type_id);
        Connection con=null;
        ArrayList<LeadTypeUserDao> list = null;
        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT Tbl_LeadType_user.id, Tbl_UserMaster.username FROM Tbl_LeadType_user inner join Tbl_UserMaster on  Tbl_LeadType_user.user_id = Tbl_UserMaster.id where Tbl_LeadType_user.lead_type_id="+lead_type_id);
            ResultSet rs  = stmt.executeQuery();
            list = new ArrayList<LeadTypeUserDao>();
           while(rs.next()){
               LeadTypeUserDao user = new LeadTypeUserDao();
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
