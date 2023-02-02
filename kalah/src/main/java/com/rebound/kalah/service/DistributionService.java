package com.rebound.kalah.service;

import com.rebound.kalah.dto.request.Distributer;
import com.rebound.kalah.model.entity.GameBoard;
import com.rebound.kalah.model.entity.Line;
import com.rebound.kalah.model.entity.Pit;
import com.rebound.kalah.model.enums.GameStatus;
import com.rebound.kalah.model.enums.PlayerType;
import java.util.List;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DistributionService {

  public GameBoard distributeStones(GameBoard board, Distributer distributer) {

    if (distributer.getStones() > 0) {
      updatePitStones(board, distributer);
      return distributeStones(board, distributer.next());
    }

    if (endedInBigPit(distributer)) {
      this.changeNext(board);
    }

    if (this.hasCleanLine(board.getPlayers())) {
      this.finishGame(board);
    }

    return board;
  }

  private void changeNext(GameBoard board) {
    if (board.getPlayerTurn().equals(PlayerType.PLAYER_ONE)) {
      board.setPlayerTurn(PlayerType.PLAYER_TWO);
    } else {
      board.setPlayerTurn(PlayerType.PLAYER_ONE);
    }
  }

  private void updatePitStones(GameBoard board, Distributer distributer) {

    Pit pit = PitService.getPit(distributer.getNext(), board);

    if (distributer.getStones() == 1 && pit.getStones().equals(0) && !pit.isBigPit()) {
      pit.setStones(PitService.captureStones(board, distributer));
    } else {
      pit.setStones(pit.getStones() + 1);
    }
  }

  private boolean hasCleanLine(List<Line> players) {

    return hasCleanLine(players.get(0)) || hasCleanLine(players.get(1));
  }

  private boolean hasCleanLine(Line line) {

    return line.getPits().stream().filter(Predicate.not(Pit::isBigPit))
        .allMatch(pit -> pit.getStones().equals(0));
  }

  private boolean endedInBigPit(Distributer distributer) {

    return !PitService.FIRST_PIT_INDEX.equals(distributer.getNext().getIndex());
  }

  public void finishGame(GameBoard board) {

    List<Line> players = board.getPlayers();
    players.forEach(Line::finish);
    board.setStatus(GameStatus.FINISHED);

    board.setWinnerPlayer(defineWinner(players));
  }

  private String defineWinner(List<Line> players) {

    if (players.get(0).getKalah().getStones() > players.get(1).getKalah().getStones()) {
      return players.get(0).getPlayerName().name();
    }

    if (players.get(0).getKalah().getStones() < players.get(1).getKalah().getStones()) {
      return players.get(1).getPlayerName().name();
    }

    return "Draw";
  }

}
