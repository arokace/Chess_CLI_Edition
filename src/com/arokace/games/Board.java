
package com.arokace.games;

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

    public Board() {

    }

    public void createBoard() {
        int length = gameBoard.length;

        for(int i = 0; i < length; i++) {
            if(i % length == 0) {
                gameBoard[0][i] = " bR ";
                gameBoard[1][i] = " bN ";
                gameBoard[2][i] = " bB ";
                gameBoard[3][i] = " bQ ";
                gameBoard[4][i] = " bK ";
                gameBoard[5][i] = " bB ";
                gameBoard[6][i] = " bN ";
                gameBoard[7][i] = " bR ";
            } else if (i % length == 7) {
                gameBoard[0][i] = " wR ";
                gameBoard[1][i] = " wN ";
                gameBoard[2][i] = " wB ";
                gameBoard[3][i] = " wQ ";
                gameBoard[4][i] = " wK ";
                gameBoard[5][i] = " wB ";
                gameBoard[6][i] = " wN ";
                gameBoard[7][i] = " wR ";
            } else {
                for (int j = 0; j < length; j++) {
                    if (i == 1) {
                        gameBoard[j][i] = " bP ";
                    } else if (i == 6) {
                        gameBoard[j][i] = " __ ";
                    } else {
                        gameBoard[j][i] = " __ ";
                    }
                }
            }
        }
    }

    public boolean checkValidChoice(int x, int y, String currentPlayer) {
        //Log/Debug
        //System.out.println("X: " + x + " | Y: " + y + " | S Player: " + gameBoard[x][y].charAt(1));
        return gameBoard[x][y].substring(1,2).equals(currentPlayer);
    }

    private boolean checkForEmptySpace(int x, int y) {
        return gameBoard[x][y].equals(" __ ");
    }

    private String getPieceType(int x, int y) {
        return gameBoard[x][y].substring(2,3);
    }

    public void displayMovableLocations(int x, int y) {
        if(getPieceType(x, y).equals("R")) {
            rookMoves(x, y);
        }
    }

    private void rookMoves(int x, int y) {
        System.out.println("Rook Moves");

        for(int i = 0; i < gameBoard.length - x; i++) {
            if(checkForEmptySpace(x + i, y)) {
                gameBoard[x+i][y] = "[__]";
            } else if(gameBoard[x+i][y].charAt(1) == 'b') {
                String temp = gameBoard[x+i][y].substring(1,3);
                gameBoard[x+i][y] = "[" + temp + "]";
                break;
            }
        }

        for(int i = 0; i <= x; i++) {
            if(checkForEmptySpace(x - i, y)) {
                gameBoard[x-i][y] = "[__]";
            } else if(gameBoard[x-i][y].charAt(1) == 'b') {
                String temp = gameBoard[x-i][y].substring(1,3);
                gameBoard[x-i][y] = "[" + temp + "]";
                break;
            }
        }

        for(int i = 0; i < gameBoard.length - x; i++) {
            if(checkForEmptySpace(x + i, y)) {
                gameBoard[x+i][y] = "[__]";
            } else if(gameBoard[x+i][y].charAt(1) == 'b') {
                String temp = gameBoard[x+i][y].substring(1,3);
                gameBoard[x+i][y] = "[" + temp + "]";
                break;
            }
        }

        for(int i = 0; i <= y; i++) {
            if(checkForEmptySpace(x, y - i)) {
                gameBoard[x][y-i] = "[__]";
            } else if(gameBoard[x][y-i].charAt(1) == 'b') {
                String temp = gameBoard[x][y-i].substring(1,3);
                gameBoard[x][y-i] = "[" + temp + "]";
                break;
            }
        }

        displayBoard();
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

    public void movePiece(int currentX, int currentY, int newX, int newY, String currentPlayer) {
        String newLocation = gameBoard[newX][newY];
        String currentLocation = gameBoard[currentX][currentY];

        if(!currentLocation.startsWith(currentPlayer)) {

        } else if(newLocation.equals(" __ ") || !newLocation.startsWith(currentPlayer)) {
            gameBoard[newX][newY] = currentLocation;
            gameBoard[currentX][currentY] = " __ ";
        }
    }
}
