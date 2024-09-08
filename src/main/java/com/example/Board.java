package com.example;

import java.util.Arrays;
import java.util.List;

public class Board
{
    private char[][] board;

    public Board() {
        reset();
    }

    public void reset() {
        board = new char[][]{
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
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

    public void placePiece(int pos, char symbol) {
        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
        }
    }

    public boolean isPositionTaken(int pos) {
        char[] symbols = {'X', 'O'};
        switch (pos) {
            case 1:
                return containsSymbol(board[0][0], symbols);
            case 2:
                return containsSymbol(board[0][2], symbols);
            case 3:
                return containsSymbol(board[0][4], symbols);
            case 4:
                return containsSymbol(board[2][0], symbols);
            case 5:
                return containsSymbol(board[2][2], symbols);
            case 6:
                return containsSymbol(board[2][4], symbols);
            case 7:
                return containsSymbol(board[4][0], symbols);
            case 8:
                return containsSymbol(board[4][2], symbols);
            case 9:
                return containsSymbol(board[4][4], symbols);
            default:
                return false;
        }
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
