package com.bpd.maju_mundur.service.Impl;

import com.bpd.maju_mundur.entity.Product;
import com.bpd.maju_mundur.repository.ProductRepository;
import com.bpd.maju_mundur.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product create(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        findByIdOrThrowNotFound(product.getId());
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
    public Product findByIdOrThrowNotFound(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
