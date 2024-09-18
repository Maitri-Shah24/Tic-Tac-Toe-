package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CpuPlayerFactory implements PlayerFactory
{

    private final char cpuSymbol;
    private final Scanner scanner = new Scanner(System.in);

    public CpuPlayerFactory(char cpuSymbol)
    {
        this.cpuSymbol = cpuSymbol;
    }

    @Override
    public Player createPlayer()
    {
        int difficultyLevel;
        while (true)
        {
            System.out.println("Choose difficulty level: 1 for EASY and 2 for HARD");
            try
            {
                difficultyLevel = scanner.nextInt();
                scanner.nextLine();
                if (difficultyLevel == 1 || difficultyLevel == 2)
                {
                    break;
                }
                else
                {
                    System.out.println("Please enter 1 for EASY or 2 for HARD.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        MoveStrategy strategy = (difficultyLevel == 1) ? new RandomMoveStrategy() : new SmartMoveStrategy();
        return new CpuPlayer(strategy, cpuSymbol);
    }
}
