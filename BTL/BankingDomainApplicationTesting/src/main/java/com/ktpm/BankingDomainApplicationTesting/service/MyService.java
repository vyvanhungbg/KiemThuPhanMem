package com.ktpm.BankingDomainApplicationTesting.service;

import com.ktpm.BankingDomainApplicationTesting.entity.CustomUserDetails;
import com.ktpm.BankingDomainApplicationTesting.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public User getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return  ((CustomUserDetails)principal).getUser();
        } else {
            return null;
        }
    }
}
