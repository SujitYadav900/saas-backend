package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.FeaturesDao;
import com.example.maxcrm.MaxCrm.Repo.FeatureRepo;
import com.example.maxcrm.MaxCrm.Service.FeatureService;
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
import java.util.HashMap;
import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(FeatureServiceImpl.class);
    @Autowired
    FeatureRepo featureRepo;

    @Override
    public FeaturesDao insertFeatureDao(FeaturesDao featuresDao) {
        logger.debug("Inserting Feature {}", featuresDao);
        return featureRepo.save(featuresDao);
    }

    @Override
    public FeaturesDao updateFeatureDao(FeaturesDao featuresDao) {
        logger.debug("Updating Feature {}", featuresDao);
        return featureRepo.save(featuresDao);
    }

    @Override
    public Iterable<FeaturesDao> getAllFeature() {
        return featureRepo.findBy();
    }

    @Override
    public Iterable<FeaturesDao> getAllActive() {
        return featureRepo.findAllActive();
    }

    @Override
    public HashMap<String, String> getAssignedRole(String  role) {
        Connection con=null;
        HashMap<String, String> hashMap=new HashMap<>();
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT      Tbl_Features.name FROM     Tbl_FeatureAssign         INNER JOIN     Tbl_Features ON Tbl_FeatureAssign.featureId = Tbl_Features.id         INNER JOIN     Tbl_Role ON Tbl_FeatureAssign.roleId = Tbl_Role.id where roleName=?;");
            stmt.setString(1,role);
            ResultSet rs= stmt.executeQuery();
            String val;
           while (rs.next())
           {
               val=rs.getString(1);
               hashMap.put(val,val);
           }
        }catch (SQLException sql)
        {
            logger.error("Error Occured While getting data {}",sql);

        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return hashMap;
    }

    @Override
    public List<FeaturesDao> getRoleByRoleId(int role) {
        Connection con=null;
        List<FeaturesDao> al=new ArrayList<>();
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT      Tbl_Features.name,     Tbl_Features.id,        Tbl_Features.descri, IFNULL(Tbl_FeatureAssign.id, - 1) as refId FROM     Tbl_Features         LEFT JOIN     Tbl_FeatureAssign ON Tbl_Features.id = Tbl_FeatureAssign.featureId     and Tbl_FeatureAssign.roleId=? WHERE     Tbl_Features.status = 1 ORDER BY Tbl_Features.id DESC;");
            stmt.setInt(1,role);
            logger.debug("Running RoleByQuery {}",stmt);
            ResultSet rs= stmt.executeQuery();
            FeaturesDao featuresDao;
            while (rs.next())
            {
                featuresDao=new FeaturesDao();
                featuresDao.setName(rs.getString(1));
                featuresDao.setId(rs.getInt(2));
                featuresDao.setDescri(rs.getString(3));
                featuresDao.setRefId(rs.getInt(4));
                al.add(featuresDao);

            }
        }catch (SQLException sql)
        {
            logger.error("Error Occured While getting data {}",sql);

        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return al;
    }
}
