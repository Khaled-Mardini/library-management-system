package com.maids.libms.patron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maids.libms.main.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter @Setter
@Builder @Accessors(chain = true)
@NoArgsConstructor @AllArgsConstructor
public class Contact extends BaseEntity<Integer> {
    @NotNull
    @Column(nullable = false)
    String name;

    @NotNull
    @Column(nullable = false)
    String url;

    @JsonIgnoreProperties(value = "contacts")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patron_id")
    Patron patron;
}
