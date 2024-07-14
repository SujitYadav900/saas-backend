package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tbl_LeadMaster", uniqueConstraints={@UniqueConstraint(name = "unqphonenumber",columnNames = "phonenumber")})
public class  LeadMasterDao {
    @Id
    private long id;

    private String salutation;
    private String firstName;
    private String lastName;

    private String phonenumber;

    private String leadSource;

    private String interestedProduct;

    private String leadStatus;

    private String clientType;
    private String company;
    private double prospectiveBuissness;
    private String descrip;
    private String country;
    private int countryId;
    private String state;
    private int stateId;
    private String city;
    private String callStatus;
    private String email;

    private String pincode;
    private String address;
    private String nextFollowUp;
    private int lastForward;
    private String leadsourceinner;//how lead came into system for eg Api/BulkUpload/Etc
    private String attachmentList;
    private String createBy;
    private String createDate;
    private String updateBy;
    private String updateDate;
    private String convertDate;
    private int dateFilter;

    private String leadPriority;// THIS FIELD IS NOW BEING USED FOR CAMPAIGN_NAME

    //new filed for momsbelief
    private String parentName;
    private String childName;
    private String dob;
    private String childDevDelay;
    private String professionFilled;// USED AS ENABLER FOR MOMSBELIEF
    private String childPlayPattern;// USED AS BPO FOR MOMSBELIEF
    @Column(columnDefinition= "VARCHAR(20)")
    private String typeSchool;//USED AS ALTERATE PHONE NUMBER FOR MOMSBELIEF
    private String mbopsParentId;// USED AS PARENT ID (RETURNED BY MBOPS api)
    private String mbopsChildId;// USED AS CHIILD ID (RETURNED BY MBOPS api)
    private String doesChildGoToSchool;
    private String mbopsChildEnrollId;
    private String assessmentNotes;
    private String assessmentDate;
    private int assessmentDateFilter;
    private String pageName;
    private String seekingSupport;
    private String supportFor;
    private String interventionAreas;
    private String developmentTime;
    private String seizureHistory;
    private String callReqCstTeam;
    private String personAssigned;
    private String assignmentDate;
    private String reviewResult;
    private String approved;

    private String formalAssessment;
    private String ongoingTherapy;
    private String typeTherapy;

    private String callComments;
    private String cpName;
    private String registeredInMb;
    private String registrationDate;
    private String gender;
    private String relation;
    private String childLanguage;
    private String parentLanguage;
    private String learningConcern;
    private String difficultyInFriendship;
    private int payment;
    private String clinicalCallTime;
    private String preferredCallingTime;//created on 12 oct 2021 to store time when user wants to get a call from team
    private String preferredCallingSlot;//created on 12 oct 2021 to store slot as per time when user wants to get a call from team
    private String pgBdName;
    private String pgBdManager;
    private String supportOption;
    //private String campaignName;
    private String keyword;
    private long adId;
    private String leadDate;// ACTUAL DATE OF LEAD WHEN IT WAS CREATED (NOT ENTERED IN TO SYSTEM)
    private int leadDatefilter;
    private String urnNumber;
    private int updatedatefilter;

    private String profilingAgent;//agent who moved lead to Profiling Done stage
    private int profilingAgentId;//ID of agent who moved lead to Profiling Done stage
    private String profilingDate;
    private int profilingDateFilter;

    private String interestedAgent;//agent who moved the lead in Interested stage
    private String interestedDate;
    private int interestedDateFilter;
    private String lastQueryDate;
    private int lastQueryDateFilter;
    @Transient
    private List<LogEventDao> logEvents;


    @Transient
    private String username;
    private int convertDateFilter;
    private String leadStage;
    private boolean leadConvert;
    private String timeTakenToConvert;
    @Transient
    private String creatorName;
    private int c2cAttempts;
    private short counter;
    private int leadScore;
    private String adFormData;
    private String messenger;
    private String active;
    private String pre_Gross_Motor_Skills1;
    private String pre_Gross_Motor_Skills2;
//    ================================== PRE SCHOOL ASSESSMENT FIELDS ============
//1	Can walk independently with good balance
//2	Can kick a ball forward without falling
//3	Can walk up and down stairs without assistance
//4	Can hop forward on one foot without support
//5	Can throw a ball and hit a target with a dominant hand.
//6	Can jump rope without assistance.
    private String pre_Gross_Motor_Skills3;
    private String pre_Gross_Motor_Skills4;
    private String pre_Gross_Motor_Skills5;
    private String pre_Gross_Motor_Skills6;
    private String pre_Fine_motor_Skills1;
    private String pre_Fine_motor_Skills2;

//    1	Can remove forms from a form board or inset puzzle like shapes or animals etc
//2	Can scribble linear and/or circular patterns spontaneously.
//            3	Can use pads of fingertips to grasp pencil or tripod grip.
//4	Can string 4 large beads.
//            5	Can hold paper with one hand while drawing or writing with other hand.
//            6	Can draw a person with 4 or more parts
//7	Can cut with scissors, following a line.
//            8	Can imitate vertical and horizontal markings
//9	Can copy a circle
//10	Can copy the letters
    private String pre_Fine_motor_Skills3;
    private String pre_Fine_motor_Skills4;
    private String pre_Fine_motor_Skills5;
    private String pre_Fine_motor_Skills6;
    private String pre_Fine_motor_Skills7;
    private String pre_Fine_motor_Skills8;
    private String pre_Fine_motor_Skills9;
    private String pre_Fine_motor_Skills10;
    private String followUpTime;
    private String followUpMessage;
    private byte otpStatus;
    private String pre_Self_help1;
    private String pre_Self_help2;
    private String pre_Self_help3;
    private String pre_Self_help4;
    private String pre_Self_help5;
    private String pre_Self_help6;
    private String pre_Self_help7;
    private String pre_Self_help8;
//    1	Can use spoon or fork for eating solid food
//2	Can drink from a cup without assistance (little SPILLING is okay)
//3	Can undo atleast two of these - large buttons, snaps, shoes laces, zipper,velcro etc
//4	Can pull pants/underpants down


//5	Can put on shoes (even if he or she put on the wrong foot)
//6	Can express need to use the toilet
//7	Can control his or her bowel movements
//8	Can washes hands independently
//9	Can take care of toileting needs (i.e undressing, cleaning, and dressing)
//10	Can manipulates large buttons, velcro, zippers independently
    private String pre_Self_help9;
    private String pre_Self_help10;
    private String pre_Social_Emotional1;
    private String pre_Social_Emotional2;
    private String pre_Social_Emotional3;
    private String pre_Social_Emotional4;
    private String pre_Social_Emotional5;
    private String pre_Social_Emotional6;
    private String pre_Social_Emotional7;
    private String pre_Social_Emotional8;

//    1	Can identify self as boy or a girl
//2	Can greet familiar adults spontaneously
//3	Can imitate or help in simple house hold tasks
//4	Can participate in group activities
//5	Can name his or her friends
//6	Can understand rules of simple games when playing with adults or peers
//7	Can waits for his turn for adults or teachers attention
//8	Can describe his or her feelings i.e says I am sad or happy or mad
//9	Can recognize the feelings of others
//10	Can show responsibility and admits errors or wrong doing i.e says sorry when made a mistake
    private String pre_Social_Emotional9;
    private String pre_Social_Emotional10;
    private String pre_Cognitive1;
    private String pre_Cognitive2;
    private String pre_Cognitive3;
    private String pre_Cognitive4;
    private String pre_Cognitive5;
    private String pre_Cognitive6;
    private String pre_Cognitive7;
    private String pre_Cognitive8;

//    1	Can point to atleast twenty things or pictures when they are named.
//            2	Can show interest and enjoyment in age appropriate books and printed material?
//            3	Can match colors or point correctly to atleast two colors.
//            4	Can say size words like large/big or little/small correctly?
//            5	Can dress up and play/act pretending to be someone else?
//            6	Can name five different colors?
//            7	Can know the difference between living and nonliving things.
//8	Can attend to a learning task /story in a small group for 5 minutes.
//9	Can follow two or more step directions or instructions
//10	Can answer simple logical questions like " What do we eat"?
//            11	Can say alphabets or letter names by rote?
    private String pre_Cognitive9;
    private String pre_Cognitive10;
    private String pre_Cognitive11;
    private String pre_Communication1;
    private String pre_Communication2;
    private String pre_Communication3;
    private String pre_Communication4;
    private String pre_Communication5;
    private String pre_Communication6;
    private String pre_Communication7;
    private String pre_Communication8;
    private String pre_Communication9;

//    1	Can identify his or her own body parts like nose, eyes, ears
//2	Can look at or point to objects across the room when it is named
//3	Can use 10 or more words
//4	Can say two or three words sentences.
//5	Can correctly use words like me, I, Mine or You
//6	Can respond to who or what questions e.g "who gives us milk" or "what do you do with a pen"
//            7	Can tell his or her first and last name
//8	Can use pronouns like I, you and me
//9	Can respond with yes or no appropriately
//10	Can ask questions that begin with who and where
//11	Can answer WH questions - what, who, when, where, why, and how
//12	Can use 5-6 words sentences
//13	Can recall events from a story presented orally
//14	Can communicate his or her experiences clearly enough for other to understand
//15	Can recognize a printing of his or her first and last names
//16	Can read a simple story aloud so that someone who is only listening can follow the story
//17	Can engage in a meaningful conversations with adults or peers
//18	Can describe what is happening in a picture
    private String pre_Communication10;
    private String pre_Communication11;
    private String pre_Communication12;
    private String pre_Communication13;
    private String pre_Communication14;
    private String pre_Communication15;
    private String pre_Communication16;
    private String pre_Communication17;
    private String pre_Communication18;
    private String pre_Behaviors1;
    private String pre_Behaviors2;
    private String pre_Behaviors3;
    private String pre_Behaviors4;
    private String pre_Behaviors5;
    private String pre_Behaviors6;
    private int mainlistening;
    private int mainloralexpression;
    private int mainbasicreading;

//    1	Inappropriate movements like fidgeting, walking and running excessively
//2	Inappropriate vocalizations like talking out, excessive crying or teasing or jargon speech
//3	Repetive body movements like rocking, toe walking, spinning etc
//4	Poor peer interactions
//5	Self absorbed or does not relate to other adults or children
//6	Difficulty transitioning from one activity to another
    private int mainreadingcomprehension;
    private int mainmathcalculations;
    private int mainmathreasoning;
    private int mainwrittenexpression;
    private int mainbehavior;
    private int pregrossmotorskills;

//    =================== FIELDS TO STORE SUM OF OPTIONS GROUPS
    private int prefinemotorskills;
    private int preselfhelp;
    private int presocialemotional;
    private int precognitive;
    private int precommunication;
    private int prebehaviors;
    private String main_Listening1;
    private String main_Listening2;
    private String main_Listening3;
    private String main_Listening4;
    private String main_Listening5;
    private String main_Listening6;
    private String main_Listening7;
    private String main_Listening8;
    private String main_Listening9;
    private String main_Listening10;
    private String main_Listening11;
    private String main_Oral_Expression_Speaking1;
    private String main_Oral_Expression_Speaking2;
    private String main_Oral_Expression_Speaking3;
    private String main_Oral_Expression_Speaking4;
    private String main_Oral_Expression_Speaking5;
    private String main_Oral_Expression_Speaking6;
    private String main_Oral_Expression_Speaking7;
    private String main_Oral_Expression_Speaking8;
    private String main_Oral_Expression_Speaking9;
    private String main_Oral_Expression_Speaking10;
    private String main_Oral_Expression_Speaking11;
    private String main_Oral_Expression_Speaking12;
    private String main_Basic_Reading1;
    private String main_Basic_Reading2;
    private String main_Basic_Reading3;
    private String main_Basic_Reading4;
    private String main_Basic_Reading5;
    private String main_Basic_Reading6;
    private String main_Basic_Reading7;
    private String main_Basic_Reading8;
    private String main_Basic_Reading9;
    private String main_Basic_Reading10;
    private String main_Basic_Reading11;
    private String main_Basic_Reading12;
    private String main_Basic_Reading13;
    private String main_Reading_Comprehension1;
    private String main_Reading_Comprehension2;
    private String main_Reading_Comprehension3;
    private String main_Reading_Comprehension4;
    private String main_Reading_Comprehension5;
    private String main_Reading_Comprehension6;
    private String main_Reading_Comprehension7;
    private String main_Math_Calculations1;
    private String main_Math_Calculations2;
    private String main_Math_Calculations3;
    private String main_Math_Calculations4;
    private String main_Math_Calculations5;
    private String main_Math_Calculations6;
    private String main_Math_Calculations7;
    private String main_Math_Calculations8;
    private String main_Math_Calculations9;
    private String main_Math_Calculations10;
    private String main_Math_Reasoning1;
    private String main_Math_Reasoning2;
    private String main_Math_Reasoning3;
    private String main_Math_Reasoning4;
    private String main_Math_Reasoning5;
    private String main_Math_Reasoning6;
    private String main_Math_Reasoning7;
    private String main_Written_Expression1;
    private String main_Written_Expression2;
    private String main_Written_Expression3;
    private String main_Written_Expression4;
    private String main_Written_Expression5;
    private String main_Written_Expression6;
    private String main_Written_Expression7;
    private String main_Written_Expression8;
    private String main_Written_Expression9;
    private String main_Written_Expression10;
    private String main_Written_Expression11;
    private String main_Written_Expression12;
    private String main_Behavior1;
    private String main_Behavior2;
    private String main_Behavior3;
    private String main_Behavior4;
    private String main_Behavior5;
    private String main_Behavior6;
    private String main_Behavior7;
    private String main_Behavior8;
    private String main_Behavior9;
    private String main_Behavior10;
    private String main_Behavior11;
    private String main_Behavior12;
    private String main_Behavior13;
    private String main_Behavior14;
    private String main_Behavior15;
    private boolean paymentStatus;

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public byte getOtpStatus() {
        return otpStatus;
    }

