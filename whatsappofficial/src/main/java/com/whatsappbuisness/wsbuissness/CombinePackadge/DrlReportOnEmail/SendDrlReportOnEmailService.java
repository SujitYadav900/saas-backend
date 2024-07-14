package com.whatsappbuisness.wsbuissness.CombinePackadge.DrlReportOnEmail;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

public interface SendDrlReportOnEmailService {

	ResponseEntity<?> sendDrlReportOnEmail() throws IOException;

}
