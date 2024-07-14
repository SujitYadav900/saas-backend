package com.example.maxcrm.MaxCrm.Dao;

public class ZapierDao extends FacebookIntegrationDao{

//    all fields from fb

    private String city;
    private String country;
    private String created_time;
    private String date_of_birth;
    private String email;
    private String form_id;
    private String full_name;
    private String gender;
    private String id;
    private String job_title;
    private String leadgen_id;
    private String marital_status;
    private String military_status;
    private String page_id;
    private String phone_number;
    private String post_code;
    private String relationship_status;
    private String state;
    private String street_address;
    private String work_phone_number;
    private String work_email;
    private String company_name;
    private String ad_name;
    private String adset_name;
    private String campaign_name;
    private String platform;


    private String formatPhonenumber(String phonenumber) {
        int len = phonenumber.length();
        if(len > 10){
            phonenumber = phonenumber.substring(len-10,len);
            return phonenumber;
        }else {
            return phonenumber;
        }

    }

    public LeadMasterDao convertToLeadMaster()
    {
        LeadMasterDao dao=new LeadMasterDao();
        dao.setParentName(this.full_name);
        dao.setCountry(this.country);
        dao.setCity(this.city);
        dao.setDob(this.date_of_birth);
        dao.setEmail(this.email);
        dao.setGender(this.gender);
        dao.setPhonenumber(formatPhonenumber(this.phone_number));
        dao.setPincode(this.post_code);
        dao.setState(this.state);
        dao.setAddress(this.street_address);

        return dao;

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getLeadgen_id() {
        return leadgen_id;
    }

    public void setLeadgen_id(String leadgen_id) {
        this.leadgen_id = leadgen_id;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getMilitary_status() {
        return military_status;
    }

    public void setMilitary_status(String military_status) {
        this.military_status = military_status;
    }

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getRelationship_status() {
        return relationship_status;
    }

    public void setRelationship_status(String relationship_status) {
        this.relationship_status = relationship_status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getWork_phone_number() {
        return work_phone_number;
    }

    public void setWork_phone_number(String work_phone_number) {
        this.work_phone_number = work_phone_number;
    }

    public String getWork_email() {
        return work_email;
    }

    public void setWork_email(String work_email) {
        this.work_email = work_email;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAdset_name() {
        return adset_name;
    }

    public void setAdset_name(String adset_name) {
        this.adset_name = adset_name;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "ZapierDao{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", created_time='" + created_time + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", email='" + email + '\'' +
                ", form_id='" + form_id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                ", job_title='" + job_title + '\'' +
                ", leadgen_id='" + leadgen_id + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", military_status='" + military_status + '\'' +
                ", page_id='" + page_id + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", post_code='" + post_code + '\'' +
                ", relationship_status='" + relationship_status + '\'' +
                ", state='" + state + '\'' +
                ", street_address='" + street_address + '\'' +
                ", work_phone_number='" + work_phone_number + '\'' +
                ", work_email='" + work_email + '\'' +
                ", company_name='" + company_name + '\'' +
                ", ad_name='" + ad_name + '\'' +
                ", adset_name='" + adset_name + '\'' +
                ", campaign_name='" + campaign_name + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
