package com.ktpm.BankingDomainApplicationTesting.controller;

import com.ktpm.BankingDomainApplicationTesting.entity.FormMoney;
import com.ktpm.BankingDomainApplicationTesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/money")
@PreAuthorize("isAuthenticated()")
public class MoneyController {
    @Autowired
    private final UserService userService;

    public MoneyController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("")
    String Tranfers(@RequestBody FormMoney formMoney)  {
        return userService.tranferMoney(formMoney.getSdtNguoiNhan(), formMoney.getSoTien(), "BANK_CKICKEN");
    }
}
