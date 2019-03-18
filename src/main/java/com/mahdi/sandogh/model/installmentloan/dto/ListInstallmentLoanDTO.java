package com.mahdi.sandogh.model.installmentloan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListInstallmentLoanDTO extends BaseDTO {

    private List<InstallmentLoanDTO> data;

    public ListInstallmentLoanDTO() {
    }

    public ListInstallmentLoanDTO(Integer status, String message) {
        super(status, message);
    }
}
