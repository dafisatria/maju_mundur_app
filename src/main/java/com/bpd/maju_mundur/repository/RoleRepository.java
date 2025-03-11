package com.bpd.maju_mundur.repository;

import com.bpd.maju_mundur.constant.ERole;
import com.bpd.maju_mundur.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRole(ERole role);
}
