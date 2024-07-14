package com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos;

import java.util.Arrays;
import java.util.List;

public class WatiReceiverDao {

    private String whatsappNumber;
    private List<CustomParamDao> customParams;

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public List<CustomParamDao> getCustomParams() {
        return customParams;
    }

    public void setCustomParams(List<CustomParamDao> customParams) {
        this.customParams = customParams;
    }

    @Override
    public String toString() {
        return "WatiReceiverDao{" +
                "whatsappNumber='" + whatsappNumber + '\'' +
                ", customParams=" + customParams +
                '}';
    }
}


//        {
//        "whatsappNumber": "919711335984",
//        "customParams": [
//        {
//        "name": "string",
//        "value": "string"
//        }
//        ]
//        }