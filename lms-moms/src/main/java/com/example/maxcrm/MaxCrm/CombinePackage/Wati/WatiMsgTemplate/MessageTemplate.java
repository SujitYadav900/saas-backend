package com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiMsgTemplate;

public class MessageTemplate {


    private String id;
    private String elementName;
    private String status;
    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MessageTemplate{" +
                "id='" + id + '\'' +
                ", elementName='" + elementName + '\'' +
                ", status='" + status + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
