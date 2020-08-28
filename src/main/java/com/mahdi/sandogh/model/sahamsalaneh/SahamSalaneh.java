package com.mahdi.sandogh.model.sahamsalaneh;


import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.bank.Bank;
import com.mahdi.sandogh.model.fund.Fund;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "sahamsalanehs")
public class SahamSalaneh extends DateAudit { // سهام سالانه
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "yesrs")
    private Integer yesrs; //سال

    @Column(name = "membershipfee")
    private Long membershipFee; // حق عضویت


}
