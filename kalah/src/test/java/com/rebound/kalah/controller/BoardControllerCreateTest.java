package com.rebound.kalah.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenHappyPath_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.playerTurn").value("PLAYER_ONE"))
            .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
            .andExpect(jsonPath("$.players[0]").isNotEmpty())
            .andExpect(jsonPath("$.players[0].pits[0].stones").value(6))
            .andExpect(jsonPath("$.players[0].pits[1].stones").value(6))
            .andExpect(jsonPath("$.players[0].pits[2].stones").value(6))
            .andExpect(jsonPath("$.players[0].pits[3].stones").value(6))
            .andExpect(jsonPath("$.players[0].pits[4].stones").value(6))
            .andExpect(jsonPath("$.players[0].pits[5].stones").value(6))
            .andExpect(jsonPath("$.players[0].pits[6].stones").value(0))
            .andExpect(jsonPath("$.players[0].playerName").value("PLAYER_ONE"))
            .andExpect(jsonPath("$.players[1]").isNotEmpty())
            .andExpect(jsonPath("$.players[1].pits[0].stones").value(6))
            .andExpect(jsonPath("$.players[1].pits[1].stones").value(6))
            .andExpect(jsonPath("$.players[1].pits[2].stones").value(6))
            .andExpect(jsonPath("$.players[1].pits[3].stones").value(6))
            .andExpect(jsonPath("$.players[1].pits[4].stones").value(6))
            .andExpect(jsonPath("$.players[1].pits[5].stones").value(6))
            .andExpect(jsonPath("$.players[1].pits[6].stones").value(0))
            .andExpect(jsonPath("$.players[1].playerName").value("PLAYER_TWO"));
    }
}
