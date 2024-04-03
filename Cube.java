import java.util.Vector;

public class Cube {
    private static int size; // final keyword? 
    private static int cubesCount = 0;

    // A list to store the coordinates of visited (filled) cubes
    private static List<Coordinates> filledCoordinates = new ArrayList<>();

    private Coordinates pos;
    private Cube previous;

    public Cube(Coordinates c, Cube p){
        pos = c;
        previous = p;
        cubesCount++;
    }


// GETTERS 
    public int getCubesCount(){
        return cubesCount;
    }

    public int getSize(){
        return size;
    }
    public List<Coordinates> getVisited(){
        return filledCoordinates;
    }

    public Coordinates getPos(){
        return pos;
    }

    public Cube getPrevious(){
        return previous;
    }

   



    
}
