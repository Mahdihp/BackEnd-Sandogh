package com.mahdi.sandogh.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

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

    @Column(name = "username")
    @Size(min = 5, max = 30) //
    private String userName;

    @Column(name = "password")
    @Size(min = 5, max = 30)
    private String password;

    public User() {
    }
}
