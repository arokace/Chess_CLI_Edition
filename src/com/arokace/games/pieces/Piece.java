package com.arokace.games.pieces;

public class Piece {

    protected int x, y;
    private final char type, side;
    protected boolean selected;
    protected boolean alive;

    public Piece(char type, char side) {
        this.type = type;
        this.side = side;
        selected = false;
        alive = true;
    }

    public int[] movePiece(int newX, int newY) {
        x = newX;
        y = newY;
        return new int[] {x, y};
    }

    public String getPiece() {
        return String.valueOf(new char[] {side, type});
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public char getSide() {
        return side;
    }

    public char getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //    public int[] getLocation() {
//        return new int[] {x, y};
//    }
}