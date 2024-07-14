package com.whatsappbuisness.wsbuissness.CombinePackadge.Features.DirectChatFeature.ChatThread;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Features.DirectChatFeature.UserAsignDao.UserCompositeKeyDao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Chat_Thread_Dao")
public class ChatThreadDao {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String phn;
    private long agentId;

    @Override
    public String toString() {
        return "ChatThreadDao{" +
                "id=" + id +
                ", phn='" + phn + '\'' +
                ", agentId=" + agentId +
                ", lastMessageTime='" + lastMessageTime + '\'' +
                '}';
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    private String lastMessageTime;





}
