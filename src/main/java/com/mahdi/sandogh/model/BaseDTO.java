package com.mahdi.sandogh.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    private Integer status;
    private String message;
    private String accountId;
    private Boolean active;

    public BaseDTO() {
    }

    public BaseDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseDTO(Integer status, String message, String accountId) {
        this.status = status;
        this.message = message;
        this.accountId = accountId;
    }

    public BaseDTO(Integer status, String message, String accountId, Boolean active) {
        this.status = status;
        this.message = message;
        this.accountId = accountId;
        this.active = active;
    }
}
