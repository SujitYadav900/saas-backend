package com.example.maxcrm.MaxCrm.CombinePackage.CentreAppointmentLogs;

import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentreAppointmentLogsServiceImpl implements CentreAppointmentLogsService{

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CentreAppointmentLogsRepo logsRepo;


    @Override
    public CentreAppointmentLogs insert(CentreAppointmentLogs log) {
        log.setCreateDate(UtilityClass.getDateRedable());
        log.setCreateDateFilter(UtilityClass.dateFilterDate());
        logger.info("Inserting CentreAppointment Log : {}",log);
        return logsRepo.save(log);
    }
}
