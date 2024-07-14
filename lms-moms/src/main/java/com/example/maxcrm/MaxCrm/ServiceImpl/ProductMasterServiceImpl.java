package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.ProductMasterDao;
import com.example.maxcrm.MaxCrm.Repo.ProductMasterRepo;
import com.example.maxcrm.MaxCrm.Service.ProductMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {

    Logger logger = LoggerFactory.getLogger(ProductMasterServiceImpl.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    ProductMasterRepo service;

    @Override
    public Iterable<ProductMasterDao> findAll() {
        logger.info("Finding all");
        return service.findAll();
    }

    @Override
    public Iterable<ProductMasterDao> findAllAvailable() {
        logger.info("Finding all available ");
        return service.findAllAvailable();
    }

    @Override
    public ProductMasterDao insert(ProductMasterDao productMasterDao) throws Exception {
        logger.info("inserting {}",productMasterDao);
        try {
            productMasterDao = service.save(productMasterDao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return productMasterDao;
    }

    @Override
    public ProductMasterDao update(ProductMasterDao productMasterDao) throws Exception {
        logger.info("updating {}",productMasterDao);
        try {
            productMasterDao = service.save(productMasterDao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return productMasterDao;
    }

    @Override
    public int findRandomUser(String product) {
        Connection con=null;
        int userId=0;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select Tbl_Product_user.user_id from Tbl_Product_user inner join Tbl_ProductMaster on Tbl_Product_user.product_id=Tbl_ProductMaster.id inner join Tbl_UserMaster on Tbl_Product_user.user_id=Tbl_UserMaster.id where Tbl_ProductMaster.name=? and Tbl_UserMaster.status='Active' order by rand() limit 1;");
            stmt.setString(1,product);
            ResultSet rs=stmt.executeQuery();
            if(rs.next())
            {
                userId=rs.getInt(1);
            }
        }catch (SQLException sql)
        {

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userId;
    }

    @Override
    public void delete(int id) {
        logger.info("deleting by id {}",id);
            service.deleteById(id);
    }

    @Override
    public ProductMasterDao findByName(String productName) {
       return service.findByName(productName);
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqproduct]")) {
            retmsg = "Product Already Exists!!";

        }else  if (message.contains("[unqproductcode]")) {
            retmsg = "Product Code Already Exists!!";

        } else {
            retmsg = "Product Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
}



