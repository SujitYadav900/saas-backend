package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.TicketReportCardDao;
import com.example.maxcrm.MaxCrm.Service.TicketReportCardService;
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
public class TicketReportCardServiceImpl implements TicketReportCardService {

    Logger logger = LoggerFactory.getLogger(TicketReportCardServiceImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public List<TicketReportCardDao> getTicketReport(String id, String startdate, String enddate) throws SQLException {

        logger.info("getting Ticket report card for id {} from {} to {}",id,startdate,enddate);

        Connection con= null;
        List<TicketReportCardDao> list = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_TICKETREPORTCARD(?,?,?)");
            stmt.setString(1,id);
            stmt.setString(2,startdate);
            stmt.setString(3,enddate);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                TicketReportCardDao dao = new TicketReportCardDao();
                dao.setSeq(rs.getInt(1));
                dao.setCount(rs.getDouble(2));
                dao.setDescription(rs.getString(3));

                list.add(dao);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            throw ex;
        }finally {
            try{
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
                throw ex;
            }
        }
        return list;
    }
}
