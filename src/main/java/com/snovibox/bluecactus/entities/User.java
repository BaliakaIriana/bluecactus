package com.snovibox.bluecactus.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name ="user_id_seq", sequenceName = "user_id_seq")
    private Integer id;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    private String password;
    private LocalDate dateExpiration;
    private Boolean locked;
    private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return dateExpiration == null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
