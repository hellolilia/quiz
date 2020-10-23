package com.twuc.shopping.api;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.dto.ProductDto;
import com.twuc.shopping.repository.ProductRepository;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public ResponseEntity getProductList(){
        return productService.getProducts();
    }

    @PostMapping("/product")
    public ResponseEntity addProductList(@RequestBody @Valid Product product){
        return productService.addProduct(product);
    }

}
