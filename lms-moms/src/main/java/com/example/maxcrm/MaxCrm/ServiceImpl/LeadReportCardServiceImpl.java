package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadReportCardDao;
import com.example.maxcrm.MaxCrm.Service.LeadReportCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeadReportCardServiceImpl implements LeadReportCardService {


    Logger logger = LoggerFactory.getLogger(LeadReportCardServiceImpl.class);
    @Autowired
    private DataSource dataSource;

    @Override
    public List<LeadReportCardDao> getReport(String id, String startdate, String enddate,int dateTypeFlag) throws SQLException {

        logger.info("Getting Lead Report Card for id {} from {} to {} for date type {}",id,startdate,enddate,dateTypeFlag);
        Connection con = null;
        List<LeadReportCardDao> list = new ArrayList<>();
        try{

            con = dataSource.getConnection();
           CallableStatement stmt = null;
            //LEAD DATE
            if(dateTypeFlag == 1){
                stmt =  con.prepareCall("call USP_LEADREPORTCARD_LEADDATE(?, ?, ?)");
            }//CONVERT DATE
            else if(dateTypeFlag == 2){
                stmt =  con.prepareCall("call USP_LEADREPORTCARD_CONVERTDATE(?, ?, ?)");
            }//UPDATE DATE
            else if(dateTypeFlag == 3){
                stmt =  con.prepareCall("call USP_LEADREPORTCARD_UPDATEDATE(?, ?, ?)");
            }//PROFILING DATE
            else if(dateTypeFlag == 5){
                stmt =  con.prepareCall("call USP_LEADREPORTCARD_PROFILINGDATE(?, ?, ?)");
            }//CREATE DATE
            else {
                stmt =  con.prepareCall("call USP_LEADREPORTCARD(?, ?, ?)");
            }

            stmt.setString(1,id);
            stmt.setString(2,startdate);
            stmt.setString(3,enddate);
            logger.info("Executing {}",stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                LeadReportCardDao dao = new LeadReportCardDao();
                dao.setSeq(rs.getInt(1));
                dao.setDescription(rs.getString(2));
                dao.setCount(rs.getInt(3));

                list.add(dao);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
            throw  ex;
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        return list;
    }

    @Override
    public List<LeadReportCardDao> getBusiness(String id, String startdate, String enddate) throws SQLException {
        logger.info("Getting Prospective Business Report for id {} from {} to {}",id,startdate,enddate);
        Connection con = null;
        List<LeadReportCardDao> list = new ArrayList<>();
        try{

            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_LEADBUSINESS(?,?,?)");
            stmt.setString(1,id);
            stmt.setString(2,startdate);
            stmt.setString(3,enddate);
            logger.info("Executing {}",stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                LeadReportCardDao dao = new LeadReportCardDao();
                dao.setSeq(rs.getInt(1));
                dao.setCount(rs.getInt(3));
                dao.setDescription(rs.getString(4));

                list.add(dao);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
            throw  ex;
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        return list;
    }

    @Override
    public List<LeadReportCardDao> getLeadStats(String startdate, String enddate, boolean isFilter, String clientType,String id) throws SQLException {
        logger.info("Getting Lead Stats from {} to {}",startdate,enddate);
        Connection con = null;
        List<LeadReportCardDao> list = new ArrayList<>();
        try{

            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_LEADSTATS(?,?,?,?,?)");
            stmt.setString(1,startdate);
            stmt.setString(2,enddate);
            stmt.setString(3,id);
            stmt.setBoolean(4,isFilter);
            stmt.setString(5,clientType);
            logger.info("Executing {}",stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                LeadReportCardDao dao = new LeadReportCardDao();
                dao.setSeq(rs.getInt(1));
                dao.setDescription(rs.getString(2));
                dao.setCount(rs.getInt(3));
                dao.setConvertCount(rs.getInt(4));


                list.add(dao);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
            throw  ex;
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        return list;
    }

    @Override
    public List<LeadReportCardDao> getLeadStatsUser(String startdate, String enddate) throws SQLException {
        logger.info("Getting User Lead Stats from {} to {}",startdate,enddate);
        Connection con = null;
        List<LeadReportCardDao> list = new ArrayList<>();
        try{

            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_LEADSTATS_USER(?,?)");
            stmt.setString(1,startdate);
            stmt.setString(2,enddate);

            logger.info("Executing {}",stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                LeadReportCardDao dao = new LeadReportCardDao();
                dao.setSeq(rs.getInt(1));//userid
                dao.setDescription(rs.getString(2));//username
                dao.setCount(rs.getInt(3));//assigned total
                dao.setConvertCount(rs.getInt(4));//converted total


                list.add(dao);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
            throw  ex;
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        return list;
    }

    @Override
    public List<LeadReportCardDao> getLeadStatsDetailed(String startdate, String enddate, String flag,String dateflag,String clientTypeFlag) throws SQLException {
        logger.info("Getting Detailed Lead Stats from : {} to : {} for flag : {}",startdate,enddate);
        Connection con = null;
        List<LeadReportCardDao> list = new ArrayList<>();
        try{

            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_LEADSTATS_DETAILED(?,?,?,?,?)");
            stmt.setString(1,startdate);
            stmt.setString(2,enddate);
            stmt.setString(3,flag);
            stmt.setString(4,dateflag);
            stmt.setString(5,clientTypeFlag);

            logger.info("Executing {}",stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                LeadReportCardDao dao = new LeadReportCardDao();
                if(flag.equalsIgnoreCase("user")){
                    dao.setSeq(rs.getInt(1));//userid
                }
                dao.setDescription(rs.getString(2));//username/source/date
                dao.setAssignedCount(rs.getInt(3));
                dao.setCount(rs.getInt(4));//profiled total
                dao.setConvertCount(rs.getInt(5));//converted total | enrolled for Date (leaddateextended)
                dao.setRejectCount(rs.getInt(6));//rejected total | Assessment Done for Date (leaddateextended)
                dao.setPendingCount(rs.getInt(7));//pending total | Assessment Pending for date (leaddateextended)

                //only 'Lead Stats Date Wise' requires lead type
                if(flag.equalsIgnoreCase("date") && dateflag.equalsIgnoreCase("multidate")){
                       dao.setClientType(rs.getString(8));
                }
               list.add(dao);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
            throw  ex;
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        return list;
    }

    @Override
    public List<LeadReportCardDao> getLeadStatsAppointmentDate(String startdate, String enddate) throws SQLException {
        logger.info("Getting Lead Stats for appointment date from : {} to : {} ",startdate,enddate);
        Connection con = null;
        List<LeadReportCardDao> list = new ArrayList<>();
        try{

            String query = "SELECT SUBSTRING(Tbl_LeadMaster.clinicalCallTime,1,10),count(id) AS appointmentDate " +
                    "FROM Tbl_LeadMaster WHERE LENGTH(Tbl_LeadMaster.mbopsChildId)>1 AND " +
                    "SUBSTRING(REPLACE(Tbl_LeadMaster.clinicalCallTime,'-',''),1,8) >= startdate " +
                    " AND  SUBSTRING(REPLACE(Tbl_LeadMaster.clinicalCallTime,'-',''),1,8) <= enddate " +
                    " Group by SUBSTRING(Tbl_LeadMaster.clinicalCallTime,1,10) " +
                    " order by SUBSTRING(Tbl_LeadMaster.clinicalCallTime,1,10) desc;";

            query = query.replaceAll("startdate",startdate);
            query = query.replaceAll("enddate",enddate);

            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            logger.info("Executing {}",stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                LeadReportCardDao dao = new LeadReportCardDao();
                dao.setDescription(rs.getString(1));//appointment date (clinicalCallTime)
                dao.setCount(rs.getInt(2));//count

                list.add(dao);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
            throw  ex;
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        return list;
    }

    @Override
    public List<LeadReportCardDao> getTransferStats(String startdate, String enddate,String username) throws SQLException {
        logger.info("Getting Lead Transfer Stats for date from : {} to : {} and user : {}",startdate,enddate,username);
        Connection con = null;
        List<LeadReportCardDao> list = new ArrayList<>();
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("select STR_TO_DATE(SUBSTRING(Tbl_LeadTransfer.datetiming,1, 10),'%d-%m-%Y') as TransferDate,count(id) as Leads,createBy,fromusername,tousername,GROUP_CONCAT(Tbl_LeadTransfer.leadId SEPARATOR ',') from Tbl_LeadTransfer ");
            sb.append(" where ");
            sb.append(" STR_TO_DATE(SUBSTRING(Tbl_LeadTransfer.datetiming,1, 10),'%d-%m-%Y') >= STR_TO_DATE('startdate','%d-%m-%Y') ");
            sb.append(" and ");
            sb.append(" STR_TO_DATE(SUBSTRING(Tbl_LeadTransfer.datetiming,1, 10),'%d-%m-%Y') <= STR_TO_DATE('enddate','%d-%m-%Y') ");
            if(!username.equalsIgnoreCase("0")){
                 sb.append(" and createBy = 'createByVal' ");
            }
            sb.append(" group by STR_TO_DATE(SUBSTRING(Tbl_LeadTransfer.datetiming,1, 10),'%d-%m-%Y'),tousername,fromusername,createBy; ");

            String query = sb.toString();

            query = query.replaceAll("startdate",startdate);
            query = query.replaceAll("enddate",enddate);
            query = query.replaceAll("createByVal",username);

            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            logger.info("Executing {}",stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                LeadReportCardDao dao = new LeadReportCardDao();
                dao.setDescription(rs.getString(1));//transfer date
                dao.setCount(rs.getInt(2));//count
                dao.setTransferedBy(rs.getString(3));
                dao.setTransferedFrom(rs.getString(4));
                dao.setTransferedTo(rs.getString(5));
                dao.setLeadIds(rs.getString(6));
                list.add(dao);
            }


        }catch(SQLException ex){
            ex.printStackTrace();
            throw  ex;
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        return list;
    }
}
