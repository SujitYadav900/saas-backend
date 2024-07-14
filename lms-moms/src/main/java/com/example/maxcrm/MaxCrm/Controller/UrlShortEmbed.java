package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Service.CampaignDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/link/allow")
public class UrlShortEmbed {
    Logger logger = LoggerFactory.getLogger(UrlShortEmbed.class);
    @Autowired
    CampaignDocumentService campaignDocumentService;


    @GetMapping("/offer")
    public void openLink(@RequestParam("campaigntransid")String campaigntransid, HttpServletRequest request, HttpServletResponse response) throws Exception {


        String userAgent = request.getHeader("User-Agent");
        String ip=request.getRemoteAddr();
        logger.info("USeragent {} ip {} campaingtrans {}",userAgent,ip,campaigntransid);
        String url=campaignDocumentService.updateClick(campaigntransid,ip,userAgent);


        response.sendRedirect(url);


    }
}
