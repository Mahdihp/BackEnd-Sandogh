package com.mahdi.sandogh.model.installmentloan.dto;

import lombok.Data;


@Data
public class InstallmentLoanForm {

    private Integer loanId;  // شماره وام
    private Long installmentLoanId;
    private String description;
    private String createBy;
    private Integer amountInstallment; // مبلغ قسط
    private Integer numberLoan; // شماره قسط
    private Long accountId;

    public InstallmentLoanForm() {
    }
}
