import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CPU {
    private final List<Integer> positions;

    public CPU() {
        positions = new ArrayList<>();
    }

    public int generateMove() {
        return new Random().nextInt(9) + 1;
    }

    public void addPosition(int pos) {
        positions.add(pos);
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void resetPositions() {
        positions.clear();
    }
}