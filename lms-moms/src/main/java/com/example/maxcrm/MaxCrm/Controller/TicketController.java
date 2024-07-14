package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    NotificationService notificationService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    TicketMessageService ticketMessageService;
    @Autowired
    private TicketService service;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TicketForwardLogService forwardLogService;
    @Autowired
    private UserMasterService userMasterService;

    @PostMapping("/insert")
    public TicketDao insert(@RequestBody TicketDao dao, @RequestParam("sendtextmessage") boolean sendtextmessage, Authentication auth) throws Exception {

        UserMasterDao user = (UserMasterDao) auth.getPrincipal();


        dao.setCreatedate(UtilityClass.getDateRedable());
        dao.setCreatedby(user.getUsername());
        dao.setUpdateby(user.getUsername());
        dao.setUpdatedate(UtilityClass.getDateRedable());
        dao.setUpdatedatefilter(UtilityClass.dateFilterDate());
        dao.setDatefilter(UtilityClass.dateFilterDate());
        dao.setTicketstatus(UtilityClass.propertyService.findProperty("Ticket", "defaultTicketStatus"));
        dao.setPriority(UtilityClass.propertyService.findProperty("Ticket", "defaultTicketPriority"));
        if (dao.getLastforward() == 0) {
            user = roleService.findRandomUser(dao.getDepartment());
            if (user == null) {
                throw new Exception("No User Cannot Be Found In The Department!!");
            }
            dao.setLastforward(user.getId());
        }

        dao = service.insert(dao);

        //logging first assigned agent for ticket
        TicketForwardLogDao forwardLogDao = new TicketForwardLogDao();
        forwardLogDao.setCreateBy(dao.getCreatedby());
        forwardLogDao.setDatefilter(dao.getDatefilter());
        forwardLogDao.setTicketid(dao.getId());
        forwardLogDao.setTimestamp(dao.getCreatedate());
        forwardLogDao.setToagent(userMasterService.findById(dao.getLastforward()).getUsername());
        forwardLogService.insert(forwardLogDao);

        TicketMessageDao ticketMessageDao = new TicketMessageDao();
        ticketMessageDao.setSender(dao.getCreatedby());
        ticketMessageDao.setSenddate(dao.getCreatedate());
        ticketMessageDao.setMessage("<p>" + dao.getDescription() + "</p>");
        ticketMessageDao.setAttachmentlist(dao.getAttachmentlist());
        ticketMessageDao.setReciever(dao.getLastforward());
        ticketMessageDao.setTicketid(dao.getId());
        ticketMessageService.insert(ticketMessageDao);

        //sending notification
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setCreateBy(dao.getCreatedby());
        notificationDao.setDatetiming(UtilityClass.fullDateLong());
        notificationDao.setCreateAt(UtilityClass.getDateRedable());
        notificationDao.setTo(dao.getLastforward());
        notificationDao.setSubject("New Ticket");
        notificationDao.setSendMessage(false);
        notificationDao.setInnerSubject("NOTIFY");
        notificationDao.setRedirectUrl("ticketportal?id=" + dao.getId());
        notificationDao.setLeadId(ticketMessageDao.getId());
        notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
        notificationDao.setMessage("New Ticket from " + dao.getCreatedby());
        String value = propertyService.findProperty("Ticket", "Send_Text_Message_On_Ticket_Creation");
        if (value.equalsIgnoreCase("1")) {
            notificationDao.setSendMessage(true);
        }


        notificationService.insertSingleNotification(notificationDao);

        return dao;
    }

    @GetMapping("/getticketbyleadid")
    public PaginationDao findByLeadId(@RequestParam("id") long id, @RequestParam("offset") int offset, @RequestParam("limit") int limit) throws SQLException {
        String wherequery = "where Tbl_Ticket.leadid=" + id;
        return service.findAllTicket(wherequery, offset, limit);
    }

    @PostMapping("/update")
    public TicketDao update(@RequestBody TicketDao dao, Authentication auth) {
        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        dao.setUpdateby(user.getUsername());
        dao.setUpdatedate(UtilityClass.getDateRedable());
        return service.insert(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        service.delete(id);
    }


    private String prepareQuery(String ticketstatus, int userId, String ticketType, String ticketPriority, boolean datefilter, String datevalue, String userList, String searchValue, boolean checkClosedTickets, long leadId, String datefiltertype) {
        StringBuilder sb = new StringBuilder();
        sb.append(" where ");
        if (!ticketstatus.equalsIgnoreCase("0")) {
            sb.append(" Tbl_Ticket.ticketstatus ='");
            sb.append(ticketstatus);
            sb.append("' and ");
        }
        if (!ticketType.equalsIgnoreCase("0")) {
            sb.append(" Tbl_Ticket.type ='");
            sb.append(ticketType);
            sb.append("' and ");
        }
        if (!ticketPriority.equalsIgnoreCase("0")) {
            sb.append(" Tbl_Ticket.priority ='");
            sb.append(ticketPriority);
            sb.append("' and ");
        }
        if (leadId != 0) {
            sb.append(" Tbl_Ticket.leadid =");
            sb.append(leadId);
            sb.append(" and ");
        }
        if (datefilter) {
            String[] dates = datevalue.split("@");
            if (datefiltertype.equalsIgnoreCase("createdate")) {

                sb.append("(Tbl_Ticket.datefilter >=");
                sb.append(dates[0].replaceAll("-", ""));
                sb.append(" and Tbl_Ticket.datefilter <=");
                sb.append(dates[1].replaceAll("-", ""));
                sb.append(")");
            } else if (datefiltertype.equalsIgnoreCase("closedate")) {
                sb.append("(Tbl_Ticket.datefilterClose >=");
                sb.append(dates[0].replaceAll("-", ""));
                sb.append(" and Tbl_Ticket.datefilterClose <=");
                sb.append(dates[1].replaceAll("-", ""));
                sb.append(")");
            } else if (datefiltertype.equalsIgnoreCase("updatedate")) {
                sb.append("(Tbl_Ticket.updatedatefilter >=");
                sb.append(dates[0].replaceAll("-", ""));
                sb.append(" and Tbl_Ticket.updatedatefilter <=");
                sb.append(dates[1].replaceAll("-", ""));
                sb.append(")");
            }
            sb.append(" and ");
        }

        if (!searchValue.equalsIgnoreCase("0")) {
            String searchquery = "(Tbl_Ticket.createdby like 'searchvaluestring%' or\n" +
                    "Tbl_Ticket.department like 'searchvaluestring%' or\n" +
                    "Tbl_Ticket.priority like 'searchvaluestring%' or\n" +
                    "Tbl_Ticket.leadid like 'searchvaluestring%' or\n" +
                    "Tbl_Ticket.subject like 'searchvaluestring%' or\n" +
                    "Tbl_Ticket.ticketstatus like 'searchvaluestring%' or\n" +
                    "Tbl_Ticket.type like 'searchvaluestring%' \n" +
                    ")";
            searchValue = searchValue.replaceAll(UtilityClass.ApplicationPrefix, "");
            searchquery = searchquery.replaceAll("searchvaluestring", searchValue);
            sb.append(searchquery);
            sb.append(" and ");
        }

        if (userList.equalsIgnoreCase("0")) {
            userList = UtilityClass.userlist.get(userId);

        }
        sb.append(" Tbl_Ticket.lastforward in (");
        sb.append(userList);
        sb.append(" ) ");


        return sb.toString();
    }

    @GetMapping("/getall")
    public PaginationDao getall(
            @RequestParam("ticketstatus") String ticketstatus
            , @RequestParam("ticketType") String ticketType
            , @RequestParam("ticketPriority") String ticketPriority
            , @RequestParam("datefilter") boolean datefilter
            , @RequestParam("checkClosedTickets") boolean checkClosedTickets
            , @RequestParam("datevalue") String datevalue
            , @RequestParam("searchValue") String searchValue
            , @RequestParam("userList") String userList
            , @RequestParam("id") long id
            , @RequestParam("leadid") long leadid
            , Authentication authentication
            , @RequestParam("datefiltertype") String datefiltertype
            , @RequestParam("length") int limit, @RequestParam("start") int offset
    ) throws SQLException {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        if (id == 0) {
            return service.findAllTicket(prepareQuery(ticketstatus, userMasterDao.getId(), ticketType, ticketPriority, datefilter, datevalue, userList, searchValue, checkClosedTickets, leadid, datefiltertype), offset, limit);
        } else {
            return service.findAllTicket("where Tbl_Ticket.id=" + id, offset, limit);

        }

    }


    @PostMapping("/updatestatus")
    public void updatestatus(@RequestParam String ticketstatus, @RequestParam long id, @RequestParam String createdate) throws SQLException {
        service.changeStatus(ticketstatus, createdate, id);
    }

    @PostMapping("/updatepriority")
    public void updatePriority(@RequestParam String priority, @RequestParam long id) throws SQLException {
        service.changePriority(priority, id);
    }

    @PostMapping("/forwardticket")
    public void forwardTicket(@RequestParam long id, @RequestParam int lastforwardname, @RequestParam String department, @RequestParam String toagent, Authentication authentication) throws SQLException {
        if (lastforwardname == 0) {
            UserMasterDao userMasterDao1 = roleService.findRandomUser(department);
            lastforwardname = userMasterDao1.getId();
            toagent = userMasterDao1.getUsername();
        }
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        service.forwardTicket(id, lastforwardname, userMasterDao.getUsername(), toagent);
        try {
            NotificationDao notificationDao = new NotificationDao();
            String createTiming = UtilityClass.getDateMysql();
            notificationDao.setAssignTo(lastforwardname);
            notificationDao.setSubject("Ticket Forward");
            notificationDao.setRedirectUrl("ticketportal?id=" + id);
            notificationDao.setNotificationTiming(createTiming);
            notificationDao.setInnerSubject("NOTIFY");
            notificationDao.setSendMessage(false);


            notificationDao.setMessage("Ticket Has Been Forwarded To You!!");
            notificationDao.setLeadId(id);
            notificationDao.setTo(lastforwardname);
            notificationDao.setCreateAt(createTiming);
            notificationDao.setDatetiming(UtilityClass.fullDateLong());
            notificationDao.setCreateBy(userMasterDao.getUsername());
            String value = propertyService.findProperty("Ticket", "Send_Text_Message_On_Ticket_Forward");

            if (value.equalsIgnoreCase("1")) {
                notificationDao.setSendMessage(true);
            }
            notificationService.insertSingleNotification(notificationDao);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
