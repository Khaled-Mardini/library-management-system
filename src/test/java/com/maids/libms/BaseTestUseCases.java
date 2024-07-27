package com.maids.libms;

import com.jayway.jsonpath.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class BaseTestUseCases {
    public String authenticate(MockMvc mockMvc) throws Exception {
        String requestBody = "{\"email\": \"khaled.mardini1999@gmail.com\", \"password\": \"12312312\"}";
        String loginResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        return JsonPath.read(loginResponse, "$.data.accessToken");
    }

    public double measureExecutionTime(Runnable function) {
        long startTime = System.nanoTime();
        function.run();
        long endTime = System.nanoTime();
        long executionTimeNano = endTime - startTime;
        return executionTimeNano / 1_000_000_000.0;
    }
}
