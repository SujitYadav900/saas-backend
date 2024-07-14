package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.UrlAccessDao;
import com.example.maxcrm.MaxCrm.Repo.UrlAccessRepository;
import com.example.maxcrm.MaxCrm.Service.UrlAccessService;
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
public class UrlAccessServiceImpl implements UrlAccessService {
    Logger logger = LoggerFactory.getLogger(UrlAccessService.class);
    @Autowired
    UrlAccessRepository urlAccessRepository;
    @Autowired
    DataSource dataSource;

    @Override
    public UrlAccessDao insert(UrlAccessDao urlAccessDao) throws Exception {
        logger.info("Inserting {}", urlAccessDao);

        try {
            urlAccessDao = urlAccessRepository.save(urlAccessDao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return urlAccessDao;
    }

    @Override
    public UrlAccessDao update(UrlAccessDao urlAccessDao) throws Exception {
        logger.info("Updating {}", urlAccessDao);
        try {
            urlAccessDao = urlAccessRepository.save(urlAccessDao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return urlAccessDao;
    }

    @Override
    public ArrayList<UrlAccessDao> findAll() throws SQLException {
        Connection con = null;
        ArrayList<UrlAccessDao> al = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("select Tbl_UrlAccess.id,Tbl_UrlAccess.indexSeq,Tbl_UrlAccess.pageDesc,Tbl_UrlAccess.pageIcon,Tbl_UrlAccess.pageName,Tbl_UrlAccess.roleName,Tbl_UrlAccess.showOnMenu,Tbl_UrlAccess.status,Tbl_UrlAccess.url,Tbl_Menu.menuName,Tbl_UrlAccess.menuId  from Tbl_UrlAccess inner join Tbl_Menu on Tbl_UrlAccess.menuId=Tbl_Menu.id");
            ResultSet rs = stmt.executeQuery();
            UrlAccessDao tempmode = null;
            while (rs.next()) {
                tempmode = new UrlAccessDao();
                tempmode.setId(rs.getInt(1));
                tempmode.setIndex(rs.getByte(2));
                tempmode.setPageDesc(rs.getString(3));
                tempmode.setPageIcon(rs.getString(4));
                tempmode.setPageName(rs.getString(5));
                tempmode.setRoleName(rs.getString(6));
                tempmode.setShowOnMenu(rs.getByte(7));
                tempmode.setStatus(rs.getByte(8));
                tempmode.setUrl(rs.getString(9));
                tempmode.setMenuName(rs.getString(10));
                tempmode.setMenuId(rs.getInt(11));
                al.add(tempmode);

            }
        } catch (SQLException sql) {
            logger.error("Error Occured {}", sql);
        } finally {
            con.close();
        }
        return al;
    }

    @Override
    public Iterable<UrlAccessDao> findAllActive() {
        return urlAccessRepository.getActive();
    }

    @Override
    public void delete(int id) {
        urlAccessRepository.deleteById(id);
    }

    @Override
    public List<UrlAccessDao> getMatchers() throws SQLException {
        Connection con = null;
        List<UrlAccessDao> list = new ArrayList<>();
        try{

            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("select Tbl_UrlAccess.url, Tbl_UrlAccess.roleName from Tbl_UrlAccess;");
            ResultSet rs = ps.executeQuery();
            UrlAccessDao dao;
            while (rs.next()){
                    dao = new UrlAccessDao();
                    dao.setUrl(rs.getString(1));
                    dao.setRoleName(rs.getString(2));

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
                throw  ex;
            }
        }
        return list;
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqpagename]")) {
            retmsg = "Page Name Already Exists!!";

        } else {
            retmsg = "Page Name Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
}
