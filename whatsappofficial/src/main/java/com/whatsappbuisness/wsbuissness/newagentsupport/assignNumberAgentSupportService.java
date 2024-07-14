package com.whatsappbuisness.wsbuissness.newagentsupport;

import java.util.List;

import org.springframework.data.mongodb.core.query.Update;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

public interface assignNumberAgentSupportService {

	void sendAgentInfo(AgentSupportRequest request);
	void insertMessageAgentAccount(MessageDao messageDao);
	void updateMessageConversion(MessageDao messageDao, Update update);
	agentSupportResponse getassignedNumberInfo(AgentSupportRequest supportRequest);


}
