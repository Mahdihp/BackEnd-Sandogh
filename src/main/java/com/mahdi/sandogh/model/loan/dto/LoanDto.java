package com.mahdi.sandogh.model.loan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDto  {

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
    private LocalDateTime createdAt ;

    public LoanDto() {
    }

}
