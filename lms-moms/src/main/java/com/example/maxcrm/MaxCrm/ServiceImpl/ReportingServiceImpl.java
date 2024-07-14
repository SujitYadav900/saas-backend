package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.ReportingDao;
import com.example.maxcrm.MaxCrm.Repo.ReportingRepository;
import com.example.maxcrm.MaxCrm.Service.ReportingService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportingServiceImpl implements ReportingService {
    @Autowired
    DataSource dataSource;
    Logger logger = LoggerFactory.getLogger(ReportingServiceImpl.class);
    @Autowired
    ReportingRepository reportingRepository;


    @Override
    public Iterable<ReportingDao> findByUserId(int userid) {
        logger.info("Finding By UserId {}", userid);
        return reportingRepository.getReportTo(userid);
    }

    @Override
    public ReportingDao insert(ReportingDao dao) {
        logger.info("Inserting {}", dao);
        return reportingRepository.save(dao);
    }

    @Override
    public ReportingDao update(ReportingDao dao) {
        logger.info("Updating {}", dao);
        return reportingRepository.save(dao);
    }

    @Override
    public void delete(long id) {
        logger.info("Deleting {}", id);
        reportingRepository.deleteById(id);
    }

    @Override
    public void deleteAndInsertByUserId(int userId, List<ReportingDao> al) throws SQLException {

        logger.info("Deleting ByUserId {} and inserting {}", userId, al);
        Connection con = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM `Tbl_Reporting` WHERE userId=?;");
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            stmt = con.prepareStatement("INSERT ignore INTO `Tbl_Reporting` ( `reportTo`, `reportToName`, `userId`) VALUES ( ?, ?, ?)");
            for (int i = 0; i < al.size(); i++) {
                stmt.setInt(1, al.get(i).getReportTo());
                stmt.setString(2, al.get(i).getReportToName());
                stmt.setInt(3, al.get(i).getUserId());
                stmt.addBatch();
            }

            stmt.executeBatch();
            con.commit();

        } catch (SQLException sql) {
            logger.error("Error Occured {}", sql);
            con.rollback();
            throw sql;

        } finally {
            con.close();
        }

    }

    @Override
    public void updateUserIdReporting() {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("select ar.reportTo,concat(ar.reportTo,\",\",ar.reporttolist) from ( SELECT Tbl_Reporting.reportTo, CONCAT(GROUP_CONCAT(userid), ',', Tbl_Reporting.reportTo) as reporttolist FROM Tbl_Reporting INNER JOIN Tbl_UserMaster ON Tbl_Reporting.userId = Tbl_UserMaster.id WHERE Tbl_UserMaster.status = 'active' GROUP BY Tbl_Reporting.reportTo)ar ;");

            ResultSet rs = stmt.executeQuery();
            UtilityClass.userlist = new HashMap<>();
            while (rs.next()) {
                UtilityClass.userlist.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<ReportingDao> findByReportTo(int reportTo) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("select Tbl_UserMaster.username,Tbl_UserMaster.id from Tbl_Reporting inner join Tbl_UserMaster  on Tbl_Reporting.userId=Tbl_UserMaster.id  where Tbl_Reporting.reportTo=?; ");
            stmt.setInt(1, reportTo);

        } catch (SQLException sql) {

        } finally {

        }
        return null;
    }
}
