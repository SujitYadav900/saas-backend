package com.example.maxcrm.MaxCrm.Dao;

import java.util.List;

public class GoogleAddwordLeadDao {
    private String lead_id;
    private String api_version;
    private int form_id;
    private int campaign_id;
    private String google_key;

    public String getLead_id() {
        return lead_id;
    }

    @Override
    public String toString() {
        return "GoogleAddwordLeadDao{" +
                "lead_id='" + lead_id + '\'' +
                ", api_version='" + api_version + '\'' +
                ", form_id=" + form_id +
                ", campaign_id=" + campaign_id +
                ", google_key='" + google_key + '\'' +
                ", is_test='" + is_test + '\'' +
                ", user_column_data=" + user_column_data +
                '}';
    }

    public void setLead_id(String lead_id) {
        this.lead_id = lead_id;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public int getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(int campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getGoogle_key() {
        return google_key;
    }

    public void setGoogle_key(String google_key) {
        this.google_key = google_key;
    }

    public boolean getIs_test() {
        return is_test;
    }

    public void setIs_test(boolean is_test) {
        this.is_test = is_test;
    }

    public List<GoogleAddwordInnerDetails> getUser_column_data() {
        return user_column_data;
    }

    public void setUser_column_data(List<GoogleAddwordInnerDetails> user_column_data) {
        this.user_column_data = user_column_data;
    }

    private boolean is_test;
    private List<GoogleAddwordInnerDetails> user_column_data;
}
