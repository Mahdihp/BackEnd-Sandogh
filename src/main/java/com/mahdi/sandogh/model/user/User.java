package com.mahdi.sandogh.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.role.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends DateAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "displayname")
    private String displayName;

    @Column(name = "username", unique = true, nullable = false)
    @Size(min = 5, max = 30)
    private String userName;

    @Column(name = "password", unique = true, nullable = false)
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @Column(name = "nationalId")
    @Size(min = 10, max = 10)
    private String nationalId;

    @Column(name = "active")
    private boolean active;

    @Column(name = "login")
    private boolean login;

    @Column(name = "deleted")
    private boolean deleted = false;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {
    }
}
