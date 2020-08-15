package com.mahdi.sandogh.model.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.fund.dto.FundDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: mahdi
 * DateTime: ۱۵/۰۸/۲۰۲۰ - 20:20:00
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListBankDto extends BaseDto {

    private List<BankDto> data=new ArrayList<>();

}
