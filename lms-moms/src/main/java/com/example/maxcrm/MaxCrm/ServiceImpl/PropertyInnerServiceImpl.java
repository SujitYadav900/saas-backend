package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.PropertyInnerDao;
import com.example.maxcrm.MaxCrm.Repo.PropertyInnerRepository;
import com.example.maxcrm.MaxCrm.Service.PropertyInnerService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PropertyInnerServiceImpl extends PropertyCls implements PropertyInnerService {

    private final Logger logger = LoggerFactory.getLogger(PropertyInnerServiceImpl.class);

    @Autowired
    private PropertyInnerRepository repository;

    @Autowired
    private DataSource dataSource;

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqproperty]")) {
            retmsg = "Property Already Exists!!";

        } else {
            retmsg = "Property Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }

    @Override
    public PropertyInnerDao insert(PropertyInnerDao dao) throws Exception {
        logger.info("inserting PropertyInnerDao {}", dao.toString());

        if (dao.getName().contains(",||") || (dao.getValue().contains(",||,"))) {
            throw new Exception("Please Remove Illegal characterset ',||,'");
        }
        try {
            System.out.println("Property Dao"+ dao);
            dao = repository.save(dao);
            refreshProperty();
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public PropertyInnerDao update(PropertyInnerDao dao) throws Exception {
        logger.info("updating PropertyInnerDao {}", dao.toString());
        if (dao.getName().contains(",||") || (dao.getValue().contains(",||"))) {
            throw new Exception("Please Remove Illegal characterset ',||'");
        }
        try {
            dao = repository.save(dao);
            refreshProperty();
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public void delete(long id) {
        logger.info("deleting PropertyInnerDaob by id {}", id);
        repository.deleteById(id);
        try {
            refreshProperty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PropertyInnerDao> getAll(String property) throws SQLException {
        logger.info("finding all PropertyInnerDao ");

        Connection con = null;
        List<PropertyInnerDao> list = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("select Tbl_Property_Inner.id, Tbl_Property_Inner.name,Tbl_Property.type,Tbl_Property_Inner.value,Tbl_Property_Inner.propertyId from Tbl_Property_Inner inner join Tbl_Property on Tbl_Property_Inner.propertyId = Tbl_Property.id where Tbl_Property.type=? order by Tbl_Property_Inner.id desc;");
            stmt.setString(1, property);
            ResultSet rs = stmt.executeQuery();
            PropertyInnerDao dao;
            while (rs.next()) {
                dao = new PropertyInnerDao();
                dao.setId(rs.getLong(1));
                dao.setName(rs.getString(2));
                dao.setPropertyType(rs.getString(3));
                dao.setValue(rs.getString(4));
                dao.setPropertyId(rs.getLong(5));

                list.add(dao);
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
            throw exc;
        } finally {
            try {
                con.close();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw exc;
            }
        }

        return list;


    }

    @Override
    public PropertyInnerDao findPropetyInnerById(long id) {
        logger.info("finding PropertyInnerDao by id {} ", id);
        return repository.findPropetyInnerById(id);
    }

    @Override
    public PropertyInnerDao findPropetyInnerByName(String name) {
        logger.info("finding PropertyInnerDao by name {} ", name);
        return repository.findPropetyInnerByName(name);
    }

    @Override
    public Iterable<PropertyInnerDao> findAllByPropertyType(String type) {
        logger.info("finding PropertyInnerDao by property name {} ", type);
        return repository.findbyPropertyType(type);
    }

    private List<PropertyInnerDao> convertStringToList(String property, String value) {
        String[] proarr = property.split("\\,\\|\\|\\,|\\,\\|\\|");// split at ,||, and '||

        String[] valuearr = value.split("\\,\\|\\|\\,|\\,\\|\\|");

        List<PropertyInnerDao> al = new ArrayList<>();
        PropertyInnerDao propertyInnerDao;
        for (int i = 0; i < proarr.length; i++) {
            propertyInnerDao = new PropertyInnerDao();
            propertyInnerDao.setName(proarr[i].trim());
            propertyInnerDao.setValue(valuearr[i].trim());
            al.add(propertyInnerDao);
        }
        return al;
    }

    @Override
    public void refreshProperty() throws SQLException {

        Connection con = null;
        HashMap<String, String> hashMap = new HashMap<>();


        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Tbl_Property.type, Tbl_Property_Inner.name,Tbl_Property_Inner.value FROM Tbl_Property_Inner left JOIN Tbl_Property ON propertyId = Tbl_Property.id");
            ResultSet rs = ps.executeQuery();


            String type;
            String property;
            String value;
            List<PropertyInnerDao> al;
            while (rs.next()) {
                type = rs.getString(1);
                property = rs.getString(2);
                value = rs.getString(3);
                property = type + "_" + property;
                hashMap.put(property, value);
            }

            propertieslist = hashMap;
            UtilityClass.ApplicationTitle = propertieslist.get("Application_" + "ApplicationTitle");
            UtilityClass.logoUrl = propertieslist.get("Application_" + "logoUrl");
            UtilityClass.minLogoUrl = propertieslist.get("Application_" + "minLogoUrl");
            UtilityClass.startYear = propertieslist.get("Application_" + "startYear");
            UtilityClass.endYear = propertieslist.get("Application_" + "endYear");
            UtilityClass.copyRightMsg = propertieslist.get("Application_" + "copyRightMsg");
            UtilityClass.footerMsg = propertieslist.get("Application_" + "footerMsg");
            UtilityClass.ApplicationPrefix = propertieslist.get("Application_" + "ApplicationPrefix");
            UtilityClass.siteUrl = propertieslist.get("Application_" + "siteUrl");


        } catch (SQLException exc) {
            exc.printStackTrace();
            throw exc;
        } finally {
            try {
                con.close();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw exc;
            }
        }


    }
}
