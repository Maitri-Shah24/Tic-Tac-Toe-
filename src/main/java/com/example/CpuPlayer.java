package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpuPlayer extends Player {

    private final Random random = new Random();

    public CpuPlayer()
    {
        super("CPU");
    }

    @Override
    public int makeMove(Board board)
    {
        int pos = random.nextInt(9) +1;
        while (board.isPositionTaken(pos))
        {
            pos = random.nextInt(9)+1;
        }
        return pos;
    }
}