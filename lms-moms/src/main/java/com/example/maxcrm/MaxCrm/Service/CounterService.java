package com.example.maxcrm.MaxCrm.Service;

import java.sql.SQLException;

public interface CounterService {
     long  getCounter(int id,int increase) throws SQLException;
}
