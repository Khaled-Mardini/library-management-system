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
public class BookApiControllerTests extends BaseTestUseCases {
    private String accessToken;
    @Autowired
    private MockMvc mockMvc;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @BeforeEach
    public void setup() throws Exception {
        accessToken = super.authenticate(mockMvc);
    }


    @Test
    public void testBorrowBook() throws Exception {
        String bookId = "1";
        String patronId = "1";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/" + bookId + "/patron/" + patronId)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testReturnBook() throws Exception {
        String bookId = "1";
        String patronId = "1";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/return/" + bookId + "/patron/" + patronId)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateBook() throws Exception {
        String requestBody = "{\"title\": \"title\", \"authorId\": \"1\", \"publicationYear\": 0, \"isbn\": \"string\", \"numOfPages\": 0, \"quantity\": 0}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateBook() throws Exception {
        String requestBody = "{\"title\": \"title\", \"authorId\": 1, \"publicationYear\": 0, \"isbn\": \"string\", \"numOfPages\": 0, \"quantity\": 0}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
