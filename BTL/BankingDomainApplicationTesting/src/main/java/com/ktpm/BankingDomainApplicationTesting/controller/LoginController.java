package com.ktpm.BankingDomainApplicationTesting.controller;


import com.ktpm.BankingDomainApplicationTesting.JWT.JwtTokenProvider;
import com.ktpm.BankingDomainApplicationTesting.entity.CustomUserDetails;
import com.ktpm.BankingDomainApplicationTesting.entity.LoginEntity;
import com.ktpm.BankingDomainApplicationTesting.entity.ResponseObject;
import com.ktpm.BankingDomainApplicationTesting.exception.ErrorAccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;


@RestController
@RequestMapping("/v1/tranfer")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @GetMapping("")
    String  getAllBill(){
        return "Heloo fake";
    }

    @PostMapping("")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginEntity loginRequest) throws ErrorAccessDeniedException {

        System.out.println(loginRequest.getUserName());
        System.out.println(loginRequest.getUserPassword());
        // Xác thực từ username và password.
       try {
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUserName(),
                           loginRequest.getUserPassword()
                   )

           );
           SecurityContextHolder.getContext().setAuthentication(authentication);
           String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());

           return ResponseEntity.status(HttpStatus.OK).body(jwt);

       }catch (BadCredentialsException e){
           //e.printStackTrace();
          return ResponseEntity.status(HttpStatus.OK).body("Incorrect account or password");
          // return ResponseEntity.status(HttpStatus.OK).body(null);
       }


    }



}
