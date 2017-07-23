import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RearrangingCars {
    private int[] originState;
    private int[] finishState;
    private int[] numbersMap;
    private int numberOfCars;
    private final Logger log = Logger.getLogger(RearrangingCars.class.getName());

    private void makeNumbersMap(){
        numbersMap = new int[numberOfCars];
        for (int i = 0; i < numberOfCars; i++){
            numbersMap[originState[i]] = i;
        }
    }

    public ArrayList<Move> getSeriesOfMoves(int[] originState, int[] finishState){ //O(n)
        if (originState == null){
            throw new IllegalArgumentException("originState array is null");
        }
        if (finishState == null){
            throw new IllegalArgumentException("finishState array is null");
        }
        if (originState.length != finishState.length){
            throw new IllegalArgumentException("originState array and finishState array have different sizes");
        }
        log.log(Level.FINE, "Arguments are ok");
        this.originState = ( originState.clone() );
        numberOfCars = originState.length;
        makeNumbersMap();
        ArrayList<Move> moveHistory = new ArrayList<Move>();
        for (int i = 0; i < numberOfCars; i++){
            int currentCar = this.originState[i];
            int needCar = finishState[i];
            if (currentCar != needCar){
                int emptyPlace = numbersMap[0];
                int needCarPlace = numbersMap[needCar];
                if (currentCar != 0 && needCar != 0){
                    this.originState[emptyPlace] = currentCar;
                    numbersMap[currentCar] = emptyPlace;
                    moveHistory.add(new Move(i, emptyPlace));
                    this.originState[needCarPlace] = 0;
                    numbersMap[0] = needCarPlace;
                    this.originState[i] = needCar;
                    numbersMap[needCar] = i;
                    moveHistory.add(new Move(needCarPlace, i));
                }
                else{
                    this.originState[i] = needCar;
                    numbersMap[needCar] = i;
                    this.originState[needCarPlace] = currentCar;
                    numbersMap[currentCar] = needCarPlace;
                    int carPlace = i;
                    if (currentCar == 0)
                        carPlace = needCarPlace;
                    moveHistory.add(new Move(carPlace, emptyPlace));
                }
            }
        }
        return moveHistory;
    }
}
