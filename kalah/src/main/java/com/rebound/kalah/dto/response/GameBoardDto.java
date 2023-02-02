package com.rebound.kalah.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rebound.kalah.model.enums.GameStatus;
import com.rebound.kalah.model.enums.PlayerType;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class GameBoardDto {

  private Long id;

  private List<PlayerPitDto> players;

  private String winnerPlayer;

  private GameStatus status;

  private PlayerType playerTurn;

}
