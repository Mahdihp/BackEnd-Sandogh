package com.mahdi.sandogh.model.monthly;


import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "monthlys")
public class Monthly extends DateAudit { // جدول ماهانه ها

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amountpermonth")
    private long amountPerMonth; // مبلغ سهم هر ماه

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Monthly() {
    }
}
