package com.example;

import java.util.Scanner;

public class HumanPlayerFactory implements PlayerFactory
{
        private final Scanner scanner = new Scanner(System.in);

        @Override
        public Player createPlayer () {
            System.out.println("So, tell me what's your name?");
            String playerName;
            while (true) {
                playerName = scanner.nextLine();
                if (!playerName.isEmpty())
                {
                    break;
                }
                else
                {
                    System.out.println("Name cannot be empty. Please enter a valid name.");
                }
            }

            char playerSymbol;
            while (true)
            {
                System.out.println("Choose your symbol (X or O):");
                playerSymbol = Character.toUpperCase(scanner.next().charAt(0));
                if (playerSymbol == 'X' || playerSymbol == 'O')
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid symbol! Please choose X or O.");
                }
            }
            return new HumanPlayer(playerName, playerSymbol);
        }
}
