package com.rebound.kalah.service;

import com.rebound.kalah.dto.request.PlayRequest;
import com.rebound.kalah.exception.EmptyPitException;
import com.rebound.kalah.exception.GameFinishedException;
import com.rebound.kalah.exception.IllegalPlayException;
import com.rebound.kalah.exception.WrongPlayException;
import com.rebound.kalah.model.entity.GameBoard;
import com.rebound.kalah.model.enums.GameStatus;
import com.rebound.kalah.model.enums.PlayerType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidationService {

  public void validatePlay(PlayRequest request, GameBoard board) {
    validateGameFinished(board);
    validatePlayer(board, request.getPlayer());
    validateIsBigPit(request);
    validatePitStones(board, request);
  }

  private void validateIsBigPit(PlayRequest request) {

    if (request.getPitIndex().equals(PitService.BIG_PIT_INDEX)) {
      throw new IllegalPlayException();
    }
  }

  private void validatePitStones(GameBoard board, PlayRequest request) {

    if (PitService.getPit(request, board).getStones() < 1) {
      throw new EmptyPitException();
    }
  }

  private void validatePlayer(GameBoard board, PlayerType player) {

    if (!board.getPlayerTurn().equals(player)) {
      throw new WrongPlayException();
    }
  }

  private void validateGameFinished(GameBoard board) {

    if (board.getStatus().equals(GameStatus.FINISHED)) {
      throw new GameFinishedException();
    }
  }
}
