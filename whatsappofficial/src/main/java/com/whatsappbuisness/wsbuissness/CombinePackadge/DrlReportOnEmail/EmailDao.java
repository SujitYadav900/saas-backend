package com.whatsappbuisness.wsbuissness.CombinePackadge.DrlReportOnEmail;

import java.util.List;

public class EmailDao {

	private String subject;
    private String html;
    private String fromEmail="admin@bulksmsadmin.com";
    private String password="2005Kavya@";
    private String toEmail="dharamsingh70533@gmail.com";
    private String recipient;
    private List<String> attachments;
    
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public List<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	
	@Override
	public String toString() {
		return "EmailDao [subject=" + subject + ", html=" + html + ", fromEmail=" + fromEmail + ", password=" + password
				+ ", toEmail=" + toEmail + ", recipient=" + recipient + ", attachments=" + attachments + "]";
	}
	

}
