package com.arokace.games.pieces;

public class Piece {

    private int x, y;
    private final char type, side;

    public Piece(char type, char side) {
        this.type = type;
        this.side = side;
    }

    public int[] movePiece(int newX, int newY) {
        x = newX;
        y = newY;
        return new int[] {x, y};
    }

    public String getPiece() {
        return String.valueOf(new char[] {side, type});
    }

    public int[] getLocation() {
        return new int[] {x, y};
    }
}