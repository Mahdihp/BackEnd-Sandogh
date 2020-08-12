package com.mahdi.sandogh.model.installmentloan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentLoanDto extends BaseDto {

    private String installmentLoanId;
    private Long amountInstallment; // مبلغ قسط
    private Integer numberLoan; // شماره قسط
    private String accountId;
    private Long creationDate; // تاریخ


    public InstallmentLoanDto() {
    }

    public InstallmentLoanDto(Integer status, String message) {
        super(status, message);
    }
}
