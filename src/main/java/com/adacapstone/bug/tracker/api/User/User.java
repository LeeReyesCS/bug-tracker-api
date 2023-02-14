package com.adacapstone.bug.tracker.api.User;

import com.adacapstone.bug.tracker.api.Bug.Bug;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer userId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @Column(unique=true)
    private String email;
    @NonNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "assignedDevs",fetch = FetchType.LAZY)
//    @JsonBackReference
    Set<Bug> assignedBugs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
