package com.mahdi.sandogh.model.found.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.found.Fund;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mahdi
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 17:48
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FundDto {

    private Integer id;
    private String displayName;
    private String createBy;
    private String description;
}
