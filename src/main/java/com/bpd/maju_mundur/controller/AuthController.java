package com.bpd.maju_mundur.controller;

import com.bpd.maju_mundur.model.request.AuthRequest;
import com.bpd.maju_mundur.model.response.CommonResponse;
import com.bpd.maju_mundur.model.response.LoginResponse;
import com.bpd.maju_mundur.model.response.RegisterResponse;
import com.bpd.maju_mundur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup-customer")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustomer(@RequestBody AuthRequest request) {
        RegisterResponse customer = userService.registerCustomer(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully created customer account")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signup-merchant")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerMerchant(@RequestBody AuthRequest request) {
        RegisterResponse merchant = userService.registerMerchant(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully created merchant account")
                .data(merchant)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signin")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody AuthRequest request) {
        LoginResponse login = userService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("Successfully logged in")
                .data(login)
                .build();
        return ResponseEntity.ok(response);
    }
}
