public class Move {

    private int origin;
    private int destination;

    public Move(int origin, int destination){
        this.origin = origin;
        this.destination = destination;
    }

    public int getOrigin(){
        return origin;
    }

    public int getDestination(){
        return destination;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("move from ");
        stringBuilder.append(origin);
        stringBuilder.append(" to ");
        stringBuilder.append(destination);
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
