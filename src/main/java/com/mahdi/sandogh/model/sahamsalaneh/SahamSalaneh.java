package com.mahdi.sandogh.model.sahamsalaneh;


import com.mahdi.sandogh.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "sahamsalanehs")
public class SahamSalaneh extends DateAudit { // سهام سالانه
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "yesrs")
    private int yesrs; //سال

    @Column(name = "membershipfee")
    private long membershipFee; // حق عضویت

}
