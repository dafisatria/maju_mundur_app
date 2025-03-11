package com.bpd.maju_mundur.model.response;

import com.bpd.maju_mundur.constant.ERole;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String username;
    private List<ERole> role;
}