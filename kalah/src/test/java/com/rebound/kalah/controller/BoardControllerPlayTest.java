package com.rebound.kalah.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebound.kalah.dto.request.PlayRequest;
import com.rebound.kalah.dto.response.GameBoardDto;
import com.rebound.kalah.model.enums.PlayerType;
import com.rebound.kalah.repository.GameBoardRepository;
import com.rebound.kalah.service.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerPlayTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  private GameBoardRepository gameBoardRepository;

  @Autowired
  private BoardService boardService;

  @Test
  public void whenPlayerOnePlayAndDoNotEndInBigPit_shouldNextBePlayerTwoAndReturnOk()
      throws Exception {
    GameBoardDto board = boardService.create();

    mockMvc.perform(
            put("/{id}", board.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(
                    PlayRequest.builder()
                        .player(PlayerType.PLAYER_ONE)
                        .pitIndex(4)
                        .build())
                )
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.playerTurn").value("PLAYER_TWO"))
        .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
        .andExpect(jsonPath("$.players[0]").isNotEmpty())
        .andExpect(jsonPath("$.players[0].pits[0].stones").value(6))
        .andExpect(jsonPath("$.players[0].pits[1].stones").value(6))
        .andExpect(jsonPath("$.players[0].pits[2].stones").value(6))
        .andExpect(jsonPath("$.players[0].pits[3].stones").value(6))
        .andExpect(jsonPath("$.players[0].pits[4].stones").value(0))
        .andExpect(jsonPath("$.players[0].pits[5].stones").value(7))
        .andExpect(jsonPath("$.players[0].pits[6].stones").value(1))
        .andExpect(jsonPath("$.players[0].playerName").value("PLAYER_ONE"))
        .andExpect(jsonPath("$.players[1]").isNotEmpty())
        .andExpect(jsonPath("$.players[1].pits[0].stones").value(7))
        .andExpect(jsonPath("$.players[1].pits[1].stones").value(7))
        .andExpect(jsonPath("$.players[1].pits[2].stones").value(7))
        .andExpect(jsonPath("$.players[1].pits[3].stones").value(7))
        .andExpect(jsonPath("$.players[1].pits[4].stones").value(6))
        .andExpect(jsonPath("$.players[1].pits[5].stones").value(6))
        .andExpect(jsonPath("$.players[1].pits[6].stones").value(0))
        .andExpect(jsonPath("$.players[1].playerName").value("PLAYER_TWO"));
  }

  @Test
  public void whenEndsInBigPit_shouldSamePlayerBeNextAndReturnOk() throws Exception {
    GameBoardDto board = boardService.create();

    mockMvc.perform(
            put("/{id}", board.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(
                    PlayRequest.builder()
                        .player(PlayerType.PLAYER_ONE)
                        .pitIndex(0)
                        .build())
                )
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.playerTurn").value("PLAYER_ONE"))
        .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
        .andExpect(jsonPath("$.players[0]").isNotEmpty())
        .andExpect(jsonPath("$.players[0].pits[0].stones").value(0))
        .andExpect(jsonPath("$.players[0].pits[1].stones").value(7))
        .andExpect(jsonPath("$.players[0].pits[2].stones").value(7))
        .andExpect(jsonPath("$.players[0].pits[3].stones").value(7))
        .andExpect(jsonPath("$.players[0].pits[4].stones").value(7))
        .andExpect(jsonPath("$.players[0].pits[5].stones").value(7))
        .andExpect(jsonPath("$.players[0].pits[6].stones").value(1))
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

  @Test
  public void whenMatchNotFound_shouldReturnNotFound() throws Exception {
    mockMvc.perform(
        put("/{id}", "99")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(
                PlayRequest.builder()
                    .player(PlayerType.PLAYER_ONE)
                    .pitIndex(4)
                    .build())
            )
    ).andExpect(status().isNotFound());
  }

  @Test
  public void whenWrongPlay_shouldReturnBadRequest() throws Exception {
    GameBoardDto board = boardService.create();

    mockMvc.perform(
        put("/{id}", board.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(
                PlayRequest.builder()
                    .player(PlayerType.PLAYER_TWO)
                    .pitIndex(4)
                    .build())
            )
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void whenNegativeIndex_shouldReturnBadRequest() throws Exception {
    GameBoardDto board = boardService.create();

    mockMvc.perform(
        put("/{id}", board.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(
                PlayRequest.builder()
                    .player(PlayerType.PLAYER_ONE)
                    .pitIndex(-1)
                    .build())
            )
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void whenEmptyPlayer_shouldReturnBadRequest() throws Exception {
    GameBoardDto board = boardService.create();

    mockMvc.perform(
        put("/{id}", board.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(
                PlayRequest.builder()
                    .pitIndex(0)
                    .build())
            )
    ).andExpect(status().isBadRequest());
  }

}
