package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao;
/*
 Author=Supreet Singh
 Date= 09/03/21 10:36 AM
*/

import java.io.Serializable;

public class DateTimeDao implements Serializable {


    @Override
    public String toString() {
        return "DateTimeDao{" +
                "fallback_value='" + fallback_value + '\'' +
                ", day_of_week=" + day_of_week +
                ", day_of_month=" + day_of_month +
                ", year=" + year +
                ", month=" + month +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }

    public String getFallback_value() {
        return fallback_value;
    }

    public void setFallback_value(String fallback_value) {
        this.fallback_value = fallback_value;
    }

    public int getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(int day_of_week) {
        this.day_of_week = day_of_week;
    }

    public int getDay_of_month() {
        return day_of_month;
    }

    public void setDay_of_month(int day_of_month) {
        this.day_of_month = day_of_month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    private String fallback_value;
    private int day_of_week;
    private int day_of_month;
    private int year;
    private int month;
    private int hour;
    private int minute;
}
