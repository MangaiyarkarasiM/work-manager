package com.example.warehouse.info;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InfoController.class)
class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getInfoReturnsApplicationInformation() throws Exception {
        mockMvc.perform(
                        get("/api/info")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.applicationName")
                        .value("Warehouse Work Manager"))
                .andExpect(jsonPath("$.message")
                        .value("Hello from Warehouse Work Manager application"));


    }

    @Test
    void unknownEndpointReturnsNotFound() throws Exception {
        mockMvc.perform(
                        get("/api/does-not-exist")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void postToInfoReturnsMethodNotAllowed() throws Exception {
        mockMvc.perform(
                        post("/api/info")
                )
                .andExpect(status().isMethodNotAllowed());
    }

}
