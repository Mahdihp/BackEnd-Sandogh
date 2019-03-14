package com.mahdi.sandogh.model.loan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListLoanDTO extends BaseDTO {

    private List<LoanDTO> data;

    public ListLoanDTO() {
    }

    public ListLoanDTO(Integer status, String message) {
        super(status, message);
    }
}
