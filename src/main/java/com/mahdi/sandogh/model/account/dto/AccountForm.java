package com.mahdi.sandogh.model.account.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AccountForm {

    private Integer fundid;  // ای دی صندوق

    @NotNull
    private String firstName;
    private String accountNumber;

    @NotNull
    private String lastName;
    private String fatherName;
    private String nationalCode;
    private Integer countLoan;
    private String mobileNumber;
    private String city;
    private String adderss;

    public AccountForm() {
    }
}
