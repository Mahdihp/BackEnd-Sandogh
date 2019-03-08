package com.mahdi.sandogh.model.sandogh;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "monthlys")
@Data
public class Monthly { // جدول ماهانه ها

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
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
