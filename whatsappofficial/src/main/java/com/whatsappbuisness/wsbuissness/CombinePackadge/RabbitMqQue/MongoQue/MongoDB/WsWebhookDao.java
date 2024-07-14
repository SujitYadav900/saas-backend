package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB;

import javax.validation.constraints.NotNull;

public class WsWebhookDao {

    public String getPhnNumber() {
        return phnNumber;
    }

    public void setPhnNumber(String phnNumber) {
        this.phnNumber = phnNumber;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }



    public String getAgentIdentifier() {
        return agentIdentifier;
    }

    public void setAgentIdentifier(String agentIdentifier) {
        this.agentIdentifier = agentIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String phnNumber;

    public WsWebhookDao(String phnNumber, String leadSource, String leadSourceInner, String agentIdentifier, String name, String email) {
        this.phnNumber = phnNumber;
        this.leadSource = leadSource;
        this.leadSourceInner = leadSourceInner;
        this.agentIdentifier = agentIdentifier;
        this.name = name;
        this.email = email;
    }

    private String leadSource;

    public String getLeadSourceInner() {
        return leadSourceInner;
    }

    public void setLeadSourceInner(String leadSourceInner) {
        this.leadSourceInner = leadSourceInner;
    }


    private String leadSourceInner;


    private String agentIdentifier;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;


    @Override
    public String toString() {
        return "WaOfficialDao{" +
                "phnNumber='" + phnNumber + '\'' +
                ", leadSource='" + leadSource + '\'' +
                ", leadSourceInner='" + leadSourceInner + '\'' +
                ", agentIdentifier='" + agentIdentifier + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
