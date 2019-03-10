package com.mahdi.sandogh.model.sandogh;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "sahamsalanehs")
@Data
public class SahamSalaneh { // سهام سالانه


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "yesrs")
    private int yesrs; //سال

    @Column(name = "membershipfee")
    private long membershipFee; // حق عضویت

    @Column(name = "creationdate", updatable = false)
    @CreatedDate
    private long creationDate;
}
