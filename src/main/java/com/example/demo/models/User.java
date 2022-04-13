package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(25) default 'ACTIVE'")
    private Status status;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;

    @Column(unique = true)
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
