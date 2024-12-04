package com.upiiz.securitydb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(name="is_enabled")
    private boolean isEnable;

    @Column(name="account__no_enabled")
    private boolean accountNoExpired;

    @Column(name="account no_Locked")
    private boolean accountNoLocked;

    @Column(name="credential_no_expired")
    private boolean credentialNoExpired;


    @ManyToMany (fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();
}
