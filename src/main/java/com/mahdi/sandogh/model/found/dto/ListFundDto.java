package com.mahdi.sandogh.model.found.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.found.Fund;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahdi
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 17:56
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListFundDto extends BaseDto {

    private List<FundDto> data=new ArrayList<>();
}
