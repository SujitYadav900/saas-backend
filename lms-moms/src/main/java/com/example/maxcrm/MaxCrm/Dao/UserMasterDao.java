package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "Tbl_UserMaster", uniqueConstraints={
        @UniqueConstraint(name = "unqusername",columnNames = "username"),
        @UniqueConstraint(name = "unqphonenumber",columnNames = "mobile"),
        @UniqueConstraint(name = "unqemail",columnNames = "email")})
public class UserMasterDao {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getFaceBookId() {
        return faceBookId;
    }

    public void setFaceBookId(String faceBookId) {
        this.faceBookId = faceBookId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isTwoStepAuthentication() {
        return twoStepAuthentication;
    }

    public void setTwoStepAuthentication(boolean twoStepAuthentication) {
        this.twoStepAuthentication = twoStepAuthentication;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAttachementList() {
        return attachementList;
    }

    public void setAttachementList(String attachementList) {
        this.attachementList = attachementList;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String faceBookId;
    private String dob;
    private String address;
    private String department;
    private String gender;
    private String status;
    private boolean sendMailOnCreation;
    private boolean twoStepAuthentication;
    private String username;
    private String password;
    private String attachementList; //comma seperated files
    private String reportTo;
    private String companyName;
    private int companySize;
    private String createBy;
    private String createDate;
    private String lastUpdate;
    private String lastUpdateBy;
    private int demoPeriod;
    private int remainingDemoPeriod;
    private int emailSent;
    private int msgSent;
    private String industry;
    @Transient
    private String leadCountOfReportTo;
    @Transient
    private String leadCountOfUser;
    @Transient
    private String reportList;

    @Override
    public String toString() {
        return "UserMasterDao{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", faceBookId='" + faceBookId + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", sendMailOnCreation=" + sendMailOnCreation +
                ", twoStepAuthentication=" + twoStepAuthentication +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", attachementList='" + attachementList + '\'' +
                ", reportTo='" + reportTo + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companySize=" + companySize +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", demoPeriod=" + demoPeriod +
                ", remainingDemoPeriod=" + remainingDemoPeriod +
                ", emailSent=" + emailSent +
                ", msgSent=" + msgSent +
                ", industry='" + industry + '\'' +
                ", leadCountOfReportTo='" + leadCountOfReportTo + '\'' +
                ", leadCountOfUser='" + leadCountOfUser + '\'' +
                ", reportList='" + reportList + '\'' +
                ", demo=" + demo +
                ", createByUserId=" + createByUserId +
                ", menuJson='" + menuJson + '\'' +
                ", features=" + features +
                '}';
    }

    public String getReportList() {
        return reportList;
    }

    public void setReportList(String reportList) {
        this.reportList = reportList;
    }

    public int getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(int emailSent) {
        this.emailSent = emailSent;
    }

    public int getMsgSent() {
        return msgSent;
    }

    public void setMsgSent(int msgSent) {
        this.msgSent = msgSent;
    }

    public int getRemainingDemoPeriod() {
        return remainingDemoPeriod;
    }

    public void setRemainingDemoPeriod(int remainingDemoPeriod) {
        this.remainingDemoPeriod = remainingDemoPeriod;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    private boolean demo;


    public int getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(int createByUserId) {
        this.createByUserId = createByUserId;
    }

    @Transient
    private int createByUserId;


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanySize() {
        return companySize;
    }

    public void setCompanySize(int companySize) {
        this.companySize = companySize;
    }

    public int getDemoPeriod() {
        return demoPeriod;
    }

    public void setDemoPeriod(int demoPeriod) {
        this.demoPeriod = demoPeriod;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }


    public boolean isSendMailOnCreation() {
        return sendMailOnCreation;
    }

    public void setSendMailOnCreation(boolean sendMailOnCreation) {
        this.sendMailOnCreation = sendMailOnCreation;
    }

    public String getReportTo() {
        return reportTo;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setReportTo(String reportTo) {
        this.reportTo = reportTo;
    }
    @Transient
    private String menuJson;

    public String getMenuJson() {
        return menuJson;
    }
    public CampaignObjectDao convertForCampaign()
    {
        CampaignObjectDao campaignObjectDao=new CampaignObjectDao();
        campaignObjectDao.setFullname(this.getFirstName()+" "+this.lastName);
        campaignObjectDao.setEmail(this.getEmail());
        campaignObjectDao.setOtp(this.password);
        campaignObjectDao.setPhonenumber(this.mobile);
        return campaignObjectDao;

    }

    public String getLeadCountOfReportTo() {
        return leadCountOfReportTo;
    }

    public void setLeadCountOfReportTo(String leadCountOfReportTo) {
        this.leadCountOfReportTo = leadCountOfReportTo;
    }

    public String getLeadCountOfUser() {
        return leadCountOfUser;
    }

    public void setLeadCountOfUser(String leadCountOfUser) {
        this.leadCountOfUser = leadCountOfUser;
    }

    public HashMap<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(HashMap<String, String> features) {
        this.features = features;
    }
    @Transient
    private HashMap<String, String> features;
    public void setMenuJson(String menuJson) {
        this.menuJson = menuJson;
    }
}
