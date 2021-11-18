
package com.arokace.games;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
    private char currentPlayer, enemy;

    public Board() {
        currentPlayer = 'w';
        enemy = 'b';
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

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean checkValidChoice(int x, int y) {
        //Log/Debug
        //System.out.println("X: " + x + " | Y: " + y + " | S Player: " + gameBoard[x][y].charAt(1));
        return gameBoard[x][y].charAt(1) == currentPlayer;
    }

    private boolean movableLocations(int x, int y) {
        if(gameBoard[x][y].equals(" __ ")) {
            gameBoard[x][y] = "[__]";
        } else if(gameBoard[x][y].charAt(1) == enemy) {
            String temp = gameBoard[x][y].substring(1,3);
            gameBoard[x][y] = "[" + temp + "]";
            return true;
        }
        return false;
    }

    private char getPieceType(int x, int y) {
        return gameBoard[x][y].charAt(2);
    }

    public void displayMovableLocations(int x, int y) {
        String[][] temp = new String[8][8];
        for(int i = 0; i < gameBoard.length; i++) {
            System.arraycopy(gameBoard[i], 0, temp[i], 0, gameBoard.length);
        }

        switch (getPieceType(x, y)) {
            case 'R' -> rookMoves(x, y);
            case 'N' -> knightMoves(x, y);
            case 'B' -> bishopMoves(x, y);
            case 'Q' -> queenMoves(x, y);
            default -> {
                System.out.println("Error!  Something went wrong!");
                System.out.println("Piece Type: " + getPieceType(x, y) + " | x: " + x + " | y: " + y);
            }
        }


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
