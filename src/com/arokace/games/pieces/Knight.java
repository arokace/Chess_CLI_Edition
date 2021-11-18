package com.arokace.games.pieces;

public class Knight extends Piece {
    public Knight(char side, int xStartLoc, int yStartLoc) {
        super('N', side);
        x = xStartLoc;
        y = yStartLoc;
    }
}
