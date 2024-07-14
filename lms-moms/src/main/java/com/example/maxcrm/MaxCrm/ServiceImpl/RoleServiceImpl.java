package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.RoleDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Repo.RoleRepository;
import com.example.maxcrm.MaxCrm.Service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class RoleServiceImpl implements RoleService {

    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DataSource dataSource;

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqrolename]")) {
            retmsg = "Role Name Already Exists!!";

        } else {
            retmsg = "Role Name Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }

    @Override
    public RoleDao insertRole(RoleDao dao) throws Exception {
        logger.info("Updating {}", dao);
        try {
            dao = roleRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public RoleDao updateRole(RoleDao dao) throws Exception {
        logger.info("Updating {}", dao);
        try {
            dao = roleRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public Iterable<RoleDao> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Collection<GrantedAuthority> getAllRolesByDepartment(String dep) {
        Connection con = null;
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT Tbl_UrlAccess.roleName FROM Tbl_RolePage inner join Tbl_UrlAccess on Tbl_RolePage.urlId=Tbl_UrlAccess.id WHERE Tbl_RolePage.roleId = (SELECT Tbl_Role.id FROM Tbl_Role WHERE roleName = ?);");
            stmt.setString(1, dep);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                grantedAuthority = new SimpleGrantedAuthority(rs.getString(1));
                grantedAuthorities.add(grantedAuthority);
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

        return grantedAuthorities;
    }

    @Override
    public void deleteRole(int id) {
        logger.info("Deleting Id {}", id);
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDao findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public HashMap<String, Boolean> getAllRolesByDepartmentAfterLogin(String department) {
        Connection con=null;
        HashMap<String, Boolean> map=new HashMap<>();
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select Tbl_UrlAccess.roleName from Tbl_UrlAccess inner join Tbl_RolePage on Tbl_UrlAccess.id=Tbl_RolePage.urlId inner join Tbl_Role on Tbl_Role.id=Tbl_RolePage.roleId where Tbl_Role.roleName=?;");
            stmt.setString(1,department);
            ResultSet rs=stmt.executeQuery();
            while (rs.next())
            {
                map.put(rs.getString(1),true);
            }
        }catch (SQLException sql)
        {
            sql.printStackTrace();

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public UserMasterDao findRandomUser(String department) throws SQLException {
        Connection con = null;
        UserMasterDao userMasterDao=null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("select Tbl_UserMaster.id,Tbl_UserMaster.username from Tbl_UserMaster where Tbl_UserMaster.department=? and Tbl_UserMaster.status='active' ORDER BY RAND() limit 1;");
            stmt.setString(1, department);
           ResultSet rs= stmt.executeQuery();

           while (rs.next())
           {
             userMasterDao=new UserMasterDao();
             userMasterDao.setId(rs.getInt(1));
             userMasterDao.setUsername(rs.getString(2));
           }

        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
        con.close();
        }
        return userMasterDao;
    }

    @Override
    public HashMap<Integer, String> getFeatures(int roleId) {
        Connection con=null;
        HashMap<Integer, String> hashMap=new HashMap<>();
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select Tbl_Features.name,Tbl_Features.id from Tbl_Features inner join Tbl_FeatureAssign on Tbl_Features.id=Tbl_FeatureAssign.featureId where Tbl_FeatureAssign.roleId=?;");
            stmt.setInt(1,roleId);
            ResultSet rs= stmt.executeQuery();
            while (rs.next())
            {
                hashMap.put(rs.getInt(2),rs.getString(1));
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
}
