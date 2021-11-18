package com.arokace.games;

import java.util.Scanner;

public class Game {

    private Board board = new Board();
    private Scanner scanner = new Scanner(System.in);

    public Game() {
        board.createBoard();
        board.displayBoard();

        while(true) {
            System.out.println("Player turn: " + board.getCurrentPlayer());
            System.out.println("1) Move Piece");
            System.out.println("2) Concede");

            int selection;
            selection = scanner.nextInt();

            if (selection == 1) {
                while(true) {
                    int pieceX, pieceY;
                    board.displayBoard();
                    System.out.println("Enter piece to move");
                    System.out.print("X: ");
                    pieceX = scanner.nextInt();
                    System.out.print("Y: ");
                    pieceY = 7 - scanner.nextInt();
                    System.out.println("X: " + pieceX + " | Y: " + pieceY + " | Current Player: " + board.getCurrentPlayer());

                    if(pieceX >= 0 && pieceX <= 8 && pieceY >= 0 && pieceY <= 8) {
                        if(board.checkValidChoice(pieceX, pieceY)) {
                            //Log/Debug
                            //System.out.println("Hi");

                            board.displayMovableLocations(pieceX, pieceY);
                            break;
                        }
                    } else {
                        System.out.println("Invalid selection");
                    }
                }
            } else if(selection == 2) {
                break;
            } else {
                System.out.println("Invalid Selection");
            }
        }
    }
}
