package com.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final char EMPTY_SPACE = ' ';
    private static final char VERTICAL_DIVIDER = '|';
    private static final char HORIZONTAL_DIVIDER = '-';
    private static final char INTERSECTION = '+';
    private char[][] board;
    @Getter
    private int size;

    public Board(int size) {
        this.size = size;
        reset();
    }

    public void reset() {
        board = new char[2 * size - 1][2 * size - 1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    board[i][j] = EMPTY_SPACE;
                } else if (i % 2 == 0) {
                    board[i][j] = VERTICAL_DIVIDER;
                } else if (j % 2 == 0) {
                    board[i][j] = HORIZONTAL_DIVIDER;
                } else {
                    board[i][j] = INTERSECTION;
                }
            }
        }
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
        int row = (pos - 1) / size;
        int col = (pos - 1) % size;
        return new int[]{2 * row, 2 * col};
    }

    public void placePiece(int pos, char symbol) {
        int[] boardPosition = getBoardPosition(pos);
        board[boardPosition[0]][boardPosition[1]] = symbol;
    }

    public boolean isPositionTaken(int pos) {
        int[] boardPosition = getBoardPosition(pos);
        return boardPosition[0] >= 0 && boardPosition[0] < board.length &&
                boardPosition[1] >= 0 && boardPosition[1] < board[0].length &&
                board[boardPosition[0]][boardPosition[1]] != EMPTY_SPACE;
    }

    public String checkWinner(List<Integer> playerPositions, List<Integer> cpuPositions, String playerName) {
        // Check rows
        for (int i = 0; i < size; i++) {
            String result = checkAndReturnWinner(getRowPositions(i), playerPositions, cpuPositions, playerName);
            if (!result.isEmpty()) return result;
        }

        // Check columns
        for (int i = 0; i < size; i++) {
            String result = checkAndReturnWinner(getColumnPositions(i), playerPositions, cpuPositions, playerName);
            if (!result.isEmpty()) return result;
        }

        // Check diagonals
        String result = checkAndReturnWinner(getDiagonalPositions(true), playerPositions, cpuPositions, playerName);
        if (!result.isEmpty()) return result;

        result = checkAndReturnWinner(getDiagonalPositions(false), playerPositions, cpuPositions, playerName);
        if (!result.isEmpty()) return result;

        // If all positions are taken and there's no winner, it's a tie
        if (playerPositions.size() + cpuPositions.size() == size * size) {
            return "TIE!";
        }

        return "";
    }

    private String checkAndReturnWinner(List<Integer> positions, List<Integer> playerPositions, List<Integer> cpuPositions, String playerName) {
        if (isWinningLine(positions, playerPositions)) {
            return "Congratulations " + playerName + " you \uD83E\uDEF5 won!! \uD83C\uDFC6";
        } else if (isWinningLine(positions, cpuPositions)) {
            return "Computer wins! Sorry (\u2060 \u2060⚈̥\u2060⌢\u2060⚈̥\u2060)" + playerName;
        }
        return "";
    }

    private boolean isWinningLine(List<Integer> linePositions, List<Integer> playerPositions) {
        return playerPositions.containsAll(linePositions);
    }

    private List<Integer> getRowPositions(int row) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            positions.add(row * size + i + 1);
        }
        return positions;
    }

    private List<Integer> getColumnPositions(int column) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            positions.add(i * size + column + 1);
        }
        return positions;
    }

    private List<Integer> getDiagonalPositions(boolean mainDiagonal) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int pos = mainDiagonal ? i * size + i + 1 : (i + 1) * size - i;
            positions.add(pos);
        }
        return positions;
    }
}
