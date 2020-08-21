package com.mahdi.sandogh.model.installmentloan.dto;

import lombok.Data;


@Data
public class InstallmentLoanForm {

    private Long loanId;  // شماره وام
    private Long installmentLoanId;
    private Long amountInstallment; // مبلغ قسط
    private Integer numberLoan; // شماره قسط
    private Long accountId;

    public InstallmentLoanForm() {
    }
}
