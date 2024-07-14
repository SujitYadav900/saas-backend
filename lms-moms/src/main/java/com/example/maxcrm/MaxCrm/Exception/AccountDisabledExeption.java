package com.example.maxcrm.MaxCrm.Exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AccountDisabledExeption extends UsernameNotFoundException {
    public AccountDisabledExeption(String str)
    {
        super(str);
    }
}
