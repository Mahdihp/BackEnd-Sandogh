package com.mahdi.sandogh.model.installmentloan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListInstallmentLoanDto extends BaseDto {

    private List<InstallmentLoanDto> data;

    public ListInstallmentLoanDto() {
    }

    public ListInstallmentLoanDto(Integer status, String message) {
        super(status, message);
    }
}
