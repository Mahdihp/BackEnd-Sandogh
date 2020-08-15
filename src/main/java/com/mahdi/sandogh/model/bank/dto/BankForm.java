package com.mahdi.sandogh.model.bank.dto;

import com.mahdi.sandogh.model.fund.Fund;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by: mahdi
 * DateTime: ۱۵/۰۸/۲۰۲۰ - 19:36:14
 * Email: mahdip.devsc@gmail.com
 * http://github.com/mahdihp & http://gitlab.com/mahdihp
 */


@Setter
@Getter
public class BankForm {

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
