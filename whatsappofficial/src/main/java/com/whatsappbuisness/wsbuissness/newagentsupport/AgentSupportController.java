package com.whatsappbuisness.wsbuissness.newagentsupport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentSupportController {

	@Autowired
	assignNumberAgentSupportService agentSupportService;


	@PostMapping("/assignagentsupport")
	public void assignNumberAgentSupport(@RequestBody AgentSupportRequest request) {
		if(request!=null ){
			agentSupportService.sendAgentInfo(request);

		}else {
			System.out.println("There do not have message");
		}
	}


	@PostMapping("/assignednumber")
	public agentSupportResponse assignedNumberInfo(@RequestBody AgentSupportRequest supportRequest) {
		if(supportRequest.getMobileNumber()!=null) {
			return agentSupportService.getassignedNumberInfo(supportRequest);
		}
		return null;

	}

}
