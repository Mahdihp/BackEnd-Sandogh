package com.mahdi.sandogh.model.user;

import com.mahdi.sandogh.model.role.Role;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID uid;

    @Size(max = 50)
    @Column(name = "firstname")
    private String firstName;

    @Size(max = 50)
    @Column(name = "lastname")
    private String lastName;

    @Column(name = "username", unique = true, nullable = false)
    @Size(min = 5, max = 30)
    private String userName;

    @Column(name = "password", unique = true, nullable = false)
    @Size(min = 5, max = 30)
    private String password;

    @Column(name = "mobilenumber")
    @Size(min = 11, max = 11)
    private String mobileNumber;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {
    }
}