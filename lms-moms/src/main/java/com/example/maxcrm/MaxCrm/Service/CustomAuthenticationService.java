package com.example.maxcrm.MaxCrm.Service;

import org.springframework.security.core.Authentication;

public interface CustomAuthenticationService {
    Authentication authenticateUser(Authentication authentication);
}
