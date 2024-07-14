package com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos;

import java.util.List;

public class WatiTemplateMsgRequestDao {

    private String template_name;
    private String broadcast_name;
    private List<WatiReceiverDao> receivers;

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getBroadcast_name() {
        return broadcast_name;
    }

    public void setBroadcast_name(String broadcast_name) {
        this.broadcast_name = broadcast_name;
    }

    public List<WatiReceiverDao> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<WatiReceiverDao> receivers) {
        this.receivers = receivers;
    }

    @Override
    public String toString() {
        return "WatiTemplateMsgRequestDao{" +
                "template_name='" + template_name + '\'' +
                ", broadcast_name='" + broadcast_name + '\'' +
                ", receivers=" + receivers +
                '}';
    }
}
//
//{
//        "template_name": "21_nov_webinar",
//        "broadcast_name": "string",
//        "receivers": [
//        {
//        "whatsappNumber": "919711335984",
//        "customParams": [
//        {
//        "name": "string",
//        "value": "string"
//        }
//        ]
//        },
//        {
//        "whatsappNumber": "918851706788",
//        "customParams": [
//        {
//        "name": "string",
//        "value": "string"
//        }
//        ]
//        }
//        ]
//        }