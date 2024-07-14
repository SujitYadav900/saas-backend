package com.whatsappbuisness.wsbuissness.newagentsupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name="tbl_agentcustnumbermapping")
public class AgentCustNumberMapping{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long agentAccountId;
	private String custMobileNumber;
	private boolean assignAgent;
	private long adminAccountId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAgentAccountId() {
		return agentAccountId;
	}
	public void setAgentAccountId(long agentAccountId) {
		this.agentAccountId = agentAccountId;
	}
	public String getCustMobileNumber() {
		return custMobileNumber;
	}
	public void setCustMobileNumber(String custMobileNumber) {
		this.custMobileNumber = custMobileNumber;
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
