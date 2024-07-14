package com.example.maxcrm.MaxCrm.Controller;


import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/user")
    public String user() {
        return "usermaster";
    }

    @RequestMapping("/template")
    public String template() {
        return "template";
    }
    @RequestMapping("/feature")
    public String features()
    {
        return "features";
    }
    @RequestMapping(value = "/loginotp", method = RequestMethod.GET)
    public String loginOtp(Model model, String error, HttpServletRequest request) {

        if (error != null)
            model.addAttribute("errorMsg", error);


        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("otp") == null) {
            return "login";
        }

        return "loginotp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, Authentication authentication, HttpServletResponse response,HttpServletRequest request) {
        try {
            UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
            if(userMasterDao!=null)
            {
                response.sendRedirect("/");

            }
            else{
                HttpSession httpSession=request.getSession();
                httpSession.invalidate();
            }
        }catch (Exception ew)
        {
            HttpSession httpSession=request.getSession();
            httpSession.invalidate();
        }

        if (error != null)
            model.addAttribute("errorMsg", error);


        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }


    @RequestMapping("/templatetype")
    public String templatetype() {
        return "templatetype";
    }


    @RequestMapping("/profile")
    public String profile(HttpServletRequest request) {


        return "profile";
    }

    @RequestMapping("/lead")
    public String lead() {
        return "leadmaster";
    }


    @RequestMapping("/campaigndaywise")
    public String campaigndaywise() {
        return "campaigndaywise";
    }


    @RequestMapping("/campaign")
    public String campaign() {
        return "campaign";
    }

    @RequestMapping("/leadstage")
    public String leadstage() {
        return "leadstage";
    }

    @RequestMapping("/mailhistory")
    public String mailhistory() {
        return "mailhistory";
    }

    @RequestMapping("/templateapproval")
    public String templateapprove() {
        return "templateapprove";
    }
    @RequestMapping("/campaigntransaction")
    public String campaigntransaction() {
        return "campaigntransaction";
    }

}



