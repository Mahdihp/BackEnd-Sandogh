package com.mahdi.sandogh.model.loan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListLoanDto extends BaseDto {

    private List<LoanDto> data;

    public ListLoanDto() {
    }

    public ListLoanDto(Integer status, String message) {
        super(status, message);
    }
}
