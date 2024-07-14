package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    DataSource dataSource;
    @Override
    public long getCounter(int id, int increase) throws SQLException {
        Connection con=null;
        long countervalue=0;
        try{
            con=dataSource.getConnection();
            CallableStatement stmt=con.prepareCall("CALL `USP_GETCOUNTER`(?,?);");
            stmt.setInt(1,id);
            stmt.setInt(2,increase);
            System.out.println(stmt);
           ResultSet rs= stmt.executeQuery();
           if(rs.next())
           {
               countervalue=rs.getLong(1);
           }
        }catch (SQLException sql)
        {
            throw sql;

        }finally {
            con.close();
        }
        if(countervalue==0)
        {
            throw new SQLException("Counter Value Cannot Be Zero!!");
        }
        return countervalue;
    }
}
