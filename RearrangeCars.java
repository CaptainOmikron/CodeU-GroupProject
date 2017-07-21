import java.util.ArrayList;
import java.util.HashMap;

public class RearrangeCars {

	public static HashMap<Integer, Integer> buildMap(int[] list) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < list.length; i++) {
			map.put(list[i], i);
		}

		return map;
	}

	public static ArrayList<Move> rearrangeCars(int[] initialList, int[] finalList) {
		ArrayList<Move> moves = new ArrayList<Move>();

		HashMap<Integer, Integer> currentPositions = buildMap(initialList);

		for (int i = 0; i < initialList.length; i++) {
			int car = finalList[i];
			if (finalList[i] != initialList[i]) {
				int currentPosition = currentPositions.get(car);
				int emptySpot = currentPositions.get(0);

				currentPositions.put(0, currentPosition);
				currentPositions.put(initialList[i], emptySpot);
				currentPositions.put(car, i);

				if (car == 0) {

					initialList[emptySpot] = initialList[i];
					initialList[i] = 0;

					moves.add(new Move(i, emptySpot));
					
				} else if (emptySpot != i) {
					int aux = initialList[i];
					initialList[i] = 0;
					initialList[emptySpot] = aux;

					moves.add(new Move(i, emptySpot));

					aux = initialList[currentPosition];
					initialList[currentPosition] = 0;
					initialList[i] = aux;

					moves.add(new Move(currentPosition, i));
				} else {
					initialList[i] = initialList[currentPosition];
					initialList[currentPosition] = 0;

					moves.add(new Move(currentPosition, i));
				}
			}
		}

		for (int i = 0; i < initialList.length; i++) {
			System.out.print(initialList[i] + " ");
		}

		System.out.println();

		return moves;
	}
}
