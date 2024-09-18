package com.example;

public class MoveStrategyFactory {

    public static MoveStrategy createStrategy(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "smart":
                return new SmartMoveStrategy();
            case "random":
                return new RandomMoveStrategy();
            default:
                throw new IllegalArgumentException("Unknown strategy type: " + difficulty);
        }
    }
}
