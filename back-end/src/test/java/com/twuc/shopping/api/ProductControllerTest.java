package com.twuc.shopping.api;

import com.twuc.shopping.dto.ProductDto;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired(required=false)
    ProductDto productDto;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void shouldGetProductList() throws Exception {

        ProductDto productDto = ProductDto.builder()
                .name("可乐2").price("2.50").unit("瓶").build();

        productRepository.save(productDto);

        mockMvc
                .perform(get("/pro/list"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("可乐2")))
                .andExpect(jsonPath("$[0].price", is("2.50")))
                .andExpect(status().isOk());
    }


}