package com.mahdi.sandogh.model.fund.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
