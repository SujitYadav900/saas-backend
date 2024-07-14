package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription;


import com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao.DeductionType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Gateway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Tbl_Subscription" )
public class SubscriptionDao implements Serializable {

	@Id
	private String transactionId;
	private double freeTemplatesMessage;
	private double freeSessionMessage;
	private boolean mediaStoretoDriveStatus;
	private boolean enablePriorityQueue;
	private double freeTemplates;
	private double afterConsumptionTemplateDed;
	private double afterConsumptionSessionDed;
	private double templateCreationDed;
	private double sentTemplate;
	private double sentSession;
	private double templateCreated;
	private String expiry;
	private boolean isActive;
	private double subscriptionAmt;
	private int maximumPerCampaign;
	private String waVerfiedNumber;
	private Gateway gateway;
	private String karixToken;
	private String cloudAPIToken;
	private String cloudAPIPhoneNumberID;
	private String cloudAPIWhatsappID;
	private String emailTo;
	private String emailCc;
	private boolean webhookEnable;
	private String webhookURL;
	//test varrible for testing
	private boolean outWebhookEnable;
	private String outWebhookUrl;
	private String current_limit;
	private String phoneNumberEvent;
	private String createBy;
	private String updateBy;
	private String createAt;
	private String updateAt;
	private long updateAtFilter;
	private String cloudAPIAppID;
	private int expiryDateFilter;
	private DeductionType deductionType;
	private String userName;
	private String baseUrl;
	private String password;
	private long accountId;
	private boolean test;
	private boolean autoRenew;
	private boolean enableConvertUnverified;
	private String convertUnverifiedUrl;
	private boolean dlrReportOnEmail;
	private String clientEmail;
	private boolean enableGoogleSheet;
	private String spreadsheetId;
	@Column(name = "`range`") 
	private String range;
	private boolean showMessageContent;
	private boolean agentAssignManually;
	private boolean enableAgentSupport;

	public boolean isShowMessageContent() {
		return showMessageContent;
	}

	public void setShowMessageContent(boolean showMessageContent) {
		this.showMessageContent = showMessageContent;
	}


	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getFreeTemplatesMessage() {
		return freeTemplatesMessage;
	}

	public void setFreeTemplatesMessage(double freeTemplatesMessage) {
		this.freeTemplatesMessage = freeTemplatesMessage;
	}

	public double getFreeSessionMessage() {
		return freeSessionMessage;
	}

	public void setFreeSessionMessage(double freeSessionMessage) {
		this.freeSessionMessage = freeSessionMessage;
	}

	public double getAfterConsumptionTemplateDed() {
		return afterConsumptionTemplateDed;
	}

	public void setAfterConsumptionTemplateDed(double afterConsumptionTemplateDed) {
		this.afterConsumptionTemplateDed = afterConsumptionTemplateDed;
	}

	public double getAfterConsumptionSessionDed() {
		return afterConsumptionSessionDed;
	}

	public void setAfterConsumptionSessionDed(double afterConsumptionSessionDed) {
		this.afterConsumptionSessionDed = afterConsumptionSessionDed;
	}

	public double getTemplateCreationDed() {
		return templateCreationDed;
	}

	public void setTemplateCreationDed(double templateCreationDed) {
		this.templateCreationDed = templateCreationDed;
	}

	public double getSentTemplate() {
		return sentTemplate;
	}

	public void setSentTemplate(double sentTemplate) {
		this.sentTemplate = sentTemplate;
	}

	public double getSentSession() {
		return sentSession;
	}

	public void setSentSession(double sentSession) {
		this.sentSession = sentSession;
	}

	public double getTemplateCreated() {
		return templateCreated;
	}

