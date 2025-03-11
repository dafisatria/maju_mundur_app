package com.bpd.maju_mundur.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_product")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private Integer price;
}
