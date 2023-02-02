package com.rebound.kalah.dto.request;


import com.rebound.kalah.model.enums.PlayerType;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@With
@Getter
@Builder
public class NextPlay {
    private Integer index;
    private PlayerType player;
}
