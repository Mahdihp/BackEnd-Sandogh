package com.mahdi.sandogh.model.loan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDto extends BaseDto {

    private String loanId;
    private Integer countLoan; // تعداد وام
    private Long sumLoan; // مجموع تعداد وام
    private Long currentLoanAmount; // مبلغ وام جاری
    private Long dateCurrentLoan; // تاریخ شروع وام جاری
    private Integer countInstallments; // تعداد اقساط
    private Long amountPerInstallment; // مبلغ هر قسط
    private Long dateFinishInstallment; // تاریخ پایان قسط یا تاریخ آخرین قسط
    private String accountId;

    public LoanDto() {
    }

    public LoanDto(Integer status, String message) {
        super(status, message);
    }
}
