package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.DashboardPermissionDao;
import com.example.maxcrm.MaxCrm.Repo.DashboardPermissionRepository;
import com.example.maxcrm.MaxCrm.Service.DashboardPermissionService;
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
public class DashboardPermissionServiceImpl implements DashboardPermissionService {

    private final Logger logger = LoggerFactory.getLogger(DashboardPermissionServiceImpl.class);

    @Autowired
    private DashboardPermissionRepository repo;

    @Autowired
    private DataSource dataSource;

    @Override
    public DashboardPermissionDao insert(DashboardPermissionDao dao) {
        logger.info("Inserting DashboardPermissionDao {}",dao.toString());
        return repo.save(dao);
    }

    @Override
    public void delete(long id) {
        logger.info("deleting DashboardPermissionDao with id {}",id);
        repo.deleteById(id);
    }

    @Override
    public List<DashboardPermissionDao> findAllByRoleId(long roleId) {
        logger.info("finding all DashboardPermissionDao with roleId {}",roleId);
        return repo.findAllByRoleId(roleId);
    }

    @Override
    public List<DashboardPermissionDao> findAllByRoleName(String roleName) {

        Connection con = null;
        List<DashboardPermissionDao> list = new ArrayList<>();

        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("select url,TBP.id,dashBoardName,seq,description,TBP.roleId from Tbl_Dashboard_Permission TBP inner join Tbl_Role on TBP.roleId = Tbl_Role.id where roleName =? order by seq asc");
            stmt.setString(1,roleName);
            logger.info("finding dashboard permission with role o: "+roleName);
            ResultSet rs = stmt.executeQuery();
            DashboardPermissionDao dao;
            while(rs.next()){
                dao = new DashboardPermissionDao();
                dao.setUrl(rs.getString(1));
                dao.setId(rs.getLong(2));
                dao.setDashboardName(rs.getString(3));
                dao.setSeq(rs.getInt(4));
                dao.setDescription(rs.getString(5));
                dao.setRoleId(rs.getLong(6));
                list.add(dao);
            }

        }catch (SQLException exc){
            exc.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception exc){
            }
        }

        return list;
    }
}
