package com.whatsappbuisness.wsbuissness.CombinePackadge.ConvertUnferified;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;

public class UnverifiedMessages {
	
	private String content;
	private String messageId;
	private String unqId;
	private String contentType;
	private String senderName;
	private String messageType;
	private String phone;
	private String caption;
	private String time;
	private String groupName;
	private boolean groupMsg;
	private String fileName;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getUnqId() {
		return unqId;
	}
	public void setUnqId(String unqId) {
		this.unqId = unqId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public boolean isGroupMsg() {
		return groupMsg;
	}
	public void setGroupMsg(boolean groupMsg) {
		this.groupMsg = groupMsg;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		return "UnverifiedMessages [content=" + content + ", messageId=" + messageId + ", unqId=" + unqId
				+ ", contentType=" + contentType + ", senderName=" + senderName + ", messageType=" + messageType
				+ ", phone=" + phone + ", caption=" + caption + ", time=" + time + ", groupName=" + groupName
				+ ", groupMsg=" + groupMsg + ", fileName=" + fileName + "]";
	}



}
