package com.mahdi.sandogh.model.found;


import com.mahdi.sandogh.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*

بانک ها
 */
@Setter
@Getter
@Entity
@Table(name = "banks")
public class Bank  extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
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

    @NotNull
    @Column(name = "cardNumber", unique = true)
    private String cardNumber;  //شماره کارت

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

}
