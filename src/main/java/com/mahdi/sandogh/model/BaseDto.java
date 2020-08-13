package com.mahdi.sandogh.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDto {

    private Integer status;
    private String message;
    private String accountNumber;
    private Boolean active;

    public BaseDto() {
    }

    public BaseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseDto(Integer status, String message, String accountNumber) {
        this.status = status;
        this.message = message;
        this.accountNumber = accountNumber;
    }

    public BaseDto(Integer status, String message, String accountNumber, Boolean active) {
        this.status = status;
        this.message = message;
        this.accountNumber = accountNumber;
        this.active = active;
    }
}
