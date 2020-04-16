package com.roche.application.rest.integration;

import com.roche.application.rest.config.RestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for ProductEndPoint.
 * <p>
 * Created on 16.04.20.
 *
 * @author waldemarkipka
 */
@AutoConfigureMockMvc
@ContextConfiguration(classes = RestConfig.class)
@WebMvcTest
@Disabled
public class ProductEndPointIntegrationTest {
    private final static String PRODUCT_URL = "/api/product";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void crud() throws Exception {
        String jsonProduct = "{ \"sku\": \"" + UUID.randomUUID().toString() + "\", \"name\" : \"Milk\", \"price\" : \"80\"}";
        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(PRODUCT_URL).
                accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonProduct);
        mockMvc.perform(builder).andExpect(status().isCreated());
    }
}
