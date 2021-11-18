package com.arokace.games.pieces;

public class King extends Piece{
    public King(char side, int xStartLoc, int yStartLoc) {
    super('K', side);
    x = xStartLoc;
    y = yStartLoc;
}
}
