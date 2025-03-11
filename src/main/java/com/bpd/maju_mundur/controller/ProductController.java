package com.bpd.maju_mundur.controller;

import com.bpd.maju_mundur.entity.Product;
import com.bpd.maju_mundur.model.response.CommonResponse;
import com.bpd.maju_mundur.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CommonResponse<Product>> create(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        CommonResponse<Product> response = CommonResponse.<Product>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Product created")
                .data(createdProduct)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Product>> update(@RequestBody Product product) {
        Product updatedProduct = productService.update(product);
        CommonResponse<Product> response = CommonResponse.<Product>builder()
                .message("Product updated")
                .data(updatedProduct)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable String id) {
        productService.delete(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message("Product deleted")
                .data(id)
                .build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<CommonResponse<List<Product>>> getAll() {
        List<Product> products = productService.getAll();
        CommonResponse<List<Product>> response = CommonResponse.<List<Product>>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Product retrieved")
                .data(products)
                .build();
        return ResponseEntity.ok(response);
    }
}
