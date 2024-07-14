package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;

public class TextSMSSendingDao {
    private String msg;
    private String number;

    private String senderId;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "TextSMSSendingDao{" +
                "msg='" + msg + '\'' +
                ", number='" + number + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }
}
