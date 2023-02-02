package com.rebound.kalah.controller;

import com.rebound.kalah.dto.request.PlayRequest;
import com.rebound.kalah.dto.response.GameBoardDto;
import com.rebound.kalah.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = GameController.ROOT_PATH)
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "boards")
public class GameController {

  static final String ROOT_PATH = "/";

  @NonNull
  private BoardService boardService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(
      value = "Create new board",
      response = GameBoardDto.class
  )
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Board created successfully")
  })
  public GameBoardDto create() {
    return boardService.create();
  }


  @GetMapping(path = "/{id}")
  @ApiOperation(
      value = "Find specific board",
      response = GameBoardDto.class
  )
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Board retrieved successfully"),
      @ApiResponse(code = 404, message = "Board not found")
  })
  public GameBoardDto findById(@PathVariable Long id) {
    return boardService.findById(id);
  }

  @PutMapping(path = "/{id}")
  @ApiOperation(
      value = "Play in board",
      response = GameBoardDto.class
  )
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Play made successfully"),
      @ApiResponse(code = 404, message = "Board not found"),
      @ApiResponse(code = 400, message = "Validation issues")
  })
  public GameBoardDto play(@PathVariable Long id,
      @Valid @RequestBody PlayRequest request) {
    return boardService.play(request.withId(id));
  }

}
