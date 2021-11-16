package com.arokace.games;

public class Piece {

    private int x, y;
    private String type, side;

    public Piece(String type, String side) {
        this.type = type;
        this.side = side;
    }

    public void movePiece(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public String getPiece() {
        return side + type;
    }

    public String getLocation() {
        return x + ", " + y;
    }
}