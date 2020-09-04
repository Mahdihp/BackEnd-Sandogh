package com.mahdi.sandogh.model.monthly.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 21:18
 * https://github.com/mahdihp
 */



@Setter
@Getter
public class MonthlyForm {

    private Long monthlyId;
    private Integer amountPerMonth; // مبلغ سهم هر ماه
    @NotNull
    private Integer fundId;
    @NotNull
    private String accountNumber;

    public MonthlyForm() {
    }
}
