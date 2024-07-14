package com.whatsappbuisness.wsbuissness.newagentsupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_agentindex")
public class AgentIndex {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private long adminAccountId;
	private int currentIndex;
	
	public AgentIndex() {
		super();
	}
	
	public AgentIndex(Long id, int currentIndex) {
		super();
		this.id = id;
		this.currentIndex = currentIndex;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public long getAdminAccountId() {
		return adminAccountId;
	}

	public void setAdminAccountId(long adminAccountId) {
		this.adminAccountId = adminAccountId;
	}
	
	

}
