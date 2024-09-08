package com.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class HumanPlayer extends Player {

    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name)
    {
        super(name);
    }

    @Override
    public int makeMove(Board board) {
        System.out.println(getName()+ ", Where do you want to place (1-9)?");
        int pos = scanner.nextInt();
        while (board.isPositionTaken(pos))
        {
            System.out.println("Already hai waha pe \uD83E\uDD26\u200D♀\uFE0F, chalo fir se apna dalo apna number");
            pos = scanner.nextInt();
        }
        return pos;
    }
}

