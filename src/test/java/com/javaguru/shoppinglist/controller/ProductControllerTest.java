package com.javaguru.shoppinglist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProductService service;

    private static final String ROOT_PATH = "/api/v1/products";


    @Test
    public void findAll_ShouldReturnProducts() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("name");
        dto.setCategory("category");
        dto.setPrice(BigDecimal.valueOf(1));
        dto.setDiscount(BigDecimal.valueOf(0.5));
        dto.setFinalPrice(BigDecimal.valueOf(0.5));
        dto.setDescription("description");

        List<ProductDTO> result = Collections.singletonList(dto);

        given(service.findAll()).willReturn(result);

        MvcResult mvcResult = mvc.perform(get(ROOT_PATH)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        String expected = mapper.writeValueAsString(result);

        assertThat(actual, equalToCompressingWhiteSpace(expected));
    }

    @Test
    public void findProductById_ShouldReturnProduct() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("name");
        dto.setCategory("category");
        dto.setPrice(BigDecimal.valueOf(1));
        dto.setDiscount(BigDecimal.valueOf(0.5));
        dto.setFinalPrice(BigDecimal.valueOf(0.5));
        dto.setDescription("description");

        given(service.findById(1L)).willReturn(dto);

        MvcResult mvcResult = mvc.perform(get(ROOT_PATH + "/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        String expected = mapper.writeValueAsString(dto);

        assertThat(actual, equalToCompressingWhiteSpace(expected));
    }

    @Test
    public void findProductById_shouldThrowNoSuchElementException() throws Exception {
        given(service.findById(1L)).willThrow(new NoSuchElementException("No item found for id: 1"));

        mvc.perform(get(ROOT_PATH + "/1")).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.error", is(NoSuchElementException.class.getSimpleName())))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[0]", is("No item found for id: 1")));
    }

    @Test
    public void create_success() throws Exception {
        Long id = 1L;

        String postRequest = "{\"name\":\"test\",\"category\":\"testov\",\"price\":42,\"discount\":0.25,\"description\":\"testovich\"}";

        given(service.save(BDDMockito.any())).willReturn(id);

        mvc.perform(post(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postRequest).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith(ROOT_PATH + "/" + id)));
    }

    @Test
    public void create_shouldThrowValidationException() throws Exception {
        String postRequest = "{\"name\":\"test\",\"category\":\"testov\",\"price\":42,\"discount\":0.25,\"description\":\"testovich\"}";

        given(service.save(BDDMockito.any())).willThrow(new ValidationException("Name must be unique"));

        mvc.perform(post(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postRequest)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.error", is(ValidationException.class.getSimpleName())))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[0]", is("Name must be unique")));
    }

    @Test
    public void create_shouldThrowMethodArgumentNotValidException() throws Exception {
        String postRequest = "{\"id\":1,\"name\":\"test\",\"category\":\"testov\",\"price\":42,\"discount\":0.25,\"description\":\"testovich\"}";

        mvc.perform(post(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postRequest)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.error", is(MethodArgumentNotValidException.class.getSimpleName())))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[0]", is("id: Please do not provide ID")));
    }

    @Test
    public void update_success() throws Exception {
        String putRequest = "{\"id\":1,\"name\":\"test\",\"category\":\"testov\",\"price\":42,\"discount\":0.25,\"description\":\"testovich\"}";

        mvc.perform(put(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(putRequest)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(header().string("Location", endsWith(ROOT_PATH + "/1")));
    }

    @Test
    public void update_shouldThrowNoSuchElementException() throws Exception {
        String putRequest = "{\"id\":1,\"name\":\"test\",\"category\":\"testov\",\"price\":42,\"discount\":0.25,\"description\":\"testovich\"}";

        willThrow(new NoSuchElementException("No item found for id: 1")).given(service).update(BDDMockito.any());

        mvc.perform(put(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(putRequest)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.error", is(NoSuchElementException.class.getSimpleName())))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[0]", is("No item found for id: 1")));
    }

    @Test
    public void update_shouldThrowValidationException() throws Exception {
        String putRequest = "{\"id\":1,\"name\":\"test\",\"category\":\"testov\",\"price\":42,\"discount\":0.25,\"description\":\"testovich\"}";

        willThrow(new ValidationException("error")).given(service).update(BDDMockito.any());

        mvc.perform(put(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(putRequest)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.error", is(ValidationException.class.getSimpleName())))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[0]", is("error")));
    }

    @Test
    public void delete_success() throws Exception {

        mvc.perform(delete(ROOT_PATH + "/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service).delete(BDDMockito.any());
    }
}