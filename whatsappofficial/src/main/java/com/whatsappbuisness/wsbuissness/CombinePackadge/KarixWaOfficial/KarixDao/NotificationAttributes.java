package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class NotificationAttributes {

    public String status;
    public String reason;
    public String code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;

    }

    @Override
    public String toString() {
        return "NotificationAttributes{" +
                "status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
