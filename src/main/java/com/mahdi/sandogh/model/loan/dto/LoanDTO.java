package com.mahdi.sandogh.model.loan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDTO extends BaseDTO {

    private String loanId;
    private Integer countLoan; // تعداد وام
    private Long sumLoan; // مجموع تعداد وام
    private Long currentLoanAmount; // مبلغ وام جاری
    private Long dateCurrentLoan; // تاریخ شروع وام جاری
    private Integer countInstallments; // تعداد اقساط
    private Long amountPerInstallment; // مبلغ هر قسط
    private Long dateFinishInstallment; // تاریخ پایان قسط یا تاریخ آخرین قسط
    private String accountId;

    public LoanDTO() {
    }

    public LoanDTO(Integer status, String message) {
        super(status, message);
    }
}
