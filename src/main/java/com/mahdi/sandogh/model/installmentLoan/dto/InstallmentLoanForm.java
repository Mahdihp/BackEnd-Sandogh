package com.mahdi.sandogh.model.installmentLoan.dto;

import lombok.Data;


@Data
public class InstallmentLoanForm {

    private String installmentLoanId;
    private Long amountInstallment; // مبلغ قسط
    private Integer numberLoan; // شماره قسط
    private String accountId;

    public InstallmentLoanForm() {
    }
}
