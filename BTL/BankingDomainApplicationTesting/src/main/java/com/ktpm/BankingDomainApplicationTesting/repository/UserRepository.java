package com.ktpm.BankingDomainApplicationTesting.repository;

import com.ktpm.BankingDomainApplicationTesting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserName(String userName);
    User findUserByNumberPhone(String numberPhone);
}
