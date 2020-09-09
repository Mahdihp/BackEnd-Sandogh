package com.mahdi.sandogh.model.loan.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class LoanForm {

    private Integer loanId;
    private Integer countLoan; // تعداد وام
    private Long sumLoan; // مجموع تعداد وام
    private Long currentLoanAmount; // مبلغ وام جاری
    private LocalDateTime dateCurrentLoan; // تاریخ شروع وام جاری
    private Integer countInstallments; // تعداد اقساط
    private Integer amountPerInstallment; // مبلغ هر قسط
    private LocalDateTime dateFinishInstallment; // تاریخ پایان قسط یا تاریخ آخرین قسط
    private Long accountId;
    private Integer fundId;
    private String description;
    private String createBy;
    public LoanForm() {
    }
}
