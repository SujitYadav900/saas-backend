package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao;
/*
 Author=Supreet Singh
 Date= 09/03/21 2:15 PM
*/

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageReportRepo extends CrudRepository<MessageReportDao,Long> {
    long countAllById(long id);

    @Query("from MessageReportDao  where dateFilter<=?1 and picked=FALSE")
    List<MessageReportDao> findByByAndDateFilter(int dateFilter);
    @Query("from MessageReportDao  where id>=?1 and id<=?2 order by id desc")
    List<MessageReportDao> getReport(long start,long enddate);
}
