package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/urlshort")
@RestController
public class UrlShortnerController {
    @Autowired
    UrlShortnerService urlShortnerService;

    @GetMapping("/shorturl")
    List<String> shortAll(@RequestBody List<String> al) throws Exception {
        return null;
    }
}
