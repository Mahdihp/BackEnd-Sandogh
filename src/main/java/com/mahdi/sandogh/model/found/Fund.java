package com.mahdi.sandogh.model.found;


import com.mahdi.sandogh.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *  صندوق ها
 */
@Setter
@Getter
@Entity
@Table(name = "funds")
public class Fund extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID uid;

    @NotNull
    @Column(name = "displayName", unique = true)
    private String displayName;

    @Column(name = "createBy")
    private String createBy;

    @NotNull
    @Column(name = "description")
    private String description;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "fund")
    private Set<Bank> banks = new HashSet<>();

}
