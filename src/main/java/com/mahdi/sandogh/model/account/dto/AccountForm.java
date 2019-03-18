package com.mahdi.sandogh.model.account.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountForm {

    private String accountId;

    @NotNull
    private String firstName;

    private String accountNumber;

    @NotNull
    private String lastName;

    @NotNull
    private String fatherName;

    @NotNull
    private String nationalCode;

    private Integer countLoan;

    @NotNull
    private String mobileNumber;

    private String city;
    private String adderss;

    public AccountForm() {
    }
}
