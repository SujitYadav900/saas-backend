package com.example.maxcrm.MaxCrm.Dao;

import java.util.ArrayList;
import java.util.List;


public class MessageCampaignDao extends CampaignDataService {
  private final List<InnerTextMessageDao> innerTextMessageDaos;

    public MessageCampaignDao(String[] arrtemp,List<CampaignObjectDao> al,String templateName)
    {

        innerTextMessageDaos=new ArrayList<>();
        StringBuilder sb;
        InnerTextMessageDao temp;
        for(int k=0;k<al.size();k++)
            {
                temp=new InnerTextMessageDao();
                 sb=new StringBuilder();
                for(int i=0;i<arrtemp.length;i++)
                {
                    String content=arrtemp[i].trim();

                    System.out.println("content :: "+content);
                    if(content=="phonenumber")
                    {
                        sb.append(al.get(k).getPhonenumber());

                    }
                    else{
                        sb.append(al.get(k).getMatchingValue(content));
                    }
                    if(i!=arrtemp.length-1)
                    {
                        sb.append("~");
                    }

                }
                temp.setMobileNo(al.get(k).getPhonenumber());
                temp.setTemplateParams(sb.toString());
                temp.setTemplateName(templateName);
                innerTextMessageDaos.add(temp);


            }



    }
    public MessageCampaignDao(String[] arrtemp,CampaignObjectDao al,String templateName,String[] phonenumbers)
    {

        innerTextMessageDaos=new ArrayList<>();
        StringBuilder sb;
        InnerTextMessageDao temp;
        for(int k=0;k<phonenumbers.length;k++)
        {
            temp=new InnerTextMessageDao();
            sb=new StringBuilder();
            for(int i=0;i<arrtemp.length;i++)
            {
                String content=arrtemp[i].trim();
                if(content=="phonenumber")
                {
                    sb.append(al.getPhonenumber());

                }
                else{
                    sb.append(al.getMatchingValue(content));
                }
                if(i!=arrtemp.length-1)
                {
                    sb.append("~");
                }

            }
            temp.setTemplateName(templateName);
            temp.setTemplateParams(sb.toString());
            temp.setMobileNo(phonenumbers[k]);
            innerTextMessageDaos.add(temp);


        }



    }

    public MessageCampaignDao(String[] arrtemp,CampaignObjectDao al,String templateName)
    {
        innerTextMessageDaos=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        InnerTextMessageDao temp=new InnerTextMessageDao();
        for (int i = 0; i < arrtemp.length; i++) {


                String content=arrtemp[i].trim();
                if(content=="phonenumber")
                {
                    sb.append( al.getPhonenumber());
                }
                else{
                    sb.append(al.getMatchingValue(content));
                }


            if(i!=arrtemp.length-1)
            {
                sb.append("~");
            }

        }
        temp.setMobileNo(al.getPhonenumber());
        temp.setTemplateParams(sb.toString());
        temp.setTemplateName(templateName);
        innerTextMessageDaos.add(temp);

    }



    public TextMessage convertToObject(String send)
    {
        TextMessage textMessage=new TextMessage();
        textMessage.setSender(send);
        textMessage.setSmsTemplateParams(innerTextMessageDaos);
        return textMessage;


    }


}
