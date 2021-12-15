package com.ktpm.BankingDomainApplicationTesting.repository;

import com.ktpm.BankingDomainApplicationTesting.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
