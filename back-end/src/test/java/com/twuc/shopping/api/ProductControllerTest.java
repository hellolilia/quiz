package com.twuc.shopping.api;

import com.twuc.shopping.dto.ProductDto;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void shouldGetProductList() throws Exception {

        ProductDto productDto = ProductDto.builder()
                .name("可乐2").price("2.50").unit("瓶").build();

        productRepository.save(productDto);

        mockMvc
                .perform(get("/product"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("可乐2")))
                .andExpect(jsonPath("$[0].price", is("2.50")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddProductWithoutImg() throws Exception {

        String jsonString = "{\"name\":\"可乐1\",\"price\":\"3.50\",\"unit\":\"瓶\"}";

        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<ProductDto> all = productRepository.findAll();
        assertNotNull( all );
        assertEquals(1,all.size());
        assertEquals("可乐1",all.get(0).getName());
        assertEquals("3.50",all.get(0).getPrice());
        assertEquals("瓶",all.get(0).getUnit());
        }

    @Test
    public void shouldAddProductWithImg() throws Exception {
        String img = "https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg";

        String jsonString = "{\"name\":\"可乐1\",\"price\":\"3.50\",\"unit\":\"瓶\",\"image\":\"https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg\"}";
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<ProductDto> all = productRepository.findAll();
        assertNotNull( all );
        assertEquals(1,all.size());
        assertEquals("可乐1",all.get(0).getName());
        assertEquals("3.50",all.get(0).getPrice());
        assertEquals("瓶",all.get(0).getUnit());
        assertEquals(img,all.get(0).getImage());

    }




}