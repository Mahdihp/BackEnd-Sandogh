package com.mahdi.sandogh.model.permission;

import com.mahdi.sandogh.model.audit.DateAudit;
import com.mahdi.sandogh.model.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by mahdi
 * DateTime: ۱۱/۰۸/۲۰۲۰:20:07
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Setter
@Getter
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "displayname")
    private String displayName;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;
}
