package com.mahdi.sandogh.model.installmentLoan;


import com.mahdi.sandogh.model.account.Account;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "installmentloan")
@Data
public class InstallmentLoan { // جدول اقساط وام

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    @Type(type = "uuid-char")
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
