package com.mahdi.sandogh.model.installmentloan;


import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.loan.Loan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "installmentloan")
public class InstallmentLoan extends DateAudit { // جدول اقساط وام

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amountinstallment")
    private Integer amountInstallment; // مبلغ قسط

    @Column(name = "numberloan")
    private Integer numberLoan; // شماره قسط

    @Column(name = "description")
    private String description;

    @Column(name = "createby")
    private String createBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

}
