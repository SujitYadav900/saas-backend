package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.MailReportCardDao;

import java.util.List;

public interface MailReportCardService {
    List<MailReportCardDao> getReportCard(int startdate , int enddate);
}
