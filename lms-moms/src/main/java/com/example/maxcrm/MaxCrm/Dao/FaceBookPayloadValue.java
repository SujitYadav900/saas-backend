package com.example.maxcrm.MaxCrm.Dao;

public class FaceBookPayloadValue {

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    @Override
    public String toString() {
        return "FacebookPayloadInnerDao{" +
                "created_time=" + created_time +
                ", page_id='" + page_id + '\'' +
                ", form_id='" + form_id + '\'' +
                ", leadgen_id='" + leadgen_id + '\'' +
                '}';
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getLeadgen_id() {
        return leadgen_id;
    }

    public void setLeadgen_id(String leadgen_id) {
        this.leadgen_id = leadgen_id;
    }

    private long created_time;
    private String page_id;
    private String form_id;
    private String leadgen_id;
}
