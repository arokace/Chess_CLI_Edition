package com.arokace.games.pieces;

public class Pawn extends Piece{
    public Pawn(char side, int xStartLoc, int yStartLoc) {
        super('P', side);
        x = xStartLoc;
        y = yStartLoc;
    }
}
