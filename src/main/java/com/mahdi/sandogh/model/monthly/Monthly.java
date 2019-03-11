package com.mahdi.sandogh.model.monthly;


import com.mahdi.sandogh.model.account.Account;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "monthlys")
@Data
public class Monthly { // جدول ماهانه ها

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID uid;

    @Column(name = "amountpermonth")
    private long amountPerMonth; // مبلغ سهم هر ماه

    @Column(name = "creationdate", updatable = false)
    @CreatedDate
    private long creationDate; // تاریخ

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountid", nullable = false)
    private Account account;

    public Monthly() {
    }
}
