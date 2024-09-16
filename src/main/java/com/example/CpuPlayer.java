package com.example;

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