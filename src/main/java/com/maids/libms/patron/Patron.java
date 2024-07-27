package com.maids.libms.patron;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maids.libms.auth.model.Token;
import com.maids.libms.auth.enums.Role;
import com.maids.libms.borrowing.record.BorrowingRecord;
import com.maids.libms.main.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.*;

@Entity
@Getter @Setter
@Builder @Accessors(chain = true)
@NoArgsConstructor @AllArgsConstructor
public class Patron extends BaseEntity<Integer> implements UserDetails {
    @NotNull
    @Column(nullable = false)
    String name;

    String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patron", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "patron")
    Set<Contact> contacts = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patron")
    @JsonIgnoreProperties(value = "patron")
    Set<BorrowingRecord> borrowingRecords = new HashSet<>();

    @Column(nullable = false, unique = true)
    String mobileNumber;

    @Column(nullable = false)
    Boolean activated = false;

    @JsonIgnore
    @Column(length = 20)
    String activationKey;

    @JsonIgnore
    Instant activationKeyCreation;

    @Email
    @Column(unique = true, nullable = false)
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 60, nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
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
        return activated;
    }
}
