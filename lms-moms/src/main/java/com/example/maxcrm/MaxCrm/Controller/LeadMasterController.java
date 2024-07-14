package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.CombinePackage.ServiceReview.ServiceReviewDao;
import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.google.gson.Gson;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@RequestMapping("/api/lead")
@RestController
public class LeadMasterController {
    Logger logger = LoggerFactory.getLogger(LeadMasterController.class);
    @Autowired
    LeadMasterService leadMasterService;

    @Autowired
    UserMasterService userMasterService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    LeadSourceService leadSourceService;

    @Autowired
    LeadStageService leadStageService;

    @Autowired
    LeadStatusService leadStatusService;


    @GetMapping("/getlead")
    public PaginationDao getAll(@RequestParam("statusValue") String statusValue
            , @RequestParam("leadSource") String leadSource
            , @RequestParam("product") String product
            , @RequestParam("leadType") String leadType
            , @RequestParam("userFilter") String userFilter
            , @RequestParam("datefilter") boolean datefilter
            , @RequestParam("datevalue") String datevalue
            , @RequestParam("searchvalue") String searchValue
            , @RequestParam("fieldArray") String fieldArray
            , @RequestParam("leadstage") String leadstage
            , @RequestParam("leadpriority") String leadpriority
            , @RequestParam("id") long id
            , @RequestParam("innersource") String innersource
            , @RequestParam("datefiltertype") String datefiltertype
            , @RequestParam(name = "leadids", required = false, defaultValue = "0") String leadIds
            , @RequestParam(name = "userflag", required = false, defaultValue = "0") int userflag
            , Authentication authentication
            , @RequestParam("length") int limit, @RequestParam("start") int offset


    ) throws SQLException {


        logger.info("statusValue >>> {}", statusValue);
        logger.info("leadSource >>> {}", leadSource);
        logger.info("product >>> {}", product);
        logger.info("leadType >>> {}", leadType);
        logger.info("userFilter >>> {}", userFilter);
        logger.info("datefilter >>> {}", datefilter);
        logger.info("datevalue >>> {}", datevalue);
        logger.info("searchvalue >>> {}", searchValue);
        logger.info("fieldArray >>> {}", fieldArray);
        logger.info("leadstage >>> {}", leadstage);
        logger.info("leadpriority >>> {}", leadpriority);
        logger.info("id >>> {}", id);
        logger.info("innersource >>> {}", innersource);
        logger.info("datefiltertype >>> {}", datefiltertype);
        logger.info("leadids >>> {}", leadIds);
        logger.info("length >>> {}", limit);
        logger.info("start >>> {}", offset);

        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        MaskingDao maskingDao = new MaskingDao().convertHashMapToMasking(userMasterDao.getFeatures());

        if (userflag == 1) {
            userFilter = UtilityClass.userlist.get(Integer.parseInt(userFilter));
        }

        String wherequery = "";
        if (id == 0) {
            wherequery = buildQuery(statusValue, leadSource, product, leadType, leadpriority, userFilter, datefilter, datevalue, userMasterDao.getId(), searchValue, leadstage, innersource, datefiltertype, fieldArray, leadIds);
        } else {
            wherequery = " where Tbl_LeadMaster.id=" + id + " ";
        }
        return leadMasterService.getAllDynamicQuery(limit, offset, wherequery, maskingDao);
    }

