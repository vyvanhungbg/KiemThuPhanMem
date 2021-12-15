package com.ktpm.BankingDomainApplicationTesting.exception;

public class ErrorAccessDeniedException extends Exception{
    private static final long serialVersionUID = 1L;

    public ErrorAccessDeniedException(String message) {
        super(message);
    }
}
