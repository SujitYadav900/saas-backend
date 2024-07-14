package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LoginHistoryDao;
import com.example.maxcrm.MaxCrm.Repo.LoginHistoryRepo;
import com.example.maxcrm.MaxCrm.Service.LoginHistoryService;
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
import java.util.List;
@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {
    Logger logger = LoggerFactory.getLogger(LoginHistoryService.class);
    @Autowired
    LoginHistoryRepo loginHistoryRepo;
    @Autowired
    DataSource dataSource;
    @Override
    public List<LoginHistoryDao> getByUserId(int userId) {
        return loginHistoryRepo.getAllByUserId(userId);
    }

    @Override
    public LoginHistoryDao getByUserIdSingle(int userId) throws SQLException {
        Connection con=null;
        LoginHistoryDao loginHistoryDao=new LoginHistoryDao();
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select Tbl_Login_History.loginTiming,Tbl_Login_History.ip from Tbl_Login_History where userId=? order by id desc limit 1;");
            stmt.setInt(1,userId);
            ResultSet rs=stmt.executeQuery();
            while (rs.next())
            {
                loginHistoryDao.setLoginTiming(rs.getString(1));
                loginHistoryDao.setIp(rs.getString(2));
            }
        }catch (SQLException sql)
        {
            sql.printStackTrace();

        }finally {
            con.close();
        }
        return loginHistoryDao;
    }

    @Override
    public LoginHistoryDao insert(LoginHistoryDao loginHistoryDao) {
        logger.info("Inserting {}",loginHistoryDao);
        return loginHistoryRepo.save(loginHistoryDao);
    }

    @Override
    public List<LoginHistoryDao> lastTenHistory(int userId) throws SQLException {
        Connection con=null;

        List<LoginHistoryDao> list = new ArrayList<>();
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select Tbl_Login_History.loginTiming,Tbl_Login_History.ip from Tbl_Login_History where userId=? order by id desc limit 10;");
            stmt.setInt(1,userId);
            ResultSet rs=stmt.executeQuery();
            LoginHistoryDao loginHistoryDao;
            while (rs.next())
            {
                loginHistoryDao=new LoginHistoryDao();
                loginHistoryDao.setLoginTiming(rs.getString(1));
                loginHistoryDao.setIp(rs.getString(2));
                list.add(loginHistoryDao);
            }
        }catch (SQLException sql)
        {
            sql.printStackTrace();

        }finally {
            con.close();
        }
        return list;
    }
}
