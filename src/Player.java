import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;
    private final List<Integer> positions;

    public Player() {
        positions = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

