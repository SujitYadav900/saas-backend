package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadStatusTransferDao;
import com.example.maxcrm.MaxCrm.Repo.LeadStatusTransferRepo;
import com.example.maxcrm.MaxCrm.Service.LeadStatusTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LeadStatusTransferServiceImpl implements LeadStatusTransferService {
    @Autowired
    LeadStatusTransferRepo leadStatusTransferRepo;
    @Autowired
    DataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Iterable<LeadStatusTransferDao> findAllByLeadId(long id) {

        return leadStatusTransferRepo.findAllByLeadId(id);
    }

    @Override
    public LeadStatusTransferDao insert(LeadStatusTransferDao dao) throws SQLException {
        logger.info("Inserting LeadStatusTransferDao ::  {}",dao);
        Connection con=null;
        try{

           con=dataSource.getConnection();
            CallableStatement stmt=con.prepareCall("call USP_STATUSCHANGEINSORUP(?,?,?,?,?,?,?,?)");
            stmt.setLong(1,dao.getLeadId());
            stmt.setString(2,dao.getStatus());
            stmt.setString(3,dao.getStage());
            stmt.setString(4,dao.getPreStatus());
            stmt.setString(5,dao.getPreStage());
            stmt.setString(6,dao.getCreateBy());
            stmt.setString(7,dao.getReadableDate());
            stmt.setLong(8,dao.getTiming());
            System.out.println(stmt);
            ResultSet rs=stmt.executeQuery();
            if (rs.next())
            {

                dao.setId(rs.getLong(1));
            }
        }catch (SQLException sql)
        {
            sql.printStackTrace();
            throw sql;

        }finally {
            con.close();
        }
        logger.info("USP_STATUSCHANGEINSORUP Response :: {}",dao);

        return dao;
    }

    @Override
    public void bulkInsert(List<LeadStatusTransferDao> list) throws SQLException {
        leadStatusTransferRepo.saveAll(list);
    }



    public void bulkInsert1(List<LeadStatusTransferDao> list) throws SQLException {

        Connection con = null;

        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);

            CallableStatement stmt = con.prepareCall("call USP_STATUSCHANGEINSORUP(?,?,?,?,?,?,?,?)");

            for(LeadStatusTransferDao dao : list){
                stmt.setLong(1,dao.getLeadId());
                stmt.setString(2,dao.getStatus());
                stmt.setString(3,dao.getStage());
                stmt.setString(4,dao.getPreStatus());
                stmt.setString(5,dao.getPreStage());
                stmt.setString(6,dao.getCreateBy());
                stmt.setString(7,dao.getReadableDate());
                stmt.setLong(8,dao.getTiming());
                logger.info("Inserting batch :: {}",stmt);
                stmt.addBatch();
            }

            int [] updatesCount=stmt.executeBatch();

            //manually commit
            con.commit();

            logger.info("Bacth LeadStatusTransferDao insert count :: {}",updatesCount);
        } catch (SQLException sql) {
            con.rollback();
            throw sql;
        } finally {
            con.close();
        }

    }


    @Override
    public LeadStatusTransferDao update(LeadStatusTransferDao dao) {
        return leadStatusTransferRepo.save(dao);
    }
}