	public void setTemplateCreated(double templateCreated) {
		this.templateCreated = templateCreated;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public double getSubscriptionAmt() {
		return subscriptionAmt;
	}

	public void setSubscriptionAmt(double subscriptionAmt) {
		this.subscriptionAmt = subscriptionAmt;
	}

	public int getMaximumPerCampaign() {
		return maximumPerCampaign;
	}

	public void setMaximumPerCampaign(int maximumPerCampaign) {
		this.maximumPerCampaign = maximumPerCampaign;
	}



	public boolean isEnablePriorityQueue() {
		return enablePriorityQueue;
	}

	public void setEnablePriorityQueue(boolean enablePriorityQueue) {
		this.enablePriorityQueue = enablePriorityQueue;
	}

	public boolean isMediaStoretoDriveStatus() {
		return mediaStoretoDriveStatus;
	}

	public void setMediaStoretoDriveStatus(boolean mediaStoretoDriveStatus) {
		this.mediaStoretoDriveStatus = mediaStoretoDriveStatus;
	}

	public double getFreeTemplates() {
		return freeTemplates;
	}

	public void setFreeTemplates(double freeTemplates) {
		this.freeTemplates = freeTemplates;
	}


	//

	//getetr setter for testing
	public boolean isOutWebhookEnable() {
		return outWebhookEnable;
	}

	public void setOutWebhookEnable(boolean outWebhookEnable) {
		this.outWebhookEnable = outWebhookEnable;
	}

	public String getOutWebhookUrl() {
		return outWebhookUrl;
	}

	public void setOutWebhookUrl(String outWebhookUrl) {
		this.outWebhookUrl = outWebhookUrl;
	}
	//

	public String getCurrent_limit() {
		return current_limit;
	}

	public void setCurrent_limit(String current_limit) {
		this.current_limit = current_limit;
	}

	public String getPhoneNumberEvent() {
		return phoneNumberEvent;
	}

	public void setPhoneNumberEvent(String phoneNumberEvent) {
		this.phoneNumberEvent = phoneNumberEvent;
	}


	public String getWebhookURL() {
		return webhookURL;
	}

	public void setWebhookURL(String webhookURL) {
		this.webhookURL = webhookURL;
	}

	public boolean isWebhookEnable() {
		return webhookEnable;
	}

	public void setWebhookEnable(boolean webhookEnable) {
		this.webhookEnable = webhookEnable;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public String getCloudAPIToken() {
		return cloudAPIToken;
	}

	public void setCloudAPIToken(String cloudAPIToken) {
		this.cloudAPIToken = cloudAPIToken;
	}

	public String getCloudAPIPhoneNumberID() {
		return cloudAPIPhoneNumberID;
	}

	public void setCloudAPIPhoneNumberID(String cloudAPIPhoneNumberID) {
		this.cloudAPIPhoneNumberID = cloudAPIPhoneNumberID;
	}

	public String getCloudAPIWhatsappID() {
		return cloudAPIWhatsappID;
	}

	public void setCloudAPIWhatsappID(String cloudAPIWhatsappID) {
		this.cloudAPIWhatsappID = cloudAPIWhatsappID;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

	public String getWaVerfiedNumber() {
		return waVerfiedNumber;
	}

	public void setWaVerfiedNumber(String waVerfiedNumber) {
		this.waVerfiedNumber = waVerfiedNumber;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}



	public long getUpdateAtFilter() {
		return updateAtFilter;
	}

	public void setUpdateAtFilter(long updateAtFilter) {
		this.updateAtFilter = updateAtFilter;
	}

	public String getCloudAPIAppID() {
		return cloudAPIAppID;
	}

	public void setCloudAPIAppID(String cloudAPIAppID) {
		this.cloudAPIAppID = cloudAPIAppID;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}


	public int getExpiryDateFilter() {
		return expiryDateFilter;
	}

	public void setExpiryDateFilter(int expiryDateFilter) {
		this.expiryDateFilter = expiryDateFilter;
	}

	public String getKarixToken() {
		return karixToken;
	}

	public void setKarixToken(String karixToken) {
		this.karixToken = karixToken;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}



	public DeductionType getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(DeductionType deductionType) {
		this.deductionType = deductionType;
	}

	public SubscriptionDao() {
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

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}


	public boolean isAutoRenew() {
		return autoRenew;
	}

	public void setAutoRenew(boolean autoRenew) {
		this.autoRenew = autoRenew;
	}

	public boolean isEnableConvertUnverified() {
		return enableConvertUnverified;
	}

	public void setEnableConvertUnverified(boolean enableConvertUnverified) {
		this.enableConvertUnverified = enableConvertUnverified;
	}
	public String getConvertUnverifiedUrl() {
		return convertUnverifiedUrl;
	}

	public void setConvertUnverifiedUrl(String convertUnverifiedUrl) {
		this.convertUnverifiedUrl = convertUnverifiedUrl;
	}
	public boolean isDlrReportOnEmail() {
		return dlrReportOnEmail;
	}

	public void setDlrReportOnEmail(boolean dlrReportOnEmail) {
		this.dlrReportOnEmail = dlrReportOnEmail;
	}
	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
	public boolean isEnableGoogleSheet() {
		return enableGoogleSheet;
	}

	public void setEnableGoogleSheet(boolean enableGoogleSheet) {
		this.enableGoogleSheet = enableGoogleSheet;
	}

	public String getSpreadsheetId() {
		return spreadsheetId;
	}

	public void setSpreadsheetId(String spreadsheetId) {
		this.spreadsheetId = spreadsheetId;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public boolean isAgentAssignManually() {
		return agentAssignManually;
	}

	public void setAgentAssignManually(boolean agentAssignManually) {
		this.agentAssignManually = agentAssignManually;
	}

	public boolean isEnableAgentSupport() {
		return enableAgentSupport;
	}

	public void setEnableAgentSupport(boolean enableAgentSupport) {
		this.enableAgentSupport = enableAgentSupport;
	}

	@Override
	public String toString() {
		return "SubscriptionDao [transactionId=" + transactionId + ", freeTemplatesMessage=" + freeTemplatesMessage
				+ ", freeSessionMessage=" + freeSessionMessage + ", mediaStoretoDriveStatus=" + mediaStoretoDriveStatus
				+ ", enablePriorityQueue=" + enablePriorityQueue + ", freeTemplates=" + freeTemplates
				+ ", afterConsumptionTemplateDed=" + afterConsumptionTemplateDed + ", afterConsumptionSessionDed="
				+ afterConsumptionSessionDed + ", templateCreationDed=" + templateCreationDed + ", sentTemplate="
				+ sentTemplate + ", sentSession=" + sentSession + ", templateCreated=" + templateCreated + ", expiry="
				+ expiry + ", isActive=" + isActive + ", subscriptionAmt=" + subscriptionAmt + ", maximumPerCampaign="
				+ maximumPerCampaign + ", waVerfiedNumber=" + waVerfiedNumber + ", gateway=" + gateway + ", karixToken="
				+ karixToken + ", cloudAPIToken=" + cloudAPIToken + ", cloudAPIPhoneNumberID=" + cloudAPIPhoneNumberID
				+ ", cloudAPIWhatsappID=" + cloudAPIWhatsappID + ", emailTo=" + emailTo + ", emailCc=" + emailCc
				+ ", webhookEnable=" + webhookEnable + ", webhookURL=" + webhookURL + ", outWebhookEnable="
				+ outWebhookEnable + ", outWebhookUrl=" + outWebhookUrl + ", current_limit=" + current_limit
				+ ", phoneNumberEvent=" + phoneNumberEvent + ", createBy=" + createBy + ", updateBy=" + updateBy
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + ", updateAtFilter=" + updateAtFilter
				+ ", cloudAPIAppID=" + cloudAPIAppID + ", expiryDateFilter=" + expiryDateFilter + ", deductionType="
				+ deductionType + ", userName=" + userName + ", baseUrl=" + baseUrl + ", password=" + password
				+ ", accountId=" + accountId + ", test=" + test + ", autoRenew=" + autoRenew
				+ ", enableConvertUnverified=" + enableConvertUnverified + ", convertUnverifiedUrl="
				+ convertUnverifiedUrl + ", dlrReportOnEmail=" + dlrReportOnEmail + ", clientEmail=" + clientEmail
				+ ", enableGoogleSheet=" + enableGoogleSheet + ", spreadsheetId=" + spreadsheetId + ", range=" + range
				+ ", showMessageContent=" + showMessageContent + ", agentAssignManually=" + agentAssignManually
				+ ", enableAgentSupport=" + enableAgentSupport + "]";
	}
	
	
	

	
}
