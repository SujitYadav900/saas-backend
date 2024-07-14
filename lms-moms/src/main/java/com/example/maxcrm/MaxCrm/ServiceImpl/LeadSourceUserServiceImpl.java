package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadSourceUser;
import com.example.maxcrm.MaxCrm.Repo.LeadSourceUserRepository;
import com.example.maxcrm.MaxCrm.Service.LeadSourceUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;


@Service
public class LeadSourceUserServiceImpl implements LeadSourceUserService {

    Logger logger = LoggerFactory.getLogger(LeadSourceUserServiceImpl.class);
    @Autowired
    LeadSourceUserRepository repo;

    @Autowired
    DataSource dataSource;

    @Override
    public Iterable<LeadSourceUser> getAll() {


        logger.info("Getting all LeadSource Users");
        return repo.findAll();
    }

    @Override
    public LeadSourceUser insert(LeadSourceUser leadSourceUser)throws Exception {
        logger.info("Insertig LeadSource User {}",leadSourceUser);
        try{
            leadSourceUser = repo.save(leadSourceUser);
        }catch (Exception ex){
            logger.error("Error Occured!! {}", ex);
            String message = handleException(ex.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return leadSourceUser;
    }

    @Override
    public void delete(long id) {
        logger.info("deletig leadsouceuser by id {}",id);
        repo.deleteById(id);
    }


    public ArrayList<LeadSourceUser> getAllBySourceId(long lead_Source_id) throws SQLException {

        logger.info("getting all leadsouceuser by id {}",lead_Source_id);
        Connection con=null;
        ArrayList<LeadSourceUser> list = null;
        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT Tbl_LeadSource_user.id, Tbl_UserMaster.username FROM Tbl_LeadSource_user inner join Tbl_UserMaster on  Tbl_LeadSource_user.user_id = Tbl_UserMaster.id where Tbl_LeadSource_user.lead_source_id="+lead_Source_id);
            ResultSet rs  = stmt.executeQuery();
            list = new ArrayList<LeadSourceUser>();
           while(rs.next()){
                LeadSourceUser user = new LeadSourceUser();
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
