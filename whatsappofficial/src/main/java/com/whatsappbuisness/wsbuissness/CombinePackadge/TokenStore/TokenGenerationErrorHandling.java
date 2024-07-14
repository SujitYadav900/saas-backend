package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class TokenGenerationErrorHandling extends Exception {

    public TokenGenerationErrorHandling(String msg) {
        super(msg);
    }
}