    public void setOtpStatus(byte otpStatus) {
        this.otpStatus = otpStatus;
    }

    public String getFollowUpTime() {
        return followUpTime;
    }

    public void setFollowUpTime(String followUpTime) {
        this.followUpTime = followUpTime;
    }

    public String getFollowUpMessage() {
        return followUpMessage;
    }

    public void setFollowUpMessage(String followUpMessage) {
        this.followUpMessage = followUpMessage;
    }

    @Override
    public String toString() {
        return "LeadMasterDao{" +
                "id=" + id +
                ", salutation='" + salutation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", leadSource='" + leadSource + '\'' +
                ", interestedProduct='" + interestedProduct + '\'' +
                ", leadStatus='" + leadStatus + '\'' +
                ", clientType='" + clientType + '\'' +
                ", company='" + company + '\'' +
                ", prospectiveBuissness=" + prospectiveBuissness +
                ", descrip='" + descrip + '\'' +
                ", country='" + country + '\'' +
                ", countryId=" + countryId +
                ", state='" + state + '\'' +
                ", stateId=" + stateId +
                ", city='" + city + '\'' +
                ", callStatus='" + callStatus + '\'' +
                ", email='" + email + '\'' +
                ", pincode='" + pincode + '\'' +
                ", address='" + address + '\'' +
                ", nextFollowUp='" + nextFollowUp + '\'' +
                ", lastForward=" + lastForward +
                ", leadsourceinner='" + leadsourceinner + '\'' +
                ", attachmentList='" + attachmentList + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", convertDate='" + convertDate + '\'' +
                ", dateFilter=" + dateFilter +
                ", leadPriority='" + leadPriority + '\'' +
                ", parentName='" + parentName + '\'' +
                ", childName='" + childName + '\'' +
                ", dob='" + dob + '\'' +
                ", childDevDelay='" + childDevDelay + '\'' +
                ", professionFilled='" + professionFilled + '\'' +
                ", childPlayPattern='" + childPlayPattern + '\'' +
                ", typeSchool='" + typeSchool + '\'' +
                ", mbopsParentId='" + mbopsParentId + '\'' +
                ", mbopsChildId='" + mbopsChildId + '\'' +
                ", doesChildGoToSchool='" + doesChildGoToSchool + '\'' +
                ", mbopsChildEnrollId='" + mbopsChildEnrollId + '\'' +
                ", assessmentNotes='" + assessmentNotes + '\'' +
                ", assessmentDate='" + assessmentDate + '\'' +
                ", assessmentDateFilter=" + assessmentDateFilter +
                ", pageName='" + pageName + '\'' +
                ", seekingSupport='" + seekingSupport + '\'' +
                ", supportFor='" + supportFor + '\'' +
                ", interventionAreas='" + interventionAreas + '\'' +
                ", developmentTime='" + developmentTime + '\'' +
                ", seizureHistory='" + seizureHistory + '\'' +
                ", callReqCstTeam='" + callReqCstTeam + '\'' +
                ", personAssigned='" + personAssigned + '\'' +
                ", assignmentDate='" + assignmentDate + '\'' +
                ", reviewResult='" + reviewResult + '\'' +
                ", approved='" + approved + '\'' +
                ", formalAssessment='" + formalAssessment + '\'' +
                ", ongoingTherapy='" + ongoingTherapy + '\'' +
                ", typeTherapy='" + typeTherapy + '\'' +
                ", callComments='" + callComments + '\'' +
                ", cpName='" + cpName + '\'' +
                ", registeredInMb='" + registeredInMb + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", gender='" + gender + '\'' +
                ", relation='" + relation + '\'' +
                ", childLanguage='" + childLanguage + '\'' +
                ", parentLanguage='" + parentLanguage + '\'' +
                ", learningConcern='" + learningConcern + '\'' +
                ", difficultyInFriendship='" + difficultyInFriendship + '\'' +
                ", payment=" + payment +
                ", clinicalCallTime='" + clinicalCallTime + '\'' +
                ", preferredCallingTime='" + preferredCallingTime + '\'' +
                ", preferredCallingSlot='" + preferredCallingSlot + '\'' +
                ", pgBdName='" + pgBdName + '\'' +
                ", pgBdManager='" + pgBdManager + '\'' +
                ", supportOption='" + supportOption + '\'' +
                ", keyword='" + keyword + '\'' +
                ", adId=" + adId +
                ", leadDate='" + leadDate + '\'' +
                ", leadDatefilter=" + leadDatefilter +
                ", urnNumber='" + urnNumber + '\'' +
                ", updatedatefilter=" + updatedatefilter +
                ", profilingAgent='" + profilingAgent + '\'' +
                ", profilingAgentId=" + profilingAgentId +
                ", profilingDate='" + profilingDate + '\'' +
                ", profilingDateFilter=" + profilingDateFilter +
                ", interestedAgent='" + interestedAgent + '\'' +
                ", interestedDate='" + interestedDate + '\'' +
                ", interestedDateFilter=" + interestedDateFilter +
                ", lastQueryDate='" + lastQueryDate + '\'' +
                ", lastQueryDateFilter=" + lastQueryDateFilter +
                ", username='" + username + '\'' +
                ", convertDateFilter=" + convertDateFilter +
                ", leadStage='" + leadStage + '\'' +
                ", leadConvert=" + leadConvert +
                ", timeTakenToConvert='" + timeTakenToConvert + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", c2cAttempts=" + c2cAttempts +
                ", counter=" + counter +
                ", leadScore=" + leadScore +
                ", adFormData='" + adFormData + '\'' +
                ", messenger='" + messenger + '\'' +
                ", active='" + active + '\'' +
                ", pre_Gross_Motor_Skills1='" + pre_Gross_Motor_Skills1 + '\'' +
                ", pre_Gross_Motor_Skills2='" + pre_Gross_Motor_Skills2 + '\'' +
                ", pre_Gross_Motor_Skills3='" + pre_Gross_Motor_Skills3 + '\'' +
                ", pre_Gross_Motor_Skills4='" + pre_Gross_Motor_Skills4 + '\'' +
                ", pre_Gross_Motor_Skills5='" + pre_Gross_Motor_Skills5 + '\'' +
                ", pre_Gross_Motor_Skills6='" + pre_Gross_Motor_Skills6 + '\'' +
                ", pre_Fine_motor_Skills1='" + pre_Fine_motor_Skills1 + '\'' +
                ", pre_Fine_motor_Skills2='" + pre_Fine_motor_Skills2 + '\'' +
                ", pre_Fine_motor_Skills3='" + pre_Fine_motor_Skills3 + '\'' +
                ", pre_Fine_motor_Skills4='" + pre_Fine_motor_Skills4 + '\'' +
                ", pre_Fine_motor_Skills5='" + pre_Fine_motor_Skills5 + '\'' +
                ", pre_Fine_motor_Skills6='" + pre_Fine_motor_Skills6 + '\'' +
                ", pre_Fine_motor_Skills7='" + pre_Fine_motor_Skills7 + '\'' +
                ", pre_Fine_motor_Skills8='" + pre_Fine_motor_Skills8 + '\'' +
                ", pre_Fine_motor_Skills9='" + pre_Fine_motor_Skills9 + '\'' +
                ", pre_Fine_motor_Skills10='" + pre_Fine_motor_Skills10 + '\'' +
                ", followUpTime='" + followUpTime + '\'' +
                ", followUpMessage='" + followUpMessage + '\'' +
                ", otpStatus=" + otpStatus +
                ", pre_Self_help1='" + pre_Self_help1 + '\'' +
                ", pre_Self_help2='" + pre_Self_help2 + '\'' +
                ", pre_Self_help3='" + pre_Self_help3 + '\'' +
                ", pre_Self_help4='" + pre_Self_help4 + '\'' +
                ", pre_Self_help5='" + pre_Self_help5 + '\'' +
                ", pre_Self_help6='" + pre_Self_help6 + '\'' +
                ", pre_Self_help7='" + pre_Self_help7 + '\'' +
                ", pre_Self_help8='" + pre_Self_help8 + '\'' +
                ", pre_Self_help9='" + pre_Self_help9 + '\'' +
                ", pre_Self_help10='" + pre_Self_help10 + '\'' +
                ", pre_Social_Emotional1='" + pre_Social_Emotional1 + '\'' +
                ", pre_Social_Emotional2='" + pre_Social_Emotional2 + '\'' +
                ", pre_Social_Emotional3='" + pre_Social_Emotional3 + '\'' +
                ", pre_Social_Emotional4='" + pre_Social_Emotional4 + '\'' +
                ", pre_Social_Emotional5='" + pre_Social_Emotional5 + '\'' +
                ", pre_Social_Emotional6='" + pre_Social_Emotional6 + '\'' +
                ", pre_Social_Emotional7='" + pre_Social_Emotional7 + '\'' +
                ", pre_Social_Emotional8='" + pre_Social_Emotional8 + '\'' +
                ", pre_Social_Emotional9='" + pre_Social_Emotional9 + '\'' +
                ", pre_Social_Emotional10='" + pre_Social_Emotional10 + '\'' +
                ", pre_Cognitive1='" + pre_Cognitive1 + '\'' +
                ", pre_Cognitive2='" + pre_Cognitive2 + '\'' +
                ", pre_Cognitive3='" + pre_Cognitive3 + '\'' +
                ", pre_Cognitive4='" + pre_Cognitive4 + '\'' +
                ", pre_Cognitive5='" + pre_Cognitive5 + '\'' +
                ", pre_Cognitive6='" + pre_Cognitive6 + '\'' +
                ", pre_Cognitive7='" + pre_Cognitive7 + '\'' +
                ", pre_Cognitive8='" + pre_Cognitive8 + '\'' +
                ", pre_Cognitive9='" + pre_Cognitive9 + '\'' +
                ", pre_Cognitive10='" + pre_Cognitive10 + '\'' +
                ", pre_Cognitive11='" + pre_Cognitive11 + '\'' +
                ", pre_Communication1='" + pre_Communication1 + '\'' +
                ", pre_Communication2='" + pre_Communication2 + '\'' +
                ", pre_Communication3='" + pre_Communication3 + '\'' +
                ", pre_Communication4='" + pre_Communication4 + '\'' +
                ", pre_Communication5='" + pre_Communication5 + '\'' +
                ", pre_Communication6='" + pre_Communication6 + '\'' +
                ", pre_Communication7='" + pre_Communication7 + '\'' +
                ", pre_Communication8='" + pre_Communication8 + '\'' +
                ", pre_Communication9='" + pre_Communication9 + '\'' +
                ", pre_Communication10='" + pre_Communication10 + '\'' +
                ", pre_Communication11='" + pre_Communication11 + '\'' +
                ", pre_Communication12='" + pre_Communication12 + '\'' +
                ", pre_Communication13='" + pre_Communication13 + '\'' +
                ", pre_Communication14='" + pre_Communication14 + '\'' +
                ", pre_Communication15='" + pre_Communication15 + '\'' +
                ", pre_Communication16='" + pre_Communication16 + '\'' +
                ", pre_Communication17='" + pre_Communication17 + '\'' +
                ", pre_Communication18='" + pre_Communication18 + '\'' +
                ", pre_Behaviors1='" + pre_Behaviors1 + '\'' +
                ", pre_Behaviors2='" + pre_Behaviors2 + '\'' +
                ", pre_Behaviors3='" + pre_Behaviors3 + '\'' +
                ", pre_Behaviors4='" + pre_Behaviors4 + '\'' +
                ", pre_Behaviors5='" + pre_Behaviors5 + '\'' +
                ", pre_Behaviors6='" + pre_Behaviors6 + '\'' +
                ", mainlistening=" + mainlistening +
                ", mainloralexpression=" + mainloralexpression +
                ", mainbasicreading=" + mainbasicreading +
                ", mainreadingcomprehension=" + mainreadingcomprehension +
                ", mainmathcalculations=" + mainmathcalculations +
                ", mainmathreasoning=" + mainmathreasoning +
                ", mainwrittenexpression=" + mainwrittenexpression +
                ", mainbehavior=" + mainbehavior +
                ", pregrossmotorskills=" + pregrossmotorskills +
                ", prefinemotorskills=" + prefinemotorskills +
                ", preselfhelp=" + preselfhelp +
                ", presocialemotional=" + presocialemotional +
                ", precognitive=" + precognitive +
                ", precommunication=" + precommunication +
                ", prebehaviors=" + prebehaviors +
                ", main_Listening1='" + main_Listening1 + '\'' +
                ", main_Listening2='" + main_Listening2 + '\'' +
                ", main_Listening3='" + main_Listening3 + '\'' +
                ", main_Listening4='" + main_Listening4 + '\'' +
                ", main_Listening5='" + main_Listening5 + '\'' +
                ", main_Listening6='" + main_Listening6 + '\'' +
                ", main_Listening7='" + main_Listening7 + '\'' +
                ", main_Listening8='" + main_Listening8 + '\'' +
                ", main_Listening9='" + main_Listening9 + '\'' +
                ", main_Listening10='" + main_Listening10 + '\'' +
                ", main_Listening11='" + main_Listening11 + '\'' +
                ", main_Oral_Expression_Speaking1='" + main_Oral_Expression_Speaking1 + '\'' +
                ", main_Oral_Expression_Speaking2='" + main_Oral_Expression_Speaking2 + '\'' +
                ", main_Oral_Expression_Speaking3='" + main_Oral_Expression_Speaking3 + '\'' +
                ", main_Oral_Expression_Speaking4='" + main_Oral_Expression_Speaking4 + '\'' +
                ", main_Oral_Expression_Speaking5='" + main_Oral_Expression_Speaking5 + '\'' +
                ", main_Oral_Expression_Speaking6='" + main_Oral_Expression_Speaking6 + '\'' +
                ", main_Oral_Expression_Speaking7='" + main_Oral_Expression_Speaking7 + '\'' +
                ", main_Oral_Expression_Speaking8='" + main_Oral_Expression_Speaking8 + '\'' +
                ", main_Oral_Expression_Speaking9='" + main_Oral_Expression_Speaking9 + '\'' +
                ", main_Oral_Expression_Speaking10='" + main_Oral_Expression_Speaking10 + '\'' +
                ", main_Oral_Expression_Speaking11='" + main_Oral_Expression_Speaking11 + '\'' +
                ", main_Oral_Expression_Speaking12='" + main_Oral_Expression_Speaking12 + '\'' +
                ", main_Basic_Reading1='" + main_Basic_Reading1 + '\'' +
                ", main_Basic_Reading2='" + main_Basic_Reading2 + '\'' +
                ", main_Basic_Reading3='" + main_Basic_Reading3 + '\'' +
                ", main_Basic_Reading4='" + main_Basic_Reading4 + '\'' +
                ", main_Basic_Reading5='" + main_Basic_Reading5 + '\'' +
                ", main_Basic_Reading6='" + main_Basic_Reading6 + '\'' +
                ", main_Basic_Reading7='" + main_Basic_Reading7 + '\'' +
                ", main_Basic_Reading8='" + main_Basic_Reading8 + '\'' +
                ", main_Basic_Reading9='" + main_Basic_Reading9 + '\'' +
                ", main_Basic_Reading10='" + main_Basic_Reading10 + '\'' +
                ", main_Basic_Reading11='" + main_Basic_Reading11 + '\'' +
                ", main_Basic_Reading12='" + main_Basic_Reading12 + '\'' +
                ", main_Basic_Reading13='" + main_Basic_Reading13 + '\'' +
                ", main_Reading_Comprehension1='" + main_Reading_Comprehension1 + '\'' +
                ", main_Reading_Comprehension2='" + main_Reading_Comprehension2 + '\'' +
                ", main_Reading_Comprehension3='" + main_Reading_Comprehension3 + '\'' +
                ", main_Reading_Comprehension4='" + main_Reading_Comprehension4 + '\'' +
                ", main_Reading_Comprehension5='" + main_Reading_Comprehension5 + '\'' +
                ", main_Reading_Comprehension6='" + main_Reading_Comprehension6 + '\'' +
                ", main_Reading_Comprehension7='" + main_Reading_Comprehension7 + '\'' +
                ", main_Math_Calculations1='" + main_Math_Calculations1 + '\'' +
                ", main_Math_Calculations2='" + main_Math_Calculations2 + '\'' +
                ", main_Math_Calculations3='" + main_Math_Calculations3 + '\'' +
                ", main_Math_Calculations4='" + main_Math_Calculations4 + '\'' +
                ", main_Math_Calculations5='" + main_Math_Calculations5 + '\'' +
                ", main_Math_Calculations6='" + main_Math_Calculations6 + '\'' +
                ", main_Math_Calculations7='" + main_Math_Calculations7 + '\'' +
                ", main_Math_Calculations8='" + main_Math_Calculations8 + '\'' +
                ", main_Math_Calculations9='" + main_Math_Calculations9 + '\'' +
                ", main_Math_Calculations10='" + main_Math_Calculations10 + '\'' +
                ", main_Math_Reasoning1='" + main_Math_Reasoning1 + '\'' +
                ", main_Math_Reasoning2='" + main_Math_Reasoning2 + '\'' +
                ", main_Math_Reasoning3='" + main_Math_Reasoning3 + '\'' +
                ", main_Math_Reasoning4='" + main_Math_Reasoning4 + '\'' +
                ", main_Math_Reasoning5='" + main_Math_Reasoning5 + '\'' +
                ", main_Math_Reasoning6='" + main_Math_Reasoning6 + '\'' +
                ", main_Math_Reasoning7='" + main_Math_Reasoning7 + '\'' +
                ", main_Written_Expression1='" + main_Written_Expression1 + '\'' +
                ", main_Written_Expression2='" + main_Written_Expression2 + '\'' +
                ", main_Written_Expression3='" + main_Written_Expression3 + '\'' +
                ", main_Written_Expression4='" + main_Written_Expression4 + '\'' +
                ", main_Written_Expression5='" + main_Written_Expression5 + '\'' +
                ", main_Written_Expression6='" + main_Written_Expression6 + '\'' +
                ", main_Written_Expression7='" + main_Written_Expression7 + '\'' +
                ", main_Written_Expression8='" + main_Written_Expression8 + '\'' +
                ", main_Written_Expression9='" + main_Written_Expression9 + '\'' +
                ", main_Written_Expression10='" + main_Written_Expression10 + '\'' +
                ", main_Written_Expression11='" + main_Written_Expression11 + '\'' +
                ", main_Written_Expression12='" + main_Written_Expression12 + '\'' +
                ", main_Behavior1='" + main_Behavior1 + '\'' +
                ", main_Behavior2='" + main_Behavior2 + '\'' +
                ", main_Behavior3='" + main_Behavior3 + '\'' +
                ", main_Behavior4='" + main_Behavior4 + '\'' +
                ", main_Behavior5='" + main_Behavior5 + '\'' +
                ", main_Behavior6='" + main_Behavior6 + '\'' +
                ", main_Behavior7='" + main_Behavior7 + '\'' +
                ", main_Behavior8='" + main_Behavior8 + '\'' +
                ", main_Behavior9='" + main_Behavior9 + '\'' +
                ", main_Behavior10='" + main_Behavior10 + '\'' +
                ", main_Behavior11='" + main_Behavior11 + '\'' +
                ", main_Behavior12='" + main_Behavior12 + '\'' +
                ", main_Behavior13='" + main_Behavior13 + '\'' +
                ", main_Behavior14='" + main_Behavior14 + '\'' +
                ", main_Behavior15='" + main_Behavior15 + '\'' +
                ", paymentStatus=" + paymentStatus +
                '}';
    }

