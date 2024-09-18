package com.example;

public class PlayerFactory {
    public static Player createHumanPlayer(String name, char symbol) {
        return new HumanPlayer(name, symbol);
    }

    public static Player createCpuPlayer(MoveStrategy strategy, char symbol) {
        return new CpuPlayer(strategy, symbol);
    }
}
