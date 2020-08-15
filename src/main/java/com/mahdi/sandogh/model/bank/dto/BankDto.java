package com.mahdi.sandogh.model.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by: mahdi
 * DateTime: ۱۵/۰۸/۲۰۲۰ - 20:18:43
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankDto {

    private Integer id;
    private String displayName;
    private long amount;   // مقدار پول
    private String sheba; //شماره شبا
    private String accountNumber;  //شماره حساب
    private String customerNumber;  //شماره مشتری
    private String username;
    private String password;
    private String cardNumber;  //شماره کارت
    private String description;
    private Integer fundId;
}
