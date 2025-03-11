package com.bpd.maju_mundur.service.Impl;

import com.bpd.maju_mundur.constant.ERole;
import com.bpd.maju_mundur.entity.Role;
import com.bpd.maju_mundur.entity.User;
import com.bpd.maju_mundur.model.request.AuthRequest;
import com.bpd.maju_mundur.model.response.LoginResponse;
import com.bpd.maju_mundur.model.response.RegisterResponse;
import com.bpd.maju_mundur.repository.RoleRepository;
import com.bpd.maju_mundur.repository.UserRepository;
import com.bpd.maju_mundur.security.JwtTokenProvider;
import com.bpd.maju_mundur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public RegisterResponse registerCustomer(AuthRequest request) {
        Role roleCustomer = roleRepository.findByRole(ERole.ROLE_CUSTOMER);
        List<Role> roles = new ArrayList<>();
        roles.add(roleCustomer);
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        userRepository.saveAndFlush(user);
        return RegisterResponse.builder()
                .username(user.getUsername())
                .role(user.getRoles().stream().map(Role::getRole).toList())
                .build();

    }

    @Override
    public LoginResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRoles().stream().map(Role::getRole).toList());
        return LoginResponse.builder()
                .username(user.getUsername())
                .role(user.getRoles().stream().map(Role::getRole).toList())
                .token(token)
                .build();
    }

    @Override
    public RegisterResponse registerMerchant(AuthRequest request) {
        Role roleMerchant = roleRepository.findByRole(ERole.ROLE_MERCHANT);
        List<Role> roles = new ArrayList<>();
        roles.add(roleMerchant);

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        userRepository.saveAndFlush(user);
        return RegisterResponse.builder()
                .username(user.getUsername())
                .role(user.getRoles().stream().map(Role::getRole).toList())
                .build();
    }
}
