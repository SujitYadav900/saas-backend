package com.example.maxcrm.MaxCrm.Dao.HIS;

public class AppointmentDao {
    @Override
    public String toString() {
        return "AppointmentDao{" +
                "patient_name='" + patient_name + '\'' +
                ", patient_phone='" + patient_phone + '\'' +
                ", booking_datetime='" + booking_datetime + '\'' +
                ", created_on='" + created_on + '\'' +
                ", created_by='" + created_by + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public String getBooking_datetime() {
        return booking_datetime;
    }

    public void setBooking_datetime(String booking_datetime) {
        this.booking_datetime = booking_datetime;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String patient_name;
    private String patient_phone;
    private String booking_datetime;
    private String created_on;
    private String created_by;
    private String status;

    public String getLeadStage() {
        return leadStage;
    }

    public void setLeadStage(String leadStage) {
        this.leadStage = leadStage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    private String leadStage;
    private long id;
    private String leadStatus;

    public boolean isConvertLead() {
        return convertLead;
    }

    public void setConvertLead(boolean convertLead) {
        this.convertLead = convertLead;
    }

    private boolean convertLead;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    public void setLeadDeatails(String leadStatus,String leadStage,long id)
    {
        this.leadStage=leadStage;
        this.leadStatus=leadStatus;
        this.id=id;
    }
    private String query;

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    private boolean notification;

    public String getNotificationString() {
        return notificationString;
    }

    public void setNotificationString(String notificationString) {
        this.notificationString = notificationString;
    }

    private String notificationString;







}
