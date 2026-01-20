package com.AbedProjects.ShopIt.Security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import thirdProject.EntityClasses.Patient;
import thirdProject.config.PermissionType;
import thirdProject.config.RoleType;
import thirdProject.config.PermissionRoleMapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@Table(name = "user_tableForAuth")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    Set<RoleType> roles = new HashSet<>();

//    @OneToOne(mappedBy = "user")
//    private Patient patient;

    @Column(unique = true)
    private String username;

    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//       Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//
//        roles.forEach(role -> { Set<SimpleGrantedAuthority> permissions = PermissionRoleMapping.getAuthoritiesForRole(role);
//                grantedAuthorities.addAll(permissions);
//                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
//        }
//        );
//        return grantedAuthorities;
//    }

}
