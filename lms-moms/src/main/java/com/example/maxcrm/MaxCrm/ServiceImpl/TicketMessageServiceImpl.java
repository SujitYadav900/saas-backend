package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.TicketMessageDao;
import com.example.maxcrm.MaxCrm.Repo.TicketMessageRepo;
import com.example.maxcrm.MaxCrm.Service.TicketMessageService;
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
public class TicketMessageServiceImpl implements TicketMessageService {
    Logger logger = LoggerFactory.getLogger(TicketMessageServiceImpl.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    TicketMessageRepo repo;

    @Override
    public TicketMessageDao insert(TicketMessageDao dao) {
        logger.info("Saving Ticketmessagedao {}",dao);
        return repo.save(dao);
    }



    @Override
    public void delete(long id) {
        logger.info("deleting Ticketmessagedao with id {}",id);
        repo.deleteById(id);
    }

    @Override
    public Iterable<TicketMessageDao> findAll() {
        logger.info("finding all Ticketmessagedao ");
        return repo.findAll();
    }

    @Override
   public List<TicketMessageDao> findAllByTicketId(long id, int offset, int limit) {
        Connection con=null;
        List<TicketMessageDao> al =new ArrayList<>();
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("SELECT Tbl_Ticket_Message.id, Tbl_Ticket_Message.attachmentlist, Tbl_Ticket_Message.message, Tbl_Ticket_Message.senddate, Tbl_Ticket_Message.sender FROM Tbl_Ticket_Message where Tbl_Ticket_Message.ticketid=? order by id asc;");
            stmt.setLong(1,id);

            ResultSet rs=stmt.executeQuery();
            TicketMessageDao model=null;
            while (rs.next())
            {
                model=new TicketMessageDao();
                model.setId(rs.getLong(1));
                model.setAttachmentlist(rs.getString(2));
                model.setMessage(rs.getString(3));
                model.setSenddate(rs.getString(4));
                model.setSender(rs.getString(5));
                al.add(model);

            }
        }catch (SQLException sql)
        {
            sql.printStackTrace();

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return al;
    }



}
