package com.example.maxcrm.MaxCrm.Dao;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("CampaignTransactionDocument")
public class CampaignObjectDao  {


    @Id
    private String id;
    private String fullname;
    private String phonenumber;
    private String email;
    private String childName;
    private String parentName;
    private String appointmentDate;
    @Indexed
    String campaignId;
    @Indexed
    private String messageId;
    @Indexed
    private String dlrStatus;
    @Indexed
    private long datetiming;
    private String userAgent;
    private long leadId;
    private int lastForward;
    private int openCount;
    private String timing;
    private String ip;
    private String state;
    private String city;
    private String product;
    private String message;
    private String mbopsChildId;
    private String url;
    private String otp;
    private String centrename;
    private String centrehead;
    private String centrepin;
    private String centrephonenumber;
    private String centreemail;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDlrStatus() {
        return dlrStatus;
    }

    public void setDlrStatus(String dlrStatus) {
        this.dlrStatus = dlrStatus;
    }

    public String getCentrename() {
        return centrename;
    }

    public void setCentrename(String centrename) {
        this.centrename = centrename;
    }

    public String getCentrehead() {
        return centrehead;
    }

    public void setCentrehead(String centrehead) {
        this.centrehead = centrehead;
    }

    public String getCentrepin() {
        return centrepin;
    }

    public void setCentrepin(String centrepin) {
        this.centrepin = centrepin;
    }

    public String getCentrephonenumber() {
        return centrephonenumber;
    }

    public void setCentrephonenumber(String centrephonenumber) {
        this.centrephonenumber = centrephonenumber;
    }

    public String getCentreemail() {
        return centreemail;
    }

    public void setCentreemail(String centreemail) {
        this.centreemail = centreemail;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public long getDatetiming() {
        return datetiming;
    }

    public void setDatetiming(long datetiming) {
        this.datetiming = datetiming;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMbopsChildId() {
        return mbopsChildId;
    }

    public void setMbopsChildId(String mbopsChildId) {
        this.mbopsChildId = mbopsChildId;
    }

    @Override
    public String toString() {
        return "CampaignObjectDao{" +
                "id='" + id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", childName='" + childName + '\'' +
                ", parentName='" + parentName + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", campaignId='" + campaignId + '\'' +
                ", datetiming=" + datetiming +
                ", userAgent='" + userAgent + '\'' +
                ", leadId=" + leadId +
                ", lastForward=" + lastForward +
                ", openCount=" + openCount +
                ", timing='" + timing + '\'' +
                ", ip='" + ip + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", product='" + product + '\'' +
                ", message='" + message + '\'' +
                ", mbopsChildId='" + mbopsChildId + '\'' +
                ", url='" + url + '\'' +
                ", otp='" + otp + '\'' +
                ", centrename='" + centrename + '\'' +
                ", centrehead='" + centrehead + '\'' +
                ", centrepin='" + centrepin + '\'' +
                ", centrephonenumber='" + centrephonenumber + '\'' +
                ", centreemail='" + centreemail + '\'' +
                '}';
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }







    public String getCampaignId() {
        return campaignId;
    }


    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }


    public String getUserAgent() {
        return userAgent;
    }


    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getOpenCount() {
        return openCount;
    }

    public void setOpenCount(int openCount) {
        this.openCount = openCount;
    }


    public String getTiming() {
        return timing;
    }


    public void setTiming(String timing) {
        this.timing = timing;
    }


    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }



    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public int getLastForward() {
        return lastForward;
    }

    public void setLastForward(int lastForward) {
        this.lastForward = lastForward;
    }


    public String getMatchingValue(String name) {

        switch (name)
        {
            case "fullname":
                return getFullname();
            case "phonenumber":
                return getPhonenumber();
            case "email":
                return getEmail();
            case "state":
                return getState();
            case "city":
                return getCity();
            case "product":
                return getProduct();
            case "url":
                return getUrl();
            case "childName":
                return getChildName();
            case "parentName":
                return getParentName();
            case "otp":
                return getOtp();
            case "id":
                return String.valueOf(getLeadId());
            case "message":
                return getMessage();
            case "mbopschildid":
                return getMbopsChildId();
            case "appointmentDate":
                return getAppointmentDate();
            case "centrename":
                return getCentrename();
            case "centrehead":
                return getCentrehead();
            case "centrepin":
                return getCentrepin();
            case "centrephonenumber":
                return getCentrephonenumber();
            case "centreemail":
                return getCentreemail();

        }
        return "";



    }
}
