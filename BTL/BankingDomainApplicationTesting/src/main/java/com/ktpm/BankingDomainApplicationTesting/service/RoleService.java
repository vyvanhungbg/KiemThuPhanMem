package com.ktpm.BankingDomainApplicationTesting.service;

import com.ktpm.BankingDomainApplicationTesting.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
