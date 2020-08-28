package com.mahdi.sandogh.model.fund;


import com.mahdi.sandogh.model.account.Account;
import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.bank.Bank;
import com.mahdi.sandogh.model.loan.Loan;
import com.mahdi.sandogh.model.monthly.Monthly;
import com.mahdi.sandogh.model.sahamsalaneh.SahamSalaneh;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * صندوق ها
 */
@Setter
@Getter
@Entity
@Table(name = "funds")
public class Fund extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "displayName", unique = true)
    private String displayName;

    @Column(name = "createBy")
    private String createBy;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "fund")
    private Set<Bank> banks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "fund_account",
            joinColumns = {@JoinColumn(name = "fund_id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id")})
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "fund")
    private Set<Monthly> monthlies = new HashSet<>();

    @OneToMany(mappedBy = "fund")
    private Set<Loan> loans = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sahamsalaneh_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SahamSalaneh sahamSalaneh;
}
