package com.mahdi.sandogh.model.sandogh;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "installmentloan")
@Data
public class InstallmentLoan { // جدول اقساط وام

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "amountinstallment")
    private long amountInstallment; // مبلغ قسط

    @Column(name = "creationdate", updatable = false)
    @CreatedDate
    private long creationDate; // تاریخ

    @Column(name = "numberloan")
    private int numberLoan; // شماره قسط

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountid", nullable = false)
    private Account account;

}
