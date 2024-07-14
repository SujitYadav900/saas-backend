package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.ReportAllTicketDao;
import com.example.maxcrm.MaxCrm.Service.ReportAllTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReportAllTicketServiceImpl implements ReportAllTicketService {


    Logger logger = LoggerFactory.getLogger(ReportAllTicketServiceImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public List<ReportAllTicketDao> getReport(int flag, String searchvalue, String startdate, String enndate, String userlist) throws Exception {

        logger.info("getting ReportAllLead for id {}, search value {} from {} to {}");

        Connection con= null;
        List<ReportAllTicketDao> list = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_GETTICKETALLREPORTCARD(?, ?, ?,?,?)");
            stmt.setInt(1,flag);
            stmt.setString(2,searchvalue);
            stmt.setString(3,startdate);
            stmt.setString(4,enndate);
            stmt.setString(5,userlist);
            logger.info("statement {}",stmt);

            ResultSet rs = stmt.executeQuery();
            ReportAllTicketDao reportAllTicketDao = null;
            while(rs.next()){
                reportAllTicketDao = new ReportAllTicketDao();
                reportAllTicketDao.setSeq(rs.getInt(1));
                reportAllTicketDao.setCount(rs.getInt(2));
                reportAllTicketDao.setDescription(rs.getString(3));

                list.add(reportAllTicketDao);
            }

        }catch (SQLException exc){
            exc.printStackTrace();
            throw  exc;
        }finally {
            try{
                con.close();
            }catch (Exception exc){
                exc.printStackTrace();
                throw exc;
            }
        }
        return list;
    }
}
