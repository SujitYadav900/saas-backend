package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.ProductUserDao;
import com.example.maxcrm.MaxCrm.Repo.ProductUserRepository;
import com.example.maxcrm.MaxCrm.Service.ProductUserService;
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


@Service
public class ProductUserServiceImpl implements ProductUserService {

    Logger logger = LoggerFactory.getLogger(ProductUserServiceImpl.class);
    @Autowired
    ProductUserRepository repo;

    @Autowired
    DataSource dataSource;

    @Override
    public Iterable<ProductUserDao> getAll() {


        logger.info("Getting all Product Users");
        return repo.findAll();
    }

    @Override
    public ProductUserDao insert(ProductUserDao productUserDao)throws Exception {
        logger.info("Insertig Product User {}",productUserDao);
        try{
            productUserDao = repo.save(productUserDao);
        }catch (Exception ex){
            logger.error("Error Occured!! {}", ex);
            String message = handleException(ex.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return productUserDao;
    }

    @Override
    public void delete(long id) {
        logger.info("deletig product user by id {}",id);
        repo.deleteById(id);
    }


    public ArrayList<ProductUserDao> getAllByProductId(long product_id) throws SQLException {

        logger.info("getting all product user by id {}",product_id);
        Connection con=null;
        ArrayList<ProductUserDao> list = null;
        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT Tbl_Product_user.id, Tbl_UserMaster.username FROM Tbl_Product_user inner join Tbl_UserMaster on  Tbl_Product_user.user_id = Tbl_UserMaster.id where Tbl_Product_user.product_id="+product_id);
            ResultSet rs  = stmt.executeQuery();
            list = new ArrayList<ProductUserDao>();
           while(rs.next()){
               ProductUserDao user = new ProductUserDao();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                list.add(user);
           }


        }catch (SQLException sql)
        {
            sql.printStackTrace();
            logger.error("Error Occured!! {}", sql);
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Error Occured!! {}", e);
            }
        }
        return list;
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqproductuser]")) {
            retmsg = "User Already Exists in the List!!";

        } else {
            retmsg = "User Can Not Be Added!!Please Try Again Later";
        }
        return retmsg;
    }
}
