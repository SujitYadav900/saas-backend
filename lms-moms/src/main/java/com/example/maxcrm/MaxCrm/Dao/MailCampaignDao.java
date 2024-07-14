package com.example.maxcrm.MaxCrm.Dao;

import com.example.maxcrm.MaxCrm.Utility.UtilityClass;

public class MailCampaignDao extends CampaignDataService {
    private final String content;
    private final String date;
    private final int filterDate;
    private final String to;
    private final String subject;

    public MailCampaignDao(String content,String[] arr,CampaignObjectDao campaignDataService,String subject) {

        for(int i=0;i<arr.length;i++)
        {

            content=content.replaceAll("@"+arr[i].trim(),campaignDataService.getMatchingValue(arr[i].trim()));
        }
        this.content = content;
        this.date= UtilityClass.getDateRedable();
        this.filterDate=UtilityClass.dateFilterDate();
        this.to=campaignDataService.getEmail();
        this.subject=subject;
    }












}
