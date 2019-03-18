package com.mahdi.sandogh.model.monthly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 21:26
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonthlyDTO extends BaseDTO {

    private String monthlyId;
    private Long amountPerMonth; // مبلغ سهم هر ماه
    private Long creationDate; // تاریخ
    private Long modificationDate;
    private String accountId;

    public MonthlyDTO() {
    }
}
