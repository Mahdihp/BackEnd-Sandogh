package com.mahdi.sandogh.model.found.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Created by mahdi
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 17:36
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Setter
@Getter
public class FundForm {

    private Integer id;
    private String displayName;
    private String createBy;
    private String description;
}
