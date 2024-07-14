package com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTiming {
    public static String getDateRedable() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static long changeGivenDateFormat(String strDate, String fromDateFormat, String toDateFormat){
        try{
            SimpleDateFormat previousFormat = new SimpleDateFormat(fromDateFormat);
            Date date = previousFormat.parse(strDate);
            SimpleDateFormat newFormat = new SimpleDateFormat(toDateFormat);
            String newFormatedDate  = newFormat.format(date);
            return Long.parseLong(newFormatedDate);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static String[] getCurrentAndNextDate()
    {
        String[] timeArr=new String[3];
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        Date newDate = new Date(date.getTime() + TimeUnit.HOURS.toMillis(24));
        timeArr[1]= dateFormat.format(newDate);
        timeArr[2]=dateFormat2.format(newDate );
        timeArr[0]= dateFormat.format(date);

        return timeArr;
    }


    public static String getDateRedableFilter(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        return df.format(date);
    }

    public static String getMysqlDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static int getDateFilterDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return Integer.parseInt(dateFormat.format(date));
    }

    public static long getDateFilterDateLong() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return Long.parseLong(dateFormat.format(date));
    }

    public static int getMonthFilterDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        return Integer.parseInt(dateFormat.format(date));
    }

    public static int dateFilterDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return Integer.parseInt(dateFormat.format(date));

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

    public static String increateMonth(String date, int monthToIncrease) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.MONTH, monthToIncrease);  // number of days to add
        return sdf.format(c.getTime());

    } public static String increateDay(int dayToIncrease) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, dayToIncrease);  // number of days to add
        return sdf.format(c.getTime());

    }
    public static String decreaseDay(int dayToDecrease) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, dayToDecrease);  // number of days togo back
        return sdf.format(c.getTime());

    }


    public static int decreaseMonth(int date, int monthToDecrease) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(String.valueOf(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.MONTH, -monthToDecrease);  // number of days to add
        return Integer.parseInt(sdf.format(c.getTime()));

    }





    public static String convertDateFromDateFilter(int dateFilter) {
        try {

            String dateStr = String.valueOf(dateFilter);

            DateFormat srcDf = new SimpleDateFormat("yyyyMMdd");

            // parse the date string into Date object
            Date date = srcDf.parse(dateStr);

            DateFormat destDf = new SimpleDateFormat("MM-dd-yyyy");

            // format the date into another format
            dateStr = destDf.format(date);

            return dateStr;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] getFirstDateAndEndDateInt() {
        int[] arr = new int[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day);
        int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        arr[0] = Integer.parseInt(sdf.format(c.getTime()));
        c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
        arr[1] = Integer.parseInt(sdf.format(c.getTime()));
        return arr;

    }

    public static String getFirstDayOfMonth() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day);
        return sdf.format(c.getTime());

    }

    public static FilterDao validateFilterDao(FilterDao filterDao) {

        String todayDate = String.valueOf(getDateFilterDate());
        switch (filterDao.getDateFilterType()) {
            case TODAY:
                filterDao.setStartdate(todayDate);
                filterDao.setEnddate(todayDate);
                break;
            case CURRENTMONTH:
                filterDao.setStartdate(getFirstDayOfMonth());
                filterDao.setEnddate(todayDate);
                break;

            case CUSTOM:
                break;
        }
        filterDao.setStartdate(filterDao.getStartdate().replaceAll("-", ""));
        filterDao.setEnddate(filterDao.getEnddate().replaceAll("-", ""));
        return filterDao;
    }

    public static int decreaseDayOnly(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        dateFormat.format(cal.getTime());
        return Integer.parseInt(dateFormat.format(cal.getTime()));
    }
    public static void main(String[] args) {
//        String[] arr=getCurrentAndNextDate();
//        System.out.println(arr[0]+" "+arr[1]);

    }
}
