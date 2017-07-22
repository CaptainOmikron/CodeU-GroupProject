import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ioana-chirca on 20-Jul-17.
 */
class Move {
    private int origin;
    private int destination;

    public Move() {}

    public Move(int origin, int destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public String toString() {
        return "Move from " + origin + " to " + destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (origin != move.origin) return false;
        return destination == move.destination;
    }

    @Override
    public int hashCode() {
        int result = origin;
        result = 31 * result + destination;
        return result;
    }
}

public class RearrangingCars {
    public static ArrayList<Move> rearrange(int[] originalOrder, int[] finalOrder) {
        if (originalOrder == null || finalOrder == null || originalOrder.length != finalOrder.length)
            return null;

        ArrayList<Move> moves = new ArrayList<>();
        if (originalOrder.length == 0) return moves;

        HashMap<Integer, Integer> positions = new HashMap<>();
        int N = originalOrder.length;
        for (int i = 0; i < N; i++) {
            positions.put(originalOrder[i], i);
        }

        int index = 0;
        while (index < N) {
            if (originalOrder[index] == finalOrder[index]) {
                // nothing to modify, the cars already match
                index++;
            } else {
                // must bring the desired car in the index position
                // first we move the current car (if we have one) into the empty slot
                if (originalOrder[index] != 0) {
                    moves.add(new Move(index, positions.get(0)));

                    int emptySlotPosition = positions.get(0);
                    int currentCar = originalOrder[index];

                    originalOrder[emptySlotPosition] = currentCar;
                    originalOrder[index] = 0;
                    positions.put(currentCar, emptySlotPosition);
                    positions.put(0, index);
                }

                if (finalOrder[index] != 0) {
                    // we bring the desired car into the new empty slot
                    moves.add(new Move(positions.get(finalOrder[index]), index));

                    int emptySlotPosition = index;
                    int desiredCar = finalOrder[index];
                    int desiredCarPosition = positions.get(desiredCar);

                    originalOrder[emptySlotPosition] = desiredCar;
                    originalOrder[desiredCarPosition] = 0;
                    positions.put(desiredCar, emptySlotPosition);
                    positions.put(0, desiredCarPosition);
                }

                index++;
            }
        }

        return moves;
    }

    public static void main(String[] args) {
        int[] originalOrder = {1, 2, 0, 3};
        int[] finalOrder = {3, 1, 2, 0};

        ArrayList<Move> moves = rearrange(originalOrder, finalOrder);

        for(Move move : moves) {
            System.out.println(move);
        }
    }
}
