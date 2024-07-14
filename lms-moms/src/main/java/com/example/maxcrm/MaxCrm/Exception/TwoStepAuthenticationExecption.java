package com.example.maxcrm.MaxCrm.Exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TwoStepAuthenticationExecption extends UsernameNotFoundException {
    public TwoStepAuthenticationExecption(String str)
    {
        super(str);
    }
}