    public int getMainlistening() {
        return mainlistening;
    }

    public void setMainlistening(int mainlistening) {
        this.mainlistening = mainlistening;
    }

    public int getMainloralexpression() {
        return mainloralexpression;
    }

    public void setMainloralexpression(int mainloralexpression) {
        this.mainloralexpression = mainloralexpression;
    }

    public int getMainbasicreading() {
        return mainbasicreading;
    }

    public void setMainbasicreading(int mainbasicreading) {
        this.mainbasicreading = mainbasicreading;
    }

    public int getMainreadingcomprehension() {
        return mainreadingcomprehension;
    }

    public void setMainreadingcomprehension(int mainreadingcomprehension) {
        this.mainreadingcomprehension = mainreadingcomprehension;
    }

    public int getMainmathcalculations() {
        return mainmathcalculations;
    }

    public void setMainmathcalculations(int mainmathcalculations) {
        this.mainmathcalculations = mainmathcalculations;
    }

    public int getMainmathreasoning() {
        return mainmathreasoning;
    }

    public void setMainmathreasoning(int mainmathreasoning) {
        this.mainmathreasoning = mainmathreasoning;
    }

    public int getMainwrittenexpression() {
        return mainwrittenexpression;
    }

    public void setMainwrittenexpression(int mainwrittenexpression) {
        this.mainwrittenexpression = mainwrittenexpression;
    }

