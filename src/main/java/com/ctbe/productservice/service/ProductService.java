package com.ctbe.productservice.service;

import com.ctbe.productservice.dto.ProductRequest;
import com.ctbe.productservice.dto.ProductResponse;
import com.ctbe.productservice.exception.ResourceNotFoundException;
import com.ctbe.productservice.model.Product;
import com.ctbe.productservice.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<ProductResponse> findAll() {

        return repo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse findById(Long id) {

        Product product = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(id));

        return toResponse(product);
    }

    public ProductResponse create(ProductRequest req) {

        Product saved = repo.save(toEntity(req));

        return toResponse(saved);
    }

    public ProductResponse update(Long id, ProductRequest req) {

        Product existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(id));

        existing.setName(req.getName());
        existing.setPrice(req.getPrice());
        existing.setStockQty(req.getStockQty());
        existing.setCategory(req.getCategory());

        return toResponse(repo.save(existing));
    }

    public void delete(Long id) {

        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }

        repo.deleteById(id);
    }

    private ProductResponse toResponse(Product p) {

        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getStockQty(),
                p.getCategory()
        );
    }

    private Product toEntity(ProductRequest req) {

        Product p = new Product();

        p.setName(req.getName());
        p.setPrice(req.getPrice());
        p.setStockQty(req.getStockQty());
        p.setCategory(req.getCategory());

        return p;
    }
}
