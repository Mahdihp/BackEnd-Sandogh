package com.mahdi.sandogh.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO extends BaseDTO {

    private String accountId;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nationalCode;
    private String mobileNumber;
    private String city;
    private String adderss;
    private Long creationDate;


    public AccountDTO() {
    }
}
