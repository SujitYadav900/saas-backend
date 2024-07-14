package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.MailReportCardDao;
import com.example.maxcrm.MaxCrm.Service.MailReportCardService;
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
public class MailReportCardServieImpl implements MailReportCardService {

    private final Logger logger = LoggerFactory.getLogger(MailReportCardServieImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public List<MailReportCardDao> getReportCard(int startdate , int enddate) {

        Connection con = null;

    List<MailReportCardDao> list = new ArrayList<>();
        try{

            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT Tbl_Mail_Transaction.status, SUM(Tbl_Mail_Transaction.count) FROM Tbl_Mail_Transaction WHERE Tbl_Mail_Transaction.datefiler >= ? AND Tbl_Mail_Transaction.datefiler <= ? GROUP BY Tbl_Mail_Transaction.status;");
            stmt.setInt(1,startdate);
            stmt.setInt(2, enddate);
            ResultSet rs = stmt.executeQuery();
            MailReportCardDao dao;
            while(rs.next()){
                dao = new MailReportCardDao();
                dao.setStatus(rs.getInt(1));
                dao.setCount(rs.getLong(2));
                list.add(dao);
            }
                
            
        }catch(SQLException exc){
            exc.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
        return list;
    }
}
