
package com.arokace.games;

import com.arokace.games.pieces.*;

import java.util.Arrays;

public class Board {

    // Game Board description
    //
    // Color/Side:
    // b = Black pieces
    // w = White pieces
    //
    // Pieces:
    // R = Rook
    // N = kNight
    // B = Bishop
    // Q = Queen
    // K = King
    // P = Pawn
    //
    // __ = Empty Space
    //
    // Example:
    // bB - Black Bishop
    // wB - White Bishop
    // wK - White King
    // __ - Empty Space

    String[][] gameBoard = new String[8][8];
    Piece[][] pieces = new Piece[2][16]; // 0 - Black | 1 - White

    private int[] selection = new int[2];

    private int currentPlayer;

    public Board() {
        currentPlayer = 1;
    }

    public void createBoard() {
        int length = gameBoard.length;

        for(int i = 0; i < length; i++) {
            if(i % length == 0) {
                pieces[0][0] = new Rook('b', 0, i);
                pieces[0][1] = new Knight('b', 1, i);
                pieces[0][2] = new Bishop('b', 2, i);
                pieces[0][3] = new Queen('b', 3, i);
                pieces[0][4] = new King('b', 4, i);
                pieces[0][5] = new Bishop('b', 5, i);
                pieces[0][6] = new Knight('b', 6, i);
                pieces[0][7] = new Rook('b', 7, i);
            } else if (i % length == 7) {
                pieces[1][0] = new Rook('w', 0, i);
                pieces[1][1] = new Knight('w', 1, i);
                pieces[1][2] = new Bishop('w', 2, i);
                pieces[1][3] = new Queen('w', 3, i);
                pieces[1][4] = new King('w', 4, i);
                pieces[1][5] = new Bishop('w', 5, i);
                pieces[1][6] = new Knight('w', 6, i);
                pieces[1][7] = new Rook('w', 7, i);
            } else {
                for (int j = 0; j < length; j++) {
                    if (i == 1) {
                        pieces[0][8 + j] = new Pawn('b', j, i);
                    } else if (i == 6) {
                        pieces[1][8 + j] = new Pawn('w', j, i);
                    }
                }
            }
        }

        for(int i = 0; i < length; i++) {
            gameBoard[i][0] = " " + pieces[0][i].getPiece() + " ";
            gameBoard[i][1] = " " + pieces[0][8 + i].getPiece() + " ";

            for(int j = 2; j < 7; j++) {
                gameBoard[i][j] = " __ ";
            }

            //gameBoard[i][6] = " " + pieces[1][8 + i].getPiece() + " ";
            gameBoard[i][7] = " " + pieces[1][i].getPiece() + " ";
        }
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    private boolean movableLocations(int x, int y) {
        if(gameBoard[x][y].equals(" __ ")) {
            gameBoard[x][y] = "[__]";
        } else if(gameBoard[x][y].charAt(1) == (currentPlayer == 0 ? 119)) {
            String temp = gameBoard[x][y].substring(1,3);
            gameBoard[x][y] = "[" + temp + "]";
            return true;
        }
        return false;
    }

    private int findPiece() {
        for(int i = 0; i < pieces[currentPlayer].length; i++) {
            if (pieces[currentPlayer][i].getX() == selection[0] && pieces[currentPlayer][i].getY() == selection[1]) {
                pieces[currentPlayer][i].setSelected(true);
                return i;
            }
        }

        return -1;
    }

    private void selectPiece() {
        int pieceIndex = findPiece();

        if(pieceIndex == -1) {
            System.out.println("Invalid Selection!");
        } else if (pieceIndex >= 0 && pieceIndex <= pieces[currentPlayer].length) {
            int x = pieces[currentPlayer][pieceIndex].getX();
            int y = pieces[currentPlayer][pieceIndex].getY();
            char type = pieces[currentPlayer][pieceIndex].getType();

            switch(type) {
                case 'R' -> rookMoves(x, y);
                case 'N' -> knightMoves(x, y);
                case 'B' -> bishopMoves(x, y);
                case 'Q' -> queenMoves(x, y);
                case 'K' -> kingMoves(x, y);
                case 'P' -> pawnMoves(x, y);
                default -> {
                    System.out.println("Error!  Something went wrong!");
                    System.out.println("Piece Type: " + type + " | x: " + x + " | y: " + y);
                }
            }
        }
    }

    public void displayMovableLocations(int x, int y) {
        String[][] temp = new String[8][8];

        selection[0] = x;
        selection[1] = y;

        for(int i = 0; i < gameBoard.length; i++) {
            System.arraycopy(gameBoard[i], 0, temp[i], 0, gameBoard.length);
        }

        selectPiece();

        displayBoard();
        gameBoard = temp;
    }

    private void rookMoves(int x, int y) {
        System.out.println("Rook Moves");

        for(int i = 0; i < gameBoard.length - x; i++) {
            if(movableLocations(x + i, y)) {
                break;
            }
        }

        for(int i = 0; i <= x; i++) {
            if(movableLocations(x - i, y)) {
                break;
            }
        }

        for(int i = 0; i < gameBoard.length - y; i++) {
            if(movableLocations(x, y + i)) {
                break;
            }
        }

        for(int i = 0; i <= y; i++) {
            if(movableLocations(x, y - i)) {
                break;
            }
        }
    }

    private void knightMoves(int x, int y) {
        System.out.println("Knight Moves");

        if(x + 2 <= 7) {
            if(y + 1 <= 7) {
                movableLocations(x + 2, y + 1);
            }

            if(y - 1 >= 0) {
                movableLocations(x + 2, y - 1);
            }
        }

        if(x - 2 >= 0) {
            if(y + 1 <= 7) {
                movableLocations(x - 2, y + 1);
            }

            if(y - 1 >= 0) {
                movableLocations(x - 2, y - 1);
            }

        }

        if(y + 2 <= 7) {
            if(x + 1 <= 7) {
                movableLocations(x + 1, y + 2);
            }

            if(x - 1 >= 0) {
                movableLocations(x - 1, y + 2);
            }

        }

        if(y - 2 >= 0) {
            if(x + 1 <= 7) {
                movableLocations(x + 1, y - 2);
            }

            if(x - 1 >= 0) {
                movableLocations(x - 1, y - 2);
            }

        }
    }

    private void bishopMoves(int x, int y) {
        System.out.println("Bishop Moves");

        for(int i = 0; i < gameBoard.length - x ; i++) {
            if(y + i <= gameBoard.length - 1) {
                if(movableLocations(x + i, y + i)) {
                    break;
                }
            }
        }

        for(int i = 0; i <= x; i++) {
            if(y + i <= gameBoard.length - 1) {
                if(movableLocations(x - i, y + i)) {
                    break;
                }
            }
        }

        for(int i = 0; i < gameBoard.length - x; i++) {
            if(y - i >= 0) {
                if(movableLocations(x + i, y - i)) {
                    break;
                }
            }
        }

        for(int i = 0; i <= x; i++) {
            if(y - i >= 0) {
                if(movableLocations(x - i, y - i)) {
                    break;
                }
            }
        }
    }

    private void queenMoves(int x, int y) {
        System.out.println("Queen Moves");

        rookMoves(x, y);
        bishopMoves(x, y);
    }

    private void kingMoves(int x, int y) {
        boolean xInBoundsPositive = x + 1 <= 7;
        boolean xInBoundsNegative = x - 1 >= 0;
        boolean yInBoundsPositive = y + 1 <= 7;
        boolean yInBoundsNegative = y - 1 >= 0;

        if(xInBoundsPositive) {
            movableLocations(x + 1, y);
        }
        if(xInBoundsPositive && yInBoundsPositive) {
            movableLocations(x + 1, y + 1);
        }
        if(yInBoundsPositive) {
            movableLocations(x, y + 1);
        }
        if(yInBoundsPositive && xInBoundsNegative) {
            movableLocations(x - 1, y + 1);
        }
        if(xInBoundsNegative) {
            movableLocations(x - 1, y);
        }
        if(xInBoundsNegative && yInBoundsNegative) {
            movableLocations(x - 1, y - 1);
        }
        if(yInBoundsNegative) {
            movableLocations(x, y - 1);
        }
        if(yInBoundsNegative && xInBoundsPositive) {
            movableLocations(x + 1, y - 1);
        }
    }

    private void pawnMoves(int x, int y) {

    }

    public void displayBoard() {
        for(int i = 0; i < gameBoard.length; i++) {
            StringBuilder row = new StringBuilder((gameBoard.length - i) + " | ");
            for (String[] strings : gameBoard) {
                row.append(strings[i]);
            }
            System.out.println(row);
        }
        System.out.println("     A   B   C   D   E   F   G   H");
    }

//    public void movePiece(int currentX, int currentY, int newX, int newY, String currentPlayer) {
//        String newLocation = gameBoard[newX][newY];
//        String currentLocation = gameBoard[currentX][currentY];
//
//        if(!currentLocation.startsWith(currentPlayer)) {
//
//        } else if(newLocation.equals(" __ ") || !newLocation.startsWith(currentPlayer)) {
//            gameBoard[newX][newY] = currentLocation;
//            gameBoard[currentX][currentY] = " __ ";
//        }
//    }
}