    private String buildQuery(String statusValue, String leadSource, String product, String leadType, String leadPriority, String userFilter, boolean datefilter, String datevalue, int userId, String searchvalue, String leadStage, String innersource, String datefiltertype, String fieldArray, String leadIds) {
        StringBuilder sb = new StringBuilder();
        sb.append("where ");
        if (!statusValue.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadStatus='");
            sb.append(statusValue);
            sb.append("' and ");
        }
        if (!leadSource.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadSource='");
            sb.append(leadSource);
            sb.append("' and ");
        }
        if (!product.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.interestedProduct='");
            sb.append(product);
            sb.append("' and ");
        }
        if (!leadType.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.clientType='");
            sb.append(leadType);
            sb.append("' and ");
        }

        if (!leadPriority.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadPriority='");
            sb.append(leadPriority);
            sb.append("' and ");
        }

        if (!leadStage.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadStage='");
            sb.append(leadStage);
            sb.append("' and ");
        }
        if (!innersource.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadsourceinner='");
            sb.append(innersource);
            sb.append("' and ");
        }

        if (!leadIds.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.id IN (");
            sb.append(leadIds);
            sb.append(") AND ");
        }


        if (datefilter == true) {
            if (datefiltertype.equalsIgnoreCase("createdate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.dateFilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.dateFilter<=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            } else if (datefiltertype.equalsIgnoreCase("convertdate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.convertDateFilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.convertDateFilter<=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            } else if (datefiltertype.equalsIgnoreCase("updatedate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.updatedatefilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.updatedatefilter<=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            } else if (datefiltertype.equalsIgnoreCase("leaddate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.leadDatefilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.leadDatefilter <=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            } else if (datefiltertype.equalsIgnoreCase("profilingdate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.profilingDateFilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.profilingDateFilter <=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            }

            // THIS LOGIC IF FOR LEAD STATS SAME DATE
            //REQUIREMENT IS - ALL RESULTS, ASSIGNED,PROFILED,CONVERTED,REJECTCED AND PENDING
            //HAS TO BE CALCULATED BASED ON  dateFilter (date on which lead was entered in the LMS) AND profilingAgentId
            //
            else if (datefiltertype.equalsIgnoreCase("profilingdatecustom")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.dateFilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.dateFilter <=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            }
            //APPOINTMENT DATE (APPOINTMENT SLOT) IS STORED IN '2020-12-30 09 AM to 12 PM' FORMAT
            // IT REQUIRES TO BE IN 20201230 FORMAT FOR FILTERING
            else if (datefiltertype.equalsIgnoreCase("appointmentdate")) {
                String[] arr = datevalue.split("@");
                sb.append("( SUBSTRING(replace(Tbl_LeadMaster.clinicalCallTime,'-',''),1, 8) >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and SUBSTRING(replace(Tbl_LeadMaster.clinicalCallTime,'-',''),1, 8) <=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");

                //get appointments, lead must have MBOPSchildId, (leadstatsappointmentdate)
                if (searchvalue.equalsIgnoreCase("appointment")) {
                    sb.append(" LENGTH(Tbl_LeadMaster.mbopsChildId)>1 ");
                    sb.append(" AND ");
                }


            }
            //DATE ON WHICH ASSESSENT FORM WAS FILLED
            else if (datefiltertype.equalsIgnoreCase("assessmentdate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.assessmentDateFilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.assessmentDateFilter <=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            }

        }
        //SearchString searchString=new SearchString().convertToObject(searchvalue)[0];
        String[] fields = fieldArray.split(",");
        if (fields.length >= 1 && !fieldArray.trim().equalsIgnoreCase("") && !fieldArray.trim().equalsIgnoreCase("0")) {
            StringBuilder searchQuery = new StringBuilder();
            searchQuery.append("(");
            //searchString.setSearchValue(searchString.getSearchValue().replaceAll(UtilityClass.ApplicationPrefix, ""));
            for (int i = 0; i < fields.length; i++) {

                if (!fields[i].trim().equalsIgnoreCase("")) {
                    searchQuery.append("Tbl_LeadMaster.");
                    searchQuery.append(fields[i]);
                    if (fields[i].equalsIgnoreCase("counter") || fields[i].equalsIgnoreCase("c2cAttempts") || fields[i].equalsIgnoreCase("leadScore")) {
                        searchQuery.append(" >= ");
                        searchQuery.append(searchvalue);
                    } else {
                        searchQuery.append(" like '%");
                        searchQuery.append(searchvalue);
                        searchQuery.append("%'");
                    }

                    if (i < fields.length - 1)
                        searchQuery.append(" or ");

                }

                //searchQuery.append(" or ");
            }
            searchQuery.append(")");
            sb.append(searchQuery);
            sb.append(" and ");
        }

        if (!userFilter.equalsIgnoreCase("0")) {

            if (datefiltertype.equalsIgnoreCase("profilingdate")) {
//                sb.append("Tbl_LeadMaster.profilingAgentId=");
//                sb.append(userFilter);
                sb.append("Tbl_LeadMaster.profilingAgentId IN (");
                sb.append(userFilter);
                sb.append(")");

            } else {
                if (datefiltertype.equalsIgnoreCase("profilingdatecustom")) {
                    sb.append("Tbl_LeadMaster.profilingAgentId IN (");
                    sb.append(userFilter);
                    sb.append(")");
                } else {
                    if (userFilter.equalsIgnoreCase("1")) {

                        sb.append("Tbl_LeadMaster.profilingAgentId IN (");
                        sb.append(UtilityClass.getCurrentUser().getId());
                        sb.append(")");
                    } else {
                        sb.append("Tbl_LeadMaster.lastForward IN (");
                        sb.append(userFilter);
                        sb.append(")");
                    }
                }

            }


        } else {
            sb.append("Tbl_LeadMaster.lastForward in (");
            sb.append(UtilityClass.userlist.get(userId));
            sb.append(" ) ");
        }


        return sb.toString();

    }

    @PostMapping("/updateleadstatus")
    public ResponseDao updateleadstatus(@RequestParam("leadstatus") String leadstatus, @RequestParam("id") long id, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        leadMasterService.updateLeadStatus(id, userMasterDao.getUsername(), leadstatus);
        ResponseDao resDao = new ResponseDao();
        resDao.setStatusCode(200);
        resDao.setMsg("Success");
        return resDao;

    }

    @PostMapping("/updateleadstatusandstage")
    public ResponseDao updateleadstatusandstage(@RequestParam("leadstatus") String leadstatus, @RequestParam("leadstage") String leadstage, @RequestParam("id") long id, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        leadMasterService.updateLeadStageAndStatus(id, userMasterDao, leadstage, leadstatus);
        ResponseDao resDao = new ResponseDao();
        resDao.setStatusCode(200);
        resDao.setMsg("Success");
        return resDao;
    }

    @PostMapping("/bulkupdateleadstatusandstage")
    public int bulkupdateleadstatusandstage(@RequestBody List<LeadTransferDao> al, @RequestParam("leadstatus") String leadstatus, @RequestParam("leadstage") String leadstage, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        return leadMasterService.bulkUpdateLeadStageAndStatus(al, userMasterDao, leadstage, leadstatus);
    }

//    @PostMapping("/bulksourcechange")
//    public void bulksourcechange(@RequestBody List<Long> leadidList,@RequestParam("newsource") String newSource) throws SQLException {
//        //UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
//        leadMasterService.bulkSourceChange(leadidList,newSource);
//    }

    @PostMapping("/bulkchange")
    public void bulkchangechange(Authentication authentication, @RequestBody List<Long> leadidList, @RequestParam("newvalue") String newvalue, @RequestParam("flag") String flag) throws SQLException {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        if (flag.equalsIgnoreCase("SOURCE")) {
            leadMasterService.bulkSourceChange(leadidList, newvalue, userMasterDao);
        }

        if (flag.equalsIgnoreCase("PROGRAM")) {
            leadMasterService.bulkProgramChange(leadidList, newvalue, userMasterDao);
        }

    }

    @PostMapping("/convertlead")
    public void convertlead(@RequestParam("createdate") String createdate, @RequestParam("id") long id, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        leadMasterService.convertLead(id, userMasterDao.getUsername(), createdate);
    }

    @PostMapping("/quickleadnotification")
    public void quickLeadNotification(@RequestBody QuickNotificationDao quickNotificationDao, Authentication authentication) throws Exception {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        LeadMasterDao leadMasterDao = leadMasterService.findById(quickNotificationDao.getLeadId());
        HashMap<String, String> hashMap = quickNotificationDao.getHashMap();
        hashMap.put("@agentName", userMasterDao.getFirstName() + " " + userMasterDao.getLastName());
        hashMap.put("@agentNumber", userMasterDao.getMobile());
        hashMap.put("@agentEmail", userMasterDao.getEmail());

        // leadMasterService.singleNotificationWithParameter(leadMasterDao, quickNotificationDao.getTemplateId(), hashMap);
        leadMasterService.singleNotificationWithParameter(leadMasterDao, quickNotificationDao.getTemplateId(), quickNotificationDao.getTemplateName(), hashMap);

    }

    @PostMapping("/insertlead")
    public LeadMasterDao insert(@RequestBody LeadMasterDao leadMasterDao, Authentication authentication) throws Exception {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        leadMasterDao.setCreateBy(userMasterDao.getUsername());
        leadMasterDao.setUpdateBy(leadMasterDao.getCreateBy());
        leadMasterDao.setLastForward(userMasterDao.getId());
        leadMasterDao.setCreateDate(UtilityClass.getDateRedable());
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setDateFilter(UtilityClass.dateFilterDate());
        leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
        leadMasterDao.setLastQueryDate(UtilityClass.getDateRedable());
        leadMasterDao.setLastQueryDateFilter(UtilityClass.dateFilterDate());
        leadMasterDao.setLeadDatefilter(UtilityClass.leadDatefilter(leadMasterDao.getLeadDate()));

        leadMasterDao.setLeadsourceinner("Application");

        // String leadStage = UtilityClass.propertyService.findProperty("Lead", "firstLeadStage");
        //String leadStatus = UtilityClass.propertyService.findProperty("Lead", "firstLeadStatus");

        //leadMasterDao.setLeadStage(leadStage);
        //leadMasterDao.setLeadStatus(leadStatus);

        logger.info("Insertting Lead {}", leadMasterDao);
        try {
            leadMasterDao = leadMasterService.insert(leadMasterDao);
        } catch (Exception e) {
            if (e.getMessage().contains("unqphonenumber")) {
                throw new Exception("Lead with this phone number already exists!!");
            }

        }
        return leadMasterDao;
    }

    @PostMapping("/updateurngenerated")
    public void updateUrnGenerated(@RequestParam("leadId") long id, @RequestParam("urnnumber") String urnnumber, Authentication authentication) throws SQLException {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        leadMasterService.updateUrnNumberOfLead(id, urnnumber, u.getUsername());
    }

    @PostMapping("/updatelead")
    public LeadMasterDao updatelead(@RequestBody LeadMasterDao leadMasterDao, Authentication authentication) throws SQLException {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        MaskingDao maskingDao = new MaskingDao().convertHashMapToMasking(u.getFeatures());
        leadMasterDao.setUpdateBy(u.getUsername());
        // leadMasterDao.setUpdateBy("System");
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setLeadsourceinner("Application");
        leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());

        return leadMasterService.update(leadMasterDao, maskingDao);
    }


    private int findFreestUser(HashMap<Integer, Integer> hashMap) {
        int minCount = 100000000;
        int minUserId = 0;
        for (int userId : hashMap.keySet()) {

            if (minCount > hashMap.get(userId)) {

                minUserId = userId;
                minCount = hashMap.get(userId);
            }

        }
        return minUserId;
    }

    public HashMap<Integer, Integer> updateHashMap(HashMap<Integer, Integer> hashMap, int userId) {
        hashMap.put(userId, hashMap.get(userId) + 1);
        return hashMap;
    }

    @PostMapping("/bulkuploadlead")
    public Map<String, String> bulkUploadLeads(@RequestBody List<LeadMasterDao> al, @RequestParam("filtertype") byte filterType, @RequestParam("filterValue") String filerValue, Authentication authentication) throws Exception {
        logger.info("leadController>bulkInsert");
        Iterable<LeadSourceDao> list = leadSourceService.findAll();
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        HashMap<String, LeadSourceDao> leadSourceHashMap = new HashMap<>();
        int total = al.size();
        int successInsert = 0;
        Map<String, String> responseMap = new HashMap<>();
        logger.info("Inserting Bulk Leads of size {}", total);

        for (LeadSourceDao leadSourceDao : list) {
            leadSourceHashMap.put(leadSourceDao.getName(), leadSourceDao);
        }
        if (filterType == 4) {
            int i = 0;
            for (LeadMasterDao leadMasterDao : al) {
                leadMasterDao.setLastForward(Integer.parseInt(filerValue));
                String leadSource = leadMasterDao.getLeadSource();
                if (!leadSourceHashMap.containsKey(leadSource)) {
                    throw new Exception("Invalid Lead Source " + leadSource + " At row" + i + 1);
                }
                leadMasterDao.setLeadStage(leadSourceHashMap.get(leadSource).getInitialStage());
                leadMasterDao.setLeadStatus(leadSourceHashMap.get(leadSource).getInitialStatus());

                al.set(i, leadMasterDao);
                i++;

            }
            logger.info("Inserting ");
            //return leadMasterService.bulkInsert(al, userMasterDao);
            // return mapToExcel(leadMasterService.bulkInsert(al, userMasterDao));
            return leadMasterService.bulkInsert(al, userMasterDao);
        } else {
            HashMap<String, String> hashMap = leadSourceService.getUser(filterType);
            if (hashMap.size() == 0) {
                throw new Exception("No User Can be Found in Lead Allocation!!");
            }
            logger.info("Getting UserList {} and filter type {} ", hashMap, filterType);

            HashMap<Integer, Integer> perUserNotification = new HashMap<>();
            List<String> phonenumberList = new ArrayList<>();
            String date = UtilityClass.getDateRedable();
            int dateFilter = UtilityClass.dateFilterDate();
            int i = 0;
            for (LeadMasterDao leadMasterDao : al) {

                String value = getValueFilterType(leadMasterDao, filterType);
                HashMap<Integer, Integer> userFreeMap;
                try {
                    userFreeMap = leadMasterService.findFreeUser("Conversation", "Pending", hashMap.get(value), value, filterType);
                } catch (Exception ew) {
                    throw new Exception("No User Can be found In " + value);
                }
                logger.info("User Hashmap is after count {}", userFreeMap);
                int userId = findFreestUser(userFreeMap);
                leadMasterDao.setLastForward(userId);
                leadMasterDao.setCreateDate(date);
                leadMasterDao.setDateFilter(dateFilter);
                leadMasterDao.setUpdateDate(date);
                leadMasterDao.setUpdatedatefilter(dateFilter);
                leadMasterDao.setPaymentStatus(false);

                leadMasterDao.setLeadsourceinner("Bulk Upload");
                leadMasterDao.setCreateBy(userMasterDao.getUsername());
                leadMasterDao.setUpdateBy(userMasterDao.getUsername());
                String leadSource = leadMasterDao.getLeadSource();

                if (!leadSourceHashMap.containsKey(leadSource)) {
                    throw new Exception("Invalid Lead Source " + leadSource + " At row" + i + 1);
                }
                leadMasterDao.setLeadStage(leadSourceHashMap.get(leadSource).getInitialStage());
                leadMasterDao.setLeadStatus(leadSourceHashMap.get(leadSource).getInitialStatus());
                try {
                    leadMasterDao = leadMasterService.insert(leadMasterDao);
                    successInsert++;
                    responseMap.put(leadMasterDao.getPhonenumber(), "Uploaded");
                    try {
                        int userCount = perUserNotification.get(leadMasterDao.getLastForward());
                        perUserNotification.put(leadMasterDao.getLastForward(), userCount + 1);

                    } catch (NullPointerException n) {

                        perUserNotification.put(leadMasterDao.getLastForward(), 1);
                    }
                } catch (Exception ew) {
                    responseMap.put(leadMasterDao.getPhonenumber(), "Already Present");
                    phonenumberList.add(leadMasterDao.getPhonenumber());
                }

            }
            logger.info("After Inserting will update Count {} and notification {}", phonenumberList, perUserNotification);
            leadMasterService.updateLeadConterByPhonenumber(phonenumberList, perUserNotification, userMasterDao);
            int duplicate = total - successInsert;
//            if(duplicate == 0){
//                return "Successfully Inserted " + successInsert + " Lead(s)! ";
//            }else{
//                return "Successfully Inserted " + successInsert + " Lead(s)! Ignored "+duplicate+" leads(Probably Duplicates)";
//            }

            //return "Successfully Inserted " + successInsert + " Lead(s)! ";
            // return mapToExcel(responseMap);
            return responseMap;

        }


    }

    private ResponseEntity<Resource> mapToExcel(Map<String, String> insertResponseMap) throws IOException {

        SXSSFWorkbook workbook = new SXSSFWorkbook(insertResponseMap.size());
        CreationHelper createHelper = workbook.getCreationHelper();
        // Create a Sheet
        Sheet sheet = workbook.createSheet("Insert Stats");
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);
        String[] columns = new String[2];
        //String[] columns = {"LeadID","Lead Owner","Created By","Campaign","Keyword","Lead Date","Duplicate-Count","Created On","Updated By","Updated On","Product","Client Type","Source","Inner Source","Lead Stage","Lead Status","Converted?","Convert Date","Time Taken To Convert(Mins)","Parent_Name","Child_Name","Relation_to_the_child","primary_language_of_the_child","primary_language_of_parrent","Gender_of_the_child","Date_of_birth","Contact_Number","Alternate_Contact_Number","Email_ID","City","State","Country","Address","Pincode","Basic_concerns_of_the_child","Does_Your_Child_Have_Development_Delays","We_can_support_you_in_diferent_ways_You_suggest_where_you_want_to_start_with","Does_your_child_have_any_academic_or_learning_concerns?","Does_your_child_have_difficulty_maintaining_friendships","Payment","preferred_time_of_clinical_call","PG_DB_Name","Enabler","BPO","PG_BD_Manager","Set Interested By","Set Interested On","Set Profiling Done By","Set Profiling Done On","C2C-Attempts","Page Name","MBOPS Parent ID","MBOPS Child ID","Lead Score","Primary reason to contact MB","Any previous assessment?","Has your child received any therapies before?","What kind of therapies you would prefer","MBOPS Enroll ID","Assessment Notes"};
        columns[0] = "Phone Number";
        columns[1] = "Status";

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        logger.info("Added headers");
        int rowNum = 1;
        String applicationPrefix = UtilityClass.ApplicationPrefix;

        logger.info("completed test loop");
        logger.info("Adding cells");
        for (Map.Entry<String, String> pair : insertResponseMap.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(pair.getKey() + "");
            row.createCell(1).setCellValue(pair.getValue() + "");
        }

        logger.info("Added cells");
        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            // sheet.autoSizeColumn(i);//this is evry slow
            // get autosized column width
            int currentColumnWidth = sheet.getColumnWidth(i);

            // add custom value to the current width and apply it to column
            sheet.setColumnWidth(i, (currentColumnWidth + 2500));
        }
        logger.info("Resized");

        // Write the output to a file
        final String path = System.getProperty("user.dir") + "/lead-reports/";
        logger.info("path > {}", path);
        final long timeStamp = System.currentTimeMillis();
        final String fileName = "Bulk_Insert_Stats_" + timeStamp + ".xlsx";

        logger.info("writing file");
        FileOutputStream fileOut = new FileOutputStream(path + fileName);
        try {
            workbook.write(fileOut);
            fileOut.close();
            // Closing the workbook
            workbook.dispose();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(path + fileName);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path2 = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path2));

        ResponseEntity re = ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        //Files.deleteIfExists(file.toPath());
        return re;
    }


    private String getValueFilterType(LeadMasterDao lead, byte filterType) {
        if (filterType == 1) {
            return lead.getLeadSource();
        }
        if (filterType == 2) {
            return lead.getInterestedProduct();
        }
        if (filterType == 3) {
            return lead.getClientType();
        }
        return null;
    }

    private int findFreeUser(HashMap<Integer, Integer> hashMap) {
        int lowest = 100000000;
        int userId = 0;
        for (int key : hashMap.keySet()) {

            int value = hashMap.get(key);
            if (lowest < value) {
                lowest = value;
                userId = key;
            }

        }
        return userId;
    }

    @GetMapping("/getleadsourceinner")
    public List<String> getleadsourceinner() throws Exception {
        return leadMasterService.getLeadSourceInner();
    }

    @GetMapping("/getuserbytype")
    public HashMap<String, String> getUserByType(@RequestParam("type") String type) throws SQLException {
        return leadMasterService.getUserByType(type);
    }


    @GetMapping("/transferleadtodep")
    public String tranferLead(@RequestParam("leadId") long leadId, @RequestParam("department") String department, Authentication authentication) throws Exception {
        UserMasterDao currentuser = (UserMasterDao) authentication.getPrincipal();
        UserMasterDao userMasterDao = userMasterService.getRandomUserDepartment(department);//get data from Tbl_UserMaster departmentwise
        if (userMasterDao == null) {
            throw new Exception("No User Can Be Found Inside Department");
        }
        leadMasterService.tranferLead(leadId, userMasterDao.getId());
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setSendMessage(false);
        notificationDao.setCreateBy(currentuser.getUsername());
        notificationDao.setSubject("Lead Transfer");
        notificationDao.setInnerSubject("Lead Transfer");
        notificationDao.setMessage("A New Lead Has Been Transfered To You");
        notificationDao.setLeadId(leadId);
        notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
        notificationDao.setInnerSubject("NOTIFY");
        notificationDao.setRedirectUrl("lead?id=" + leadId);
        notificationDao.setCreateAt(UtilityClass.getDateRedable());
        notificationDao.setTo(userMasterDao.getId());
        notificationService.insertSingleNotification(notificationDao);
        return "Lead has Been Successfully Transfered To " + userMasterDao.getUsername();


    }

    static class SearchString {
        private String[] parameters;
        private String searchValue;

        public String[] getParameters() {
            return parameters;
        }

        public void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        public SearchString[] convertToObject(String json) {
            return new Gson().fromJson(json, SearchString[].class);
        }

    }

    static class QuickNotificationDao {
        private long leadId;
        private String templateId;
        private String templateName;
        private HashMap<String, String> hashMap;

        public long getLeadId() {
            return leadId;
        }

        public void setLeadId(long leadId) {
            this.leadId = leadId;
        }

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public HashMap<String, String> getHashMap() {
            return hashMap;
        }

        public void setHashMap(HashMap<String, String> hashMap) {
            this.hashMap = hashMap;
        }

        public String getTemplateName() {
            return templateName;
        }

        public void setTemplateName(String templateName) {
            this.templateName = templateName;
        }
    }

    @GetMapping("/downloadreport")
    public ResponseEntity<Resource> downloadreport(@RequestParam("statusValue") String statusValue
            , @RequestParam("leadSource") String leadSource
            , @RequestParam("product") String product
            , @RequestParam("leadType") String leadType
            , @RequestParam("userFilter") String userFilter
            , @RequestParam("datefilter") boolean datefilter
            , @RequestParam("datevalue") String datevalue
            , @RequestParam("searchvalue") String searchValue
            , @RequestParam("fieldArray") String fieldArray
            , @RequestParam("leadstage") String leadstage
            , @RequestParam("leadpriority") String leadpriority
            , @RequestParam("id") long id
            , @RequestParam("innersource") String innersource
            , @RequestParam("datefiltertype") String datefiltertype
            , @RequestParam(name = "leadids", required = false, defaultValue = "0") String leadIds
            , Authentication authentication
            , @RequestParam("length") int limit, @RequestParam("start") int offset


    ) throws SQLException, IOException {

        logger.info("Downloading leads");
        logger.info("searchValue >>>> {}", searchValue);
        logger.info("fieldArray >>>> {}", fieldArray);
        logger.info("datefiltertype >>>> {}", datefiltertype);

        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        MaskingDao maskingDao = new MaskingDao().convertHashMapToMasking(userMasterDao.getFeatures());

        String wherequery = "";
        if (id == 0) {
            wherequery = buildQuery(statusValue, leadSource, product, leadType, leadpriority, userFilter, datefilter, datevalue, userMasterDao.getId(), searchValue, leadstage, innersource, datefiltertype, fieldArray, leadIds);
        } else {
            wherequery = " where Tbl_LeadMaster.id=" + id + " ";
        }

        logger.info(">>>>> {}", wherequery);
        PaginationDao pagedLeads = leadMasterService.getAllDynamicQuery(100000, 0, wherequery, maskingDao);
        logger.info("Received PaginationDao of leads for downloading");
        List<Object> leads = pagedLeads.getData();

        SXSSFWorkbook workbook = new SXSSFWorkbook(leads.size());
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Report");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"LeadID", "Lead Owner", "Created By", "Campaign", "Keyword", "Lead Date", "Duplicate-Count", "Created On", "Updated By", "Updated On", "Product", "Client Type", "Source", "Inner Source", "Lead Stage", "Lead Status", "Converted?", "Convert Date", "Time Taken To Convert(Mins)", "Parent_Name", "Child_Name", "Relation_to_the_child", "primary_language_of_the_child", "primary_language_of_parrent", "Gender_of_the_child", "Date_of_birth", "Contact_Number", "Alternate_Contact_Number", "Email_ID", "City", "State", "Country", "Address", "Pincode", "Basic_concerns_of_the_child", "Does_Your_Child_Have_Development_Delays", "We_can_support_you_in_diferent_ways_You_suggest_where_you_want_to_start_with", "Does_your_child_have_any_academic_or_learning_concerns?", "Does_your_child_have_difficulty_maintaining_friendships", "Payment", "preferred_time_of_clinical_call", "PG_DB_Name", "Enabler", "BPO", "PG_BD_Manager", "Set Interested By", "Set Interested On", "Set Profiling Done By", "Set Profiling Done On", "C2C-Attempts", "Page Name", "MBOPS Parent ID", "MBOPS Child ID", "Lead Score", "Primary reason to contact MB", "Any previous assessment?", "Has your child received any therapies before?", "What kind of therapies you would prefer", "MBOPS Enroll ID", "Assessment Notes", "Messenger", "Active", "Preferred Call Time", "Preferred Call Slot", "First Name", "PaymentStatus", "FollowUpMessage", "FollowUpTime"};

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        logger.info("Added headers");
        int rowNum = 1;
        String applicationPrefix = UtilityClass.ApplicationPrefix;
        for (Object lead : leads) {

        }
        logger.info("completed test loop");
        logger.info("Adding cells");

        Map<String, String> codeToCountryMap = new HashMap<>();
        codeToCountryMap.put("0", "NA");
        codeToCountryMap.put("1", "United States of America (USA)");
        codeToCountryMap.put("2", "India");
        codeToCountryMap.put("3", "Australia");
        codeToCountryMap.put("4", "United Kingdom");
        codeToCountryMap.put("5", "China");
        codeToCountryMap.put("6", "United Arab Emirates");
        codeToCountryMap.put("7", "Canada");
        codeToCountryMap.put("8", "Saudi Arabia");
        codeToCountryMap.put("9", "Kuwait");
        codeToCountryMap.put("10", "Bangladesh");
        codeToCountryMap.put("11", "Qatar");
        codeToCountryMap.put("12", "Oman");
        codeToCountryMap.put("13", "Sweden");
        codeToCountryMap.put("14", "Bahrain");
        codeToCountryMap.put("15", "Czech Republic");
        codeToCountryMap.put("16", "Nepal");
        codeToCountryMap.put("17", "Germany");

        for (Object lead : leads) {

            Row row = sheet.createRow(rowNum++);
            LeadMasterDao dao = (LeadMasterDao) lead;


            row.createCell(0).setCellValue(applicationPrefix + dao.getId() + "");
            row.createCell(1).setCellValue(dao.getUsername() + "");
            row.createCell(2).setCellValue(dao.getCreateBy() + "");
            row.createCell(3).setCellValue(dao.getLeadPriority() + "");//CAMPAIGN
            row.createCell(4).setCellValue(dao.getKeyword() + "");//FBCAMPAIGN
            row.createCell(5).setCellValue(dao.getLeadDate() + "");//DATEONWHICH
            row.createCell(6).setCellValue(dao.getCounter() + "");
            row.createCell(7).setCellValue(dao.getCreateDate() + "");
            row.createCell(8).setCellValue(dao.getUpdateBy() + "");
            row.createCell(9).setCellValue(dao.getUpdateDate() + "");
            row.createCell(10).setCellValue(dao.getInterestedProduct() + "");
            row.createCell(11).setCellValue(dao.getClientType() + "");
            row.createCell(12).setCellValue(dao.getLeadSource() + "");
            row.createCell(13).setCellValue(dao.getLeadsourceinner() + "");
            row.createCell(14).setCellValue(dao.getLeadStage() + "");
            row.createCell(15).setCellValue(dao.getLeadStatus() + "");
            row.createCell(16).setCellValue(dao.isLeadConvert() + "");
            row.createCell(17).setCellValue(dao.getConvertDate() + "");
            row.createCell(18).setCellValue(dao.getTimeTakenToConvert() + "");
            row.createCell(19).setCellValue(dao.getParentName() + "");
            row.createCell(20).setCellValue(dao.getChildName() + "");
            row.createCell(21).setCellValue(dao.getRelation() + "");
            row.createCell(22).setCellValue(dao.getChildLanguage() + "");
            row.createCell(23).setCellValue(dao.getParentLanguage() + "");
            row.createCell(24).setCellValue(dao.getGender() + "");
            row.createCell(25).setCellValue(dao.getDob() + "");
            String phone_number = dao.getPhonenumber() + "";
            phone_number = phone_number.replaceAll("[^0-9]", "");
            row.createCell(26).setCellValue(phone_number);
            row.createCell(27).setCellValue(dao.getTypeSchool() + "");
            row.createCell(28).setCellValue(dao.getEmail() + "");
            row.createCell(29).setCellValue(dao.getCity() + "");

            row.createCell(30).setCellValue(dao.getState() + "");
            row.createCell(31).setCellValue(codeToCountryMap.get(dao.getCountry()) + "");


            row.createCell(32).setCellValue(dao.getAddress() + "");
            row.createCell(33).setCellValue(dao.getPincode() + "");
            row.createCell(34).setCellValue(dao.getDescrip() + "");
            row.createCell(35).setCellValue(dao.getChildDevDelay() + "");
            row.createCell(36).setCellValue(dao.getSupportOption() + "");
            row.createCell(37).setCellValue(dao.getLearningConcern() + "");
            row.createCell(38).setCellValue(dao.getDifficultyInFriendship() + "");
            row.createCell(39).setCellValue(dao.getPayment() + "");
            row.createCell(40).setCellValue(dao.getClinicalCallTime() + "");
            row.createCell(41).setCellValue(dao.getPgBdName() + "");
            row.createCell(42).setCellValue(dao.getProfessionFilled() + "");//enabler
            row.createCell(43).setCellValue(dao.getChildPlayPattern() + "");//bpo
            row.createCell(44).setCellValue(dao.getPgBdManager() + "");

            row.createCell(45).setCellValue(dao.getInterestedAgent() + "");
            row.createCell(46).setCellValue(dao.getInterestedDate() + "");
            row.createCell(47).setCellValue(dao.getProfilingAgent() + "");
            row.createCell(48).setCellValue(dao.getProfilingDate() + "");
            row.createCell(49).setCellValue(dao.getC2cAttempts() + "");

            row.createCell(50).setCellValue(dao.getPageName() + "");
            row.createCell(51).setCellValue(dao.getMbopsParentId() + "");
            row.createCell(52).setCellValue(dao.getMbopsChildId() + "");
            row.createCell(53).setCellValue(dao.getLeadScore() + "");


            //"Primary reason to contact MB",
            // "Any previous assessment?",
            // "Has your child received any therapies before?",
            // "What kind of therapies you would prefer"
            row.createCell(54).setCellValue(dao.getChildDevDelay() + "");

            String formalAssessment = dao.getFormalAssessment() + "";
            formalAssessment = formalAssessment.replace("|", "");
            row.createCell(55).setCellValue(formalAssessment + "");

            String ongoingTherapy = dao.getOngoingTherapy() + "";
            ongoingTherapy = ongoingTherapy.replace("|", "");
            row.createCell(56).setCellValue(ongoingTherapy + "");

            row.createCell(57).setCellValue(dao.getTypeTherapy() + "");

            row.createCell(58).setCellValue(dao.getMbopsChildEnrollId() + "");
            row.createCell(59).setCellValue(dao.getAssessmentNotes() + "");
            row.createCell(60).setCellValue(dao.getMessenger() + "");
            row.createCell(61).setCellValue(dao.getActive() + "");
            row.createCell(62).setCellValue(dao.getPreferredCallingTime() + "");
            row.createCell(63).setCellValue(dao.getPreferredCallingSlot() + "");


            try {
                String parentName = dao.getParentName().trim();
                String parentFirstName = parentName.split(" ")[0];
                if (parentFirstName.length() > 3) {
                    row.createCell(64).setCellValue(parentFirstName + "");
                } else {
                    row.createCell(64).setCellValue(parentName + "");
                }
                row.createCell(65).setCellValue(dao.isPaymentStatus() + "");
                row.createCell(66).setCellValue(dao.getFollowUpTime() + "");
                row.createCell(67).setCellValue(dao.getFollowUpMessage() + "");

            } catch (Exception ex) {
            }
        }

        logger.info("Added cells");
        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            // sheet.autoSizeColumn(i);//this is evry slow
            // get autosized column width
            int currentColumnWidth = sheet.getColumnWidth(i);

            // add custom value to the current width and apply it to column
            sheet.setColumnWidth(i, (currentColumnWidth + 2500));
        }
        logger.info("Resized");

        // Write the output to a file
        final String path = System.getProperty("user.dir") + "/lead-reports/";
        logger.info("path > {}", path);
        final long timeStamp = System.currentTimeMillis();
        final String fileName = "Report_" + timeStamp + ".xlsx";

        logger.info("writing file");
        FileOutputStream fileOut = new FileOutputStream(path + fileName);
        try {
            logger.info("writting workbook");
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.dispose();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(path + fileName);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path2 = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path2));

        logger.info("retruning report");

        ResponseEntity re = ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        //Files.deleteIfExists(file.toPath());
        return re;


    }

    @GetMapping("/downloadreviewreport")
    public ResponseEntity<Resource> downloadreviewreport(@RequestParam("statusValue") String statusValue
            , @RequestParam("leadSource") String leadSource
            , @RequestParam("product") String product
            , @RequestParam("leadType") String leadType
            , @RequestParam("userFilter") String userFilter
            , @RequestParam("datefilter") boolean datefilter

            , @RequestParam("datevalue") String datevalue
            , @RequestParam("searchvalue") String searchValue
            , @RequestParam("fieldArray") String fieldArray
            , @RequestParam("leadstage") String leadstage
            , @RequestParam("leadpriority") String leadpriority
            , @RequestParam("id") long id
            , @RequestParam("innersource") String innersource
            , @RequestParam("datefiltertype") String datefiltertype
            , @RequestParam(name = "leadids", required = false, defaultValue = "0") String leadIds
            , Authentication authentication
            , @RequestParam("length") int limit, @RequestParam("start") int offset


    ) throws SQLException, IOException {

        logger.info("Downloading Review Report");
        logger.info("searchValue >>>> {}", searchValue);
        logger.info("fieldArray >>>> {}", fieldArray);
        logger.info("datefiltertype >>>> {}", datefiltertype);

        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        MaskingDao maskingDao = new MaskingDao().convertHashMapToMasking(userMasterDao.getFeatures());

        String wherequery = "";
        if (id == 0) {
            wherequery = buildQuery(statusValue, leadSource, product, leadType, leadpriority, userFilter, datefilter, datevalue, userMasterDao.getId(), searchValue, leadstage, innersource, datefiltertype, fieldArray, leadIds);
        } else {
            wherequery = " where Tbl_LeadMaster.id=" + id + " ";
        }
        List<ServiceReviewDao> reviewList = leadMasterService.getAll(wherequery);
        logger.info("Received PaginationDao of leads for downloading");
        //List<Object> leads = pagedLeads.getData();

        SXSSFWorkbook workbook = new SXSSFWorkbook(reviewList.size());
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Review Report");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);
        String[] columns = new String[19];
        //String[] columns = {"LeadID","Lead Owner","Created By","Campaign","Keyword","Lead Date","Duplicate-Count","Created On","Updated By","Updated On","Product","Client Type","Source","Inner Source","Lead Stage","Lead Status","Converted?","Convert Date","Time Taken To Convert(Mins)","Parent_Name","Child_Name","Relation_to_the_child","primary_language_of_the_child","primary_language_of_parrent","Gender_of_the_child","Date_of_birth","Contact_Number","Alternate_Contact_Number","Email_ID","City","State","Country","Address","Pincode","Basic_concerns_of_the_child","Does_Your_Child_Have_Development_Delays","We_can_support_you_in_diferent_ways_You_suggest_where_you_want_to_start_with","Does_your_child_have_any_academic_or_learning_concerns?","Does_your_child_have_difficulty_maintaining_friendships","Payment","preferred_time_of_clinical_call","PG_DB_Name","Enabler","BPO","PG_BD_Manager","Set Interested By","Set Interested On","Set Profiling Done By","Set Profiling Done On","C2C-Attempts","Page Name","MBOPS Parent ID","MBOPS Child ID","Lead Score","Primary reason to contact MB","Any previous assessment?","Has your child received any therapies before?","What kind of therapies you would prefer","MBOPS Enroll ID","Assessment Notes"};
        columns[0] = "Lead ID";
        columns[1] = "Parent Name";
        columns[2] = "Child Name";
        columns[3] = "Phone Number";
        columns[4] = "Email";
        columns[5] = "City";
        columns[6] = "State";
        columns[7] = "Lead Type";
        columns[8] = "Lead Stage";
        columns[9] = "Lead Status";
        columns[10] = "How is your child doing, is he enjoying the program and strategies given by the therapist?";
        columns[11] = "How about you, are you facing any challenges in terms practising the activities or skills?";
        columns[12] = "Are you interested in an in-house counsellor who can help you?";
        columns[13] = "Will you recommend us to anyone? (Rate us on the scale of 1 to 10, 1 being lowest and 10 being highest)";
        columns[14] = "How do you see the impact on child with the intervention provided? (Rate us on the scale of 1 to 10, 1 being lowest and 10 being highest)";
        columns[15] = "what 2- 3 value add or service that we should start to help you more in your day-to-day life.";
        columns[16] = "How would you rate our therapist? (Rate us on the scale of 1 to 10, 1 being lowest and 10 being highest)";
        columns[17] = "Review Date";
        columns[18] = "Review By";


        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        logger.info("Added headers");
        int rowNum = 1;
        String applicationPrefix = UtilityClass.ApplicationPrefix;

        logger.info("completed test loop");
        logger.info("Adding cells");
        for (ServiceReviewDao dao : reviewList) {

            Row row = sheet.createRow(rowNum++);


            row.createCell(0).setCellValue(dao.getLeadId() + "");
            row.createCell(1).setCellValue(dao.getParentName() + "");
            row.createCell(2).setCellValue(dao.getChildName() + "");
            row.createCell(3).setCellValue(dao.getPhonenumber() + "");
            row.createCell(4).setCellValue(dao.getEmail() + "");
            row.createCell(5).setCellValue(dao.getCity() + "");
            row.createCell(6).setCellValue(dao.getState() + "");
            row.createCell(7).setCellValue(dao.getClientType() + "");
            row.createCell(8).setCellValue(dao.getLeadStage() + "");
            row.createCell(9).setCellValue(dao.getLeadStatus() + "");
            row.createCell(10).setCellValue(dao.getHowsChildDoing() + "");
            row.createCell(11).setCellValue(dao.getFacingAnyChallenges() + "");
            String interestedInCounsellor = (dao.getInterestedInCounsellor() == 1) ? "Yes" : "No";
            row.createCell(12).setCellValue(interestedInCounsellor + "");
            row.createCell(13).setCellValue(dao.getRecommendationScore() + "");
            row.createCell(14).setCellValue(dao.getImpactScore() + "");
            row.createCell(15).setCellValue("1.) " + dao.getSuggestedService1() + " | 2.) " + dao.getSuggestedService2() + " | .3) " + dao.getSuggestedService3());
            row.createCell(16).setCellValue(dao.getTherapistScore() + "");
            row.createCell(17).setCellValue(dao.getCreateDate() + "");
            row.createCell(18).setCellValue(dao.getCreateBy() + "");


        }

        logger.info("Added cells");
        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            // sheet.autoSizeColumn(i);//this is evry slow
            // get autosized column width
            int currentColumnWidth = sheet.getColumnWidth(i);

            // add custom value to the current width and apply it to column
            sheet.setColumnWidth(i, (currentColumnWidth + 2500));
        }
        logger.info("Resized");

        // Write the output to a file
        final String path = System.getProperty("user.dir") + "/lead-reports/";
        logger.info("path > {}", path);
        final long timeStamp = System.currentTimeMillis();
        final String fileName = "Review_Report_" + timeStamp + ".xlsx";

        logger.info("writing file");
        FileOutputStream fileOut = new FileOutputStream(path + fileName);
        try {
            logger.info("writing workbook");
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.dispose();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(path + fileName);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path2 = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path2));

