package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LogEventDao;

import java.util.List;

public interface LogEventService {
    Iterable<LogEventDao> findAllByLeadId(long leadId);
    LogEventDao insert(LogEventDao logEventDao);
    int insertMany(List<LogEventDao> eventList);
    LogEventDao update(LogEventDao logEventDao);
    void delete(long id,String createBy,long leadId);


    Iterable<LogEventDao> getlogEventByeventTypeAnduserId(String eventType,  int userId);
}
