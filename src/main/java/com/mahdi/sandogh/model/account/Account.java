package com.mahdi.sandogh.model.account;


import com.mahdi.sandogh.model.installmentLoan.InstallmentLoan;
import com.mahdi.sandogh.model.loan.Loan;
import com.mahdi.sandogh.model.monthly.Monthly;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID uid;

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

    @Column(name = "nationalCode" , unique = true)
    private String nationalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "adderss")
    private String adderss;

    @Column(name = "active")
    private boolean active;

    @Column(name = "creationdate", updatable = false)
    @CreatedDate
    private long creationDate;

    @Column(name = "modificationdate", updatable = true)
    @LastModifiedDate
    private long modificationDate;

    @OneToMany(mappedBy = "account")
    private Set<Loan> loans = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Monthly> monthlies = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<InstallmentLoan> installmentLoans = new HashSet<>();

    public Account() {
    }
}
