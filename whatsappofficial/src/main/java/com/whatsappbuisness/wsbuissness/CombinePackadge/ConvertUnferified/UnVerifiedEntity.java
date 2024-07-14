package com.whatsappbuisness.wsbuissness.CombinePackadge.ConvertUnferified;

public class UnVerifiedEntity {

	private UnverifiedMessages messages;
	private long accountId;
	private long incId;
	private String accessToken;

	public UnverifiedMessages getMessages() {
		return messages;
	}

	public void setMessages(UnverifiedMessages messages) {
		this.messages = messages;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getIncId() {
		return incId;
	}

	public void setIncId(long incId) {
		this.incId = incId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "UnferifiedEntity [messages=" + messages + ", accountId=" + accountId + ", incId=" + incId
				+ ", accessToken=" + accessToken + "]";
	}





}
