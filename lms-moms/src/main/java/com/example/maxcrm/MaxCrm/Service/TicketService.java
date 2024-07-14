package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.PaginationDao;
import com.example.maxcrm.MaxCrm.Dao.TicketDao;

import java.sql.Connection;
import java.sql.SQLException;

public interface TicketService {

    TicketDao insert(TicketDao dao);
    TicketDao update(TicketDao dao);
    void delete(long id);
    Iterable<TicketDao> getAll();

    PaginationDao findAllTicket(String wherequery,int offset,int limit) throws SQLException;


    void changeStatus(String ticketSatus,String createDate, long id) throws SQLException;
    void changePriority(String priority, long id) throws SQLException;
    void forwardTicket(long id, int lastForward,String fromAgent,String toAgent) throws SQLException;
    void updateTicketDate(Connection con, long id, String createBy, int dateFilter, String date);


}
