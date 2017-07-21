import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertArrayEquals;

public class RearrangingCarsTest {

    final Logger log = Logger.getLogger(RearrangingCarsTest.class.getName());
    RearrangingCars rearrangingCars = new RearrangingCars();
    ArrayList<Move> answer;

    @After
    public void tearDown() {
        answer = null;
    }

    @Test
    public void test1() {
        log.log(Level.FINE, "TEST1:\n");
        int[] origin = {1, 2, 0, 3};
        int[] finish = {3, 1, 2, 0};
        answer = rearrangingCars.getSeriesOfMoves(origin, finish);
        goByMovingList(answer, origin);
        assertArrayEquals(origin, finish);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test2() {
        log.log(Level.FINE, "TEST2:\n");
        int[] origin = {1, 2, 0, 3};
        int[] finish = {3, 1, 2};
        answer = rearrangingCars.getSeriesOfMoves(origin, finish);
    }

    @Test
    public void test3() {
        log.log(Level.FINE, "TEST3:\n");
        int[] origin = {2, 3, 5, 0, 1, 4, 6};
        int[] finish = {4, 3, 0, 2, 1, 5, 6};
        answer = rearrangingCars.getSeriesOfMoves(origin, finish);
        goByMovingList(answer, origin);
        assertArrayEquals(origin, finish);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test4() {
        log.log(Level.FINE, "TEST4:\n");
        int[] origin = null;
        int[] finish = {3, 1, 2};
        answer = rearrangingCars.getSeriesOfMoves(origin, finish);
    }

    @Test
    public void test5() {
        log.log(Level.FINE, "TEST5:\n");
        int[] origin = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] finish = {10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        answer = rearrangingCars.getSeriesOfMoves(origin, finish);
        goByMovingList(answer, origin);
        assertArrayEquals(origin, finish);
    }

    @Test
    public void test6() {
        log.log(Level.FINE, "TEST6:\n");
        int[] origin = {};
        int[] finish = {};
        answer = rearrangingCars.getSeriesOfMoves(origin, finish);
        goByMovingList(answer, origin);
        assertArrayEquals(origin, finish);
    }

    private void goByMovingList (ArrayList <Move> movingList, int[] origin){
        log.log(Level.FINE, " Moving List:\n"+answer.toString());
        for (Move move: answer){
            int moveOrigin = move.getOrigin();
            int moveDestinition = move.getDestination();
            origin[moveDestinition] = origin[moveOrigin];
            origin[moveOrigin] = 0;
        }
    }
}
