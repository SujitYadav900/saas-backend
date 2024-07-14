package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.CampaignObjectDao;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Service.UrlShortnerService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.google.gson.Gson;


import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {
    Logger logger = LoggerFactory.getLogger(UrlShortnerServiceImpl.class);
    @Override
    public List<String> bulkUrlShorten(List<String> al,String id) throws Exception {
        logger.info("Starting Url Shortner Service");
        int size=al.size();
        String newurl;
        List<String> newal=new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            newurl= al.get(i).replaceAll("campainIdDynamic",id);
            newal.add(newurl);
        }
        return newal;
    }




    @Override
    public String singleUrlShorten(String url) throws Exception {
        Thread.sleep(1000);
        return url;
    }

    @Override
    public List<CampaignObjectDao> urlShortnerServer(List<CampaignObjectDao> al) throws IOException {
        UrlNester urlNester=new UrlNester();
        logger.info("Making Request For Url Shortner");
        String[] url=new String[al.size()];
        for(int i=0;i<al.size();i++)
        {
            url[i]=al.get(i).getUrl() ;
        }
        urlNester.setUrl(url);
        String json= urlNester.convertToJson();

        Urlshort urlshort= makeARequest(json);
        url=urlshort.getUrlshort();
        for(int i=0;i<url.length;i++)
        {
            al.get(i).setUrl("http://"+url[i].trim());
        }

        logger.info("Completed Request For Url Shortner");
        return al;
    }

    private Urlshort makeARequest(String json) throws IOException {
        String urlShortApi= UtilityClass.propertyService.findProperty("Application","Url_Shortner_Api");


        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);


        Request request = new Request.Builder()
                .url(urlShortApi)
                .post(body)

                .build();

        String resjson = OkHttpSingleTon.client.newCall(request).execute().body().string();

        Urlshort entity = new Gson().fromJson(resjson, Urlshort.class);

       return entity;

    }



    class UrlNester{
        public String[] getUrl() {
            return url;
        }

        public void setUrl(String[] url) {
            this.url = url;
        }

        String[] url;
        String convertToJson()
        {
            return new Gson().toJson(this);
        }


    }
    class Urlshort{
        public String[] getUrlshort() {
            return urlshort;
        }

        public void setUrlshort(String[] urlshort) {
            this.urlshort = urlshort;
        }

        private String[] urlshort;


    }
}
