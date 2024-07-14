package com.example.maxcrm.MaxCrm.MBOPSRegistration;

public class ParentResponse {

    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile;
    private String alternate_mobile;
    private String org_id;
    private String location_id;
    private String child_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlternate_mobile() {
        return alternate_mobile;
    }

    public void setAlternate_mobile(String alternate_mobile) {
        this.alternate_mobile = alternate_mobile;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getChild_count() {
        return child_count;
    }

    public void setChild_count(String child_count) {
        this.child_count = child_count;
    }

    @Override
    public String toString() {
        return "ParentResponse{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", alternate_mobile='" + alternate_mobile + '\'' +
                ", org_id='" + org_id + '\'' +
                ", location_id='" + location_id + '\'' +
                ", child_count='" + child_count + '\'' +
                '}';
    }
}
