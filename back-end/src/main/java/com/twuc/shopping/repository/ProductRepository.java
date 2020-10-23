package com.twuc.shopping.repository;

import com.twuc.shopping.dto.ProductDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductDto, Integer> {

    @Override
    List<ProductDto> findAll();

    long countByName(String name);
}
