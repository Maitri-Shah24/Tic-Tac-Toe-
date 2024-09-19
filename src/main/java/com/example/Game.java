package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private final Scanner scanner = new Scanner(System.in);

    public Game() {
    }

    public void initializeGame() {
        System.out.println("Bored! Let's play a Game!! 🎲");
        int size = askGridSize();
        board = new Board(size);

        String playerName = askPlayerName();
        char playerSymbol = askPlayerSymbol();
        player1 = PlayerFactory.createHumanPlayer(playerName, playerSymbol);

        MoveStrategy strategy = askDifficultyLevel();
        char cpuSymbol = (playerSymbol == 'X') ? 'O' : 'X';
        player2 = PlayerFactory.createCpuPlayer(strategy, cpuSymbol);
    }

    public void start() {
        boolean playAgain;

        do {
            resetGame();
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

        System.out.println("Okay, Bye bye! 👋");
    }

    private void resetGame() {
        board.reset();
        player1.resetPositions();
        player2.resetPositions();
    }

    private int askGridSize() {
        int size;
        while (true) {
            try {
                System.out.println("Enter the grid size: (3 for 3x3, 4 for 4x4, etc)");
                size = scanner.nextInt();
                scanner.nextLine();
                if (size >= 3) {
                    return size;
                } else {
                    System.out.println("Grid size must be 3 or larger.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private String askPlayerName() {
        String playerName;
        while (true) {
            System.out.println("So, tell me what's your name?");
            playerName = scanner.nextLine();
            if (!playerName.isEmpty()) {
                return playerName;
            } else {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }
    }

    private char askPlayerSymbol() {
        char playerSymbol;
        while (true) {
            System.out.println("Choose your symbol (X or O):");
            playerSymbol = Character.toUpperCase(scanner.next().charAt(0));
            if (playerSymbol == 'X' || playerSymbol == 'O') {
                return playerSymbol;
            } else {
                System.out.println("Invalid symbol! Please choose X or O.");
            }
        }
    }

    private MoveStrategy askDifficultyLevel() {
        int difficultyLevel;
        while (true) {
            System.out.println("Choose difficulty level for CPU: 1 for EASY and 2 for HARD");
            try {
                difficultyLevel = scanner.nextInt();
                scanner.nextLine();
                if (difficultyLevel == 1) {
                    return MoveStrategyFactory.createStrategy("random");
                } else if (difficultyLevel == 2) {
                    return MoveStrategyFactory.createStrategy("smart");
                } else {
                    System.out.println("Please enter 1 for EASY or 2 for HARD.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private boolean takeTurn(Player player) {
        int pos = player.makeMove(board);
        player.addPosition(pos);
        char symbol = player.getSymbol();
        board.placePiece(pos, symbol);

        System.out.println(player.getName() + "'s Turn");
        board.printBoard();

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
