package com.rebound.kalah.model.entity;

import com.rebound.kalah.model.enums.GameStatus;
import com.rebound.kalah.model.enums.PlayerType;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameBoard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany(mappedBy = "gameBoard", cascade = CascadeType.ALL)
  private List<Line> players;

  private String winnerPlayer;

  @Enumerated(EnumType.STRING)
  private GameStatus status;

  @Enumerated(EnumType.STRING)
  private PlayerType playerTurn;

}
