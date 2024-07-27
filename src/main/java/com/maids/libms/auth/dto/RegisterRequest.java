package com.maids.libms.auth.dto;

import com.maids.libms.auth.enums.Role;
import com.maids.libms.patron.Contact;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull
    @Size(min = 3, max = 54)
    String name;

    String address;

    @NotNull
    String mobileNumber;

    @Email
    @NotNull
    String email;

    @Size(min = 8, max = 60)
    @NotNull
    String password;

    Set<Contact> contacts = new HashSet<>();
}
