package com.example.maxcrm.MaxCrm.Controller;


import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewControllerSecondary {

    @GetMapping("/demo")
    public String demo(){
        return "crmdemo";
    }

    public String test1(){
        return "test1";
    }
    @GetMapping("/rolelist")
    public String roleList(){
        return "rolelist";
    }

    @GetMapping("/leadstatus")
    public String leadStatus(){
        return "leadstatus";
    }

    @GetMapping("/leadtype")
    public String leadType(){
        return "leadtype";
    }

    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }

    @GetMapping("/urlaccess")
    public String urlAccess(){
        return "urlaccess";
    }

    @GetMapping("/leadsource")
    public String urlSource(){
        return "leadsource";
    }

    @GetMapping("/product")
    public String product(){
        return "product";
    }

    @GetMapping("/drive")
    public String drive(){
        return "drive";
    }

    @GetMapping("/userupdate")
    public String userUpdate(Model model){

        model.addAttribute("userid", UtilityClass.getCurrentUser().getId());
        return "userupdate";
    }

    @GetMapping("/leadticketstatus")
    public String leadticketstatus(){
        return  "leadticketstatus";
    }

    @GetMapping("/leadpriority")
    public String leadPriority(){
        return "leadpriority";
    }


    @GetMapping("/ticketportal")
    public String ticketportal(){
        return "ticketportal";
    }


    @GetMapping("/department")
    public String department(){
        return "department";
    }

    @GetMapping("/priority")
    public String priority(){
        return "priority";
    }

    @GetMapping("/tickettype")
    public String tickettype(){
        return "tickettype";
    }

    @GetMapping("/leadreportcard")
    public String leadreportcard(){
        return "leadreportcard";
    }

    @GetMapping("/leadstats")
    public String leadstats(){
        return "leadstats";
    }

    @GetMapping("/leadstatsuser")
    public String leadstatsuser(){
        return "leadstatsuser";
    }

    @GetMapping("/leadstatsappointmentdate")
    public String leadstatsappointmentdate(){
        return "leadstatsappointmentdate";
    }

    @GetMapping("/leadstatsdetailed")
    public String leadstatsdetailed(@RequestParam String flag,Model model){
        model.addAttribute("flag",flag);
        return "leadstatsdetailed";
    }

    @GetMapping("/leadstatsdetailedextended")
    public String leadstatsdetailedextended(@RequestParam String flag,Model model){
        model.addAttribute("flag",flag);
        return "leadstatsdetailedextended";
    }

    @GetMapping("/businessreport")
    public String businessreport(){
        return "prospectivebusinessreport";
    }

    @GetMapping("/ticketreportcard")
    public String ticketreportcard(){
        return "ticketreportcard";
    }

    @GetMapping("/getreportbyleadstage")
    public String getreportbyleadstage(){
        return "getreportbyleadstage";
    }

    @GetMapping("/getreportbyleadstatus")
    public String getreportbyleadstatus(){
        return "getreportbyleadstatus";
    }

    @GetMapping("/getreportbyleadtype")
    public String getreportbyleadtype(){
        return "getreportbyleadtype";
    }

    @GetMapping("/getreportbyleadproduct")
    public String getreportbyleadproduct(){
        return "getreportbyleadproduct";
    }

    @GetMapping("/getreportbyleadsource")
    public String getreportbyleadsource(){
        return "getreportbyleadsource";
    }

    @GetMapping("/getreportbyleadsourceinner")
    public String getreportbyleadsourceinner(){
        return "getreportbyleadsourceinner";
    }

    @GetMapping("/getreportbyleadpriority")
    public String getreportbyleadpriority(){
        return "getreportbyleadpriority";
    }

    @GetMapping("/getreportbykeyword")
    public String getreportbykeyword(){
        return "getreportbykeyword";
    }

    @GetMapping("/getreportbyticketstatus")
    public String getreportbyticketstatus(){
        return "getreportbyticketstatus";
    }

    @GetMapping("/getreportbytickettype")
    public String getreportbytickettype(){
        return "getreportbytickettype";
    }

    @GetMapping("/getreportbyticketpriority")
    public String getreportbyticketpriority(){
        return "getreportbyticketpriority";
    }

    @GetMapping("/resetpassword")
    public String resetpassword(){
        return "resetpassword";
    }

    @GetMapping("/property")
    public String property(){
        return "property";
    }

    @GetMapping("/propertyinner")
    public String propertyinner(){
        return "propertyinner";
    }

    @GetMapping("/mailreportcard")
    public String mailreportcard(){
        return "mailreportcard";
    }

    @GetMapping("/icons")
    public String icons(){
        return "fontAwesomeIcons";
    }

    @GetMapping("/privacypolicy")
    public String privacypolicy(){
        return "privacyPolicy";
    }

    @GetMapping("/terms")
    public String terms(){
        return "terms";
    }

    @GetMapping("/transferstats")
    public String transferstats(){
        return "leadstatstransfered";
    }

   @GetMapping("/myoperator")
public  String myoperator(){
        return "myoperator";
}
    @GetMapping("/logtaskreport")
    public  String logtaskreport(){
        return "LogTaskReport";
    }



}
