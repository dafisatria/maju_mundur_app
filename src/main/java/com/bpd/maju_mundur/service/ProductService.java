package com.bpd.maju_mundur.service;

import com.bpd.maju_mundur.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    List<Product> getAll();

    Product update(Product product);

    void delete(String id);
}
