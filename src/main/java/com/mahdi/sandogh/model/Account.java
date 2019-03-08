package com.mahdi.sandogh.model;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ID_SEQ")
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
    private Long creationDate;

    @Column(name = "modificationdate", updatable = true)
    @LastModifiedDate
    private Long modificationDate;

    public Account() {
    }
}
