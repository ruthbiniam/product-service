package com.ctbe.productservice;

import com.ctbe.productservice.dto.ProductRequest;
import com.ctbe.productservice.dto.ProductResponse;
import com.ctbe.productservice.exception.ResourceNotFoundException;
import com.ctbe.productservice.model.Product;
import com.ctbe.productservice.repository.ProductRepository;
import com.ctbe.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void findById_returnsProduct_whenExists() {

        Product product =
                new Product("Laptop", 1200.0, 5, "Electronics");

        product.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(product));

        ProductResponse result = service.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Laptop");
        assertThat(result.getPrice()).isEqualTo(1200.0);
    }

    @Test
    void findById_throwsException_whenNotFound() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void create_savesProduct() {

        ProductRequest req = new ProductRequest();
        req.setName("Keyboard");
        req.setPrice(100);
        req.setStockQty(10);
        req.setCategory("Accessories");

        Product saved =
                new Product("Keyboard", 100, 10, "Accessories");

        saved.setId(2L);

        when(repository.save(org.mockito.ArgumentMatchers.any(Product.class)))
                .thenReturn(saved);

        ProductResponse result = service.create(req);

        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getName()).isEqualTo("Keyboard");
    }
}
