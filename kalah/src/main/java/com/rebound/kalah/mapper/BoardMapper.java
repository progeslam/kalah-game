package com.rebound.kalah.mapper;

import com.rebound.kalah.dto.response.GameBoardDto;
import com.rebound.kalah.model.entity.GameBoard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {


  GameBoardDto mapToGameBoardDto(GameBoard gameBoard);
}
