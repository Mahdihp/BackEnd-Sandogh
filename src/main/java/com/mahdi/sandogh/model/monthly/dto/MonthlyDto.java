package com.mahdi.sandogh.model.monthly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 21:26
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonthlyDto extends BaseDto {

    private String monthlyId;
    private Integer amountPerMonth; // مبلغ سهم هر ماه
    private LocalDateTime creationDate; // تاریخ
    private LocalDateTime updatedAt;
    private String accountId;

    public MonthlyDto() {
    }
}
