package com.mahdi.sandogh.model.bank;


import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.fund.Fund;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**

بانک ها
 */
@Setter
@Getter
@Entity
@Table(name = "banks")
public class Bank  extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "displayName")
    private String displayName;

    @Column(name = "amount")
    private long amount;   // مقدار پول

    @Column(name = "sheba", unique = true)
    private String sheba; //شماره شبا

    @NotNull
    @Column(name = "accountnumber", unique = true)
    private String accountNumber;  //شماره حساب

    @Column(name = "customerNumber", unique = true)
    private String customerNumber;  //شماره مشتری

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "cardNumber", unique = true)
    private String cardNumber;  //شماره کارت

    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

}
