package com.mahdi.sandogh.model.sandogh;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "loans")
@Data
public class Loan { //جدول وام ها

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "countloan")
    private int countLoan; // تعداد وام

    @Column(name = "sumloan")
    private long sumLoan; // مجموع تعداد وام

    @Column(name = "currentloanamount")
    private long currentLoanAmount; // مبلغ وام جاری

    @Column(name = "datecurrentloan")
    @CreatedDate
    private long dateCurrentLoan; // تاریخ شروع وام جاری

    @Column(name = "countinstallments")
    private int countInstallments; // تعداد اقساط

    @Column(name = "amountperinstallment")
    private long amountPerInstallment; // مبلغ هر قسط

    @Column(name = "datefinishinstallment")
    @CreatedDate
    private long dateFinishInstallment; // تاریخ پایان قسط یا تاریخ آخرین قسط

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountid", nullable = false)
    private Account account;

    public Loan() {
    }
}
