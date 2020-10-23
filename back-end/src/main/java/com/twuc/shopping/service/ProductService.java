package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.dto.ProductDto;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity addProduct(Product product) {
        ProductDto productDto = ProductDto.builder().name(product.getName())
                .price(product.getPrice()).unit(product.getUnit())
                .image(product.getImage()).build();
        productRepository.save(productDto);
        return ResponseEntity.created(null).build();
    }

    public ResponseEntity getProducts() {
        List<Product> products = productRepository.findAll().stream()
                .map(item ->Product.builder().name(item.getName())
                        .price(item.getPrice()).image(item.getImage())
                        .unit(item.getUnit()).build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }


}
