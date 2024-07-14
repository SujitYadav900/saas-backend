package com.example.maxcrm.MaxCrm.Dao;


import com.google.gson.Gson;

public class MailObjectDao {
    private String version;
    private String userName;
    private String password;
    private String includeFooter;

    @Override
    public String toString() {
        return "MailObjectDao{" +
                "version='" + version + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", includeFooter='" + includeFooter + '\'' +
                ", message=" + message +
                ", template=" + template +
                '}';
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIncludeFooter() {
        return includeFooter;
    }

    public void setIncludeFooter(String includeFooter) {
        this.includeFooter = includeFooter;
    }

    public MailObjectMessage getMessage() {
        return message;
    }

    public void setMessage(MailObjectMessage message) {
        this.message = message;
    }

    public MailObjectTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MailObjectTemplate template) {
        this.template = template;
    }

    private MailObjectMessage message;
    private MailObjectTemplate template;
    public String convertToJson()
    {
        return new Gson().toJson(this);
    }



}
