package com.mahdi.sandogh.model.installmentloan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListInstallmentLoanDto extends BaseDto {

    private List<InstallmentLoanDto> data;

}
