package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        System.out.println("Bored! Let's play a Game!! \uD83C\uDFB2");
        System.out.println("Enter the grid size: (3 for 3x3, 4 for 4x4, etc)");
        int size;
        while (true) {
            try {
                size = scanner.nextInt();
                scanner.nextLine();
                if (size >= 3) {
                    break;
                } else {
                    System.out.println("Grid size must be 3 or larger than 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number");
                scanner.nextLine();
            }
        }
        board = new Board(size);

        System.out.println("So, tell me what's your name?");
        String playerName;
        while (true) {
            playerName = scanner.nextLine();
            if (!playerName.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }

        char playerSymbol;
        while (true) {
            System.out.println("Choose your symbol (X or O):");
            playerSymbol = Character.toUpperCase(scanner.next().charAt(0));
            if (playerSymbol == 'X' || playerSymbol == 'O') {
                break;
            } else {
                System.out.println("Invalid symbol! Please choose X or O.");
            }
        }
        scanner.nextLine();

        // Create human player using the factory method
        player1 = PlayerFactory.createHumanPlayer(playerName, playerSymbol);

        int difficultyLevel;
        while (true) {
            System.out.println("Choose difficulty level for CPU: 1 for EASY and 2 for HARD");
            try {
                difficultyLevel = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (difficultyLevel == 1 || difficultyLevel == 2) {
                    break;
                } else {
                    System.out.println("Please enter 1 for EASY or 2 for HARD.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        char cpuSymbol = (playerSymbol == 'X') ? 'O' : 'X';
        MoveStrategy strategy = (difficultyLevel == 1)
                ? MoveStrategyFactory.createStrategy("random")
                : MoveStrategyFactory.createStrategy("smart");

        // Create CPU player using the factory method
        player2 = PlayerFactory.createCpuPlayer(strategy, cpuSymbol);
    }

    public void start() {
        boolean playAgain = true;

        do {
            board.reset();
            player1.resetPositions();
            player2.resetPositions();
            board.printBoard();

            while (true) {
                if (takeTurn(player1)) {
                    break;
                }
                if (takeTurn(player2)) {
                    break;
                }
            }

            playAgain = askToPlayAgain();

        } while (playAgain);

        System.out.println("Okay, Bye bye! \uD83D\uDC4B\uD83C\uDFFB");
    }

    private boolean takeTurn(Player player) {
        int pos = player.makeMove(board);
        player.addPosition(pos);
        char symbol = player.getSymbol();
        board.placePiece(pos, symbol);

        if (player.equals(player1)) {
            System.out.println("===================" + player1.getName() + "'s Turn=================");
            board.printBoard();
        } else {
            System.out.println("======================CPU's Turn====================");
            board.printBoard();
        }
        return checkWinner(player.getName());
    }

    private boolean checkWinner(String playerName) {
        String result = board.checkWinner(player1.getPositions(), player2.getPositions(), playerName);
        if (!result.isEmpty()) {
            System.out.println(result);
            return true;
        }
        return false;
    }

    private boolean askToPlayAgain() {
        System.out.println("Wanna play Again? (Y/N)");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }
}
