package com.example;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpuPlayer extends Player
{
    private MoveStrategy strategy;

    public CpuPlayer(MoveStrategy strategy,char symbol)
    {
        super("CPU",symbol);
        this.strategy = strategy;
    }

    @Override
    public int makeMove(Board board)
    {
        return strategy.makeMove(board);
    }
}