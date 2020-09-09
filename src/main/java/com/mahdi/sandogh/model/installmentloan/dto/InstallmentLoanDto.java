package com.mahdi.sandogh.model.installmentloan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentLoanDto {

    private Long installmentLoanId;
    private Integer amountInstallment; // مبلغ قسط
    private Integer numberLoan; // شماره قسط
    private String description;
    private String createBy;
    private Long accountId;
    private Integer loanId;
    private Long creationDate; // تاریخ


    public InstallmentLoanDto() {
    }

}
