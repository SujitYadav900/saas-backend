package com.whatsappbuisness.wsbuissness.CombinePackadge.DrlReportOnEmail;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drlreportonemail")
public class DrlReportOnEmailController {
	
	private static final Logger logger = LoggerFactory.getLogger(DrlReportOnEmailController.class);
	
	@Autowired
	SendDrlReportOnEmailService drlReportOnEmailService;
	
	@GetMapping("/")
	public ResponseEntity<?> sendDrlReportOnEmail() throws IOException {
		
		return drlReportOnEmailService.sendDrlReportOnEmail();
		
	}

}
