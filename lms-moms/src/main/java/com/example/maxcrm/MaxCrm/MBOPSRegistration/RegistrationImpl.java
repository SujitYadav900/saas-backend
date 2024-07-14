package com.example.maxcrm.MaxCrm.MBOPSRegistration;

import com.example.maxcrm.MaxCrm.Dao.LeadMasterDao;
import com.example.maxcrm.MaxCrm.Dao.ProductMasterDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains.MBOPSClinicalDomainDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.countries.MBOPSCountryDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.countries.MBOPSCountryService;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.grades.MBOPSGradesDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.languages.MBOPSLanguageDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.severities.MBOPSSeverityDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.states.MBOPSStateDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.states.MBOPSStateService;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Service.LeadMasterService;
import com.example.maxcrm.MaxCrm.Service.ProductMasterService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class RegistrationImpl implements RegistrationService {

    @Autowired
    private LeadMasterService leadMasterService;
    @Autowired
    private ProductMasterService productMasterService;
    @Autowired
    private MBOPSStateService stateService;
    @Autowired
    private MBOPSCountryService countryService;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public int registerParent(long leadId, boolean useCustomIDs,String flag) throws Exception {

        logger.info("Regestering parent for LeadID > {}",leadId);

        LeadMasterDao leadDao = leadMasterService.findById(leadId);
        String email = UtilityClass.propertyService.findProperty("MBOPS","email");
        String password = UtilityClass.propertyService.findProperty("MBOPS","password");
        String client_secret = UtilityClass.propertyService.findProperty("MBOPS","client_secret");
        String provider = UtilityClass.propertyService.findProperty("MBOPS","provider");
        String client_id = UtilityClass.propertyService.findProperty("MBOPS","client_id");

        logger.info("MBOPS Email > {}",email);
        logger.info("MBOPS password > {}",password);
        logger.info("MBOPS client_secret > {}",client_secret);
        logger.info("MBOPS provider > {}",provider);
        logger.info("MBOPS client_id > {}",client_id);

        String severity = UtilityClass.propertyService.findProperty("MBOPS","defaultSeverity");
        String score = UtilityClass.propertyService.findProperty("MBOPS","defaultAssessmentScore");

        logger.info("severity > {}",severity);
        logger.info("assessment score > {}",score);

        ProductMasterDao product = productMasterService.findByName(leadDao.getInterestedProduct());
        logger.info("Product is > {} ",product);
        String locationId = "";
        String  organisationId = "";
        String[] files = null;
        if(leadDao.getAttachmentList() != null){
            files = leadDao.getAttachmentList().split(",");
        }

        logger.info("Attachment files > {}",files);

        if(useCustomIDs){
            logger.info("Using custom locationID and organisationID");
            locationId = UtilityClass.propertyService.findProperty("MBOPS", "customLocationID");
            organisationId = UtilityClass.propertyService.findProperty("MBOPS", "customOrganisationID");
        }else
        {
            if(product.getProductcode().equalsIgnoreCase("Home-Program")){
                logger.info("Getting Home-Program keys");
                //E-Therapy keys
                locationId = UtilityClass.propertyService.findProperty("MBOPS", "homeProgramLocationID");
                organisationId = UtilityClass.propertyService.findProperty("MBOPS", "homeProgramOrganisationID");
            }else if(product.getProductcode().equalsIgnoreCase("E-Therapy")){//E-Therapy
                logger.info("Getting E-Therapy keys");
                locationId = UtilityClass.propertyService.findProperty("MBOPS", "eTherapyLocationID");
                organisationId = UtilityClass.propertyService.findProperty("MBOPS", "eTherapyOrganisationID");
            }else{
                throw new Exception("Program is missing a valid program-code!!");
            }

        }


        logger.info("Location Key > {}",locationId);
        logger.info("Organisation key > {}",organisationId);


        //GET OAUTH2 ACCESS TOKEN
            String accessToken = getAccessToken(email,password,client_secret,provider,client_id).get("access_token").toString();
            logger.info(">>Access Token > {}",accessToken);

            Map<String, Object> parentUpdateMap=null;
            String parentId = null;
            String childId = null;
            Map<String, Object> childUpdateMap=null;
            //POST PARENT DATA
            logger.info(">>registering parent with MBOPS");
            parentId = createParent(accessToken, leadDao,locationId,organisationId);

            logger.info(">>ParentID > {}",parentId);
            if(parentId !=null){
                logger.info(">>Updating Parent Details");
            //POST PARENT CONTACT DETAILS
            parentUpdateMap =   updateParentContact(accessToken,leadDao,parentId,locationId,organisationId);

            }else{
                throw new Exception("Fail To Register Parent With MBOPS!");
            }

            if (parentUpdateMap.get("errors") == null){
                logger.info(">>Registering Child To MBOPS");
                //POST CHILD DATA
                childId = createChild(accessToken,leadDao, locationId,organisationId,parentId,flag);
                logger.info(">>Child ID > {}",childId);
            }else{
                throw new Exception("Fail To Update Parent Details To MBOPS!");
            }

            if(childId != null){
                logger.info(">>Updating Child Clinical Details");
                //POST CLINICAL DATA
                childUpdateMap = updateClinicalDetails(locationId,organisationId,accessToken,childId, leadDao.getInterestedProduct(), severity,score,leadDao,flag);
            }else{
                throw new Exception("Failed To Register Child With MBOPS!");
            }

            if (childUpdateMap.get("errors") != null){
                throw new Exception("Failed To Update Child's Clinical Details With MBOPS!");
            }


        logger.info("All Operations Completed Successfully! Returning status - 200");
        return 200;
    }

    private String createParent(String accessToken, LeadMasterDao leadDao,String locId,String orgId) throws Exception {
        logger.info("Creating parent");

        String alternatePhoneNumber = " ";
        if(leadDao.getTypeSchool().length() > 9){
            alternatePhoneNumber = "+91"+leadDao.getTypeSchool();
        }

        //http://opsadminstaging.momsbelief.com/api/v1/parent/create
        String url = UtilityClass.propertyService.findProperty("MBOPS","create_parent_url");
        // MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("first_name", leadDao.getParentName())
                .addFormDataPart("last_name", ".")
                .addFormDataPart("email", leadDao.getEmail())
                .addFormDataPart("mobile", "+91"+leadDao.getPhonenumber())
                .addFormDataPart("alternate_mobile", alternatePhoneNumber)
                .addFormDataPart("location_id", locId)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .addHeader("Location-Id", locId)
                .addHeader("Organization-Id", orgId)
                .build();
        Map<String,HashMap> responseMap = null;
        try {
            Response response = OkHttpSingleTon.client.newCall(request).execute();
            String responseStr = response.body().string();
            logger.info("ResponseStr > {}",responseStr);
            responseMap = new ObjectMapper().readValue(responseStr, HashMap.class);
            logger.info("ResponseMap > {}",responseMap);
            logger.info("ResponseMap.id > {}",responseMap.get("data").get("id"));
            leadMasterService.setMBOPSParentId(leadDao.getId(),responseMap.get("data").get("id").toString());
        }catch (Exception ex){
            logger.error("Error Occured while creating parent >> {}",ex);
            throw new Exception("Exception While Registering Parent With MBOPS! "+responseMap.get("errors").toString());
        }

        //parent id returned by MBOPS
        return responseMap.get("data").get("id").toString();

    }

    private Map<String, Object> updateParentContact(String accessToken, LeadMasterDao leadDao,String parentId,String locationId,String organisationId) throws IOException {
        logger.info("Updating Parent Contact Details");

        //http://opsadminstaging.momsbelief.com/api/v1/parent/contact
        String url = UtilityClass.propertyService.findProperty("MBOPS","update_parent_url");

        String selectDefaultCountry = UtilityClass.propertyService.findProperty("MBOPS", "selectDefaultCountry");
        String countryId="";
        String stateId="";

//        try{
//            stateId= String.valueOf(stateService.findByName(leadDao.getState()).getId());
//        }catch (Exception e){
//              e.printStackTrace();
//        }



        //PICKING HARD CODED VALUES FROM PROPERTIES
        if(selectDefaultCountry.equalsIgnoreCase("true")){
            countryId = UtilityClass.propertyService.findProperty("MBOPS", "defaultCountryID");

            try{
            stateId= String.valueOf(stateService.findByName(leadDao.getState()).getId());
            }catch (Exception e){
              e.printStackTrace();
            }

        }else{
           // countryId = String.valueOf(countryService.findByName("India"));
            stateId = String.valueOf(leadDao.getStateId());
            countryId = String.valueOf(leadDao.getCountryId());
        }

        String city = leadDao.getCity();
        //REMOVE ALL NUMERIC VALUES
       // city = city.replaceAll("[0-9]","");
        //replace all numeric values and special characters
        city = city.replaceAll("[^a-zA-Z]+","");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("city", city)
                .addFormDataPart("pincode", leadDao.getPincode())
                .addFormDataPart("address1", leadDao.getAddress())
                .addFormDataPart("address2", "NA")
                .addFormDataPart("country_id", countryId)
                .addFormDataPart("state_id", stateId)
                .addFormDataPart("parent_id", parentId)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .addHeader("Location-Id", locationId)
                .addHeader("Organization-Id", organisationId)
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        Map<String,Object> responseMap = new ObjectMapper().readValue(response.body().byteStream(), HashMap.class);
        logger.info("Response Map > {}",responseMap);

        return responseMap;
    }

    private String createChild(String accessToken,LeadMasterDao leadMasterDao,String locId,String orgId,String parentId,String flag) throws Exception{
        logger.info("Creating child");
        String gender = (leadMasterDao.getGender() == "Male" ?"1":"0");

        //http://opsadminstaging.momsbelief.com/api/v1/child
        String url = UtilityClass.propertyService.findProperty("MBOPS","create_child_url");
        String gradeId = UtilityClass.propertyService.findProperty("MBOPS","defaultGradeId");

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("first_name", leadMasterDao.getChildName())
                .addFormDataPart("last_name", ".")
                .addFormDataPart("dob", leadMasterDao.getDob())
                .addFormDataPart("gender", gender)
                .addFormDataPart("school", "NA")
                .addFormDataPart("grade", gradeId)//ID for NA
                .addFormDataPart("parent_id", parentId)
                .addFormDataPart("location_id", locId)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .addHeader("Location-Id", locId)
                .addHeader("Organization-Id", orgId)
                .build();
        Map<String,HashMap> responseMap = null;
        try {
            Response response = OkHttpSingleTon.client.newCall(request).execute();
            String responseStr = response.body().string();
            logger.info("ResponseStr > {}",responseStr);
            responseMap = new ObjectMapper().readValue(responseStr, HashMap.class);
            logger.info("ResponseMap > {}",responseMap);
            logger.info("ResponseMap.id > {}",responseMap.get("data").get("id"));
            leadMasterService.setMBOPSChildId(leadMasterDao.getId(),responseMap.get("data").get("child_id").toString(),flag);
        }catch (Exception ex){
            logger.error("Error Occured while creating child >> {}",ex);
            throw new Exception(responseMap.get("errors").toString());
        }


        return responseMap.get("data").get("id").toString();
    }

    private Map<String, Object> updateClinicalDetails(String locId,String orgId,String accessToken, String childId, String program, String severity,String score,LeadMasterDao leadDao,String flag) throws IOException {

        String defaultDomain = UtilityClass.propertyService.findProperty("MBOPS","defaultDomain");
        String defaultLanguage = UtilityClass.propertyService.findProperty("MBOPS","defaultLanguage");
        //http://opsadminstaging.momsbelief.com/api/v1/clinical-details
        String url = UtilityClass.propertyService.findProperty("MBOPS","update_child_url");
        StringBuilder diagnosis = new StringBuilder();
        diagnosis.append("Does your child has any-");
        diagnosis.append(leadDao.getChildDevDelay());
        diagnosis.append("----");
        String formalAssessment = leadDao.getFormalAssessment()+"";
        formalAssessment = formalAssessment.replace("|","");
        diagnosis.append("Any formal assessment done so far-");
        diagnosis.append(formalAssessment);
        diagnosis.append("----");
        String ongoingTherapy = leadDao.getOngoingTherapy()+"";
        ongoingTherapy = ongoingTherapy.replace("|","");
        diagnosis.append("Has your child received any therapies before-");
        diagnosis.append(ongoingTherapy);
        diagnosis.append("----");
        diagnosis.append("What kind of therapies you would prefer-");
        diagnosis.append(leadDao.getTypeTherapy());
        diagnosis.append("----");

        String additionalNotes = "";

        if(flag.equalsIgnoreCase("Register")){
            additionalNotes = leadDao.getAssessmentNotes();
        }
        //Register1
        else{
            if(leadDao.getClinicalCallTime() != null){
                additionalNotes = "Preferred Clinical Call Time;"+leadDao.getClinicalCallTime();
            }else{
                additionalNotes = "NA";
            }
        }


        logger.info("diagnosis {}", diagnosis);
        logger.info("additionalNote {}",additionalNotes);


        MediaType mediaType = MediaType.parse("multipart/form-data");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("child_id", childId)
                .addFormDataPart("domain", defaultDomain)
                .addFormDataPart("primary_language", defaultLanguage)
                .addFormDataPart("severity", severity)
                .addFormDataPart("assessment_tool", "NA")
                .addFormDataPart("therapies", program)
                .addFormDataPart("diagnosis", diagnosis.toString())
                .addFormDataPart("assessment_score", score)
                .addFormDataPart("additional_notes", additionalNotes)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .addHeader("Location-Id", locId)
                .addHeader("Organization-Id", orgId)
                .addHeader("Content-Type", "multipart/form-data")
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        Map<String,Object> responseMap = new ObjectMapper().readValue(response.body().byteStream(), HashMap.class);
        logger.info("Response Map > {}",responseMap);

        return responseMap;
    }


    @Override
    public Map<String, Object> getAccessToken(String email,String password,String clientSecret,String provider,String clientId) throws IOException {
       // OkHttpClient client = new OkHttpClient().newBuilder().build();

        //"http://opsadminstaging.momsbelief.com/api/v1/login";
        String url = UtilityClass.propertyService.findProperty("MBOPS","oauth_url");
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .addFormDataPart("client_secret", clientSecret)
                .addFormDataPart("provider", provider)
                .addFormDataPart("client_id", clientId)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        //System.out.println(response.body().string());
        // Read the body of the response into a hashmap
        Map<String,Object> responseMap = new ObjectMapper().readValue(response.body().byteStream(), HashMap.class);
        // Read the value of the "access_token" key from the hashmap
        //String accessToken = (String)responseMap.get("access_token");
       // String accessToken = (String)responseMap.get("access_token");
        // Return the access_token value
        System.out.println(responseMap.toString());
        return responseMap;
    }

    private Map<String, Object> getUserDetails(String accessToken) throws IOException {

        //"http://opsadminstaging.momsbelief.com/api/v1/acl/default-org-location-dept"
        String url = UtilityClass.propertyService.findProperty("MBOPS","user_details_url");
        //OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        Map<String,Object> responseMap = new ObjectMapper().readValue(response.body().byteStream(), HashMap.class);
        return responseMap;
    }

    @Override
    public List<MBOPSCountryDao> getCountries(String accessToken) throws IOException {

        //"http://opsadminstaging.momsbelief.com/api/v1/countries"
        String url = UtilityClass.propertyService.findProperty("MBOPS","get_countries_url");
        //OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        String responseStr=response.body().string();

        CountryObj obj=new CountryObj().convertToJson(responseStr);


        return obj.getData();
    }

    @Override
    public  List<MBOPSStateDao> getStates(int countryId,String accessToken) throws IOException {

        //"http://opsadminstaging.momsbelief.com/api/v1/"+countryId+"/states"
        String url = UtilityClass.propertyService.findProperty("MBOPS","get_states_url");
        url = url.replace("{{country_id}}",String.valueOf(countryId));
        //OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        String responseStr=response.body().string();
        StateObj obj =new StateObj().convertToJson(responseStr);
        return obj.getData();
    }

    @Override
    public List<MBOPSLanguageDao> getLanguages(String accessToken) throws IOException {

        //http://opsadminstaging.momsbelief.com/api/v1/clinical-languages
        String url = UtilityClass.propertyService.findProperty("MBOPS","get_languages_url");

        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        String responseStr = response.body().string();
        LanguageObj obj = new LanguageObj().convertToJson(responseStr);
        return obj.getData();

    }

    @Override
    public List<MBOPSSeverityDao> getSeverities(String locId,String orgId,String accessToken) throws IOException {
        //http://opsadminstaging.momsbelief.com/api/v1/severities
        String url = UtilityClass.propertyService.findProperty("MBOPS","get_severities_url");
        //OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .addHeader("Location-Id", locId)
                .addHeader("Organization-Id", orgId)
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        String responseStr = response.body().string();
        SeverityObj obj = new SeverityObj().convertToJson(responseStr);
        return obj.getData();
    }

    @Override
    public List<MBOPSGradesDao> getGrades(String locId,String orgId,String accessToken) throws IOException {

        //http://opsadminstaging.momsbelief.com/api/v1/grades
        String url = UtilityClass.propertyService.findProperty("MBOPS","get_grades_url");
        //OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .addHeader("Location-Id", locId)
                .addHeader("Organization-Id", orgId)
                .build();
       Response response = OkHttpSingleTon.client.newCall(request).execute();
        String responseStr = response.body().string();
        GradeObj obj = new GradeObj().convertToJson(responseStr);
        return obj.getData();
    }

    @Override
    public List<MBOPSClinicalDomainDao> getClinicalDomains(String locId,String orgId,String accessToken) throws IOException {

        //http://opsadminstaging.momsbelief.com/api/v1/clinical-domains
        String url = UtilityClass.propertyService.findProperty("MBOPS","get_domains_url");
        //OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+accessToken)
                .addHeader("Location-Id", locId)
                .addHeader("Organization-Id", orgId)
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        String responseStr = response.body().string();
         DomainObj obj = new DomainObj().convertToJson(responseStr);
        return obj.getData();
    }




    // FOR CONVERTING MBOPS COUNTRIES DATA TO LIST
    static class CountryObj{
        public List<MBOPSCountryDao> getData() {
            return data;
        }

        public void setData(List<MBOPSCountryDao> data) {
            this.data = data;
        }

        private List<MBOPSCountryDao> data;
        public CountryObj convertToJson(String json)
        {
            return new Gson().fromJson(json, CountryObj.class);
        }
    }

    // FOR CONVERTING MBOPS STATES DATA TO LIST
    static class StateObj{
        public List<MBOPSStateDao> getData() {
            return data;
        }

        public void setData(List<MBOPSStateDao> data) {
            this.data = data;
        }

        private List<MBOPSStateDao> data;
        public StateObj convertToJson(String json)
        {
            return new Gson().fromJson(json, StateObj.class);
        }
    }

    // FOR CONVERTING MBOPS LANGUAGES DATA TO LIST
    static class LanguageObj{
        public List<MBOPSLanguageDao> getData() {
            return data;
        }

        public void setData(List<MBOPSLanguageDao> data) {
            this.data = data;
        }

        private List<MBOPSLanguageDao> data;
        public LanguageObj convertToJson(String json)
        {
            return new Gson().fromJson(json, LanguageObj.class);
        }
    }

    // FOR CONVERTING MBOPS SEVERITY DATA TO LIST
    static class SeverityObj{
        public List<MBOPSSeverityDao> getData() {
            return data;
        }

        public void setData(List<MBOPSSeverityDao> data) {
            this.data = data;
        }

        private List<MBOPSSeverityDao> data;
        public SeverityObj convertToJson(String json)
        {
            return new Gson().fromJson(json, SeverityObj.class);
        }
    }

    // FOR CONVERTING MBOPS GARDES DATA TO LIST
    static class GradeObj{
        public List<MBOPSGradesDao> getData() {
            return data;
        }

        public void setData(List<MBOPSGradesDao> data) {
            this.data = data;
        }

        private List<MBOPSGradesDao> data;
        public GradeObj convertToJson(String json)
        {
            return new Gson().fromJson(json, GradeObj.class);
        }
    }

    // FOR CONVERTING MBOPS ClinicalDomain DATA TO LIST
    static class DomainObj{
        public List<MBOPSClinicalDomainDao> getData() {
            return data;
        }

        public void setData(List<MBOPSClinicalDomainDao> data) {
            this.data = data;
        }

        private List<MBOPSClinicalDomainDao> data;
        public DomainObj convertToJson(String json)
        {
            return new Gson().fromJson(json, DomainObj.class);
        }
    }

    // FOR CONVERTING MBOPS ParentResponse DATA TO LIST
    static class ParentResponseObj{
        public ParentResponse getData() {
            return data;
        }

        public void setData(ParentResponse data) {
            this.data = data;
        }

        private ParentResponse data;
        public ParentResponseObj convertToJson(String json)
        {
            return new Gson().fromJson(json, ParentResponseObj.class);
        }
    }



}
