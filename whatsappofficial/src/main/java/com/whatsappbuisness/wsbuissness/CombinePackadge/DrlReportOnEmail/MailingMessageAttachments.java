package com.whatsappbuisness.wsbuissness.CombinePackadge.DrlReportOnEmail;

public class MailingMessageAttachments {
	
	private String name;
    private String attachmentData;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttachmentData() {
		return attachmentData;
	}
	public void setAttachmentData(String attachmentData) {
		this.attachmentData = attachmentData;
	}
	@Override
	public String toString() {
		return "MailingMessageAttachments [name=" + name + ", attachmentData=" + attachmentData + "]";
	}
    
    

}
