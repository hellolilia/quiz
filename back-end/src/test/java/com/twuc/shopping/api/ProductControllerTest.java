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
        String img = "https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg";
        productRepository.deleteAll();
        productRepository.save(ProductDto.builder()
                .name("可乐1").price("1.50").unit("瓶").image(img).build());
        productRepository.save(ProductDto.builder()
                .name("可乐2").price("2.50").unit("2瓶").image(img).build());
        productRepository.save(ProductDto.builder()
                .name("可乐3").price("3.50").unit("3瓶").image(img).build());
        productRepository.save(ProductDto.builder()
                .name("可乐4").price("4.50").unit("4瓶").image(img).build());
        productRepository.save(ProductDto.builder()
                .name("可乐5").price("5.50").unit("5瓶").image(img).build());
    }

    @Test
    public void shouldGetProductList() throws Exception {
        mockMvc
                .perform(get("/product"))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[4].name", is("可乐5")))
                .andExpect(jsonPath("$[4].price", is("5.50")))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldAddProductWithRightProduct() throws Exception {
        String img = "https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg";

        String jsonString = "{\"name\":\"可乐6\",\"price\":\"6.50\",\"unit\":\"6瓶\",\"image\":\"https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg\"}";
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<ProductDto> all = productRepository.findAll();
        assertNotNull( all );
        assertEquals(6,all.size());
        assertEquals("可乐6",all.get(5).getName());
        assertEquals("6.50",all.get(5).getPrice());
        assertEquals("6瓶",all.get(5).getUnit());
        assertEquals(img,all.get(5).getImage());
    }

    @Test
    public void shouldNotAddProductWithWrongImg() throws Exception {
        String jsonString = "{\"name\":\"可乐6\",\"price\":\"6.50\",\"unit\":\"6瓶\",\"image\":\"img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg\"}";
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldNotAddProductWithWrongPrice() throws Exception {
        String jsonString = "{\"name\":\"可乐6\",\"price\":\"6瓶\",\"unit\":\"6瓶\",\"image\":\"https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg\"}";
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        jsonString = "{\"name\":\"可乐6\",\"price\":\"2.555\",\"unit\":\"6瓶\",\"image\":\"https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg\"}";
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        jsonString = "{\"name\":\"可乐6\",\"price\":\"0\",\"unit\":\"6瓶\",\"image\":\"https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg\"}";
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldAddProductWhenHasNoProductName() throws Exception {
        String img = "https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg";

        String jsonString = "{\"name\":\"可乐6\",\"price\":\"6.50\",\"unit\":\"6瓶\",\"image\":\"https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg\"}";
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<ProductDto> all = productRepository.findAll();
        assertNotNull( all );
        assertEquals(6,all.size());
        assertEquals("可乐6",all.get(5).getName());
        assertEquals("6.50",all.get(5).getPrice());
        assertEquals("6瓶",all.get(5).getUnit());
        assertEquals(img,all.get(5).getImage());
    }

    @Test
    public void shouldNotAddProductWhenHasProductName() throws Exception {
        String jsonString = "{\"name\":\"可乐1\",\"price\":\"6.50\",\"unit\":\"6瓶\",\"image\":\"https://img.zcool.cn/community/017483591da785b5b3086ed4904903.jpg@1280w_1l_2o_100sh.jpg\"}";
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}