package com.rebound.kalah.dto.request;

import com.rebound.kalah.model.enums.PlayerType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@ApiModel("Play")
public class PlayRequest {

    @With
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @ApiModelProperty(example = "PLAYER_TWO")
    private PlayerType player;

    @Max(5)
    @Min(0)
    @NotNull
    @ApiModelProperty(example = "4")
    private Integer pitIndex;

}
