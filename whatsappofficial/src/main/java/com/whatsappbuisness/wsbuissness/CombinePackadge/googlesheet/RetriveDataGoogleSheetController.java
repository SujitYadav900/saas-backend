package com.whatsappbuisness.wsbuissness.CombinePackadge.googlesheet;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/googlesheet")
public class RetriveDataGoogleSheetController {
	
	@Autowired
	RetriveDataGoogleSheetService googleSheetService;
	
	@GetMapping("/getdata")
	public void getRetriveDataFromGoogleSheet() throws IOException,GeneralSecurityException{
		googleSheetService.getRetriveDataFromGoogleSheet();
	}

	
}
