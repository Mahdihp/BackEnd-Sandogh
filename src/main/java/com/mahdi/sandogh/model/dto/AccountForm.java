package com.mahdi.sandogh.model.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountForm {

    private String accountId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String lastName;

    @NotBlank
    private String fatherName;

    @NotBlank
    private String nationalCode;

    @NotBlank
    private String mobileNumber;

    private String city;
    private String adderss;

    public AccountForm() {
    }
}
