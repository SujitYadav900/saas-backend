package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao;
/*
 Author=Supreet Singh
 Date= 09/03/21 2:19 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;

import java.util.List;

public interface MessageReportService {
void updateDlr(String id, MessageStatus messageStatus,long accountId);
List<MessageReportDao> getAllByDate(FilterDao filterDao);

void refundDebits();

    List<CommonGroupQueryDao> getReportBetweenDate(int startdate, int enddate,String chartside,String type, long accountId,byte reportBy);

    List<MessageDao> reportByAccountGroup(int startdate, int enddate, String chatside, String type, long accountId);

    List<MessageDao> reportByAccountGroup(int startdate, int enddate, String chatside, String type, long accountId,byte reportBy);
}
