package com.rebound.kalah.model.entity;

import com.rebound.kalah.model.enums.PlayerType;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Line {

    public static final int PIT_INIT_VALUE = 4;
    public static final int BIG_PIT_INIT_VALUE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private GameBoard gameBoard;

    @Enumerated(EnumType.STRING)
    private PlayerType playerName;

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
    private List<Pit> pits;

    @Transient
    private Pit kalah;

    public Line(GameBoard gameBoard, PlayerType player) {
        this.pits = IntStream.range(0, 7).mapToObj(i -> new Pit(this)).collect(Collectors.toList());
        this.kalah = this.pits.get(6);
        this.kalah.setBigPit(Boolean.TRUE);
        this.kalah.setStones(0);
        this.playerName = player;
        this.gameBoard = gameBoard;
    }

    public void finish() {
        this.getKalah().setStones(this.getKalah().getStones() + this.pits.stream().filter(Predicate.not(Pit::isBigPit)).mapToInt(Pit::getStones).sum());
        this.pits.forEach(pit -> {
            if(!pit.isBigPit())
                pit.setStones(0);
        });
    }
}