    public int getMainbehavior() {
        return mainbehavior;
    }

    public void setMainbehavior(int mainbehavior) {
        this.mainbehavior = mainbehavior;
    }

    public int getPregrossmotorskills() {
        return pregrossmotorskills;
    }

    public void setPregrossmotorskills(int pregrossmotorskills) {
        this.pregrossmotorskills = pregrossmotorskills;
    }

    public int getPrefinemotorskills() {
        return prefinemotorskills;
    }

    public void setPrefinemotorskills(int prefinemotorskills) {
        this.prefinemotorskills = prefinemotorskills;
    }

    public int getPreselfhelp() {
        return preselfhelp;
    }

    public void setPreselfhelp(int preselfhelp) {
        this.preselfhelp = preselfhelp;
    }

    public int getPresocialemotional() {
        return presocialemotional;
    }

    public void setPresocialemotional(int presocialemotional) {
        this.presocialemotional = presocialemotional;
    }

    public int getPrecognitive() {
        return precognitive;
    }

    public void setPrecognitive(int precognitive) {
        this.precognitive = precognitive;
    }

    public int getPrecommunication() {
        return precommunication;
    }

    public void setPrecommunication(int precommunication) {
        this.precommunication = precommunication;
    }

    public int getPrebehaviors() {
        return prebehaviors;
    }

    public void setPrebehaviors(int prebehaviors) {
        this.prebehaviors = prebehaviors;
    }

    public String getAdFormData() {
        return adFormData;
    }

