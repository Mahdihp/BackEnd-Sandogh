package com.mahdi.sandogh.model.monthly.dto;

import lombok.Data;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 21:18
 * https://github.com/mahdihp
 */


@Data
public class MonthlyForm {

    private Long monthlyId;
    private long amountPerMonth; // مبلغ سهم هر ماه
    private long creationDate; // تاریخ
    private Long accountId;

    public MonthlyForm() {
    }
}
