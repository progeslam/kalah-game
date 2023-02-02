package com.rebound.kalah.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer stones;

  @ManyToOne
  @JoinColumn(name = "LINE_ID")
  private Line line;

  private boolean bigPit;

  public Pit(Line line) {
    this.stones = 6;
    this.bigPit = false;
    this.line = line;
  }
}
