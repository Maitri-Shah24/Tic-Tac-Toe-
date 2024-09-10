package com.example;

import java.util.Arrays;
import java.util.List;

public class Board
{

    private static final char EMPTY_SPACE = ' ';
    private static final char VERTICAL_DIVIDER = '|';
    private static final char HORIZONTAL_DIVIDER = '-';
    private static final char INTERSECTION = '+';
    private char[][] board;

    public Board() {
        reset();
    }

    public void reset() {
        board = new char[][]{
                {EMPTY_SPACE,VERTICAL_DIVIDER,EMPTY_SPACE,VERTICAL_DIVIDER,EMPTY_SPACE},
                {HORIZONTAL_DIVIDER,INTERSECTION,HORIZONTAL_DIVIDER,INTERSECTION,HORIZONTAL_DIVIDER},
                {EMPTY_SPACE,VERTICAL_DIVIDER,EMPTY_SPACE,VERTICAL_DIVIDER,EMPTY_SPACE},
                {HORIZONTAL_DIVIDER,INTERSECTION,HORIZONTAL_DIVIDER,INTERSECTION,HORIZONTAL_DIVIDER},
                {EMPTY_SPACE,VERTICAL_DIVIDER,EMPTY_SPACE,VERTICAL_DIVIDER,EMPTY_SPACE}
        };
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public int[] getBoardPosition(int pos) {
        switch (pos) {
            case 1:
                return new int[]{0,0};
            case 2:
                return new int[]{0,2};
            case 3:
                return new int[]{0,4};
            case 4:
                return new int[]{2,0};
            case 5:
                return new int[]{2,2};
            case 6:
                return new int[]{2,4};
            case 7:
                return new int[]{4,0};
            case 8:
                return new int[]{4,2};
            case 9:
                return new int[]{4,4};
            default:
                return null;
        }
    }

    public void placePiece(int pos, char symbol) {
        int[] boardPosition = getBoardPosition(pos);
        board[boardPosition[0]][boardPosition[1]] = symbol;
    }

    public boolean isPositionTaken(int pos) {
        int[] boardPosition = getBoardPosition(pos);
        char cell = board[boardPosition[0]][boardPosition[1]];
        return containsSymbol(cell, new char[]{'X','O'});
    }

    private boolean containsSymbol(char c, char[] symbols) {
        for (char symbol : symbols) {
            if (c == symbol) return true;
        }
        return false;
    }

    public String checkWinner(List<Integer> playerPositions, List<Integer> cpuPositions, String playerName) {
        List<List<Integer>> winningCombinations = Arrays.asList(
                Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9),
                Arrays.asList(1, 4, 7), Arrays.asList(2, 5, 8), Arrays.asList(3, 6, 9),
                Arrays.asList(1, 5, 9), Arrays.asList(3, 5, 7)
        );

        for (List<Integer> combination : winningCombinations) {
            if (playerPositions.containsAll(combination)) {
                return "Congratulations " + playerName + " you \uD83E\uDEF5 won!! \uD83C\uDFC6";
            } else if (cpuPositions.containsAll(combination)) {
                return "Computer wins! Sorry (\u2060 \u2060⚈̥\u2060⌢\u2060⚈̥\u2060)" + playerName;
            }
        }

        if (playerPositions.size() + cpuPositions.size() == 9) {
            return "TIE!";
        }
        return "";
    }
}
