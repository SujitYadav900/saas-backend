package com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule;
/*
 Author=Sujit  Yadav
 Date= 16/01/2k3 12:20 AM
*/
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(collection = "CampaignScheduleDao")
public class CampaignScheduleDao {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private MessageDao messageDao;
    private String  date;
    private int dateFilter;
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CampaignScheduleDao{" +
                "id='" + id + '\'' +
                ", messageDao=" + messageDao +
                ", date='" + date + '\'' +
                ", dateFilter=" + dateFilter +
                ", isActive=" + isActive +
                '}';
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

}
