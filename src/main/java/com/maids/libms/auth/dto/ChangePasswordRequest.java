package com.maids.libms.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    @NotNull
    String currentPassword;

    @NotNull
    String newPassword;

    @NotNull
    String confirmationPassword;
}
