package com.arokace.games.pieces;

public class Queen extends Piece{
    public Queen(char side, int xStartLoc, int yStartLoc) {
        super('Q', side);
        x = xStartLoc;
        y = yStartLoc;
    }
}
