import org.junit.Assert;

import java.util.ArrayList;

/**
 * Created by ioana-chirca on 21-Jul-17.
 */
public class RearrangingCarsTest {
    @org.junit.Test
    public void testSmall() {
        int[] originalOrder = {1, 2, 0, 3};
        int[] finalOrder = {3, 1, 2, 0};

        ArrayList<Move> moves = RearrangingCars.rearrange(originalOrder, finalOrder);

        Move[] result = new Move[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            result[i] = moves.get(i);
        }

        Move[] expectedResult = {new Move(0, 2), new Move(3, 0), new Move(1, 3),
                                new Move(2, 1), new Move(3, 2)};

        Assert.assertArrayEquals(expectedResult, result);
    }

    @org.junit.Test
    public void testBigger() {
        int[] originalOrder = {1, 5, 0, 6, 2, 3, 4};
        int[] finalOrder = {1, 2, 3, 0, 4, 5, 6};

        ArrayList<Move> moves = RearrangingCars.rearrange(originalOrder, finalOrder);

        Move[] result = new Move[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            result[i] = moves.get(i);
        }

        Move[] expectedResult = {new Move(1, 2), new Move(4, 1), new Move(2, 4),
                                new Move(5, 2), new Move(3, 5), new Move(4, 3),
                                new Move(6, 4), new Move(5, 6), new Move(3, 5)};

        Assert.assertArrayEquals(expectedResult, result);
    }

    @org.junit.Test
    public void testIdenticalOrders() {
        int[] originalOrder = {1, 2, 5, 0, 4, 3};
        int[] finalOrder = {1, 2, 5, 0, 4, 3};

        ArrayList<Move> moves = RearrangingCars.rearrange(originalOrder, finalOrder);

        Move[] result = new Move[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            result[i] = moves.get(i);
        }

        Move[] expectedResult = {};

        Assert.assertArrayEquals(expectedResult, result);
    }

    @org.junit.Test
    public void testEmptyInput() {
        int[] originalOrder = {};
        int[] finalOrder = {1, 2, 5, 0, 4, 3};

        ArrayList<Move> moves = RearrangingCars.rearrange(originalOrder, finalOrder);

        Assert.assertNull(moves);
    }

    @org.junit.Test
    public void testNullInput() {
        int[] originalOrder = {1, 0, 2};
        int[] finalOrder = null;

        ArrayList<Move> moves = RearrangingCars.rearrange(originalOrder, finalOrder);

        Assert.assertNull(moves);
    }

    @org.junit.Test
    public void testDifferentLengthInputs() {
        int[] originalOrder = {1, 0, 2};
        int[] finalOrder = {1, 2, 0, 3};

        ArrayList<Move> moves = RearrangingCars.rearrange(originalOrder, finalOrder);

        Assert.assertNull(moves);
    }



}
