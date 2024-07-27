package com.maids.libms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorApiControllerTests extends BaseTestUseCases {

    @Autowired
    private MockMvc mockMvc;
    private String accessToken;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeEach
    public void setup() throws Exception {
        accessToken = super.authenticate(mockMvc);
    }

    @Test
    public void testCreateAuthor() throws Exception {
        String requestBody = "{\"name\": \"Khaled Mardini\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void testGetAuthorById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllAuthors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        String requestBody = "{\"name\": \"Yanni2\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
