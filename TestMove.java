import java.util.ArrayList;

public class TestMove {
	@org.junit.Test
	public void test1() {
		
		System.out.println("\n---------test 1---------\n");
		
		int[] toRearrange = {1,2,0,3};
		int[] finalList = {3,1,2,0};
		
		ArrayList<Move> moves = RearrangeCars.rearrangeCars(toRearrange, finalList);
		
		for(Move m : moves) {
			System.out.println(m.getOrigin() + " to " + m.getDestination());
		}
	}
	
	@org.junit.Test
	public void test2() {
		
		System.out.println("\n---------test 2---------\n");
		
		int[] toRearrange = {1,2,0,3};
		int[] finalList = {1,2,0,3};
		
		ArrayList<Move> moves = RearrangeCars.rearrangeCars(toRearrange, finalList);
		
		for(Move m : moves) {
			System.out.println(m.getOrigin() + " to " + m.getDestination());
		}
	}
	@org.junit.Test
	public void test3() {
		
		System.out.println("\n---------test 3---------\n");
		
		int[] toRearrange = {1,0};
		int[] finalList = {0,1};
		
		ArrayList<Move> moves = RearrangeCars.rearrangeCars(toRearrange, finalList);
		
		for(Move m : moves) {
			System.out.println(m.getOrigin() + " to " + m.getDestination());
		}
	}
	@org.junit.Test
	public void test4() {
		
		System.out.println("\n---------test 4---------\n");
		
		int[] toRearrange = {1,2,3,0};
		int[] finalList = {0,3,2,1};
		
		ArrayList<Move> moves = RearrangeCars.rearrangeCars(toRearrange, finalList);
		
		for(Move m : moves) {
			System.out.println(m.getOrigin() + " to " + m.getDestination());
		}
	}
}
