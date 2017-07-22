
public class Move {
	private int origin;
	private int destination;
	
	public Move(int origin, int destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public int getDestination() {
		return destination;
	}
	
	public int getOrigin() {
		return origin;
	}
}
