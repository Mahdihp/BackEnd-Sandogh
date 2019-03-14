package com.mahdi.sandogh.model.installmentLoan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentLoanDTO extends BaseDTO {

    private String installmentLoanId;
    private Long amountInstallment; // مبلغ قسط
    private Integer numberLoan; // شماره قسط
    private String accountId;
    private Long creationDate; // تاریخ


    public InstallmentLoanDTO() {
    }

    public InstallmentLoanDTO(Integer status, String message) {
        super(status, message);
    }
}
