package com.whatsappbuisness.wsbuissness.newagentsupport;

public class AgentSupportRequest {
	
	
	private long agentAccountId;
	private String mobileNumber;
	private boolean assignAgent;
	private long adminAccountId;
	
	
	public long getAgentAccountId() {
		return agentAccountId;
	}
	public void setAgentAccountId(long agentAccountId) {
		this.agentAccountId = agentAccountId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public boolean isAssignAgent() {
		return assignAgent;
	}
	public void setAssignAgent(boolean assignAgent) {
		this.assignAgent = assignAgent;
	}
	public long getAdminAccountId() {
		return adminAccountId;
	}
	public void setAdminAccountId(long adminAccountId) {
		this.adminAccountId = adminAccountId;
	}
	
}
