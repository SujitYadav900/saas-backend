package com.example.maxcrm.MaxCrm.Controller.LeadExteranl;

import com.example.maxcrm.MaxCrm.Dao.ProductMasterDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.RegistrationImpl;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.countries.MBOPSCountryDao;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalAPICallController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/getcentresbypincode")
    public CentreDao[] getCentresByPincode(@RequestParam String pincode) throws IOException {

        String url = UtilityClass.propertyService.findProperty("Lead","get_centres_url");
        url = url+"&pincode="+pincode;
        logger.info("Centres URL : {} ",url);
        //OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        String responseStr=response.body().string();
        logger.info("responseStr  : {}",responseStr);
        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        CentreDao[] centres = gson.fromJson(responseStr, CentreDao[].class);

        //ExternalAPICallController.ProductObj obj=new ExternalAPICallController.ProductObj().convertToJson(responseStr);

        return centres;

    }

    class CentreDao{
        private int id;
        private String name;
        private String productcode;
        private String description;
        private byte status;
        private double price;

        private String center;
        private String center_contact_person;
        private String center_city;
        private String center_address;
        private String center_pin;
        private String center_mobile;
        private String center_bd;
        private String center_bd_mobile;
        private byte sendNotifications;

        private String center_state;
        private String center_country;
        private String center_email;

        private String centreTimingSun;
        private String centreTimingMon;
        private String centreTimingTue;
        private String centreTimingWed;
        private String centreTimingThu;
        private String centreTimingFri;
        private String centreTimingSat;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProductcode() {
            return productcode;
        }

        public void setProductcode(String productcode) {
            this.productcode = productcode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public byte getStatus() {
            return status;
        }

        public void setStatus(byte status) {
            this.status = status;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCenter() {
            return center;
        }

        public void setCenter(String center) {
            this.center = center;
        }

        public String getCenter_contact_person() {
            return center_contact_person;
        }

        public void setCenter_contact_person(String center_contact_person) {
            this.center_contact_person = center_contact_person;
        }

        public String getCenter_city() {
            return center_city;
        }

        public void setCenter_city(String center_city) {
            this.center_city = center_city;
        }

        public String getCenter_address() {
            return center_address;
        }

        public void setCenter_address(String center_address) {
            this.center_address = center_address;
        }

        public String getCenter_pin() {
            return center_pin;
        }

        public void setCenter_pin(String center_pin) {
            this.center_pin = center_pin;
        }

        public String getCenter_mobile() {
            return center_mobile;
        }

        public void setCenter_mobile(String center_mobile) {
            this.center_mobile = center_mobile;
        }

        public String getCenter_bd() {
            return center_bd;
        }

        public void setCenter_bd(String center_bd) {
            this.center_bd = center_bd;
        }

        public String getCenter_bd_mobile() {
            return center_bd_mobile;
        }

        public void setCenter_bd_mobile(String center_bd_mobile) {
            this.center_bd_mobile = center_bd_mobile;
        }

        public byte getSendNotifications() {
            return sendNotifications;
        }

        public void setSendNotifications(byte sendNotifications) {
            this.sendNotifications = sendNotifications;
        }

        public String getCenter_state() {
            return center_state;
        }

        public void setCenter_state(String center_state) {
            this.center_state = center_state;
        }

        public String getCenter_country() {
            return center_country;
        }

        public void setCenter_country(String center_country) {
            this.center_country = center_country;
        }

        public String getCenter_email() {
            return center_email;
        }

        public void setCenter_email(String center_email) {
            this.center_email = center_email;
        }

        public String getCentreTimingSun() {
            return centreTimingSun;
        }

        public void setCentreTimingSun(String centreTimingSun) {
            this.centreTimingSun = centreTimingSun;
        }

        public String getCentreTimingMon() {
            return centreTimingMon;
        }

        public void setCentreTimingMon(String centreTimingMon) {
            this.centreTimingMon = centreTimingMon;
        }

        public String getCentreTimingTue() {
            return centreTimingTue;
        }

        public void setCentreTimingTue(String centreTimingTue) {
            this.centreTimingTue = centreTimingTue;
        }

        public String getCentreTimingWed() {
            return centreTimingWed;
        }

        public void setCentreTimingWed(String centreTimingWed) {
            this.centreTimingWed = centreTimingWed;
        }

        public String getCentreTimingThu() {
            return centreTimingThu;
        }

        public void setCentreTimingThu(String centreTimingThu) {
            this.centreTimingThu = centreTimingThu;
        }

        public String getCentreTimingFri() {
            return centreTimingFri;
        }

        public void setCentreTimingFri(String centreTimingFri) {
            this.centreTimingFri = centreTimingFri;
        }

        public String getCentreTimingSat() {
            return centreTimingSat;
        }

        public void setCentreTimingSat(String centreTimingSat) {
            this.centreTimingSat = centreTimingSat;
        }
    }
}