    public void setAdFormData(String adFormData) {
        this.adFormData = adFormData;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    //===============================================================
    public String getPre_Gross_Motor_Skills1() {
        return pre_Gross_Motor_Skills1;
    }

    public void setPre_Gross_Motor_Skills1(String pre_Gross_Motor_Skills1) {
        this.pre_Gross_Motor_Skills1 = pre_Gross_Motor_Skills1;
    }

    public String getPre_Gross_Motor_Skills2() {
        return pre_Gross_Motor_Skills2;
    }

    public void setPre_Gross_Motor_Skills2(String pre_Gross_Motor_Skills2) {
        this.pre_Gross_Motor_Skills2 = pre_Gross_Motor_Skills2;
    }

    public String getPre_Gross_Motor_Skills3() {
        return pre_Gross_Motor_Skills3;
    }

    public void setPre_Gross_Motor_Skills3(String pre_Gross_Motor_Skills3) {
        this.pre_Gross_Motor_Skills3 = pre_Gross_Motor_Skills3;
    }

    public String getPre_Gross_Motor_Skills4() {
        return pre_Gross_Motor_Skills4;
    }

    public void setPre_Gross_Motor_Skills4(String pre_Gross_Motor_Skills4) {
        this.pre_Gross_Motor_Skills4 = pre_Gross_Motor_Skills4;
    }

    public String getPre_Gross_Motor_Skills5() {
        return pre_Gross_Motor_Skills5;
    }

    public void setPre_Gross_Motor_Skills5(String pre_Gross_Motor_Skills5) {
        this.pre_Gross_Motor_Skills5 = pre_Gross_Motor_Skills5;
    }

    public String getPre_Gross_Motor_Skills6() {
        return pre_Gross_Motor_Skills6;
    }

    public void setPre_Gross_Motor_Skills6(String pre_Gross_Motor_Skills6) {
        this.pre_Gross_Motor_Skills6 = pre_Gross_Motor_Skills6;
    }

    public String getPre_Fine_motor_Skills1() {
        return pre_Fine_motor_Skills1;
    }

    public void setPre_Fine_motor_Skills1(String pre_Fine_motor_Skills1) {
        this.pre_Fine_motor_Skills1 = pre_Fine_motor_Skills1;
    }

    public String getPre_Fine_motor_Skills2() {
        return pre_Fine_motor_Skills2;
    }

    public void setPre_Fine_motor_Skills2(String pre_Fine_motor_Skills2) {
        this.pre_Fine_motor_Skills2 = pre_Fine_motor_Skills2;
    }

    public String getPre_Fine_motor_Skills3() {
        return pre_Fine_motor_Skills3;
    }

    public void setPre_Fine_motor_Skills3(String pre_Fine_motor_Skills3) {
        this.pre_Fine_motor_Skills3 = pre_Fine_motor_Skills3;
    }

    public String getPre_Fine_motor_Skills4() {
        return pre_Fine_motor_Skills4;
    }

    public void setPre_Fine_motor_Skills4(String pre_Fine_motor_Skills4) {
        this.pre_Fine_motor_Skills4 = pre_Fine_motor_Skills4;
    }

    public String getPre_Fine_motor_Skills5() {
        return pre_Fine_motor_Skills5;
    }

    public void setPre_Fine_motor_Skills5(String pre_Fine_motor_Skills5) {
        this.pre_Fine_motor_Skills5 = pre_Fine_motor_Skills5;
    }

    public String getPre_Fine_motor_Skills6() {
        return pre_Fine_motor_Skills6;
    }

    public void setPre_Fine_motor_Skills6(String pre_Fine_motor_Skills6) {
        this.pre_Fine_motor_Skills6 = pre_Fine_motor_Skills6;
    }

    public String getPre_Fine_motor_Skills7() {
        return pre_Fine_motor_Skills7;
    }

    public void setPre_Fine_motor_Skills7(String pre_Fine_motor_Skills7) {
        this.pre_Fine_motor_Skills7 = pre_Fine_motor_Skills7;
    }

    public String getPre_Fine_motor_Skills8() {
        return pre_Fine_motor_Skills8;
    }

    public void setPre_Fine_motor_Skills8(String pre_Fine_motor_Skills8) {
        this.pre_Fine_motor_Skills8 = pre_Fine_motor_Skills8;
    }

    public String getPre_Fine_motor_Skills9() {
        return pre_Fine_motor_Skills9;
    }

    public void setPre_Fine_motor_Skills9(String pre_Fine_motor_Skills9) {
        this.pre_Fine_motor_Skills9 = pre_Fine_motor_Skills9;
    }

    public String getPre_Fine_motor_Skills10() {
        return pre_Fine_motor_Skills10;
    }

    public void setPre_Fine_motor_Skills10(String pre_Fine_motor_Skills10) {
        this.pre_Fine_motor_Skills10 = pre_Fine_motor_Skills10;
    }

    public String getPre_Self_help1() {
        return pre_Self_help1;
    }

    public void setPre_Self_help1(String pre_Self_help1) {
        this.pre_Self_help1 = pre_Self_help1;
    }

    public String getPre_Self_help2() {
        return pre_Self_help2;
    }

    public void setPre_Self_help2(String pre_Self_help2) {
        this.pre_Self_help2 = pre_Self_help2;
    }

    public String getPre_Self_help3() {
        return pre_Self_help3;
    }

    public void setPre_Self_help3(String pre_Self_help3) {
        this.pre_Self_help3 = pre_Self_help3;
    }

    public String getPre_Self_help4() {
        return pre_Self_help4;
    }

    public void setPre_Self_help4(String pre_Self_help4) {
        this.pre_Self_help4 = pre_Self_help4;
    }

    public String getPre_Self_help5() {
        return pre_Self_help5;
    }

    public void setPre_Self_help5(String pre_Self_help5) {
        this.pre_Self_help5 = pre_Self_help5;
    }

    public String getPre_Self_help6() {
        return pre_Self_help6;
    }

    public void setPre_Self_help6(String pre_Self_help6) {
        this.pre_Self_help6 = pre_Self_help6;
    }

    public String getPre_Self_help7() {
        return pre_Self_help7;
    }

    public void setPre_Self_help7(String pre_Self_help7) {
        this.pre_Self_help7 = pre_Self_help7;
    }

    public String getPre_Self_help8() {
        return pre_Self_help8;
    }

    public void setPre_Self_help8(String pre_Self_help8) {
        this.pre_Self_help8 = pre_Self_help8;
    }

    public String getPre_Self_help9() {
        return pre_Self_help9;
    }

    public void setPre_Self_help9(String pre_Self_help9) {
        this.pre_Self_help9 = pre_Self_help9;
    }

    public String getPre_Self_help10() {
        return pre_Self_help10;
    }

    public void setPre_Self_help10(String pre_Self_help10) {
        this.pre_Self_help10 = pre_Self_help10;
    }

    public String getPre_Social_Emotional1() {
        return pre_Social_Emotional1;
    }

    public void setPre_Social_Emotional1(String pre_Social_Emotional1) {
        this.pre_Social_Emotional1 = pre_Social_Emotional1;
    }

    //============================================================================

//========================================   MAINSTREAM SCHOOL ASSESSMENT ===========

    //	1	Asks for statements or sentences to be repeated
//	2	Often says "what?" 'Huh" etc
//	3	Fails to follow directions given verbally
//	4	Has difficulty understanding common vocabulary words
//	5	Has difficuty listening attentively to stories, tv shows and or music
//	6	Has difficulty remembering multiple commands
//	7	Repeats phrases to self over and over before understnading takes place
//	8	Answers questions inappropriately
//	9	Asks irrelevant questions after hearing a story
//	10	Confuses time concepts (before/after) and confuses direction words (front/back)
//	11	Has difficulty understanding nonliteral or abstract language like metaphors

    public String getPre_Social_Emotional2() {
        return pre_Social_Emotional2;
    }

    public void setPre_Social_Emotional2(String pre_Social_Emotional2) {
        this.pre_Social_Emotional2 = pre_Social_Emotional2;
    }

    public String getPre_Social_Emotional3() {
        return pre_Social_Emotional3;
    }

    public void setPre_Social_Emotional3(String pre_Social_Emotional3) {
        this.pre_Social_Emotional3 = pre_Social_Emotional3;
    }

    public String getPre_Social_Emotional4() {
        return pre_Social_Emotional4;
    }

    public void setPre_Social_Emotional4(String pre_Social_Emotional4) {
        this.pre_Social_Emotional4 = pre_Social_Emotional4;
    }

    public String getPre_Social_Emotional5() {
        return pre_Social_Emotional5;
    }

    public void setPre_Social_Emotional5(String pre_Social_Emotional5) {
        this.pre_Social_Emotional5 = pre_Social_Emotional5;
    }

    public String getPre_Social_Emotional6() {
        return pre_Social_Emotional6;
    }

    public void setPre_Social_Emotional6(String pre_Social_Emotional6) {
        this.pre_Social_Emotional6 = pre_Social_Emotional6;
    }

    public String getPre_Social_Emotional7() {
        return pre_Social_Emotional7;
    }

//	1	Speaks in single word statements only
//	2	Speaks in small phrases only
//	3	Has difficulty speaking spontaneously
//	4	Has slow or labored speech
//	5	Has mis articulation in speech
//	6	Cannot repeat long sentences
//	7	Has difficulty retrieving words
//	8	Is slow in retieving words
//	9	Uses immature or bizzare patterns of language or uses immature grammer when speaking
//	10	Has difficulty relating ideas in sequence like telling stories in sequence
//	11	Contributes to class discussions with off task comments
//	12	Requires much time to respond verbally

    public void setPre_Social_Emotional7(String pre_Social_Emotional7) {
        this.pre_Social_Emotional7 = pre_Social_Emotional7;
    }

    public String getPre_Social_Emotional8() {
        return pre_Social_Emotional8;
    }

    public void setPre_Social_Emotional8(String pre_Social_Emotional8) {
        this.pre_Social_Emotional8 = pre_Social_Emotional8;
    }

    public String getPre_Social_Emotional9() {
        return pre_Social_Emotional9;
    }

    public void setPre_Social_Emotional9(String pre_Social_Emotional9) {
        this.pre_Social_Emotional9 = pre_Social_Emotional9;
    }

    public String getPre_Social_Emotional10() {
        return pre_Social_Emotional10;
    }

    public void setPre_Social_Emotional10(String pre_Social_Emotional10) {
        this.pre_Social_Emotional10 = pre_Social_Emotional10;
    }

    public String getPre_Cognitive1() {
        return pre_Cognitive1;
    }

    public void setPre_Cognitive1(String pre_Cognitive1) {
        this.pre_Cognitive1 = pre_Cognitive1;
    }

    public String getPre_Cognitive2() {
        return pre_Cognitive2;
    }

    public void setPre_Cognitive2(String pre_Cognitive2) {
        this.pre_Cognitive2 = pre_Cognitive2;
    }

    public String getPre_Cognitive3() {
        return pre_Cognitive3;
    }

//	1	Avoids reading
//	2	Behavior changes when asked to read silently or orally
//	3	Has poor memory of letter and words
//	4	Has difficulty matching letters
//	5	Is not consistent with letter sounds and blending skills
//	6	Guesses words from initial letters
//	7	Guesses words from pictorial cues
//	8	Has difficulty sounding out words correctly
//	9	Has difficulty with sight vocablary
//	10	Skips lines while reading
//	11	Uses finger to anchor self when reading
//	12	Reads high frequency sight words incorrectly like the, and, but etc
//	13	Does not observe punctuation while reading

    public void setPre_Cognitive3(String pre_Cognitive3) {
        this.pre_Cognitive3 = pre_Cognitive3;
    }

    public String getPre_Cognitive4() {
        return pre_Cognitive4;
    }

    public void setPre_Cognitive4(String pre_Cognitive4) {
        this.pre_Cognitive4 = pre_Cognitive4;
    }

    public String getPre_Cognitive5() {
        return pre_Cognitive5;
    }

    public void setPre_Cognitive5(String pre_Cognitive5) {
        this.pre_Cognitive5 = pre_Cognitive5;
    }

    public String getPre_Cognitive6() {
        return pre_Cognitive6;
    }

    public void setPre_Cognitive6(String pre_Cognitive6) {
        this.pre_Cognitive6 = pre_Cognitive6;
    }

    public String getPre_Cognitive7() {
        return pre_Cognitive7;
    }

    public void setPre_Cognitive7(String pre_Cognitive7) {
        this.pre_Cognitive7 = pre_Cognitive7;
    }

    public String getPre_Cognitive8() {
        return pre_Cognitive8;
    }

    public void setPre_Cognitive8(String pre_Cognitive8) {
        this.pre_Cognitive8 = pre_Cognitive8;
    }

    public String getPre_Cognitive9() {
        return pre_Cognitive9;
    }

    public void setPre_Cognitive9(String pre_Cognitive9) {
        this.pre_Cognitive9 = pre_Cognitive9;
    }

//	1	Has difficulty reading and following directions
//	2	Has difficulty answering questions after reading
//	3	Has difficulty in doing math word problems
//	4	Has difficulty understanding main idea of a passage
//	5	Has difficulty using content cues to guess at unkown word
//	6	Has difficulty reading independently
//	7	Cannot retell what has been read

    public String getPre_Cognitive10() {
        return pre_Cognitive10;
    }

    public void setPre_Cognitive10(String pre_Cognitive10) {
        this.pre_Cognitive10 = pre_Cognitive10;
    }

    public String getPre_Cognitive11() {
        return pre_Cognitive11;
    }

    public void setPre_Cognitive11(String pre_Cognitive11) {
        this.pre_Cognitive11 = pre_Cognitive11;
    }

    public String getPre_Communication1() {
        return pre_Communication1;
    }

    public void setPre_Communication1(String pre_Communication1) {
        this.pre_Communication1 = pre_Communication1;
    }

    public String getPre_Communication2() {
        return pre_Communication2;
    }

//	1	Confuses operational signs
//	2	Uses fingers for computation
//	3	Reverses numbers
//	4	Has difficulty with place values
//	5	Has difficulty with keeping colums straight
//	6	Has difficulty doing single digit operations (addition, subtraciton, multipication or division)
//	7	Has difficulty doing multi digit operations (addition, subtraciton, multipication or division)
//	8	Has difficulty completing sums that requires more than one operation
//	9	Has difficulty estimating answers
//	10	Completes problem in mind but unable to put on paer and in writing steps

    public void setPre_Communication2(String pre_Communication2) {
        this.pre_Communication2 = pre_Communication2;
    }

    public String getPre_Communication3() {
        return pre_Communication3;
    }

    public void setPre_Communication3(String pre_Communication3) {
        this.pre_Communication3 = pre_Communication3;
    }

    public String getPre_Communication4() {
        return pre_Communication4;
    }

    public void setPre_Communication4(String pre_Communication4) {
        this.pre_Communication4 = pre_Communication4;
    }

    public String getPre_Communication5() {
        return pre_Communication5;
    }

    public void setPre_Communication5(String pre_Communication5) {
        this.pre_Communication5 = pre_Communication5;
    }

    public String getPre_Communication6() {
        return pre_Communication6;
    }

    public void setPre_Communication6(String pre_Communication6) {
        this.pre_Communication6 = pre_Communication6;
    }

    public String getPre_Communication7() {
        return pre_Communication7;
    }

//	1	Has difficulty applying math operations to real life problems
//	2	Has difficulty telling time
//	3	Has difficulty using calendar correctly
//	4	Guesses answers instead of trying to solve problems
//	5	Has difficulty solving problems involving a sequence of steps
//	6	Has difficulty organizing a task to facilitate tis completion
//	7	Has difficulty with word probems

    public void setPre_Communication7(String pre_Communication7) {
        this.pre_Communication7 = pre_Communication7;
    }

    public String getPre_Communication8() {
        return pre_Communication8;
    }

    public void setPre_Communication8(String pre_Communication8) {
        this.pre_Communication8 = pre_Communication8;
    }

    public String getPre_Communication9() {
        return pre_Communication9;
    }

    public void setPre_Communication9(String pre_Communication9) {
        this.pre_Communication9 = pre_Communication9;
    }

    public String getPre_Communication10() {
        return pre_Communication10;
    }

    public void setPre_Communication10(String pre_Communication10) {
        this.pre_Communication10 = pre_Communication10;
    }

//	1	Has difficulty holding pencil in correct allingnment to paper
//	2	Writes slowly or awkwardly
//	3	Reverses letters like b and d
//	4	Uses extra effort to write
//	5	Misspells words phonetically
//	6	Misspells high frequnecy sight words
//	7	Fails to retain spelling words studied in word lists or spelling lists
//	8	Has difficulty expresing a complete thought in  sentence
//	9	Has difficulty expressing a complete thought in a paragraph
//	10	Has difficulty producing written work which is legible, properly spaced, capitalized and punctuated
//	11	Mixes manuscript and cursive forms
//	12	Has difficulty sequencing thoughts in a narrative

    public String getPre_Communication11() {
        return pre_Communication11;
    }

    public void setPre_Communication11(String pre_Communication11) {
        this.pre_Communication11 = pre_Communication11;
    }

    public String getPre_Communication12() {
        return pre_Communication12;
    }

    public void setPre_Communication12(String pre_Communication12) {
        this.pre_Communication12 = pre_Communication12;
    }

    public String getPre_Communication13() {
        return pre_Communication13;
    }

    public void setPre_Communication13(String pre_Communication13) {
        this.pre_Communication13 = pre_Communication13;
    }

    public String getPre_Communication14() {
        return pre_Communication14;
    }

    public void setPre_Communication14(String pre_Communication14) {
        this.pre_Communication14 = pre_Communication14;
    }

    public String getPre_Communication15() {
        return pre_Communication15;
    }

    public void setPre_Communication15(String pre_Communication15) {
        this.pre_Communication15 = pre_Communication15;
    }

    public String getPre_Communication16() {
        return pre_Communication16;
    }

    public void setPre_Communication16(String pre_Communication16) {
        this.pre_Communication16 = pre_Communication16;
    }

//	1	Listens to teacher/classmate and follows directions
//	2	Makes excessive demand on teacher time
//	3	Overactive/restless/fidgety
//	4	Impulsive
//	5	Disturbs others in class
//	6	Has diffiuclty maintaining peer relations or has poor peer interactions
//	7	Out of seat
//	8	Off task behaviors
//	9	Distractible
//	10	Talkative or makes inappropriate noises
//	11	Blurts out answers without thinking
//	12	Has trouble keeping hands and feet to self or makes repetetive motor movements
//	13	Destructive/Aggressive (kicks others, hits others, throws things etc)
//	14	Isolate self from others
//	15	Indulges in self injurious behaviors

    public String getPre_Communication17() {
        return pre_Communication17;
    }

    public void setPre_Communication17(String pre_Communication17) {
        this.pre_Communication17 = pre_Communication17;
    }

    public String getPre_Communication18() {
        return pre_Communication18;
    }

    public void setPre_Communication18(String pre_Communication18) {
        this.pre_Communication18 = pre_Communication18;
    }

    public String getPre_Behaviors1() {
        return pre_Behaviors1;
    }

    public void setPre_Behaviors1(String pre_Behaviors1) {
        this.pre_Behaviors1 = pre_Behaviors1;
    }

    public String getPre_Behaviors2() {
        return pre_Behaviors2;
    }

    public void setPre_Behaviors2(String pre_Behaviors2) {
        this.pre_Behaviors2 = pre_Behaviors2;
    }

    public String getPre_Behaviors3() {
        return pre_Behaviors3;
    }

    public void setPre_Behaviors3(String pre_Behaviors3) {
        this.pre_Behaviors3 = pre_Behaviors3;
    }

    public String getPre_Behaviors4() {
        return pre_Behaviors4;
    }

    public void setPre_Behaviors4(String pre_Behaviors4) {
        this.pre_Behaviors4 = pre_Behaviors4;
    }

    public String getPre_Behaviors5() {
        return pre_Behaviors5;
    }

    public void setPre_Behaviors5(String pre_Behaviors5) {
        this.pre_Behaviors5 = pre_Behaviors5;
    }

    public String getPre_Behaviors6() {
        return pre_Behaviors6;
    }

    public void setPre_Behaviors6(String pre_Behaviors6) {
        this.pre_Behaviors6 = pre_Behaviors6;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getMain_Listening1() {
        return main_Listening1;
    }

    public void setMain_Listening1(String main_Listening1) {
        this.main_Listening1 = main_Listening1;
    }

    public String getMain_Listening2() {
        return main_Listening2;
    }

    public void setMain_Listening2(String main_Listening2) {
        this.main_Listening2 = main_Listening2;
    }

    public String getMain_Listening3() {
        return main_Listening3;
    }

    public void setMain_Listening3(String main_Listening3) {
        this.main_Listening3 = main_Listening3;
    }

    public String getMain_Listening4() {
        return main_Listening4;
    }

    public void setMain_Listening4(String main_Listening4) {
        this.main_Listening4 = main_Listening4;
    }

    public String getMain_Listening5() {
        return main_Listening5;
    }

    public void setMain_Listening5(String main_Listening5) {
        this.main_Listening5 = main_Listening5;
    }

    public String getMain_Listening6() {
        return main_Listening6;
    }

    public void setMain_Listening6(String main_Listening6) {
        this.main_Listening6 = main_Listening6;
    }

    public String getMain_Listening7() {
        return main_Listening7;
    }

    public void setMain_Listening7(String main_Listening7) {
        this.main_Listening7 = main_Listening7;
    }

    public String getMain_Listening8() {
        return main_Listening8;
    }

    public void setMain_Listening8(String main_Listening8) {
        this.main_Listening8 = main_Listening8;
    }

    public String getMain_Listening9() {
        return main_Listening9;
    }

    public void setMain_Listening9(String main_Listening9) {
        this.main_Listening9 = main_Listening9;
    }

    public String getMain_Listening10() {
        return main_Listening10;
    }

    public void setMain_Listening10(String main_Listening10) {
        this.main_Listening10 = main_Listening10;
    }

    public String getMain_Listening11() {
        return main_Listening11;
    }

    public void setMain_Listening11(String main_Listening11) {
        this.main_Listening11 = main_Listening11;
    }

    public String getMain_Oral_Expression_Speaking1() {
        return main_Oral_Expression_Speaking1;
    }

    public void setMain_Oral_Expression_Speaking1(String main_Oral_Expression_Speaking1) {
        this.main_Oral_Expression_Speaking1 = main_Oral_Expression_Speaking1;
    }

    public String getMain_Oral_Expression_Speaking2() {
        return main_Oral_Expression_Speaking2;
    }

    public void setMain_Oral_Expression_Speaking2(String main_Oral_Expression_Speaking2) {
        this.main_Oral_Expression_Speaking2 = main_Oral_Expression_Speaking2;
    }

    public String getMain_Oral_Expression_Speaking3() {
        return main_Oral_Expression_Speaking3;
    }

    public void setMain_Oral_Expression_Speaking3(String main_Oral_Expression_Speaking3) {
        this.main_Oral_Expression_Speaking3 = main_Oral_Expression_Speaking3;
    }

    public String getMain_Oral_Expression_Speaking4() {
        return main_Oral_Expression_Speaking4;
    }

    public void setMain_Oral_Expression_Speaking4(String main_Oral_Expression_Speaking4) {
        this.main_Oral_Expression_Speaking4 = main_Oral_Expression_Speaking4;
    }

    public String getMain_Oral_Expression_Speaking5() {
        return main_Oral_Expression_Speaking5;
    }

    public void setMain_Oral_Expression_Speaking5(String main_Oral_Expression_Speaking5) {
        this.main_Oral_Expression_Speaking5 = main_Oral_Expression_Speaking5;
    }

    public String getMain_Oral_Expression_Speaking6() {
        return main_Oral_Expression_Speaking6;
    }

    public void setMain_Oral_Expression_Speaking6(String main_Oral_Expression_Speaking6) {
        this.main_Oral_Expression_Speaking6 = main_Oral_Expression_Speaking6;
    }

    public String getMain_Oral_Expression_Speaking7() {
        return main_Oral_Expression_Speaking7;
    }

    public void setMain_Oral_Expression_Speaking7(String main_Oral_Expression_Speaking7) {
        this.main_Oral_Expression_Speaking7 = main_Oral_Expression_Speaking7;
    }

    public String getMain_Oral_Expression_Speaking8() {
        return main_Oral_Expression_Speaking8;
    }

    public void setMain_Oral_Expression_Speaking8(String main_Oral_Expression_Speaking8) {
        this.main_Oral_Expression_Speaking8 = main_Oral_Expression_Speaking8;
    }

    public String getMain_Oral_Expression_Speaking9() {
        return main_Oral_Expression_Speaking9;
    }

    public void setMain_Oral_Expression_Speaking9(String main_Oral_Expression_Speaking9) {
        this.main_Oral_Expression_Speaking9 = main_Oral_Expression_Speaking9;
    }

    public String getMain_Oral_Expression_Speaking10() {
        return main_Oral_Expression_Speaking10;
    }

    public void setMain_Oral_Expression_Speaking10(String main_Oral_Expression_Speaking10) {
        this.main_Oral_Expression_Speaking10 = main_Oral_Expression_Speaking10;
    }

    public String getMain_Oral_Expression_Speaking11() {
        return main_Oral_Expression_Speaking11;
    }

    public void setMain_Oral_Expression_Speaking11(String main_Oral_Expression_Speaking11) {
        this.main_Oral_Expression_Speaking11 = main_Oral_Expression_Speaking11;
    }

    public String getMain_Oral_Expression_Speaking12() {
        return main_Oral_Expression_Speaking12;
    }

    public void setMain_Oral_Expression_Speaking12(String main_Oral_Expression_Speaking12) {
        this.main_Oral_Expression_Speaking12 = main_Oral_Expression_Speaking12;
    }

    public String getMain_Basic_Reading1() {
        return main_Basic_Reading1;
    }

    public void setMain_Basic_Reading1(String main_Basic_Reading1) {
        this.main_Basic_Reading1 = main_Basic_Reading1;
    }

    public String getMain_Basic_Reading2() {
        return main_Basic_Reading2;
    }

    public void setMain_Basic_Reading2(String main_Basic_Reading2) {
        this.main_Basic_Reading2 = main_Basic_Reading2;
    }

    public String getMain_Basic_Reading3() {
        return main_Basic_Reading3;
    }

    public void setMain_Basic_Reading3(String main_Basic_Reading3) {
        this.main_Basic_Reading3 = main_Basic_Reading3;
    }

    public String getMain_Basic_Reading4() {
        return main_Basic_Reading4;
    }

    public void setMain_Basic_Reading4(String main_Basic_Reading4) {
        this.main_Basic_Reading4 = main_Basic_Reading4;
    }

    public String getMain_Basic_Reading5() {
        return main_Basic_Reading5;
    }

    public void setMain_Basic_Reading5(String main_Basic_Reading5) {
        this.main_Basic_Reading5 = main_Basic_Reading5;
    }

    public String getMain_Basic_Reading6() {
        return main_Basic_Reading6;
    }

    public void setMain_Basic_Reading6(String main_Basic_Reading6) {
        this.main_Basic_Reading6 = main_Basic_Reading6;
    }

    public String getMain_Basic_Reading7() {
        return main_Basic_Reading7;
    }

    public void setMain_Basic_Reading7(String main_Basic_Reading7) {
        this.main_Basic_Reading7 = main_Basic_Reading7;
    }

    public String getMain_Basic_Reading8() {
        return main_Basic_Reading8;
    }

    public void setMain_Basic_Reading8(String main_Basic_Reading8) {
        this.main_Basic_Reading8 = main_Basic_Reading8;
    }

    public String getMain_Basic_Reading9() {
        return main_Basic_Reading9;
    }

    public void setMain_Basic_Reading9(String main_Basic_Reading9) {
        this.main_Basic_Reading9 = main_Basic_Reading9;
    }

    public String getMain_Basic_Reading10() {
        return main_Basic_Reading10;
    }

    public void setMain_Basic_Reading10(String main_Basic_Reading10) {
        this.main_Basic_Reading10 = main_Basic_Reading10;
    }

    public String getMain_Basic_Reading11() {
        return main_Basic_Reading11;
    }

    public void setMain_Basic_Reading11(String main_Basic_Reading11) {
        this.main_Basic_Reading11 = main_Basic_Reading11;
    }

    public String getMain_Basic_Reading12() {
        return main_Basic_Reading12;
    }

    public void setMain_Basic_Reading12(String main_Basic_Reading12) {
        this.main_Basic_Reading12 = main_Basic_Reading12;
    }

    public String getMain_Basic_Reading13() {
        return main_Basic_Reading13;
    }

    public void setMain_Basic_Reading13(String main_Basic_Reading13) {
        this.main_Basic_Reading13 = main_Basic_Reading13;
    }

    public String getMain_Reading_Comprehension1() {
        return main_Reading_Comprehension1;
    }

    public void setMain_Reading_Comprehension1(String main_Reading_Comprehension1) {
        this.main_Reading_Comprehension1 = main_Reading_Comprehension1;
    }

    public String getMain_Reading_Comprehension2() {
        return main_Reading_Comprehension2;
    }

    public void setMain_Reading_Comprehension2(String main_Reading_Comprehension2) {
        this.main_Reading_Comprehension2 = main_Reading_Comprehension2;
    }

    public String getMain_Reading_Comprehension3() {
        return main_Reading_Comprehension3;
    }

    public void setMain_Reading_Comprehension3(String main_Reading_Comprehension3) {
        this.main_Reading_Comprehension3 = main_Reading_Comprehension3;
    }

    public String getMain_Reading_Comprehension4() {
        return main_Reading_Comprehension4;
    }

    public void setMain_Reading_Comprehension4(String main_Reading_Comprehension4) {
        this.main_Reading_Comprehension4 = main_Reading_Comprehension4;
    }

    public String getMain_Reading_Comprehension5() {
        return main_Reading_Comprehension5;
    }

    public void setMain_Reading_Comprehension5(String main_Reading_Comprehension5) {
        this.main_Reading_Comprehension5 = main_Reading_Comprehension5;
    }

    public String getMain_Reading_Comprehension6() {
        return main_Reading_Comprehension6;
    }

    public void setMain_Reading_Comprehension6(String main_Reading_Comprehension6) {
        this.main_Reading_Comprehension6 = main_Reading_Comprehension6;
    }

    public String getMain_Reading_Comprehension7() {
        return main_Reading_Comprehension7;
    }

    public void setMain_Reading_Comprehension7(String main_Reading_Comprehension7) {
        this.main_Reading_Comprehension7 = main_Reading_Comprehension7;
    }

    public String getMain_Math_Calculations1() {
        return main_Math_Calculations1;
    }

    public void setMain_Math_Calculations1(String main_Math_Calculations1) {
        this.main_Math_Calculations1 = main_Math_Calculations1;
    }

    public String getMain_Math_Calculations2() {
        return main_Math_Calculations2;
    }

    public void setMain_Math_Calculations2(String main_Math_Calculations2) {
        this.main_Math_Calculations2 = main_Math_Calculations2;
    }

    public String getMain_Math_Calculations3() {
        return main_Math_Calculations3;
    }

    public void setMain_Math_Calculations3(String main_Math_Calculations3) {
        this.main_Math_Calculations3 = main_Math_Calculations3;
    }

    public String getMain_Math_Calculations4() {
        return main_Math_Calculations4;
    }

    public void setMain_Math_Calculations4(String main_Math_Calculations4) {
        this.main_Math_Calculations4 = main_Math_Calculations4;
    }

    public String getMain_Math_Calculations5() {
        return main_Math_Calculations5;
    }

    public void setMain_Math_Calculations5(String main_Math_Calculations5) {
        this.main_Math_Calculations5 = main_Math_Calculations5;
    }

    public String getMain_Math_Calculations6() {
        return main_Math_Calculations6;
    }

    public void setMain_Math_Calculations6(String main_Math_Calculations6) {
        this.main_Math_Calculations6 = main_Math_Calculations6;
    }

    public String getMain_Math_Calculations7() {
        return main_Math_Calculations7;
    }

    public void setMain_Math_Calculations7(String main_Math_Calculations7) {
        this.main_Math_Calculations7 = main_Math_Calculations7;
    }

    public String getMain_Math_Calculations8() {
        return main_Math_Calculations8;
    }

    public void setMain_Math_Calculations8(String main_Math_Calculations8) {
        this.main_Math_Calculations8 = main_Math_Calculations8;
    }

    public String getMain_Math_Calculations9() {
        return main_Math_Calculations9;
    }

    public void setMain_Math_Calculations9(String main_Math_Calculations9) {
        this.main_Math_Calculations9 = main_Math_Calculations9;
    }

    public String getMain_Math_Calculations10() {
        return main_Math_Calculations10;
    }

    public void setMain_Math_Calculations10(String main_Math_Calculations10) {
        this.main_Math_Calculations10 = main_Math_Calculations10;
    }

    public String getMain_Math_Reasoning1() {
        return main_Math_Reasoning1;
    }

    public void setMain_Math_Reasoning1(String main_Math_Reasoning1) {
        this.main_Math_Reasoning1 = main_Math_Reasoning1;
    }

    public String getMain_Math_Reasoning2() {
        return main_Math_Reasoning2;
    }

    public void setMain_Math_Reasoning2(String main_Math_Reasoning2) {
        this.main_Math_Reasoning2 = main_Math_Reasoning2;
    }

    public String getMain_Math_Reasoning3() {
        return main_Math_Reasoning3;
    }

    public void setMain_Math_Reasoning3(String main_Math_Reasoning3) {
        this.main_Math_Reasoning3 = main_Math_Reasoning3;
    }

    public String getMain_Math_Reasoning4() {
        return main_Math_Reasoning4;
    }

    public void setMain_Math_Reasoning4(String main_Math_Reasoning4) {
        this.main_Math_Reasoning4 = main_Math_Reasoning4;
    }

    public String getMain_Math_Reasoning5() {
        return main_Math_Reasoning5;
    }

    public void setMain_Math_Reasoning5(String main_Math_Reasoning5) {
        this.main_Math_Reasoning5 = main_Math_Reasoning5;
    }

    public String getMain_Math_Reasoning6() {
        return main_Math_Reasoning6;
    }

    public void setMain_Math_Reasoning6(String main_Math_Reasoning6) {
        this.main_Math_Reasoning6 = main_Math_Reasoning6;
    }

    public String getMain_Math_Reasoning7() {
        return main_Math_Reasoning7;
    }

    public void setMain_Math_Reasoning7(String main_Math_Reasoning7) {
        this.main_Math_Reasoning7 = main_Math_Reasoning7;
    }

    public String getMain_Written_Expression1() {
        return main_Written_Expression1;
    }

    public void setMain_Written_Expression1(String main_Written_Expression1) {
        this.main_Written_Expression1 = main_Written_Expression1;
    }

    public String getMain_Written_Expression2() {
        return main_Written_Expression2;
    }

    public void setMain_Written_Expression2(String main_Written_Expression2) {
        this.main_Written_Expression2 = main_Written_Expression2;
    }

    public String getMain_Written_Expression3() {
        return main_Written_Expression3;
    }

    public void setMain_Written_Expression3(String main_Written_Expression3) {
        this.main_Written_Expression3 = main_Written_Expression3;
    }

    public String getMain_Written_Expression4() {
        return main_Written_Expression4;
    }

    public void setMain_Written_Expression4(String main_Written_Expression4) {
        this.main_Written_Expression4 = main_Written_Expression4;
    }

    public String getMain_Written_Expression5() {
        return main_Written_Expression5;
    }

    public void setMain_Written_Expression5(String main_Written_Expression5) {
        this.main_Written_Expression5 = main_Written_Expression5;
    }

    public String getMain_Written_Expression6() {
        return main_Written_Expression6;
    }

    public void setMain_Written_Expression6(String main_Written_Expression6) {
        this.main_Written_Expression6 = main_Written_Expression6;
    }

    public String getMain_Written_Expression7() {
        return main_Written_Expression7;
    }

    public void setMain_Written_Expression7(String main_Written_Expression7) {
        this.main_Written_Expression7 = main_Written_Expression7;
    }

    public String getMain_Written_Expression8() {
        return main_Written_Expression8;
    }

    public void setMain_Written_Expression8(String main_Written_Expression8) {
        this.main_Written_Expression8 = main_Written_Expression8;
    }

    public String getMain_Written_Expression9() {
        return main_Written_Expression9;
    }

    public void setMain_Written_Expression9(String main_Written_Expression9) {
        this.main_Written_Expression9 = main_Written_Expression9;
    }

    public String getMain_Written_Expression10() {
        return main_Written_Expression10;
    }

    public void setMain_Written_Expression10(String main_Written_Expression10) {
        this.main_Written_Expression10 = main_Written_Expression10;
    }

    public String getMain_Written_Expression11() {
        return main_Written_Expression11;
    }

    public void setMain_Written_Expression11(String main_Written_Expression11) {
        this.main_Written_Expression11 = main_Written_Expression11;
    }

    public String getMain_Written_Expression12() {
        return main_Written_Expression12;
    }

    public void setMain_Written_Expression12(String main_Written_Expression12) {
        this.main_Written_Expression12 = main_Written_Expression12;
    }

    public String getMain_Behavior1() {
        return main_Behavior1;
    }

    public void setMain_Behavior1(String main_Behavior1) {
        this.main_Behavior1 = main_Behavior1;
    }

    public String getMain_Behavior2() {
        return main_Behavior2;
    }

    public void setMain_Behavior2(String main_Behavior2) {
        this.main_Behavior2 = main_Behavior2;
    }

    public String getMain_Behavior3() {
        return main_Behavior3;
    }

    public void setMain_Behavior3(String main_Behavior3) {
        this.main_Behavior3 = main_Behavior3;
    }

    public String getMain_Behavior4() {
        return main_Behavior4;
    }

    public void setMain_Behavior4(String main_Behavior4) {
        this.main_Behavior4 = main_Behavior4;
    }

    public String getMain_Behavior5() {
        return main_Behavior5;
    }

    public void setMain_Behavior5(String main_Behavior5) {
        this.main_Behavior5 = main_Behavior5;
    }

    public String getMain_Behavior6() {
        return main_Behavior6;
    }

    public void setMain_Behavior6(String main_Behavior6) {
        this.main_Behavior6 = main_Behavior6;
    }

    public String getMain_Behavior7() {
        return main_Behavior7;
    }

    public void setMain_Behavior7(String main_Behavior7) {
        this.main_Behavior7 = main_Behavior7;
    }

    public String getMain_Behavior8() {
        return main_Behavior8;
    }

    public void setMain_Behavior8(String main_Behavior8) {
        this.main_Behavior8 = main_Behavior8;
    }

    public String getMain_Behavior9() {
        return main_Behavior9;
    }

    public void setMain_Behavior9(String main_Behavior9) {
        this.main_Behavior9 = main_Behavior9;
    }

    public String getMain_Behavior10() {
        return main_Behavior10;
    }

    public void setMain_Behavior10(String main_Behavior10) {
        this.main_Behavior10 = main_Behavior10;
    }

    public String getMain_Behavior11() {
        return main_Behavior11;
    }

    public void setMain_Behavior11(String main_Behavior11) {
        this.main_Behavior11 = main_Behavior11;
    }

    public String getMain_Behavior12() {
        return main_Behavior12;
    }

    public void setMain_Behavior12(String main_Behavior12) {
        this.main_Behavior12 = main_Behavior12;
    }

    public String getMain_Behavior13() {
        return main_Behavior13;
    }

    public void setMain_Behavior13(String main_Behavior13) {
        this.main_Behavior13 = main_Behavior13;
    }

    public String getMain_Behavior14() {
        return main_Behavior14;
    }

    public void setMain_Behavior14(String main_Behavior14) {
        this.main_Behavior14 = main_Behavior14;
    }

    public String getMain_Behavior15() {
        return main_Behavior15;
    }

    public void setMain_Behavior15(String main_Behavior15) {
        this.main_Behavior15 = main_Behavior15;
    }

    //===============================================================================
    public int getC2cAttempts() {
        return c2cAttempts;
    }

    public void setC2cAttempts(int c2cAttempts) {
        this.c2cAttempts = c2cAttempts;
    }

    public int getLeadScore() {
        return leadScore;
    }

    public void setLeadScore(int leadScore) {
        this.leadScore = leadScore;
    }

    public String getAssessmentNotes() {
        return assessmentNotes;
    }

    public void setAssessmentNotes(String assessmentNotes) {
        this.assessmentNotes = assessmentNotes;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public int getAssessmentDateFilter() {
        return assessmentDateFilter;
    }

    public void setAssessmentDateFilter(int assessmentDateFilter) {
        this.assessmentDateFilter = assessmentDateFilter;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getDoesChildGoToSchool() {
        return doesChildGoToSchool;
    }

    public void setDoesChildGoToSchool(String doesChildGoToSchool) {
        this.doesChildGoToSchool = doesChildGoToSchool;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        if(keyword != null){
            keyword = keyword.replaceAll("\\|","-");
            keyword = keyword.replaceAll("&","and");
        }
        this.keyword = keyword;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

    public short getCounter() {
        return counter;
    }

    public void setCounter(short counter) {
        this.counter = counter;
    }

    public int getProfilingAgentId() {
        return profilingAgentId;
    }

    public void setProfilingAgentId(int profilingAgentId) {
        this.profilingAgentId = profilingAgentId;
    }

    public String getUrnNumber() {
        return urnNumber;
    }

    public void setUrnNumber(String urnNumber) {
        this.urnNumber = urnNumber;
    }

    public int getUpdatedatefilter() {
        return updatedatefilter;
    }

    public void setUpdatedatefilter(int updatedatefilter) {
        this.updatedatefilter = updatedatefilter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getInterestedProduct() {
        return interestedProduct;
    }

    public void setInterestedProduct(String interestedProduct) {
        this.interestedProduct = interestedProduct;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getProspectiveBuissness() {
        return prospectiveBuissness;
    }

    public void setProspectiveBuissness(double prospectiveBuissness) {
        this.prospectiveBuissness = prospectiveBuissness;
    }

    public String getDesc() {
        return descrip;
    }

    public void setDesc(String descrip) {
        this.descrip = descrip;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNextFollowUp() {
        return nextFollowUp;
    }

    public void setNextFollowUp(String nextFollowUp) {
        this.nextFollowUp = nextFollowUp;
    }

    public int getLastForward() {
        return lastForward;
    }

    public void setLastForward(int lastForward) {
        this.lastForward = lastForward;
    }

    public String getLeadsourceinner() {
        return leadsourceinner;
    }

    public void setLeadsourceinner(String leadsourceinner) {
        this.leadsourceinner = leadsourceinner;
    }

    public String getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(String attachmentList) {
        this.attachmentList = attachmentList;
    }

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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getConvertDate() {
        return convertDate;
    }

    public void setConvertDate(String convertDate) {
        this.convertDate = convertDate;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

    public int getConvertDateFilter() {
        return convertDateFilter;
    }

    public void setConvertDateFilter(int convertDateFilter) {
        this.convertDateFilter = convertDateFilter;
    }

    public String getLeadStage() {
        return leadStage;
    }

    public void setLeadStage(String leadStage) {
        this.leadStage = leadStage;
    }

    public boolean isLeadConvert() {
        return leadConvert;
    }

    public void setLeadConvert(boolean leadConvert) {
        this.leadConvert = leadConvert;
    }

    public String getTimeTakenToConvert() {
        return timeTakenToConvert;
    }

    public void setTimeTakenToConvert(String timeTakenToConvert) {
        this.timeTakenToConvert = timeTakenToConvert;
    }

    public String getLeadPriority() {
        return leadPriority;
    }

    public void setLeadPriority(String leadPriority) {
        if(leadPriority != null){
            leadPriority = leadPriority.replaceAll("\\|","-");
            leadPriority = leadPriority.replaceAll("&","and");
        }

        this.leadPriority = leadPriority;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        if(parentName != null){
            String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
            parentName = parentName.replaceAll(characterFilter,"");
        }

        this.parentName = parentName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getChildDevDelay() {
        return childDevDelay;
    }

    public void setChildDevDelay(String childDevDelay) {
        this.childDevDelay = childDevDelay;
    }

    public String getProfessionFilled() {
        return professionFilled;
    }

    public void setProfessionFilled(String professionFilled) {
        this.professionFilled = professionFilled;
    }

    public String getChildPlayPattern() {
        return childPlayPattern;
    }

    public void setChildPlayPattern(String childPlayPattern) {
        this.childPlayPattern = childPlayPattern;
    }

    public String getTypeSchool() {
        return typeSchool;
    }

    public void setTypeSchool(String typeSchool) {
        this.typeSchool = typeSchool;
    }


    public String getMbopsParentId() {
        return mbopsParentId;
    }

    public void setMbopsParentId(String mbopsParentId) {
        this.mbopsParentId = mbopsParentId;
    }

    public String getMbopsChildId() {
        return mbopsChildId;
    }

    public void setMbopsChildId(String mbopsChildId) {
        this.mbopsChildId = mbopsChildId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getSeekingSupport() {
        return seekingSupport;
    }

    public void setSeekingSupport(String seekingSupport) {
        this.seekingSupport = seekingSupport;
    }

    public String getSupportFor() {
        return supportFor;
    }

    public void setSupportFor(String supportFor) {
        this.supportFor = supportFor;
    }

    public String getInterventionAreas() {
        return interventionAreas;
    }

    public void setInterventionAreas(String interventionAreas) {
        this.interventionAreas = interventionAreas;
    }

    public String getFormalAssessment() {
        return formalAssessment;
    }

    public void setFormalAssessment(String formalAssessment) {
        this.formalAssessment = formalAssessment;
    }

    public String getOngoingTherapy() {
        return ongoingTherapy;
    }

    public void setOngoingTherapy(String ongoingTherapy) {
        this.ongoingTherapy = ongoingTherapy;
    }

    public String getDevelopmentTime() {
        return developmentTime;
    }

    public void setDevelopmentTime(String developmentTime) {
        this.developmentTime = developmentTime;
    }

    public String getSeizureHistory() {
        return seizureHistory;
    }

    public void setSeizureHistory(String seizureHistory) {
        this.seizureHistory = seizureHistory;
    }

    public String getCallReqCstTeam() {
        return callReqCstTeam;
    }

    public void setCallReqCstTeam(String callReqCstTeam) {
        this.callReqCstTeam = callReqCstTeam;
    }

    public String getPersonAssigned() {
        return personAssigned;
    }

    public void setPersonAssigned(String personAssigned) {
        this.personAssigned = personAssigned;
    }

    public String getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(String assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getTypeTherapy() {
        return typeTherapy;
    }

    public void setTypeTherapy(String typeTherapy) {
        this.typeTherapy = typeTherapy;
    }

    public String getCallComments() {
        return callComments;
    }

    public void setCallComments(String callComments) {
        this.callComments = callComments;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getRegisteredInMb() {
        return registeredInMb;
    }

    public void setRegisteredInMb(String registeredInMb) {
        this.registeredInMb = registeredInMb;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getChildLanguage() {
        return childLanguage;
    }

    public void setChildLanguage(String childLanguage) {
        this.childLanguage = childLanguage;
    }

    public String getParentLanguage() {
        return parentLanguage;
    }

    public void setParentLanguage(String parentLanguage) {
        this.parentLanguage = parentLanguage;
    }

    public String getLeadDate() {
        return leadDate;
    }

    public void setLeadDate(String leadDate) {
        this.leadDate = leadDate;
    }

    public int getLeadDatefilter() {
        return leadDatefilter;
    }

    public void setLeadDatefilter(int leadDatefilter) {
        this.leadDatefilter = leadDatefilter;
    }

    public String getLearningConcern() {
        return learningConcern;
    }

    public void setLearningConcern(String learningConcern) {
        this.learningConcern = learningConcern;
    }

    public String getDifficultyInFriendship() {
        return difficultyInFriendship;
    }

    public void setDifficultyInFriendship(String difficultyInFriendship) {
        this.difficultyInFriendship = difficultyInFriendship;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getClinicalCallTime() {
        return clinicalCallTime;
    }

    public void setClinicalCallTime(String clinicalCallTime) {
        this.clinicalCallTime = clinicalCallTime;
    }

    public String getPgBdName() {
        return pgBdName;
    }

    public void setPgBdName(String pgBdName) {
        this.pgBdName = pgBdName;
    }

    public String getPgBdManager() {
        return pgBdManager;
    }

    public void setPgBdManager(String pgBdManager) {
        this.pgBdManager = pgBdManager;
    }

    public String getSupportOption() {
        return supportOption;
    }

    public void setSupportOption(String supportOption) {
        this.supportOption = supportOption;
    }

    public String getProfilingAgent() {
        return profilingAgent;
    }

    public void setProfilingAgent(String profilingAgent) {
        this.profilingAgent = profilingAgent;
    }

    public String getInterestedAgent() {
        return interestedAgent;
    }

    public void setInterestedAgent(String interestedAgent) {
        this.interestedAgent = interestedAgent;
    }

    public String getInterestedDate() {
        return interestedDate;
    }

    public void setInterestedDate(String interestedDate) {
        this.interestedDate = interestedDate;
    }

    public int getInterestedDateFilter() {
        return interestedDateFilter;
    }

    public void setInterestedDateFilter(int interestedDateFilter) {
        this.interestedDateFilter = interestedDateFilter;
    }

    public String getProfilingDate() {
        return profilingDate;
    }

    public void setProfilingDate(String profilingDate) {
        this.profilingDate = profilingDate;
    }

    public int getProfilingDateFilter() {
        return profilingDateFilter;
    }

    public void setProfilingDateFilter(int profilingDateFilter) {
        this.profilingDateFilter = profilingDateFilter;
    }

    public String getMbopsChildEnrollId() {
        return mbopsChildEnrollId;
    }

    public void setMbopsChildEnrollId(String mbopsChildEnrollId) {
        this.mbopsChildEnrollId = mbopsChildEnrollId;
    }

    public String getLastQueryDate() {
        return lastQueryDate;
    }

    public void setLastQueryDate(String lastQueryDate) {
        this.lastQueryDate = lastQueryDate;
    }

    public int getLastQueryDateFilter() {
        return lastQueryDateFilter;
    }

    public void setLastQueryDateFilter(int lastQueryDateFilter) {
        this.lastQueryDateFilter = lastQueryDateFilter;
    }

    public String getPreferredCallingTime() {
        return preferredCallingTime;
    }

    public void setPreferredCallingTime(String preferredCallingTime) {
        this.preferredCallingTime = preferredCallingTime;
    }

    public String getPreferredCallingSlot() {
        return preferredCallingSlot;
    }

    public void setPreferredCallingSlot(String preferredCallingSlot) {
        this.preferredCallingSlot = preferredCallingSlot;
    }

    public List<LogEventDao> getLogEvents() {
        return logEvents;
    }

    public void setLogEvents(List<LogEventDao> logEvents) {
        this.logEvents = logEvents;
    }

    public CampaignObjectDao convertToObjectMail() {
        CampaignObjectDao campaignObjectDao = new CampaignObjectDao();
        campaignObjectDao.setPhonenumber(this.phonenumber);
        campaignObjectDao.setFullname(this.firstName + " " + this.lastName);
        campaignObjectDao.setEmail(this.email);
        campaignObjectDao.setLeadId(this.id);
        campaignObjectDao.setState(this.state);
        campaignObjectDao.setCity(this.city);
        campaignObjectDao.setLastForward(this.lastForward);
        campaignObjectDao.setProduct(this.interestedProduct);
        campaignObjectDao.setParentName(this.parentName);
        campaignObjectDao.setChildName(this.childName);
        return campaignObjectDao;
    }

}
