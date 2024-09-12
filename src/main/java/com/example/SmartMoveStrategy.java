package com.example;

import java.util.Random;

public class SmartMoveStrategy implements MoveStrategy
{
    private final Random random = new Random();
    @Override
    public int makeMove(Board board)
    {
        int boardSize = board.getSize();
        int maxPos = boardSize * boardSize;
        int pos = random.nextInt(maxPos) + 1;

        while (board.isPositionTaken(pos)) {
            pos = random.nextInt(maxPos) + 1;
        }

        return pos;
    }
}
