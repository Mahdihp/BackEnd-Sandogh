package com.mahdi.sandogh.model.loan;


import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.fund.Fund;
import com.mahdi.sandogh.model.installmentloan.InstallmentLoan;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "loans")
public class Loan extends DateAudit { //جدول وام ها

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "countloan")
    private int countLoan; // تعداد وام

//    @Column(displayName = "sumloan")
//    private long sumLoan; // مجموع تعداد وام

    @Column(name = "currentloanamount")
    private long currentLoanAmount; // مبلغ وام جاری

    @Column(name = "datecurrentloan")
    @CreatedDate
    private LocalDateTime dateCurrentLoan; // تاریخ شروع وام جاری

    @Column(name = "countinstallments")
    private int countInstallments; // تعداد اقساط

    @Column(name = "amountperinstallment")
    private Integer amountPerInstallment; // مبلغ هر قسط

    @Column(name = "datefinishinstallment")
    @CreatedDate
    private LocalDateTime dateFinishInstallment; // تاریخ پایان قسط یا تاریخ آخرین قسط

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

    @OneToMany(mappedBy = "loan")
    private Set<InstallmentLoan> installmentLoans = new HashSet<>();


}
