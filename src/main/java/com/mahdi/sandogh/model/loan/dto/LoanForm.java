package com.mahdi.sandogh.model.loan.dto;

import lombok.Data;

@Data
public class LoanForm {

    private Long loanId;
    private Integer countLoan; // تعداد وام
    private Long sumLoan; // مجموع تعداد وام
    private Long currentLoanAmount; // مبلغ وام جاری
    private Long dateCurrentLoan; // تاریخ شروع وام جاری
    private Integer countInstallments; // تعداد اقساط
    private Long amountPerInstallment; // مبلغ هر قسط
    private Long dateFinishInstallment; // تاریخ پایان قسط یا تاریخ آخرین قسط
    private Long accountId;

    public LoanForm() {
    }
}
