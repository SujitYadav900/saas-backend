package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Repo.TicketRepo;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.example.maxcrm.MaxCrm.Dao.PaginationDao;
import com.example.maxcrm.MaxCrm.Dao.TicketDao;
import com.example.maxcrm.MaxCrm.Dao.TicketForwardLogDao;
import com.example.maxcrm.MaxCrm.Dao.TicketUpdateLogDao;
import com.example.maxcrm.MaxCrm.Service.*;
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
public class TicketServiceImpl extends PaginationClass implements TicketService {

    Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);


    @Autowired
    TicketRepo repo;

    @Autowired
    DataSource dataSource;
    @Autowired
    TicketForwardLogService ticketForwardLogService;

    @Autowired
    TicketUpdateLogService ticketUpdateLogService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    LeadMasterService leadMasterService;

    @Override
    public TicketDao insert(TicketDao dao) {
        logger.info("saving TicketDao {}", dao);
        if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnLeadTransfer").equalsIgnoreCase("1")) {
            leadMasterService.updateLastUpdateLead(null, dao.getCreatedby(), dao.getDatefilter(), dao.getCreatedate(), dao.getLeadid());
        }
        return repo.save(dao);
    }

    @Override
    public TicketDao update(TicketDao dao) {

        logger.info("updating TicketDao {}", dao);
        return repo.save(dao);
    }

    @Override
    public void delete(long id) {
        repo.deleteById(id);
    }

    @Override
    public Iterable<TicketDao> getAll() {
        logger.info("finding all TicketDao");
        return repo.findAll();
    }


    @Override
    public PaginationDao findAllTicket(String wherequery, int offset, int limit) throws SQLException {
        PaginationDao paginationDao = new PaginationDao();
        String countQuery = "SELECT count(`Tbl_Ticket`.`id`) FROM `Tbl_Ticket` inner join Tbl_UserMaster on Tbl_UserMaster.id=Tbl_Ticket.lastforward wherequery;";
        String normalQuery = "SELECT `Tbl_Ticket`.`id`, `Tbl_Ticket`.`attachmentlist`, `Tbl_Ticket`.`createdate`, `Tbl_Ticket`.`createdby`,  `Tbl_Ticket`.`department`, `Tbl_Ticket`.`description`, `Tbl_Ticket`.`lastforward`, `Tbl_Ticket`.`leadid`, `Tbl_Ticket`.`priority`, `Tbl_Ticket`.`subject`, `Tbl_Ticket`.`ticketstatus`, `Tbl_Ticket`.`type`, `Tbl_Ticket`.`updateby`, `Tbl_Ticket`.`updatedate`, `Tbl_Ticket`.`closeDate`, `Tbl_Ticket`.`closeTimeTaked`,  `Tbl_Ticket`.`isClose`, Tbl_UserMaster.username FROM `Tbl_Ticket` inner join Tbl_UserMaster on Tbl_Ticket.lastforward= Tbl_UserMaster.id wherequery order by id desc limit ?,?;";
        countQuery = countQuery.replace("wherequery", wherequery);
        normalQuery = normalQuery.replace("wherequery", wherequery);
        logger.info("Running Query {}", normalQuery);
        List<Object> al = new ArrayList<>();
        Connection con = null;
        try {

            con = dataSource.getConnection();
            int count = findCount(countQuery, con);
            paginationDao.setRecordsFiltered(count);
            paginationDao.setRecordsTotal(count);
            if (count == 0) {
                paginationDao.setData(al);
                return paginationDao;
            }
            PreparedStatement stmt = con.prepareStatement(normalQuery);
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();

            TicketDao ticketDao = null;
            while (rs.next()) {
                ticketDao = new TicketDao();
                ticketDao.setId(rs.getLong(1));
                ticketDao.setAttachmentlist(rs.getString(2));
                ticketDao.setCreatedate(rs.getString(3));
                ticketDao.setCreatedby(rs.getString(4));
                ticketDao.setDepartment(rs.getString(5));
                ticketDao.setDescription(rs.getString(6));
                ticketDao.setLastforward(rs.getInt(7));
                ticketDao.setLeadid(rs.getLong(8));
                ticketDao.setPriority(rs.getString(9));
                ticketDao.setSubject(rs.getString(10));
                ticketDao.setTicketstatus(rs.getString(11));
                ticketDao.setType(rs.getString(12));
                ticketDao.setUpdateby(rs.getString(13));
                ticketDao.setUpdatedate(rs.getString(14));
                ticketDao.setCloseDate(rs.getString(15));
                ticketDao.setCloseTimeTaked(rs.getLong(16));
                ticketDao.setClose(rs.getBoolean(17));
                ticketDao.setUsername(rs.getString(18));
                al.add(ticketDao);
            }
            paginationDao.setData(al);

        } catch (SQLException sql) {
            throw sql;
        } finally {
            con.close();
        }
        return paginationDao;
    }


    @Override
    public void changeStatus(String ticketSatus, String createDate, long id) throws SQLException {

        logger.info("chaning status of ticket with id {} to {}", id, ticketSatus);
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("update Tbl_Ticket set ticketstatus=? where id=?");
            ps.setString(1, ticketSatus);
            ps.setLong(2, id);
            ps.executeUpdate();
            TicketUpdateLogDao ticketUpdateLogDao = new TicketUpdateLogDao();
            ticketUpdateLogDao.setTimestamp(UtilityClass.getDateRedable());
            ticketUpdateLogDao.setTicketid(id);
            ticketUpdateLogDao.setTostatus(ticketSatus);
            ticketUpdateLogDao.setDatefilter(UtilityClass.dateFilterDate());
            ticketUpdateLogService.insert(ticketUpdateLogDao);
            String closestatus = propertyService.findProperty("Ticket", "TicketCloseStatusName");
            if (ticketSatus.equalsIgnoreCase(closestatus)) {
                String closepriorityvalue = propertyService.findProperty("Ticket", "TicketClosedPriorityValue");
                long mins = UtilityClass.getDateDifferenceInMinutes(createDate, UtilityClass.getDateRedable());
                closeTicket(id, con, mins, closestatus, closepriorityvalue);
            }

        } catch (SQLException sql) {
            sql.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }

    }

    @Override
    public void changePriority(String priority, long id) throws SQLException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update Tbl_Ticket set Tbl_Ticket.priority=? where id=?;");
            stmt.setString(1, priority);
            stmt.setLong(2, id);
            stmt.executeUpdate();
            logger.info("Changing Priority of ticket {} to {}", id, priority);
        } catch (SQLException sql) {
            throw sql;
        } finally {
            con.close();
        }
    }

    private void closeTicket(long id, Connection con, long mins, String closests, String closeprio) {
        try {
            PreparedStatement stmt = con.prepareStatement("update Tbl_Ticket set datefilterClose=?,closeDate=?,isClose=?,closeTimeTaked=?,ticketstatus =?,priority=?,closeDateFilter=? where id=?;");
            stmt.setInt(1, UtilityClass.dateFilterDate());
            stmt.setString(2, UtilityClass.getDateRedable());
            stmt.setBoolean(3, true);
            stmt.setLong(4, mins);
            stmt.setString(5, closests);
            stmt.setString(6, closeprio);
            stmt.setInt(7, UtilityClass.dateFilterDate());
            stmt.setLong(8, id);
            stmt.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();

        }
    }

    @Override
    public void forwardTicket(long id, int lastForward, String createBy, String toAgent) throws SQLException {

        logger.info("forwarding ticket with id {} to {}", id, lastForward);
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("update Tbl_Ticket set  lastforward=?  where id=?");

            ps.setInt(1, lastForward);
            ps.setLong(2, id);
            ps.executeUpdate();
            TicketForwardLogDao ticketForwardLogDao = new TicketForwardLogDao();
            ticketForwardLogDao.setTimestamp(UtilityClass.getDateRedable());

            ticketForwardLogDao.setCreateBy(createBy);
            ticketForwardLogDao.setToagent(toAgent);
            ticketForwardLogDao.setTicketid(id);
            ticketForwardLogDao.setDatefilter(UtilityClass.dateFilterDate());

            ticketForwardLogService.insert(ticketForwardLogDao);
        } catch (SQLException sql) {
            sql.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }

    }

    @Override
    public void updateTicketDate(Connection con, long id, String createBy, int dateFilter, String date) {
        boolean defaultConnection = false;
        try {
            if (con == null) {
                con = dataSource.getConnection();
                defaultConnection = true;
            }
            PreparedStatement stmt = con.prepareStatement("update Tbl_Ticket set updateby=?,updatedate=?,updatedatefilter=? where id=?;");
            stmt.setString(1, createBy);
            stmt.setString(2, date);
            stmt.setInt(3, dateFilter);
            stmt.setLong(4, id);
            stmt.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();

        } finally {
            try {
                if (defaultConnection) {

                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
