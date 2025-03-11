package com.bpd.maju_mundur.config;

import com.bpd.maju_mundur.constant.ERole;
import com.bpd.maju_mundur.entity.Role;
import com.bpd.maju_mundur.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Role> existingRoles = roleRepository.findAll();

        List<ERole> defaultRoles = Arrays.asList(ERole.ROLE_CUSTOMER, ERole.ROLE_MERCHANT);

        for (ERole eRole : defaultRoles) {
            boolean roleExists = existingRoles.stream()
                    .anyMatch(role -> role.getRole().equals(eRole));

            if (!roleExists) {
                roleRepository.save(new Role(null, eRole));
            }
        }
    }
}
