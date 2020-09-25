package com.twuc.shopping.api;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/pro/list")
    public ResponseEntity getRsEventBetween(){
        List<Product> products = productRepository.findAll().stream()
                .map(item ->Product.builder().name(item.getName())
                        .price(item.getPrice()).image(item.getImage())
                        .unit(item.getUnit()).build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }
}
