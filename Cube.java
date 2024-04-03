import java.util.Vector;

public class Cube {
    private static int size; // final keyword? 
    private static int cubesCount = 0;
    private Coordinates pos;
    private Cube previous;
    private Cube next;

    public Cube(Coordinates c, Cube p, Cube n){
        pos = c;
        previous = p;
        next = n;
        cubesCount++;
    }


// GETTERS 
    public Coordinates getPos(){
        return pos;
    }

    public int getCubesCount(){
        return cubesCount;
    }

    public int getSize(){
        return size;
    }

    public int cubesCount(){
        return cubesCount;
    }



    
}
