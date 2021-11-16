package com.arokace.games;

import java.util.Scanner;

public class Menu {

    private int selection;
    Scanner scanner = new Scanner(System.in);

    public Menu() {
        selection = 0;
    }

    public void titleScreen() {
        System.out.println("===================================");
        System.out.println(" -   Chess - CLI and Expanding   - ");
        System.out.println("===================================");
    }

    public void mainMenu() {
        while (selection == 0) {

            System.out.println("===================================");
            System.out.println(" 1 - New Game");
            System.out.println(" 2 - Exit");
            System.out.println("===================================");

            System.out.println("Selection: ");
            selection = scanner.nextInt();

            if (selection == 1) {
                //Run Game
                Game game = new Game();
            } else if ( selection == 2) {
                //Exit Game
                System.out.println("Thank you for playing!");

                //Some kind of kill process command?
            }
            if(selection != 1 && selection != 2){
                System.out.println("Invalid Selection");
                selection = 0;
            }
        }
    }
}
