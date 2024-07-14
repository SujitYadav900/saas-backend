package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixEmailService;

import com.google.gson.Gson;
import org.apache.commons.lang3.text.StrSubstitutor;
//import org.apache.commons.lang.text.StrSubstitutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EmailDao {
    @Override
    public String toString() {
        return "EmailDao{" +
                " userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", version='" + version + '\'' +
                ", includeFooter='" + includeFooter + '\'' +
                ", message=" + message +
                '}';
    }

    private static final String htmlTemplate=" <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en-GB\">\n" +
            "<head>\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
            "  <title>Prp Notification Service</title>\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
            "\n" +
            "  <style type=\"text/css\">\n" +
            "    a[x-apple-data-detectors] {color: inherit !important;}\n" +
            "  </style>\n" +
            "\n" +
            "</head>\n" +
            "<body style=\"margin: 0; padding: 0;\">\n" +
            "  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "    <tr>\n" +
            "      <td style=\"padding: 20px 0 30px 0;\">\n" +
            "\n" +
            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse; border: 1px solid #cccccc;\">\n" +
            "  <tr>\n" +
            "    <td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0;\">\n" +
            "     <a href=\"https://prpservices.in/\">\n" +
            "\n" +
            "      <img src=\"https://prpservices.in/img/content/93d33451-cbea-4358-8414-bf9363f80078.png\" alt=\"Creating Email Magic.\" width=\"300\" height=\"230\" style=\"display: block;width: 130px;\n" +
            "      height: 50px;\" />\n" +
            "\n" +
            "     </a> \n" +
            "    </td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;background-color: rgb(112 187 217 / 26%);\">\n" +
            "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n" +
            "        <tr>\n" +
            "          <td style=\"color: #153643; font-family: Arial, sans-serif;\">\n" +
            "            <h1  style=\"font-size: 24px; margin: 0;text-align: center;\">${dynamicHeaderContent} </h1>\n" +
            "          </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 20px 0 30px 0;\">\n" +
            "            <p style=\"margin: 0;text-align: center\">  ${dynamicBodyContent} \n" +
            "              \n" +
            "              \n" +
            "            \n" +
            "            \n" +
            "            </p>\n" +
            "          </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>\n" +
            "      \n" +
            "          </td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "    </td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px;    background-color: rgb(112, 187, 217);\">\n" +
            "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n" +
            "        <tr>\n" +
            "          <td style=\"color: #000000; font-family: Arial, sans-serif; font-size: 14px;\">\n" +
            "            <p style=\"margin: 0;text-align: center;\">© 2021, PRP Services Pvt. Ltd. | ISO 9001:2015 | All rights reserved. </p><br>\n" +
            "          \n" +
            "          </td>\n" +
            "          <td align=\"right\">\n" +
            "       \n" +
            "          </td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "    </td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "\n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </table>\n" +
            "</body>\n" +
            "</html>\n" +
            "\n";

    public  EmailDao(String body, String subject, String rec)
{

    Map<String, String> valuesMap = new HashMap<String, String>();
    valuesMap.put("dynamicBodyContent", body);
    valuesMap.put("dynamicHeaderContent", subject);
    StrSubstitutor sub = new StrSubstitutor(valuesMap);
    EmailInnerDao emailInnerDao=new EmailInnerDao();
    String htmlContent=sub.replace(htmlTemplate);
    emailInnerDao.setHtml(htmlContent);
    emailInnerDao.setSubject(subject);
    emailInnerDao.setRecipient(rec);
    emailInnerDao.setAttachments(new ArrayList<>());
    message=emailInnerDao;

}
public EmailDao(String body,String subject,String rec,MailingMessageAttachments ...mailingMessageAttachments)
{
    Map<String, String> valuesMap = new HashMap<String, String>();
    valuesMap.put("dynamicBodyContent", body);
    valuesMap.put("dynamicHeaderContent", subject);
    StrSubstitutor sub = new StrSubstitutor(valuesMap);
    EmailInnerDao emailInnerDao=new EmailInnerDao();
    String htmlContent=sub.replace(htmlTemplate);
    emailInnerDao.setHtml(htmlContent);
    emailInnerDao.setSubject(subject);
    emailInnerDao.setRecipient(rec);
    emailInnerDao.setAttachments(Arrays.asList(mailingMessageAttachments.clone()));
    message=emailInnerDao;
}
    public EmailDao() {

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

    public EmailInnerDao getMessage() {
        return message;
    }

    public void setMessage(EmailInnerDao message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIncludeFooter() {
        return includeFooter;
    }

    public void setIncludeFooter(String includeFooter) {
        this.includeFooter = includeFooter;
    }

    private String userName="kunalemail";
    private String password="Kunal@12";
    private String version;
    private String includeFooter;
    private EmailInnerDao message;
   public String toJson(EmailDao al)
    {
        return new Gson().toJson(al);
    }



}
