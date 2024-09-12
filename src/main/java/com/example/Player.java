package com.example;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class Player
{
    private final String name;
    private final char symbol;
    private List<Integer> positions = new ArrayList<>();

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
