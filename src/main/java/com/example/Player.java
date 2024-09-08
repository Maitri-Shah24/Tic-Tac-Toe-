package com.example;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Player
{
    private String name;
    private List<Integer> positions = new ArrayList<>();

    public Player(String name)
    {
        this.name = name;
    }
    public abstract int makeMove(Board board);
    public  void addPosition(int pos)
    {
        positions.add(pos);
    }
    public void resetPositions()
    {
        positions.clear();
    }

}
