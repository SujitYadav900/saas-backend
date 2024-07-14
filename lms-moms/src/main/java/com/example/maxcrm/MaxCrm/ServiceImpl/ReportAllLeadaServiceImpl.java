package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.ReportAllLeadDao;
import com.example.maxcrm.MaxCrm.Service.ReportAllLeadService;
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
public class ReportAllLeadaServiceImpl implements ReportAllLeadService {


    Logger logger = LoggerFactory.getLogger(ReportAllLeadaServiceImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public List<ReportAllLeadDao> getReport(int flag, String searchvalue,String clientTypeValue, String startdate, String enndate, String userlist, int dateTypeFlag) throws Exception {

        logger.info("getting ReportAllLead for id {}, search value {},clientType value {} from {} to {} for dateType {}",userlist,searchvalue,clientTypeValue,startdate,enndate,dateTypeFlag);

        Connection con= null;
        List<ReportAllLeadDao> list = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            CallableStatement stmt = null;
            //LEAD DATE
            if(dateTypeFlag == 1){
                stmt =  con.prepareCall("call USP_GETREPORTALLLEAD_LEADDATE(?, ?, ?,?,?,?)");
            }//CONVERT DATE
            else if(dateTypeFlag == 2){
                stmt =  con.prepareCall("call USP_GETREPORTALLLEAD_CONVERTDATE(?, ?, ?,?,?,?)");
            }//UPDATE DATE
            else if(dateTypeFlag == 3){
                stmt =  con.prepareCall("call USP_GETREPORTALLLEAD_UPDATEDATE(?, ?, ?,?,?,?)");
            }//PROFILING DATE
            else if(dateTypeFlag == 5){
                stmt =  con.prepareCall("call USP_GETREPORTALLLEAD_PROFILINGDATE(?, ?, ?,?,?,?)");
            }//CREATE DATE
            else {
               stmt =  con.prepareCall("call USP_GETREPORTALLLEAD(?, ?,?,?,?,?)");
            }



            stmt.setInt(1,flag);
            stmt.setString(2,searchvalue);
            stmt.setString(3,clientTypeValue);
            stmt.setString(4,startdate);
            stmt.setString(5,enndate);
            stmt.setString(6,userlist);
            logger.info("statement {}",stmt);

            ResultSet rs = stmt.executeQuery();
            ReportAllLeadDao reportAllLeadDao = null;
            while(rs.next()){
                reportAllLeadDao = new ReportAllLeadDao();
                reportAllLeadDao.setSeq(rs.getInt(1));
                reportAllLeadDao.setDescription(rs.getString(2));
                reportAllLeadDao.setCount(rs.getInt(3));

                list.add(reportAllLeadDao);
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
