package com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing;
/*
 Author=Supreet Singh
 Date= 16/03/21 12:24 PM
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class CampaingServiceImpl implements CampaingService {

    @Autowired
    private DataSource dataSource;
    @Autowired
    CampaingServiceRepo campaingServiceRepo;

    private static final Logger logger = LoggerFactory.getLogger(CampaingServiceImpl.class);

    @Override
    public CampaingDao insert(CampaingDao campaingDao) {
        return campaingServiceRepo.save(campaingDao);
    }

    @Override
    public List<CampaingDao> getAllByAccountId(long accountId) {
        return campaingServiceRepo.getAllByAccountIdOrderByScheduledTimeDesc(accountId);
    }

    @Override
    public int updateScheuledStatus(long campaignId) {
/*
        THIS METHOD WILL UPDATE SCHEDULED CAMPAIGN IN TBL_CAMPAIGN AND
        CAMPAIGN_SCHEDULED DOCUMENT BASIS ON CAMPAIGN ID*/
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update WSBUISSNES.Tbl_Campaign set iScheduled=0 where id=?;");
            stmt.setLong(1, campaignId);
            stmt.executeUpdate();
        } catch (Exception ew) {
            logger.info(" updateScheuledStatus Error Occurred  {}", ew.getMessage());
            ew.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception ew) {
                logger.info("updateScheuledStatus Error Occurred when connection closed {}", ew.getMessage());
                ew.printStackTrace();
            }

        }

        return 0;
    }
}
