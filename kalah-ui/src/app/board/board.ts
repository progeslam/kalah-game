export interface Board {
    id: number;
    players: Player[];
    status: string;
    playerTurn: string;
    winnerPlayer: string;
}


export interface Pit {
    stones: number;
}

export interface Player {
    pits: Pit[];
    playerName: string;
}