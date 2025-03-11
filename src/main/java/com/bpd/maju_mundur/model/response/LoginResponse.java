package com.bpd.maju_mundur.model.response;
import com.bpd.maju_mundur.constant.ERole;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginResponse {
    private String username;
    private String token;
    private List<ERole> role;
}
