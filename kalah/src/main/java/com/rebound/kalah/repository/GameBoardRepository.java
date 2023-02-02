package com.rebound.kalah.repository;

import com.rebound.kalah.model.entity.GameBoard;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameBoardRepository extends CrudRepository<GameBoard, Long> {

}
