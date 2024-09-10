package com.example;

import lombok.Getter;
import lombok.Setter;
import java.util.Scanner;

@Getter
@Setter
public class HumanPlayer extends Player {

    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int makeMove(Board board) {

        int pos;
        int[] boardPosition;

        while (true) {
            System.out.println(getName() + ", Where do you want to place (1-9)?");
            pos = scanner.nextInt();

            boardPosition = board.getBoardPosition(pos);
            if (boardPosition == null) {
                System.out.println("Invalid position! Please enter a number between 1 and 9.");
                continue;

            }
            while (board.isPositionTaken(pos)) {
                System.out.println("Already hai waha pe \uD83E\uDD26\u200D♀\uFE0F, chalo fir se apna dalo apna number");
                pos = scanner.nextInt();
            }
            return pos;
        }
    }
}

