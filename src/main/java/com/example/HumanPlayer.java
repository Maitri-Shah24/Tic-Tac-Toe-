package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name, char symbol)
    {
        super(name,symbol);
    }

    public int makeMove(Board board) {
        int pos;
        int boardSize = board.getSize();
        int maxPos = boardSize * boardSize;
        while (true) {
            try {
                System.out.println("Enter your move (1-" + (board.getSize() * board.getSize()) + "):");
                pos = scanner.nextInt();

                if (pos < 1 || pos > maxPos) {
                    System.out.println("Invalid position! Please enter a number between 1 and 9.");
                    continue;
                }

                if (board.isPositionTaken(pos)) {
                    System.out.println("That position is already taken! Please choose another one.");
                    continue;
                }

                return pos;

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
        }
    }
}

