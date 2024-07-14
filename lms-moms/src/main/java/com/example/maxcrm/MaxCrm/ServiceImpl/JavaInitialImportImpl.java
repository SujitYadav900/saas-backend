package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Service.JavaInitialImport;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
@Service
public  class JavaInitialImportImpl implements JavaInitialImport {
    @Autowired
    DataSource dataSource;
    Logger logger= LoggerFactory.getLogger(JavaInitialImport.class);
    @Override
    public void importSql(String sql) {
        Connection con=null;
        try{
            logger.info("Running MysqlScript At Startup");
            con=dataSource.getConnection();
            ScriptRunner scriptRunner=new ScriptRunner(con);
            Reader reader = new BufferedReader(new FileReader(sql));
            scriptRunner.runScript(reader);
            logger.info("Completed Script Execution Startup");

        }catch (Exception er)
        {
            logger.error("Failed To Execute Startup Script",er);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public void insertMongoDb(String sql) {

    }
}
