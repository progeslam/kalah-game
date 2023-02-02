package com.rebound.kalah.dto.request;

import com.rebound.kalah.model.enums.PlayerType;
import com.rebound.kalah.service.PitService;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@With
@Getter
@Builder
public class Distributer {

    private NextPlay next;
    private Integer stones;

    public Distributer next() {

        return this.withNext(getUpdatedNext()).withStones(getUpdatedStones());
    }

    public NextPlay getUpdatedNext() {

        if (next.getIndex().equals(PitService.BIG_PIT_INDEX)) {
            return NextPlay.builder()
                .player(next.getPlayer().equals(PlayerType.PLAYER_ONE) ? PlayerType.PLAYER_TWO : PlayerType.PLAYER_ONE)
                .index(0)
                .build();
        } else {
            return next.withIndex(next.getIndex() + 1);
        }
    }

    public Integer getUpdatedStones() {

        return this.stones - 1;
    }

}
