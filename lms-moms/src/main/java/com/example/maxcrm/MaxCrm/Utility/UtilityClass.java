package com.example.maxcrm.MaxCrm.Utility;


import com.example.maxcrm.MaxCrm.Dao.LeadSourceDao;
import com.example.maxcrm.MaxCrm.Dao.StageOptionDocument;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class UtilityClass {
    //  public static String createBy = "Supreet Singh";
    // public static int maximumWrongTry = 5;
    //  public static String nodejsrealtimedeployurl = "http://localhost:8001/"; // for telling desc
    //  public static int maxFileSizeInByte = 1000000;
    public static String ApplicationTitle;
    public static String logoUrl;
    public static String minLogoUrl;
    public static String siteUrl;

    public static String startYear;
    public static String endYear;
    public static String copyRightMsg;
    public static String footerMsg;
    public static String ApplicationPrefix;
    public static HashMap<Integer, String> userlist = new HashMap<>();
    public static HashMap<String, LeadSourceDao> alleadsourcedao = new HashMap<>();
    public static String facebookAccessToken = "566887164168959|ql0dGI46LoWX---8uJnPdIk8Zvw";
    public static String notificationTemplateId = "";
    public static HashMap<String, List<StageOptionDocument>> leadStageAndStaus=new HashMap<>();


    // public static String accountId = "AccountId";
    // public static String pass = "Password";
    // public static String senderId = "asdad";
    //  public static int maximumonecampaignlimit = 10000;


    //  public static String ticketIntitalStatus = "Pending";
    // public static String ticketIntialPriority = "Pending";
    //public static String applicationUrl = "http://localhost:8080/";
    // public static String firstLeadStage = "Conversation";
    // public static String firstLeadStatus = "Pending";
    // public static String ticketCloseStatus = "Closed";
    @Autowired
    static public PropertyService propertyService;

    public static long fullDateLong() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        return Long.parseLong(dateFormat.format(date));
    }

    public static String increaseTime(String date, int increatTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, increatTime);
        return df.format(cal.getTime());

    }

    public static String decreaseTimeOnly(String date, int decreaseMins) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, -decreaseMins);
        return df.format(cal.getTime());

    }

    public static String formatFbDateTime(String fbDateTime){

        fbDateTime = fbDateTime.replaceAll("T"," ");
        fbDateTime = fbDateTime.replaceAll("\\+"," ");
        String[] tempAr = fbDateTime.split(" ");
        fbDateTime = tempAr[0]+ " " + tempAr[1];
        return fbDateTime;

    }

    public static String formatFbDateOnly(String fbDateTime){

        fbDateTime = fbDateTime.replaceAll("T"," ");
        fbDateTime = fbDateTime.replaceAll("\\+"," ");
        String[] tempAr = fbDateTime.split(" ");
        fbDateTime = tempAr[0]+ " " + tempAr[1];
        return fbDateTime;

    }

    public static String decreaseTime(String date, int increasedate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, -increasedate);
        return df.format(cal.getTime());

    }
    public static String getDateMysql() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDateFull() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public static String getDateFullLong() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String getDateRedable() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDateRedableYYYYMMdd() {
        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }



    public static UserMasterDao getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserMasterDao user = null;
        if (auth != null) {
            user = (UserMasterDao) auth.getPrincipal();
        }
        return user;

    }

    public static String[] getFirstDateAndEndDate() {
        String[] arr = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day);
        int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        arr[0] = sdf.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
        arr[1] = sdf.format(c.getTime());
        return arr;

    }

    public static String[] getFirstDateAndEndDateInt() {
        String[] arr = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day);
        int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        arr[0] = sdf.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
        arr[1] = sdf.format(c.getTime());
        return arr;

    }

    public static String[] getFirstDateAndEndDatePlain() {
        String[] arr = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day);
        int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        arr[0] = sdf.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
        arr[1] = sdf.format(c.getTime());
        return arr;

    }

    public static String[] getFirstDateAndEndDateDashed() {
        String[] arr = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day);
        int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        arr[0] = sdf.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
        arr[1] = sdf.format(c.getTime());
        return arr;

    }

    public static String generateOtp() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

    public static int dateFilterDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return Integer.parseInt(dateFormat.format(date));

    }

    public static String getLeadDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static int leadDatefilter(String date){
        date = date.replaceAll("-","");
        return Integer.parseInt(date);
    }

    public static String getDataOnly()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeOnly()
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public static long getDateDifferenceInMinutes(String startdate, String enddate) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startdate);
            d2 = format.parse(enddate);


            long diff = d2.getTime() - d1.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;

            return diffMinutes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static long getDateDifferenceInDays(String startdate, String enddate) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startdate);
            d2 = format.parse(enddate);

            long diffInMillies = Math.abs(d2.getTime() - d1.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            return diff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getDateDifferenceInYear(String startdate) {
        if (startdate!=null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(startdate);
                d2 = format.parse(getDateRedableYYYYMMdd());

                long diffInMillies = Math.abs(d2.getTime() - d1.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                long yeardiff = diff/365;

                return yeardiff;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public static int getRemainingDemoPeriod(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserMasterDao user = null;
        if (auth != null) {
            user = (UserMasterDao) auth.getPrincipal();
        }

        if(user.isDemo()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(user.getCreateDate());
                d2 = format.parse(getDateRedable());
                int demoPeriod = user.getDemoPeriod();

                long diffInMillies = Math.abs(d2.getTime() - d1.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                return (int) (demoPeriod - diff);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());

        return newUri.toString();
    }


      public  static String epepochToHumanReadableDate(String epochdate){
          long date= Long.parseLong(epochdate);
         java.util.Date time=new java.util.Date((long)date*1000);

          String readableDate = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(time);
          System.out.println("Date is this :: "+readableDate);
       return readableDate;
      }

//    //By Rahul------------------------
//    public static String epochToHumanReadableDate(String epochdate){
//        String readableDate = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epochdate));
//        return readableDate;
//    }

    public static void main(String[] args) throws Exception {




    }
}
