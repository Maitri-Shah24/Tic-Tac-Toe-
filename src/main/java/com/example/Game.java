package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        System.out.println("Bored! Let's play a Game!! \uD83C\uDFB2");

        int size = getGridSize();
        board = new Board(size);

        // Create player1 by using PlayerFactory and creating instance of HumanPlayerFactory
        PlayerFactory humanFactory = new HumanPlayerFactory();
        player1 = humanFactory.createPlayer();

        // Create player2 by using PlayerFactory and creating instance of CpuPlayerFactory
        char cpuSymbol = (player1.getSymbol() == 'X') ? 'O' : 'X';
        PlayerFactory cpuFactory = new CpuPlayerFactory(cpuSymbol);
        player2 = cpuFactory.createPlayer();
    }

    public void start()
    {
        boolean playAgain = true;

        do {
            board.reset();
            player1.resetPositions();
            player2.resetPositions();
            board.printBoard();

            while (true)
            {
                if (takeTurn(player1))
                {
                    break;
                }
                if (takeTurn(player2))
                {
                    break;
                }
            }

            playAgain = askToPlayAgain();

        } while (playAgain);

        System.out.println("Okay, Bye bye!\uD83D\uDC4B\uD83C\uDFFB");
    }

    private boolean takeTurn(Player player)
    {
        int pos = player.makeMove(board);
        player.addPosition(pos);
        char symbol = player.getSymbol();
        board.placePiece(pos, symbol);

        if (player.equals(player1))
        {
            System.out.println("===================" + player1.getName() + "'s Turn=================");
            board.printBoard();
        }
        else
        {
            System.out.println("======================CPU's Turn====================");
            board.printBoard();
        }
        if (checkWinner(player.getName()))
        {
            return true;
        }
        return false;
    }

    private boolean checkWinner(String playerName)
    {
        String result = board.checkWinner(player1.getPositions(), player2.getPositions(), playerName);
        if (!result.isEmpty())
        {
            System.out.println(result);
            return true;
        }
        return false;
    }

    private boolean askToPlayAgain()
    {
        System.out.println("Wanna play Again? (Y/N)");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }

    private int getGridSize()
    {
        System.out.println("Enter the grid size: (3 for 3x3, 4 for 4x4, etc.)");
        int size;
        while (true)
        {
            try
            {
                size = scanner.nextInt();
                scanner.nextLine();
                if (size >= 3)
                {
                    break;
                }
                else
                {
                    System.out.println("Grid size must be 3 or larger than 3.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }
        return size;
    }
}
