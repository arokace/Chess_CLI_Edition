package com.arokace.games.pieces;

public class Rook extends Piece {
    public Rook(char side, int xStartLoc, int yStartLoc) {
        super('R', side);
        x = xStartLoc;
        y = yStartLoc;
    }
}