        logger.info("returning report");

        ResponseEntity re = ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        //Files.deleteIfExists(file.toPath());
        return re;


    }


    @GetMapping("/downloadpreschoolreport")
    public ResponseEntity<Resource> downloadpreschoolreport(@RequestParam("statusValue") String statusValue
            , @RequestParam("leadSource") String leadSource
            , @RequestParam("product") String product
            , @RequestParam("leadType") String leadType
            , @RequestParam("userFilter") String userFilter
            , @RequestParam("datefilter") boolean datefilter

            , @RequestParam("datevalue") String datevalue
            , @RequestParam("searchvalue") String searchValue
            , @RequestParam("fieldArray") String fieldArray
            , @RequestParam("leadstage") String leadstage
            , @RequestParam("leadpriority") String leadpriority
            , @RequestParam("id") long id
            , @RequestParam("innersource") String innersource
            , @RequestParam("datefiltertype") String datefiltertype
            , @RequestParam(name = "leadids", required = false, defaultValue = "0") String leadIds
            , Authentication authentication
            , @RequestParam("length") int limit, @RequestParam("start") int offset


    ) throws SQLException, IOException {

        logger.info("Downloading leads");
        logger.info("searchValue >>>> {}", searchValue);
        logger.info("fieldArray >>>> {}", fieldArray);
        logger.info("datefiltertype >>>> {}", datefiltertype);

        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        MaskingDao maskingDao = new MaskingDao().convertHashMapToMasking(userMasterDao.getFeatures());

        String wherequery = "";
        if (id == 0) {
            wherequery = buildQuery(statusValue, leadSource, product, leadType, leadpriority, userFilter, datefilter, datevalue, userMasterDao.getId(), searchValue, leadstage, innersource, datefiltertype, fieldArray, leadIds);
        } else {
            wherequery = " where Tbl_LeadMaster.id=" + id + " ";
        }
        PaginationDao pagedLeads = leadMasterService.getAllDynamicQuery(100000, 0, wherequery, maskingDao);
        logger.info("Received PaginationDao of leads for downloading");
        List<Object> leads = pagedLeads.getData();

        SXSSFWorkbook workbook = new SXSSFWorkbook(leads.size());
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet preschoolsheet = workbook.createSheet("Preschool_Assessment_Report");
        Sheet mainstreamsheet = workbook.createSheet("Mainstream_Assessment_Report");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row pre_headerRow = preschoolsheet.createRow(0);
        Row main_headerRow = mainstreamsheet.createRow(0);

        String[] pre_columns = new String[95];
        pre_columns[0] = "LeadID";
        pre_columns[1] = "ChildID";
        pre_columns[2] = "Lead Owner";
        pre_columns[3] = "Parent_Name";
        pre_columns[4] = "Child_Name";
        pre_columns[5] = "Date_of_birth";
        pre_columns[6] = "Age";
        pre_columns[7] = "Goes_To_School? (Category)";
        pre_columns[8] = "Contact_Number";
        pre_columns[9] = "Alternate_Contact_Number";
        pre_columns[10] = "Email_ID";
        pre_columns[11] = "City";
        pre_columns[12] = "Lead_Type";
        pre_columns[13] = "Lead_Score";

        //============= GROSS MOTOR SKILLS
        pre_columns[14] = "Can walk independently with good balance (Gross Motor Skills)";
        pre_columns[15] = "Can kick a ball forward without falling (Gross Motor Skills)";
        pre_columns[16] = "Can walk up and down stairs without assistance (Gross Motor Skills)";
        pre_columns[17] = "Can hop forward on one foot without support (Gross Motor Skills)";
        pre_columns[18] = "Can throw a ball and hit a target with a dominant hand (Gross Motor Skills)";
        pre_columns[19] = "Can jump rope without assistance (Gross Motor Skills)";
        //============= FINE MOTOR SKILLS
        pre_columns[20] = "Can remove forms from a form board or inset puzzle like shapes or animals etc (Fine Motor Skills)";
        pre_columns[21] = "Can scribble linear and/or circular patterns spontaneously (Fine Motor Skills)";
        pre_columns[22] = "Can use pads of fingertips to grasp pencil or tripod grip (Fine Motor Skills)";
        pre_columns[23] = "Can string 4 large beads (Fine Motor Skills)";
        pre_columns[24] = "Can hold paper with one hand while drawing or writing with other hand (Fine Motor Skills)";
        pre_columns[25] = "Can draw a person with 4 or more parts (Fine Motor Skills)";
        pre_columns[26] = "Can cut with scissors, following a line (Fine Motor Skills)";
        pre_columns[27] = "Can imitate vertical and horizontal markings (Fine Motor Skills)";
        pre_columns[28] = "Can copy a circle (Fine Motor Skills)";
        pre_columns[29] = "Can copy the letters (Fine Motor Skills)";
        //============= SELF HELP
        pre_columns[30] = "Can use spoon or fork for eating solid food (Self Help)";
        pre_columns[31] = "Can drink from a cup without assistance (little SPILLING is okay) (Self Help)";
        pre_columns[32] = "Can undo atleast two of these - large buttons, snaps, shoes laces, zipper,velcro etc (Self Help)";
        pre_columns[33] = "Can pull pants/underpants down  (Self Help)";
        pre_columns[34] = "Can put on shoes (even if he or she put on the wrong foot) (Self Help)";
        pre_columns[35] = "Can express need to use the toile (Self Help)t";
        pre_columns[36] = "Can control his or her bowel movements (Self Help)";
        pre_columns[37] = "Can washes hands independently (Self Help)";
        pre_columns[38] = "Can take care of toileting needs (i.e undressing, cleaning, and dressing) (Self Help)";
        pre_columns[39] = "Can manipulates large buttons, velcro, zippers independently (Self Help)";
        //============ SOCIAL EMOTIONAL
        pre_columns[40] = "Can identify self as boy or a girl (Social Emotional)";
        pre_columns[41] = "Can greet familiar adults spontaneously (Social Emotional)";
        pre_columns[42] = "Can imitate or help in simple house hold tasks (Social Emotional)";
        pre_columns[43] = "Can participate in group activities (Social Emotional)";
        pre_columns[44] = "Can name his or her friends (Social Emotional)";
        pre_columns[45] = "Can understand rules of simple games when playing with adults or peers (Social Emotional)";
        pre_columns[46] = "Can waits for his turn for adults or teachers attention (Social Emotional)";
        pre_columns[47] = "Can describe his or her feelings i.e says I am sad or happy or mad (Social Emotional)";
        pre_columns[48] = "Can recognize the feelings of others (Social Emotional)";
        pre_columns[49] = "Can show responsibility and admits errors or wrong doing i.e says sorry when made a mistake (Social Emotional)";
        //============ COGNITIVE
        pre_columns[50] = "Can point to atleast twenty things or pictures when they are named (Cognitive)";
        pre_columns[51] = "Can show interest and enjoyment in age appropriate books and printed material? (Cognitive)";
        pre_columns[52] = "Can match colors or point correctly to at least two colors (Cognitive)";
        pre_columns[53] = "Can say size words like large/big or little/small correctly? (Cognitive)";
        pre_columns[54] = "Can dress up and play/act pretending to be someone else? (Cognitive)";
        pre_columns[55] = "Can name five different colors? (Cognitive)";
        pre_columns[56] = "Can know the difference between living and nonliving things (Cognitive)";
        pre_columns[57] = "Can attend to a learning task /story in a small group for 5 minutes (Cognitive)";
        pre_columns[58] = "Can follow two or more step directions or instructions (Cognitive)";
        pre_columns[59] = "Can answer simple logical questions like ' What do we eat'? (Cognitive)";
        pre_columns[60] = "Can say alphabets or letter names by rote? (Cognitive)";
        //============ COMMUNICATION
        pre_columns[61] = "Can identify his or her own body parts like nose, eyes, ears (Communication)";
        pre_columns[62] = "Can look at or point to objects across the room when it is named (Communication)";
        pre_columns[63] = "Can use 10 or more words (Communication)";
        pre_columns[64] = "Can say two or three words sentences (Communication)";
        pre_columns[65] = "Can correctly use words like me, I, Mine or You (Communication)";
        pre_columns[66] = "Can respond to who or what questions e.g 'who gives us milk' or 'what do you do with a pen' (Communication)";
        pre_columns[67] = "Can tell his or her first and last name (Communication)";
        pre_columns[68] = "Can use pronouns like I, you and me (Communication)";
        pre_columns[69] = "Can respond with yes or no appropriately (Communication)";
        pre_columns[70] = "Can ask questions that begin with who and where (Communication)";
        pre_columns[71] = "Can answer WH questions - what, who, when, where, why, and how (Communication)";
        pre_columns[72] = "Can use 5-6 words sentences (Communication)";
        pre_columns[73] = "Can recall events from a story presented orally (Communication)";
        pre_columns[74] = "Can communicate his or her experiences clearly enough for other to understand (Communication)";
        pre_columns[75] = "Can recognize a printing of his or her first and last names (Communication)";
        pre_columns[76] = "Can read a simple story aloud so that someone who is only listening can follow the story (Communication)";
        pre_columns[77] = "Can engage in a meaningful conversations with adults or peers (Communication)";
        pre_columns[78] = "Can describe what is happening in a picture (Communication)";
        //============ BEHAVIOUR
        pre_columns[79] = "Inappropriate movements like fidgeting, walking and running excessively (Behaviour)";
        pre_columns[80] = "Inappropriate vocalizations like talking out, excessive crying or teasing or jargon speech (Behaviour)";
        pre_columns[81] = "Repetitive body movements like rocking, toe walking, spinning etc (Behaviour)";
        pre_columns[82] = "Poor peer interactions (Behaviour)";
        pre_columns[83] = "Self absorbed or does not relate to other adults or children (Behaviour)";
        pre_columns[84] = "Difficulty transitioning from one activity to another (Behaviour)";

        //========= GROUPS
        pre_columns[85] = "Gross Motor Skills Score";
        pre_columns[86] = "Fine Motor Skills Score";
        pre_columns[87] = "Self Help Score";
        pre_columns[88] = "Social Emotional Score";
        pre_columns[89] = "Cognitive Score";
        pre_columns[90] = "Communication Score";
        pre_columns[91] = "Behaviors Score";
        pre_columns[92] = "Lead_Score";
        pre_columns[93] = "Assessment_Notes";
        pre_columns[94] = "Assessment_Date";


//================================================================== MAINSTREAM FORM

        String[] main_columns = new String[112];
        main_columns[0] = "LeadID";
        main_columns[1] = "ChildId";
        main_columns[2] = "Lead Owner";
        main_columns[3] = "Parent_Name";
        main_columns[4] = "Child_Name";
        main_columns[5] = "Date_of_birth";
        main_columns[6] = "Age";
        main_columns[7] = "Goes_To_School? (Category)";
        main_columns[8] = "Contact_Number";
        main_columns[9] = "Alternate_Contact_Number";
        main_columns[10] = "Email_ID";
        main_columns[11] = "City";
        main_columns[12] = "Lead_Type";
        main_columns[13] = "Lead_Score";

        //=============LISTENING

        main_columns[14] = "Asks for statements or sentences to be repeated (Listening)";
        main_columns[15] = "Often says 'what?' 'Huh' etc (Listening)";
        main_columns[16] = "Fails to follow directions given verbally (Listening)";
        main_columns[17] = "Has difficulty understanding common vocabulary words (Listening)";
        main_columns[18] = "Has difficuty listening attentively to stories, tv shows and or music (Listening)";
        main_columns[19] = "Has difficulty remembering multiple commands (Listening)";
        main_columns[20] = "Repeats phrases to self over and over before understanding takes place (Listening)";
        main_columns[21] = "Answers questions inappropriately (Listening)";
        main_columns[22] = "Asks irrelevant questions after hearing a story (Listening)";
        main_columns[23] = "Confuses time concepts (before/after) and confuses direction words (front/back) (Listening)";
        main_columns[24] = "Has difficulty understanding nonliteral or abstract language like metaphors (Listening)";
        //============= ORAL EXPRESSION SPEAKING
        main_columns[25] = "Speaks in single word statements only (Oral Expression Speaking)";
        main_columns[26] = "Speaks in small phrases only (Oral Expression Speaking)";
        main_columns[27] = "Has difficulty speaking spontaneously (Oral Expression Speaking)";
        main_columns[28] = "Has slow or labored speech (Oral Expression Speaking)";
        main_columns[29] = "Has mis articulation in speech (Oral Expression Speaking)";
        main_columns[30] = "Cannot repeat long sentences (Oral Expression Speaking)";
        main_columns[31] = "Has difficulty retrieving words  (Oral Expression Speaking)";
        main_columns[32] = "Is slow in retrieving words (Oral Expression Speaking)";
        main_columns[33] = "Uses immature or bizarre patterns of language or uses immature grammar when speaking (Oral Expression Speaking)";
        main_columns[34] = "Has difficulty relating ideas in sequence like telling stories in sequence (Oral Expression Speaking)";
        main_columns[35] = "Contributes to class discussions with off task comments (Oral Expression Speaking)";
        main_columns[36] = "Requires much time to respond verbally (Oral Expression Speaking)";
        //============= BASIC READING
        main_columns[37] = "Avoids reading ";
        main_columns[38] = "Behavior changes when asked to read silently or orally (Basic Reading)";
        main_columns[39] = "Has poor memory of letter and words (Basic Reading)";
        main_columns[40] = "Has difficulty matching letters (Basic Reading)";
        main_columns[41] = "Is not consistent with letter sounds and blending skills (Basic Reading)";
        main_columns[42] = "Guesses words from initial letters (Basic Reading)";
        main_columns[43] = "Guesses words from pictorial cues (Basic Reading)";
        main_columns[44] = "Has difficulty sounding out words correctly (Basic Reading)";
        main_columns[45] = "Has difficulty with sight vocabulary (Basic Reading)";
        main_columns[46] = "Skips lines while reading (Basic Reading)";
        main_columns[47] = "Uses finger to anchor self when reading (Basic Reading)";
        main_columns[48] = "Reads high frequency sight words incorrectly like the, and, but etc (Basic Reading)";
        main_columns[49] = "Does not observe punctuation while reading (Basic Reading)";
        //============= READING COMPREHENSION
        main_columns[50] = "Has difficulty reading and following directions (Reading Comprehension)";
        main_columns[51] = "Has difficulty answering questions after reading (Reading Comprehension)";
        main_columns[52] = "Has difficulty in doing math word problems (Reading Comprehension)";
        main_columns[53] = "Has difficulty understanding main idea of a passage (Reading Comprehension)";
        main_columns[54] = "Has difficulty using content cues to guess at unkown word (Reading Comprehension)";
        main_columns[55] = "Has difficulty reading independently (Reading Comprehension)";
        main_columns[56] = "Cannot retell what has been read (Reading Comprehension)";
        //============= MATH CALCULATIONS
        main_columns[57] = "Confuses operational signs (Math Calculations)";
        main_columns[58] = "Uses fingers for computation (Math Calculations)";
        main_columns[59] = "Reverses numbers (Math Calculations)";
        main_columns[60] = "Has difficulty with place values (Math Calculations)";
        main_columns[61] = "Has difficulty with keeping colums straight (Math Calculations)";
        main_columns[62] = "Has difficulty doing single digit operations (addition, substraciton, multiplication or division) (Math Calculations)";
        main_columns[63] = "Has difficulty doing multi digit operations (addition, substraciton, multiplication or division) (Math Calculations)";
        main_columns[64] = "Has difficulty completing sums that requires more than one operation (Math Calculations)";
        main_columns[65] = "Has difficulty estimating answers (Math Calculations)";
        main_columns[66] = "Completes problem in mind but unable to put on paer and in writing steps (Math Calculations)";
        //============= MATH REASONING
        main_columns[67] = "Has difficulty applying math operations to real life problems (Math Reasoning)";
        main_columns[68] = "Has difficulty telling time (Math Reasoning)";
        main_columns[69] = "Has difficulty using calendar correctly (Math Reasoning)";
        main_columns[70] = "Guesses answers instead of trying to solve problems (Math Reasoning)";
        main_columns[71] = "Has difficulty solving problems involving a sequence of steps (Math Reasoning)";
        main_columns[72] = "Has difficulty organizing a task to facilitate tis completion (Math Reasoning)";
        main_columns[73] = "Has difficulty with word problems (Math Reasoning)";
        //============= WRITTEN EXPRESSION
        main_columns[74] = "Has difficulty holding pencil in correct allignment to paper (Written Expression)";
        main_columns[75] = "Writes slowly or awkwardly (Written Expression)";
        main_columns[76] = "Reverses letters like b and d  (Written Expression)";
        main_columns[77] = "Uses extra effort to write  (Written Expression)";
        main_columns[78] = "Misspells words phonetically (Written Expression)";
        main_columns[79] = "Misspells high frequnecy sight words (Written Expression)";
        main_columns[80] = "Fails to retain spelling words studied in word lists or spelling lists (Written Expression)";
        main_columns[81] = "Has difficulty expresing a complete thought in  sentence (Written Expression)";
        main_columns[82] = "Has difficulty expressing a complete thought in a paragraph (Written Expression)";
        main_columns[83] = "Has difficulty producing written work which is legible, properly spaced, capitalized and punctuated (Written Expression)";
        main_columns[84] = "Mixes manuscript and cursive forms (Written Expression)";
        main_columns[85] = "Has difficulty sequencing thoughts in a narrative (Written Expression)";
        //============= BEHAVIOUR
        main_columns[86] = "Listens to teacher/classmate and follows directions (Behaviour)";
        main_columns[87] = "Makes excessive demand on teacher time (Behaviour)";
        main_columns[88] = "Overactive/restless/fidgety (Behaviour)";
        main_columns[89] = "Impulsive (Behaviour)";
        main_columns[90] = "Disturbs others in class (Behaviour)";
        main_columns[91] = "Has diffiuclty maintaining peer relations or has poor peer interactions (Behaviour)";
        main_columns[92] = "Out of seat (Behaviour)";
        main_columns[93] = "Off task behaviors (Behaviour)";
        main_columns[94] = "Destractible (Behaviour)";
        main_columns[95] = "Talkative or makes inappropriate noises (Behaviour)";
        main_columns[96] = "Blurts out answers without thinking (Behaviour)";
        main_columns[97] = "Has trouble keeping hands and feet to self or makes repetetive motor movements (Behaviour)";
        main_columns[98] = "Destructive/Aggressive (kicks others, hits others, throws things etc) (Behaviour)";
        main_columns[99] = "Isolate self from others (Behaviour)";
        main_columns[100] = "Indulges in self injurious behaviors (Behaviour)";

        //===================== GROUPS

        main_columns[101] = "Listening Score";
        main_columns[102] = "Oral Expression Score";
        main_columns[103] = "Basic Reading Score";
        main_columns[104] = "Reading Comprehension Score";
        main_columns[105] = "Math Calculations Score";
        main_columns[106] = "Math Reasoning Score";
        main_columns[107] = "Written Expression Score";
        main_columns[108] = "Behavior Score";
        main_columns[109] = "Lead_Score";

        main_columns[110] = "Assessment_Notes";
        main_columns[111] = "Assessment_Date";


        // Create cells
        for (int i = 0; i < pre_columns.length; i++) {
            Cell pre_cell = pre_headerRow.createCell(i);
            logger.info("i > {} , val > {}", i, pre_columns[i]);
            pre_cell.setCellValue(pre_columns[i]);
            pre_cell.setCellStyle(headerCellStyle);
        }
        // Create cells
        for (int i = 0; i < main_columns.length; i++) {
            Cell main_cell = main_headerRow.createCell(i);
            main_cell.setCellValue(main_columns[i]);
            main_cell.setCellStyle(headerCellStyle);
        }

        logger.info("Added headers");
        int pre_rowNum = 1;
        int main_rowNum = 1;
        String applicationPrefix = UtilityClass.ApplicationPrefix;
        logger.info("completed test loop");
        logger.info("Adding cells");
        for (Object lead : leads) {

            LeadMasterDao dao = (LeadMasterDao) lead;
            long age = UtilityClass.getDateDifferenceInYear(dao.getDob());
            logger.info("age {}", age);
            logger.info("goes to school ? > {}", dao.getDoesChildGoToSchool());
            if (dao.getDoesChildGoToSchool() == null) {
                dao.setDoesChildGoToSchool("NO");
            }

            //if age < 5 and goes to school OR age > 5 but doesnt go to school, add to preschool sheet
            if ((age < 5) || (age > 5 && dao.getDoesChildGoToSchool().equalsIgnoreCase("no"))) {
                Row pre_row = preschoolsheet.createRow(pre_rowNum++);

                pre_row.createCell(0).setCellValue(applicationPrefix + dao.getId() + "");
                pre_row.createCell(1).setCellValue(dao.getMbopsChildId() + "");
                pre_row.createCell(2).setCellValue(dao.getUsername() + "");
                pre_row.createCell(3).setCellValue(dao.getParentName() + "");
                pre_row.createCell(4).setCellValue(dao.getChildName() + "");
                pre_row.createCell(5).setCellValue(dao.getDob() + "");
                pre_row.createCell(6).setCellValue(age + "");
                pre_row.createCell(7).setCellValue(dao.getDoesChildGoToSchool() + "(PreSchool)");
                pre_row.createCell(8).setCellValue(dao.getPhonenumber() + "");
                pre_row.createCell(9).setCellValue(dao.getTypeSchool() + "");//ALTERNATE FOR NUMBER
                pre_row.createCell(10).setCellValue(dao.getEmail() + "");
                pre_row.createCell(11).setCellValue(dao.getCity() + "");
                pre_row.createCell(12).setCellValue(dao.getClientType() + "");
                pre_row.createCell(13).setCellValue(dao.getLeadScore() + "");

                //=================================================================

                pre_row.createCell(14).setCellValue(dao.getPre_Gross_Motor_Skills1() + "");
                pre_row.createCell(15).setCellValue(dao.getPre_Gross_Motor_Skills2() + "");
                pre_row.createCell(16).setCellValue(dao.getPre_Gross_Motor_Skills3() + "");
                pre_row.createCell(17).setCellValue(dao.getPre_Gross_Motor_Skills4() + "");
                pre_row.createCell(18).setCellValue(dao.getPre_Gross_Motor_Skills5() + "");
                pre_row.createCell(19).setCellValue(dao.getPre_Gross_Motor_Skills6() + "");
                pre_row.createCell(20).setCellValue(dao.getPre_Fine_motor_Skills1() + "");
                pre_row.createCell(21).setCellValue(dao.getPre_Fine_motor_Skills2() + "");
                pre_row.createCell(22).setCellValue(dao.getPre_Fine_motor_Skills3() + "");
                pre_row.createCell(23).setCellValue(dao.getPre_Fine_motor_Skills4() + "");
                pre_row.createCell(24).setCellValue(dao.getPre_Fine_motor_Skills5() + "");
                pre_row.createCell(25).setCellValue(dao.getPre_Fine_motor_Skills6() + "");
                pre_row.createCell(26).setCellValue(dao.getPre_Fine_motor_Skills7() + "");
                pre_row.createCell(27).setCellValue(dao.getPre_Fine_motor_Skills8() + "");
                pre_row.createCell(28).setCellValue(dao.getPre_Fine_motor_Skills9() + "");
                pre_row.createCell(29).setCellValue(dao.getPre_Fine_motor_Skills10() + "");
                pre_row.createCell(30).setCellValue(dao.getPre_Self_help1() + "");
                pre_row.createCell(31).setCellValue(dao.getPre_Self_help2() + "");
                pre_row.createCell(32).setCellValue(dao.getPre_Self_help3() + "");
                pre_row.createCell(33).setCellValue(dao.getPre_Self_help4() + "");
                pre_row.createCell(34).setCellValue(dao.getPre_Self_help5() + "");
                pre_row.createCell(35).setCellValue(dao.getPre_Self_help6() + "");
                pre_row.createCell(36).setCellValue(dao.getPre_Self_help7() + "");
                pre_row.createCell(37).setCellValue(dao.getPre_Self_help8() + "");
                pre_row.createCell(38).setCellValue(dao.getPre_Self_help9() + "");
                pre_row.createCell(39).setCellValue(dao.getPre_Self_help10() + "");
                pre_row.createCell(40).setCellValue(dao.getPre_Social_Emotional1() + "");
                pre_row.createCell(41).setCellValue(dao.getPre_Social_Emotional2() + "");
                pre_row.createCell(42).setCellValue(dao.getPre_Social_Emotional3() + "");
                pre_row.createCell(43).setCellValue(dao.getPre_Social_Emotional4() + "");
                pre_row.createCell(44).setCellValue(dao.getPre_Social_Emotional5() + "");
                pre_row.createCell(45).setCellValue(dao.getPre_Social_Emotional6() + "");
                pre_row.createCell(46).setCellValue(dao.getPre_Social_Emotional7() + "");
                pre_row.createCell(47).setCellValue(dao.getPre_Social_Emotional8() + "");
                pre_row.createCell(48).setCellValue(dao.getPre_Social_Emotional9() + "");
                pre_row.createCell(49).setCellValue(dao.getPre_Social_Emotional10() + "");
                pre_row.createCell(50).setCellValue(dao.getPre_Cognitive1() + "");
                pre_row.createCell(51).setCellValue(dao.getPre_Cognitive2() + "");
                pre_row.createCell(52).setCellValue(dao.getPre_Cognitive3() + "");
                pre_row.createCell(53).setCellValue(dao.getPre_Cognitive4() + "");
                pre_row.createCell(54).setCellValue(dao.getPre_Cognitive5() + "");
                pre_row.createCell(55).setCellValue(dao.getPre_Cognitive6() + "");
                pre_row.createCell(56).setCellValue(dao.getPre_Cognitive7() + "");
                pre_row.createCell(57).setCellValue(dao.getPre_Cognitive8() + "");
                pre_row.createCell(58).setCellValue(dao.getPre_Cognitive9() + "");
                pre_row.createCell(59).setCellValue(dao.getPre_Cognitive10() + "");
                pre_row.createCell(60).setCellValue(dao.getPre_Cognitive11() + "");
                pre_row.createCell(61).setCellValue(dao.getPre_Communication1() + "");
                pre_row.createCell(62).setCellValue(dao.getPre_Communication2() + "");
                pre_row.createCell(63).setCellValue(dao.getPre_Communication3() + "");
                pre_row.createCell(64).setCellValue(dao.getPre_Communication4() + "");
                pre_row.createCell(65).setCellValue(dao.getPre_Communication5() + "");
                pre_row.createCell(66).setCellValue(dao.getPre_Communication6() + "");
                pre_row.createCell(67).setCellValue(dao.getPre_Communication7() + "");
                pre_row.createCell(68).setCellValue(dao.getPre_Communication8() + "");
                pre_row.createCell(69).setCellValue(dao.getPre_Communication9() + "");
                pre_row.createCell(70).setCellValue(dao.getPre_Communication10() + "");
                pre_row.createCell(71).setCellValue(dao.getPre_Communication11() + "");
                pre_row.createCell(72).setCellValue(dao.getPre_Communication12() + "");
                pre_row.createCell(73).setCellValue(dao.getPre_Communication13() + "");
                pre_row.createCell(74).setCellValue(dao.getPre_Communication14() + "");
                pre_row.createCell(75).setCellValue(dao.getPre_Communication15() + "");
                pre_row.createCell(76).setCellValue(dao.getPre_Communication16() + "");
                pre_row.createCell(77).setCellValue(dao.getPre_Communication17() + "");
                pre_row.createCell(78).setCellValue(dao.getPre_Communication18() + "");
                pre_row.createCell(79).setCellValue(dao.getPre_Behaviors1() + "");
                pre_row.createCell(80).setCellValue(dao.getPre_Behaviors2() + "");
                pre_row.createCell(81).setCellValue(dao.getPre_Behaviors3() + "");
                pre_row.createCell(82).setCellValue(dao.getPre_Behaviors4() + "");
                pre_row.createCell(83).setCellValue(dao.getPre_Behaviors5() + "");
                pre_row.createCell(84).setCellValue(dao.getPre_Behaviors6() + "");
                //====== GROUP SCORES
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X6
                pre_row.createCell(85).setCellValue(dao.getPregrossmotorskills() + " (" + ((dao.getPregrossmotorskills() * 100) / 54) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X10
                pre_row.createCell(86).setCellValue(dao.getPrefinemotorskills() + " (" + ((dao.getPrefinemotorskills() * 100) / 90) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X10
                pre_row.createCell(87).setCellValue(dao.getPreselfhelp() + " (" + ((dao.getPreselfhelp() * 100) / 90) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X10
                pre_row.createCell(88).setCellValue(dao.getPresocialemotional() + " (" + ((dao.getPresocialemotional() * 100) / 90) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X11
                pre_row.createCell(89).setCellValue(dao.getPrecognitive() + " (" + ((dao.getPrecognitive() * 100) / 99) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X18
                pre_row.createCell(90).setCellValue(dao.getPrecommunication() + " (" + ((dao.getPrecommunication() * 100) / 162) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X6
                pre_row.createCell(91).setCellValue(dao.getPrebehaviors() + " (" + ((dao.getPrebehaviors() * 100) / 54) + "%)");


                pre_row.createCell(92).setCellValue(dao.getLeadScore() + "");
                pre_row.createCell(93).setCellValue(dao.getAssessmentNotes() + "");
                pre_row.createCell(94).setCellValue(dao.getAssessmentDate() + "");
                pre_row.createCell(95).setCellValue(dao.isPaymentStatus());

            } else {//if age > 5 and goes to school, add to mainstream sheet
                Row main_row = mainstreamsheet.createRow(main_rowNum++);
                main_row.createCell(0).setCellValue(applicationPrefix + dao.getId() + "");
                main_row.createCell(1).setCellValue(dao.getMbopsChildId() + "");
                main_row.createCell(2).setCellValue(dao.getUsername() + "");
                main_row.createCell(3).setCellValue(dao.getParentName() + "");
                main_row.createCell(4).setCellValue(dao.getChildName() + "");
                main_row.createCell(5).setCellValue(dao.getDob() + "");
                main_row.createCell(6).setCellValue(age + "");
                main_row.createCell(7).setCellValue(dao.getDoesChildGoToSchool() + "(MainStream)");
                main_row.createCell(8).setCellValue(dao.getPhonenumber() + "");
                main_row.createCell(9).setCellValue(dao.getTypeSchool() + "");//ALTERNATE FOR NUMBER
                main_row.createCell(10).setCellValue(dao.getEmail() + "");
                main_row.createCell(11).setCellValue(dao.getCity() + "");
                main_row.createCell(12).setCellValue(dao.getClientType() + "");
                main_row.createCell(13).setCellValue(dao.getLeadScore() + "");

                //=====================================================================

                main_row.createCell(14).setCellValue(dao.getMain_Listening1() + "");
                main_row.createCell(15).setCellValue(dao.getMain_Listening2() + "");
                main_row.createCell(16).setCellValue(dao.getMain_Listening3() + "");
                main_row.createCell(17).setCellValue(dao.getMain_Listening4() + "");
                main_row.createCell(18).setCellValue(dao.getMain_Listening5() + "");
                main_row.createCell(19).setCellValue(dao.getMain_Listening6() + "");
                main_row.createCell(20).setCellValue(dao.getMain_Listening7() + "");
                main_row.createCell(21).setCellValue(dao.getMain_Listening8() + "");
                main_row.createCell(22).setCellValue(dao.getMain_Listening9() + "");
                main_row.createCell(23).setCellValue(dao.getMain_Listening10() + "");
                main_row.createCell(24).setCellValue(dao.getMain_Listening11() + "");
                main_row.createCell(25).setCellValue(dao.getMain_Oral_Expression_Speaking1() + "");
                main_row.createCell(26).setCellValue(dao.getMain_Oral_Expression_Speaking2() + "");
                main_row.createCell(27).setCellValue(dao.getMain_Oral_Expression_Speaking3() + "");
                main_row.createCell(28).setCellValue(dao.getMain_Oral_Expression_Speaking4() + "");
                main_row.createCell(29).setCellValue(dao.getMain_Oral_Expression_Speaking5() + "");
                main_row.createCell(30).setCellValue(dao.getMain_Oral_Expression_Speaking6() + "");
                main_row.createCell(31).setCellValue(dao.getMain_Oral_Expression_Speaking7() + "");
                main_row.createCell(32).setCellValue(dao.getMain_Oral_Expression_Speaking8() + "");
                main_row.createCell(33).setCellValue(dao.getMain_Oral_Expression_Speaking9() + "");
                main_row.createCell(34).setCellValue(dao.getMain_Oral_Expression_Speaking10() + "");
                main_row.createCell(35).setCellValue(dao.getMain_Oral_Expression_Speaking11() + "");
                main_row.createCell(36).setCellValue(dao.getMain_Oral_Expression_Speaking12() + "");
                main_row.createCell(37).setCellValue(dao.getMain_Basic_Reading1() + "");
                main_row.createCell(38).setCellValue(dao.getMain_Basic_Reading2() + "");
                main_row.createCell(39).setCellValue(dao.getMain_Basic_Reading3() + "");
                main_row.createCell(40).setCellValue(dao.getMain_Basic_Reading4() + "");
                main_row.createCell(41).setCellValue(dao.getMain_Basic_Reading5() + "");
                main_row.createCell(42).setCellValue(dao.getMain_Basic_Reading6() + "");
                main_row.createCell(43).setCellValue(dao.getMain_Basic_Reading7() + "");
                main_row.createCell(44).setCellValue(dao.getMain_Basic_Reading8() + "");
                main_row.createCell(45).setCellValue(dao.getMain_Basic_Reading9() + "");
                main_row.createCell(46).setCellValue(dao.getMain_Basic_Reading10() + "");
                main_row.createCell(47).setCellValue(dao.getMain_Basic_Reading11() + "");
                main_row.createCell(48).setCellValue(dao.getMain_Basic_Reading12() + "");
                main_row.createCell(49).setCellValue(dao.getMain_Basic_Reading13() + "");
                main_row.createCell(50).setCellValue(dao.getMain_Reading_Comprehension1() + "");
                main_row.createCell(51).setCellValue(dao.getMain_Reading_Comprehension2() + "");
                main_row.createCell(52).setCellValue(dao.getMain_Reading_Comprehension3() + "");
                main_row.createCell(53).setCellValue(dao.getMain_Reading_Comprehension4() + "");
                main_row.createCell(54).setCellValue(dao.getMain_Reading_Comprehension5() + "");
                main_row.createCell(55).setCellValue(dao.getMain_Reading_Comprehension6() + "");
                main_row.createCell(56).setCellValue(dao.getMain_Reading_Comprehension7() + "");
                main_row.createCell(57).setCellValue(dao.getMain_Math_Calculations1() + "");
                main_row.createCell(58).setCellValue(dao.getMain_Math_Calculations2() + "");
                main_row.createCell(59).setCellValue(dao.getMain_Math_Calculations3() + "");
                main_row.createCell(60).setCellValue(dao.getMain_Math_Calculations4() + "");
                main_row.createCell(61).setCellValue(dao.getMain_Math_Calculations5() + "");
                main_row.createCell(62).setCellValue(dao.getMain_Math_Calculations6() + "");
                main_row.createCell(63).setCellValue(dao.getMain_Math_Calculations7() + "");
                main_row.createCell(64).setCellValue(dao.getMain_Math_Calculations8() + "");
                main_row.createCell(65).setCellValue(dao.getMain_Math_Calculations9() + "");
                main_row.createCell(66).setCellValue(dao.getMain_Math_Calculations10() + "");
                main_row.createCell(67).setCellValue(dao.getMain_Math_Reasoning1() + "");
                main_row.createCell(68).setCellValue(dao.getMain_Math_Reasoning2() + "");
                main_row.createCell(69).setCellValue(dao.getMain_Math_Reasoning3() + "");
                main_row.createCell(70).setCellValue(dao.getMain_Math_Reasoning4() + "");
                main_row.createCell(71).setCellValue(dao.getMain_Math_Reasoning5() + "");
                main_row.createCell(72).setCellValue(dao.getMain_Math_Reasoning6() + "");
                main_row.createCell(73).setCellValue(dao.getMain_Math_Reasoning7() + "");
                main_row.createCell(74).setCellValue(dao.getMain_Written_Expression1() + "");
                main_row.createCell(75).setCellValue(dao.getMain_Written_Expression2() + "");
                main_row.createCell(76).setCellValue(dao.getMain_Written_Expression3() + "");
                main_row.createCell(77).setCellValue(dao.getMain_Written_Expression4() + "");
                main_row.createCell(78).setCellValue(dao.getMain_Written_Expression5() + "");
                main_row.createCell(79).setCellValue(dao.getMain_Written_Expression6() + "");
                main_row.createCell(80).setCellValue(dao.getMain_Written_Expression7() + "");
                main_row.createCell(81).setCellValue(dao.getMain_Written_Expression8() + "");
                main_row.createCell(82).setCellValue(dao.getMain_Written_Expression9() + "");
                main_row.createCell(83).setCellValue(dao.getMain_Written_Expression10() + "");
                main_row.createCell(84).setCellValue(dao.getMain_Written_Expression11() + "");
                main_row.createCell(85).setCellValue(dao.getMain_Written_Expression12() + "");
                main_row.createCell(86).setCellValue(dao.getMain_Behavior1() + "");
                main_row.createCell(87).setCellValue(dao.getMain_Behavior2() + "");
                main_row.createCell(88).setCellValue(dao.getMain_Behavior3() + "");
                main_row.createCell(89).setCellValue(dao.getMain_Behavior4() + "");
                main_row.createCell(90).setCellValue(dao.getMain_Behavior5() + "");
                main_row.createCell(91).setCellValue(dao.getMain_Behavior6() + "");
                main_row.createCell(92).setCellValue(dao.getMain_Behavior7() + "");
                main_row.createCell(93).setCellValue(dao.getMain_Behavior8() + "");
                main_row.createCell(94).setCellValue(dao.getMain_Behavior9() + "");
                main_row.createCell(95).setCellValue(dao.getMain_Behavior10() + "");
                main_row.createCell(96).setCellValue(dao.getMain_Behavior11() + "");
                main_row.createCell(97).setCellValue(dao.getMain_Behavior12() + "");
                main_row.createCell(98).setCellValue(dao.getMain_Behavior13() + "");
                main_row.createCell(99).setCellValue(dao.getMain_Behavior14() + "");
                main_row.createCell(100).setCellValue(dao.getMain_Behavior15() + "");
                //================== GROUP sCORES
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X11
                main_row.createCell(101).setCellValue(dao.getMainlistening() + " (" + ((dao.getMainlistening() * 100) / 99) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X12
                main_row.createCell(102).setCellValue(dao.getMainloralexpression() + " (" + ((dao.getMainloralexpression() * 100) / 108) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X13
                main_row.createCell(103).setCellValue(dao.getMainbasicreading() + " (" + ((dao.getMainbasicreading() * 100) / 117) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X7
                main_row.createCell(104).setCellValue(dao.getMainreadingcomprehension() + " (" + ((dao.getMainreadingcomprehension() * 100) / 63) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X10
                main_row.createCell(105).setCellValue(dao.getMainmathcalculations() + " (" + ((dao.getMainmathcalculations() * 100) / 90) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X7
                main_row.createCell(106).setCellValue(dao.getMainmathreasoning() + " (" + ((dao.getMainmathreasoning() * 100) / 63) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X12
                main_row.createCell(107).setCellValue(dao.getMainwrittenexpression() + " (" + ((dao.getMainwrittenexpression() * 100) / 108) + "%)");
                //Max possible score for this group =  No of Options X 9 (max possible core per option) = 9X15
                main_row.createCell(108).setCellValue(dao.getMainbehavior() + " (" + ((dao.getMainbehavior() * 100) / 135) + "%)");

                main_row.createCell(109).setCellValue(dao.getLeadScore() + "");
                main_row.createCell(110).setCellValue(dao.getAssessmentNotes() + "");
                main_row.createCell(111).setCellValue(dao.getAssessmentDate() + "");


            }

        }

        logger.info("Added cells");
        // Resize all columns to fit the content size
        for (int i = 0; i < pre_columns.length; i++) {
            // sheet.autoSizeColumn(i);//this is evry slow
            // get autosized column width
            int currentColumnWidth = preschoolsheet.getColumnWidth(i);

            // add custom value to the current width and apply it to column
            preschoolsheet.setColumnWidth(i, (currentColumnWidth + 2500));
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < main_columns.length; i++) {
            // sheet.autoSizeColumn(i);//this is evry slow
            // get autosized column width
            int currentColumnWidth = mainstreamsheet.getColumnWidth(i);

            // add custom value to the current width and apply it to column
            mainstreamsheet.setColumnWidth(i, (currentColumnWidth + 2500));
        }

        logger.info("Resized");

        // Write the output to a file
        final String path = System.getProperty("user.dir") + "/lead-reports/";
        logger.info("path > {}", path);
        final long timeStamp = System.currentTimeMillis();
        final String fileName = "Assessment_Report_" + timeStamp + ".xlsx";

        logger.info("writing file");
        FileOutputStream fileOut = new FileOutputStream(path + fileName);
        try {
            logger.info("writting workbook");
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.dispose();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(path + fileName);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path2 = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path2));

        logger.info("retruning report");

        ResponseEntity re = ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        //Files.deleteIfExists(file.toPath());
        return re;
    }


    @PostMapping("/transferleadtocrm")
    public String transferleadtocrm(@RequestParam String url,
                                    @RequestParam long leadId) throws IOException {
        System.out.println("url > " + url);
        System.out.println("leadId > " + leadId);
        LeadMasterDao leadMasterDao = leadMasterService.findById(leadId);
        String source = UtilityClass.propertyService.findProperty("Lead", "MINDLMS_LeadSource");
        String token = UtilityClass.propertyService.findProperty("Lead", "MINDLMS_LeadSourceToken");
//         return leadMasterService.transferleadtocrm(url,leadId,source,token);
        return leadMasterService.transferleadtocrm(url, leadId, source, token);
    }

    @GetMapping("/gettimeslotcount")
    public int gettimeslotcount(@RequestParam String timeSlot) throws SQLException {
        return leadMasterService.getAppointmentSlotCount(timeSlot);
    }

    @PostMapping("/updatemainstreamform")
    public LeadMasterDao updateMainstamForm(@RequestBody LeadMasterDao leadMasterDao, Authentication auth) {
        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        leadMasterDao.setUpdateBy(user.getUsername());
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
        return leadMasterService.updateMainstreamAssessmentForm(leadMasterDao);
    }

    @PostMapping("/updatepreschoolform")
    public LeadMasterDao updatepreschoolform(@RequestBody LeadMasterDao leadMasterDao, Authentication auth) {
        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        leadMasterDao.setUpdateBy(user.getUsername());
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
        return leadMasterService.updatePreschoolAssessmentForm(leadMasterDao);
    }

    @PostMapping("/updateaccessmentnotes")
    public LeadMasterDao upadatesAssessmentNotes(@RequestParam("id") long id, @RequestParam("notes") String notes) {
        return leadMasterService.upadatesAssessmentNotes(id, notes);
    }


}
