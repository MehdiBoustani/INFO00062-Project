import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

public class Cube {
    private static int size; // final keyword? 
    private static int cubesCount = 0;

    // A list to store the coordinates of visited (filled) cubes
    private static List<Coordinates> filledPositions = new ArrayList<>();

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

    public static int getSize(){
        return size;
    }

    public List<Coordinates> getVisited(){
        return filledPositions;
    }

    public Coordinates getPos(){
        return pos;
    }

    public Cube getPrevious(){
        return previous;
    }

    public static void setSize(int n){
        size = n;
    }

    // public void setPos(int x, int y, int z){
    //     pos.getX() = x;

    // }

    public boolean isValid(Coordinates c){
        if ( c.getX() >= size    || c.getX() < 0
            || c.getY() >= size  || c.getY() < 0
            || c.getZ() >= size  || c.getZ() < 0 )
            return false;
        
        for (int i = 0; i < filledPositions.size(); i++){
            if (c.isEqual(filledPositions.get(i))){
                return false;
            }
        }

        return true;
    }



    // public void move(Direction d){
    //     switch (d){
    //         case UP :
    //             pos.translate(-1, 0, 0);
    //             break;
    //         case DOWN :
    //             pos.translate(1, 0, 0);
    //             break;
    //         case LEFT :
    //             pos.translate(0, -1, 0);
    //             break;
    //         case RIGHT :
    //             pos.translate(0, 1, 0);
    //             break;
    //         case BACK :
    //             pos.translate(0, 0, -1);
    //             break;
    //         case FRONT :
    //             pos.translate(0, 0,1);
    //             break;
    //         default :
    //             break;
    //     }
    // }





   
    
}
