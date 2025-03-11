package com.bpd.maju_mundur.service;

import com.bpd.maju_mundur.model.request.AuthRequest;
import com.bpd.maju_mundur.model.response.LoginResponse;
import com.bpd.maju_mundur.model.response.RegisterResponse;

public interface UserService {
    RegisterResponse registerCustomer(AuthRequest request);
    LoginResponse login(AuthRequest request);
    RegisterResponse registerMerchant(AuthRequest request);
}
