package com.bpd.maju_mundur.entity;

import com.bpd.maju_mundur.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_role")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERole role;
}

