package com.rebound.kalah.service;

import com.rebound.kalah.dto.request.Distributer;
import com.rebound.kalah.dto.request.NextPlay;
import com.rebound.kalah.dto.request.PlayRequest;
import com.rebound.kalah.dto.response.GameBoardDto;
import com.rebound.kalah.exception.BoardNotFoundException;
import com.rebound.kalah.mapper.BoardMapper;
import com.rebound.kalah.model.entity.GameBoard;
import com.rebound.kalah.model.entity.Line;
import com.rebound.kalah.model.enums.GameStatus;
import com.rebound.kalah.model.enums.PlayerType;
import com.rebound.kalah.repository.GameBoardRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardService {

  private final GameBoardRepository gameBoardRepository;
  private final ValidationService validationService;
  private final DistributionService distributionService;

  private final BoardMapper boardMapper;

  public GameBoardDto create() {
    List<Line> players = new ArrayList<>();
    GameBoard gameBoard = GameBoard.builder().players(players).playerTurn(PlayerType.PLAYER_ONE)
        .status(GameStatus.IN_PROGRESS).build();
    players.add(new Line(gameBoard, PlayerType.PLAYER_ONE));
    players.add(new Line(gameBoard, PlayerType.PLAYER_TWO));
    return boardMapper.mapToGameBoardDto(gameBoardRepository.save(gameBoard));
  }

  public GameBoardDto findById(final Long id) {

    return boardMapper.mapToGameBoardDto(load(id));
  }

  private GameBoard load(final Long id) {
    GameBoard gameBoard = gameBoardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
    gameBoard.getPlayers().forEach(player -> player.setKalah(player.getPits().get(6)));
    return gameBoard;
  }

  public GameBoardDto play(final PlayRequest request) {

    GameBoard board = load(request.getId());

    validationService.validatePlay(request, board);

    Distributer parameter = Distributer.builder()
        .next(NextPlay.builder()
            .index(request.getPitIndex() + 1)
            .player(request.getPlayer())
            .build())
        .stones(PitService.clearPitAndGetStones(request, board))
        .build();

    return boardMapper.mapToGameBoardDto(
        gameBoardRepository.save(distributionService.distributeStones(board, parameter)));
  }


}
