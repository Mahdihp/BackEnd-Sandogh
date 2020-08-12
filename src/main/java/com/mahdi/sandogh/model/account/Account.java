package com.mahdi.sandogh.model.account;

/**
 * اعضای صندوق
 */

import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.found.Fund;
import com.mahdi.sandogh.model.installmentloan.InstallmentLoan;
import com.mahdi.sandogh.model.loan.Loan;
import com.mahdi.sandogh.model.monthly.Monthly;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.type.StringNVarcharType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "accounts")
public class Account extends DateAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountnumber", unique = true)
    private String accountNumber;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "fathername")
    private String fatherName;

    @Column(name = "mobilenumber")
    private String mobileNumber;

    @Column(name = "nationalCode", unique = true)
    private String nationalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "adderss")
    private String adderss;

    @Column(name = "active")
    private boolean active;

    @Column(name = "countloan")
    private int countLoan;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "accounts")
    private Set<Fund> funds = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Loan> loans = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Monthly> monthlies = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<InstallmentLoan> installmentLoans = new HashSet<>();

    public Account() {
    }
}
