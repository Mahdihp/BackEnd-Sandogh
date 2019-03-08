package com.mahdi.sandogh.model.sandogh;


import lombok.Data;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "firstname")
    private String lastName;

    @Column(name = "fathername")
    private String fatherName;

    @Column(name = "mobilenumber")
    private String mobileNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "adderss")
    private String adderss;

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

    public Account() {
    }
}
