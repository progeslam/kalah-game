package com.rebound.kalah.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rebound.kalah.model.enums.PlayerType;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class PlayerPitDto {

  private List<PitDto> pits;
  private PlayerType playerName;
}
