package com.mahdi.sandogh.model.monthly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;

import java.util.List;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 21:52
 * https://github.com/mahdihp
 */


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListMonthlyDto extends BaseDto {

    private List<MonthlyDto> data;

    public ListMonthlyDto() {
    }


}
