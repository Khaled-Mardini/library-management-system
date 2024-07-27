package com.maids.libms.auth.dto;

import com.maids.libms.patron.Patron;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyResponse {

    String accessToken;
    String refreshToken;
    Patron patron;
}
