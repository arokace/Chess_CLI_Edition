package com.arokace.games.pieces;

public class Bishop extends Piece{
    public Bishop(char side, int xStartLoc, int yStartLoc) {
        super('B', side);
        x = xStartLoc;
        y = yStartLoc;
    }
}
