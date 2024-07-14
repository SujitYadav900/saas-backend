package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.DashboardDao;
import com.example.maxcrm.MaxCrm.Service.DashboardReporting;
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
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardReporting {

    private final Logger logger=LoggerFactory.getLogger(DashboardServiceImpl.class);
    @Autowired
    DataSource dataSource;

    private static String loadQuery(String type) throws Exception
    {
        System.out.println("DashboardServiceImpl >>>>> "+type);
        switch (type){
            case "leadcount":
                return "SELECT 1 as seq, 'Total Lead' as name, count(Tbl_LeadMaster.id) as counting FROM Tbl_LeadMaster WHERE FIND_IN_SET(Tbl_LeadMaster.lastForward, 'userfilter') and Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 union all SELECT 2 as seq, 'Total Lead Conversion' as name, count(Tbl_LeadMaster.id) as counting FROM Tbl_LeadMaster WHERE find_in_set(Tbl_LeadMaster.lastForward,'userfilter') and Tbl_LeadMaster.convertDateFilter >= 20190101 AND Tbl_LeadMaster.convertDateFilter <= 20201010 ";
            case "leadstagegraph":
                return "SELECT 3 as seq, Tbl_LeadMaster.leadStage, count(Tbl_LeadMaster.id) FROM Tbl_LeadMaster WHERE find_in_set(Tbl_LeadMaster.lastForward,'userfilter') and Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 group by Tbl_LeadMaster.leadStage ";
            case "leadstatusgraph":
                return "SELECT 4 as seq, Tbl_LeadMaster.leadStatus, count(Tbl_LeadMaster.id) FROM Tbl_LeadMaster WHERE find_in_set(Tbl_LeadMaster.lastForward,'userfilter') and Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 group by Tbl_LeadMaster.leadStatus ";
            case "leadtypegraph":
                return "SELECT 5 AS seq, ifnull(Tbl_LeadMaster.clientType,\"NA\") AS name, COUNT(Tbl_LeadMaster.id) AS count FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 AND find_in_set(Tbl_LeadMaster.lastForward,'userfilter') GROUP BY clientType";
            case "leadproductgraph":
                return "SELECT 6 AS seq, ifnull(Tbl_LeadMaster.interestedProduct,\"NA\") AS name, COUNT(Tbl_LeadMaster.id) AS count FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 AND find_in_set(Tbl_LeadMaster.lastForward,'userfilter') GROUP BY interestedProduct";
            case "leadsourcegraph":
                return "SELECT 7 AS seq, ifnull(Tbl_LeadMaster.leadSource,\"NA\") AS name, COUNT(Tbl_LeadMaster.id) AS count FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 AND find_in_set(Tbl_LeadMaster.lastForward,'userfilter') GROUP BY leadSource";
            case "leadsourceinnergraph":
                return "SELECT 8 AS seq, ifnull(Tbl_LeadMaster.leadsourceinner,\"NA\") AS name, COUNT(Tbl_LeadMaster.id) AS count FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 AND find_in_set(Tbl_LeadMaster.lastForward,'userfilter') GROUP BY leadsourceinner";
            case "totalticket":
                return "SELECT 9 AS seq, 'Total Tickets' AS name, COUNT(Tbl_Ticket.id) FROM Tbl_Ticket WHERE Tbl_Ticket.datefilter >= 20190101 AND Tbl_Ticket.datefilter <= 20201010 AND FIND_IN_SET(lastforward, 'userfilter') UNION ALL SELECT 10 AS seq, 'Total Tickets Closed' AS name , COUNT(Tbl_Ticket.id) FROM Tbl_Ticket WHERE Tbl_Ticket.isClose = 1 AND Tbl_Ticket.closeDateFilter >= 20190101 AND Tbl_Ticket.closeDateFilter <= 20201010 AND FIND_IN_SET(lastforward, 'userfilter') UNION ALL SELECT 11 AS seq, 'Average Ticket Time' AS name , IFNULL(SUM(Tbl_Ticket.closeTimeTaked), 0) FROM Tbl_Ticket WHERE Tbl_Ticket.isClose = 1 AND Tbl_Ticket.closeDateFilter >= 20190101 AND Tbl_Ticket.closeDateFilter <= 20201010 AND FIND_IN_SET(lastforward, 'userfilter')";
            case "ticketstatusgraph":
                return "SELECT 12 AS seq, Tbl_Ticket.ticketstatus AS name, COUNT(Tbl_Ticket.id) FROM Tbl_Ticket WHERE Tbl_Ticket.datefilter >= 20190101 AND Tbl_Ticket.datefilter <= 20201010 AND FIND_IN_SET(lastforward, 'userfilter') GROUP BY Tbl_Ticket.ticketstatus";
            case "ticketprioritygraph":
                return "SELECT 13 AS seq, Tbl_Ticket.priority AS name, COUNT(Tbl_Ticket.id) FROM Tbl_Ticket WHERE Tbl_Ticket.datefilter >= 20190101 AND Tbl_Ticket.datefilter <= 20201010 AND FIND_IN_SET(lastforward, 'userfilter') GROUP BY Tbl_Ticket.priority";
            case "tickettypegraph":
                return "SELECT 14 AS seq, Tbl_Ticket.type AS name, COUNT(Tbl_Ticket.id) FROM Tbl_Ticket WHERE Tbl_Ticket.datefilter >= 20190101 AND Tbl_Ticket.datefilter <= 20201010 AND FIND_IN_SET(lastforward, 'userfilter') GROUP BY Tbl_Ticket.type ;";
            case "totalmail":
                return "SELECT 15 as seq,Tbl_Mail_Transaction.status AS name, SUM(Tbl_Mail_Transaction.count) FROM Tbl_Mail_Transaction WHERE Tbl_Mail_Transaction.datefiler >= 20190101 AND Tbl_Mail_Transaction.datefiler <= 20201010 GROUP BY Tbl_Mail_Transaction.status;";
            case "mailstatusgraph":
                return "SELECT 16 as seq,Tbl_Mail_Transaction.status AS name, SUM(Tbl_Mail_Transaction.count) FROM Tbl_Mail_Transaction WHERE Tbl_Mail_Transaction.datefiler >= 20190101 AND Tbl_Mail_Transaction.datefiler <= 20201010 GROUP BY Tbl_Mail_Transaction.status;";
            case "leadprioritygraph":
                return "SELECT 17 AS seq, IFNULL(Tbl_LeadMaster.leadPriority, 'NA') AS name, COUNT(Tbl_LeadMaster.id) AS count FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 AND FIND_IN_SET(Tbl_LeadMaster.lastForward, 'userfilter') GROUP BY leadPriority";

            case "keywordgraph":
                return "SELECT 18 AS seq, IFNULL(Tbl_LeadMaster.keyword, 'NA') AS name, COUNT(Tbl_LeadMaster.id) AS count FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 AND FIND_IN_SET(Tbl_LeadMaster.lastForward, 'userfilter') GROUP BY keyword";

            case "agentleadcount":
                return "SELECT 19 AS seq, 'Total Lead' AS name, COUNT(Tbl_LeadMaster.id) AS counting FROM Tbl_LeadMaster WHERE FIND_IN_SET(Tbl_LeadMaster.lastForward, userid) AND Tbl_LeadMaster.dateFilter >= 20200101 AND Tbl_LeadMaster.dateFilter <= 20201010  UNION ALL SELECT 20 AS seq, 'Total Lead Conversion' AS name, COUNT(Tbl_LeadMaster.id) AS counting FROM Tbl_LeadMaster WHERE FIND_IN_SET(Tbl_LeadMaster.profilingAgentId, userid) AND Tbl_LeadMaster.dateFilter >= 20200101 AND Tbl_LeadMaster.dateFilter <= 20201010 UNION ALL SELECT 21 AS seq, 'Total Pending' AS name, COUNT(Tbl_LeadMaster.id) AS counting FROM Tbl_LeadMaster WHERE FIND_IN_SET(Tbl_LeadMaster.lastForward, userid) AND Tbl_LeadMaster.dateFilter >= 20200101 AND Tbl_LeadMaster.dateFilter <= 20201010 AND Tbl_LeadMaster.leadStage = 'Conversation' AND Tbl_LeadMaster.leadStatus = 'Pending'; ";


            default: throw new Exception("Request Url of Dashboard Cannot be found!!");


        }

    }

    @Override
    public List<DashboardDao> reportDashboard(int startdate, String type, int enddate, int user) throws Exception {
        Connection con=null;
        String query=loadQuery(type);

        //   String query="SELECT 1 as seq, 'Total Lead' as name, count(Tbl_LeadMaster.id) as counting FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.lastForward=userfilter and Tbl_LeadMaster.dateFilter >= 20190101 AND Tbl_LeadMaster.dateFilter <= 20201010 union all SELECT 2 as seq, 'Total Lead Conversion' as name, count(Tbl_LeadMaster.id) as counting FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.lastForward=userfilter and Tbl_LeadMaster.convertDateFilter >= 20190101 AND Tbl_LeadMaster.convertDateFilter <= 20201010 union all SELECT 3 as seq, 'Lead Conversion Average Time' as name, sum(ifnull(Tbl_LeadMaster.timeTakenToConvert,0)) as counting FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.lastForward=userfilter and Tbl_LeadMaster.convertDateFilter >= 20190101 AND Tbl_LeadMaster.convertDateFilter <= 20201010 union all SELECT 4 as seq, Tbl_LeadMaster.leadStage, count(Tbl_LeadMaster.id) FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.lastForward=userfilter and Tbl_LeadMaster.dateFilter >= 20191010 AND Tbl_LeadMaster.dateFilter <= 20201010 group by Tbl_LeadMaster.leadStage union all SELECT 5 as seq, Tbl_LeadMaster.leadStatus, count(Tbl_LeadMaster.id) FROM Tbl_LeadMaster WHERE Tbl_LeadMaster.lastForward=userfilter and Tbl_LeadMaster.dateFilter >= 20191010 AND Tbl_LeadMaster.dateFilter <= 20201010 group by Tbl_LeadMaster.leadStatus;";
        String userList= UtilityClass.userlist.get(user);
        String userid = UtilityClass.getCurrentUser().getId()+"";

        query=query.replaceAll("20190101", String.valueOf(startdate));
        query=query.replaceAll("20201010", String.valueOf(enddate));
        query=query.replaceAll("userfilter", userList);
        query=query.replaceAll("userid", userid);

        System.out.println("DashboardServiceImpl >>>>> Query "+query);
        List<DashboardDao> al=new ArrayList<>();

        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            DashboardDao model=null;
            while (rs.next())
            {
                model=new DashboardDao();
                model.setSeq(rs.getInt(1));
                model.setDesc(rs.getString(2));
                model.setCount(rs.getInt(3));

                al.add(model);
            }

        }catch (SQLException sql)
        {
            throw sql;


        }finally {
                con.close();
        }
        return al;
    }
}
