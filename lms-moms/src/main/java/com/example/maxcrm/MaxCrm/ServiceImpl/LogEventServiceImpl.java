package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LogEventDao;
import com.example.maxcrm.MaxCrm.Repo.LogEventRepo;
import com.example.maxcrm.MaxCrm.Service.LeadMasterService;
import com.example.maxcrm.MaxCrm.Service.LogEventService;
import com.example.maxcrm.MaxCrm.Service.UserMasterService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogEventServiceImpl implements LogEventService {
    Logger logger = LoggerFactory.getLogger(LogEventServiceImpl.class);
    @Autowired
    LeadMasterService leadMasterService;
    @Autowired
    UserMasterService userMasterService;

    @Autowired
    LogEventRepo logEventRepo;

    @Override
    public Iterable<LogEventDao> findAllByLeadId(long leadId) {
        return logEventRepo.findByLeadId(leadId);
    }


    @Override
    public LogEventDao insert(LogEventDao logEventDao) {
        logger.info("Insert {}", logEventDao);
        logEventDao = logEventRepo.save(logEventDao);
        if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnEventCreation").equalsIgnoreCase("1")) {
            leadMasterService.updateLastUpdateLead(null, logEventDao.getCreateBy(), UtilityClass.dateFilterDate(), logEventDao.getCreateAt(), logEventDao.getLeadId());
        }

        return logEventDao;
    }

    @Override
    public int insertMany(List<LogEventDao> eventList) {
        logger.info("Inserting {} LogEvents",eventList.size());
        for(LogEventDao event : eventList){
            this.insert(event);
        }
        return eventList.size();
    }

    @Override
    public LogEventDao update(LogEventDao logEventDao) {
        logger.info("Updating {}", logEventDao);
        logEventDao = logEventRepo.save(logEventDao);
        if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnEventUpdating").equalsIgnoreCase("1")) {
            leadMasterService.updateLastUpdateLead(null, logEventDao.getCreateBy(), UtilityClass.dateFilterDate(), logEventDao.getCreateAt(), logEventDao.getLeadId());

        }
        return logEventDao;
    }

    @Override
    public void delete(long id, String createBy, long leadId) {

        logger.info("deleting {}", id);
        logEventRepo.deleteById(id);
        if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnEventDeleting").equalsIgnoreCase("1")) {
            leadMasterService.updateLastUpdateLead(null, createBy, UtilityClass.dateFilterDate(), UtilityClass.getDateRedable(), leadId);
        }

    }

    @Override
    public Iterable<LogEventDao> getlogEventByeventTypeAnduserId(String eventType,  int userId) {

        String reportingUser = userMasterService.findReportingUser(userId);
        String[] reportinguserarray = reportingUser.split(",");
        List<Integer> listOfIntegerUser = new ArrayList<>();
        Integer temp;
        for (int i=0 ; i<reportinguserarray.length;i++) {
            temp = Integer.valueOf(reportinguserarray[i]);
            listOfIntegerUser.add(temp);
        }
        logger.info("LogEventServiceImpl: getlogEventByeventTypeAndUserID: listOfIntegerUser {}" , listOfIntegerUser);
        return logEventRepo.findByEventTypeAndUserIdIn(eventType, listOfIntegerUser);
    }
}
