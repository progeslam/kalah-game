package com.rebound.kalah.service;

import com.rebound.kalah.dto.request.Distributer;
import com.rebound.kalah.dto.request.NextPlay;
import com.rebound.kalah.dto.request.PlayRequest;
import com.rebound.kalah.model.entity.GameBoard;
import com.rebound.kalah.model.entity.Line;
import com.rebound.kalah.model.entity.Pit;
import com.rebound.kalah.model.enums.PlayerType;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;

@Service
public class PitService {
    public static final Integer BIG_PIT_INDEX = 6;
    public static final Integer FIRST_PIT_INDEX = 0;
    public static Integer clearPitAndGetStones(PlayRequest request, GameBoard board) {
        Pit pit = getPit(request, board);

        Integer stones = pit.getStones();
        pit.setStones(0);
        return stones;
    }

    public static Pit getPit(NextPlay nextPlay, GameBoard board) {
        if (PlayerType.PLAYER_ONE.equals(nextPlay.getPlayer())) {
            return BIG_PIT_INDEX.equals(nextPlay.getIndex()) ?
                getPlayerByName(board, PlayerType.PLAYER_ONE).getKalah() :
                getPit(getPlayerByName(board, PlayerType.PLAYER_ONE), nextPlay.getIndex());
        } else {
            return BIG_PIT_INDEX.equals(nextPlay.getIndex()) ?
                getPlayerByName(board, PlayerType.PLAYER_TWO).getKalah() :
                getPit(getPlayerByName(board, PlayerType.PLAYER_TWO), nextPlay.getIndex());
        }
    }

    public static Pit getPit(PlayRequest request, GameBoard board) {

        Line playerOne = getPlayerByName(board, PlayerType.PLAYER_ONE);
        Line playerTwo = getPlayerByName(board, PlayerType.PLAYER_TWO);

        return PlayerType.PLAYER_ONE.equals(request.getPlayer()) ?
            getPit(playerOne, request.getPitIndex()) :
            getPit(playerTwo, request.getPitIndex());
    }

    private static Pit getPit(Line player, Integer pitIndex) {

        return player.getPits().get(pitIndex);
    }

    public static Integer captureStones(GameBoard board, Distributer distributer) {

        Pit mirrored = getMirrored(distributer, board);
        Integer stones = mirrored.getStones() + 1;
        mirrored.setStones(0);

        return stones;
    }

    private static Pit getMirrored(Distributer distributer, GameBoard board) {

        if (PlayerType.PLAYER_ONE.equals(distributer.getNext().getPlayer())) {
            return getPit(
                NextPlay.builder()
                    .index(PitService.getMirroredIndex(distributer.getNext().getIndex()))
                    .player(PlayerType.PLAYER_TWO)
                    .build(),
                board);
        } else {
            return getPit(
                NextPlay.builder()
                    .index(PitService.getMirroredIndex(distributer.getNext().getIndex()))
                    .player(PlayerType.PLAYER_ONE)
                    .build(),
                board);
        }
    }

    private static Line getPlayerByName(GameBoard board, PlayerType playerName){
        return board.getPlayers().stream().filter(player -> player.getPlayerName().equals(playerName)).findFirst().get();
    }
    private static Integer getMirroredIndex(Integer index) {
        return 5 - index;
    }

}
