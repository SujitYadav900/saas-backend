package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.CampaignObjectDao;

import java.io.IOException;
import java.util.List;

public interface UrlShortnerService
{
    List<String> bulkUrlShorten(List<String> al,String id) throws Exception;
    String singleUrlShorten(String url) throws Exception;
    List<CampaignObjectDao> urlShortnerServer( List<CampaignObjectDao> al) throws IOException;

}
