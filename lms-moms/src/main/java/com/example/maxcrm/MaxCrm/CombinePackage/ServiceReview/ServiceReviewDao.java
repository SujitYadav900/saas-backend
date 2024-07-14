package com.example.maxcrm.MaxCrm.CombinePackage.ServiceReview;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_ServiceReview")
public class ServiceReviewDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //Question: How is your child doing, is he enjoying the program and strategies given by the therapist?
    private String howsChildDoing;
    //Question: How about you, are you facing any challenges in terms practising the activities or skills.
    private String facingAnyChallenges;
    //Question: Are you interested in an in-house counsellor who can help you?
    private byte interestedInCounsellor;
    //Will you recommend us to anyone? (Rate us on the scale of 1 to 10, 1 being lowest and 10 being highest)
    private byte recommendationScore;
    //Question: How you see the impact on child with the intervention provided?
    private byte impactScore;
    //what 2- 3 value add or service that we should start to help you more in your day-to-day
    private String suggestedService1;
    private String suggestedService2;
    private String suggestedService3;
    //Question: How would you rate our therapist?
    private byte therapistScore;
    private long leadId;
    private String createBy;
    private String createDate;
    private int createDateFilter;
    private String updateBy;
    private String updateDate;
    private int updateDateFilter;

    //======= LEAD MASTER VARIABLES ON REQUIRED IN REPORT
    @Transient
    private String parentName;
    @Transient
    private String childName;
    @Transient
    private String phonenumber;
    @Transient
    private String email;
    @Transient
    private String state;
    @Transient
    private String city;
    @Transient
    private String clientType;
    @Transient
    private String leadStage;
    @Transient
    private String leadStatus;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHowsChildDoing() {
        return howsChildDoing;
    }

    public void setHowsChildDoing(String howsChildDoing) {
        this.howsChildDoing = howsChildDoing;
    }

    public String getFacingAnyChallenges() {
        return facingAnyChallenges;
    }

    public void setFacingAnyChallenges(String facingAnyChallenges) {
        this.facingAnyChallenges = facingAnyChallenges;
    }

    public byte getInterestedInCounsellor() {
        return interestedInCounsellor;
    }

    public void setInterestedInCounsellor(byte interestedInCounsellor) {
        this.interestedInCounsellor = interestedInCounsellor;
    }

    public byte getRecommendationScore() {
        return recommendationScore;
    }

    public void setRecommendationScore(byte recommendationScore) {
        this.recommendationScore = recommendationScore;
    }

    public byte getImpactScore() {
        return impactScore;
    }

    public void setImpactScore(byte impactScore) {
        this.impactScore = impactScore;
    }

    public String getSuggestedService1() {
        return suggestedService1;
    }

    public void setSuggestedService1(String suggestedService1) {
        this.suggestedService1 = suggestedService1;
    }

    public String getSuggestedService2() {
        return suggestedService2;
    }

    public void setSuggestedService2(String suggestedService2) {
        this.suggestedService2 = suggestedService2;
    }

    public String getSuggestedService3() {
        return suggestedService3;
    }

    public void setSuggestedService3(String suggestedService3) {
        this.suggestedService3 = suggestedService3;
    }

    public byte getTherapistScore() {
        return therapistScore;
    }

    public void setTherapistScore(byte therapistScore) {
        this.therapistScore = therapistScore;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateDateFilter() {
        return createDateFilter;
    }

    public void setCreateDateFilter(int createDateFilter) {
        this.createDateFilter = createDateFilter;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateDateFilter() {
        return updateDateFilter;
    }

    public void setUpdateDateFilter(int updateDateFilter) {
        this.updateDateFilter = updateDateFilter;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
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

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getLeadStage() {
        return leadStage;
    }

    public void setLeadStage(String leadStage) {
        this.leadStage = leadStage;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    @Override
    public String toString() {
        return "ServiceReviewDao{" +
                "id=" + id +
                ", howsChildDoing='" + howsChildDoing + '\'' +
                ", facingAnyChallenges='" + facingAnyChallenges + '\'' +
                ", interestedInCounsellor=" + interestedInCounsellor +
                ", recommendationScore=" + recommendationScore +
                ", impactScore=" + impactScore +
                ", suggestedService1='" + suggestedService1 + '\'' +
                ", suggestedService2='" + suggestedService2 + '\'' +
                ", suggestedService3='" + suggestedService3 + '\'' +
                ", therapistScore=" + therapistScore +
                ", leadId=" + leadId +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createDateFilter=" + createDateFilter +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateDateFilter=" + updateDateFilter +
                ", parentName='" + parentName + '\'' +
                ", childName='" + childName + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", clientType='" + clientType + '\'' +
                ", leadStage='" + leadStage + '\'' +
                ", leadStatus='" + leadStatus + '\'' +

                '}';
    }
}
