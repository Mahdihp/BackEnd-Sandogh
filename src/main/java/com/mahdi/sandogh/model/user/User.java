package com.mahdi.sandogh.model.user;

import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.role.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends DateAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {
    }
}
