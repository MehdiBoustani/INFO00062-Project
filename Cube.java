import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.lang.Math;
import java.util.Random;


public class Cube {
    private static int size; // final keyword? 
    private static ArrayList<Coordinates> initialPos = new ArrayList<>();

    private ArrayList<Direction> possibleMoves = new ArrayList<>();
    private Direction direction;
    private Coordinates pos;
    private Cube previous;
    private CubeType type;

    // public Cube(CubeType t, Cube p){
    //     type = t;
    //     previous = p;
    //     if (previous == null){
    //         pos = new Coordinates();
    //     }
    //     else {
    //         possibleMoves = getPossibleMoves();
    //         direction = possibleMoves.get(0);
    //         pos = directionToPos();
    //     }
    // }

    public Cube(CubeType t, Cube p){
        type = t;
        previous = p;
        if (previous == null){
            pos = new Coordinates();
        }
        else {
            possibleMoves = getPossibleMoves();
            direction = possibleMoves.get(0);
            pos = directionToPos();
        }
    }

    public Cube(CubeType t, Coordinates c, Cube p){
        type = t;
        pos = c;
        previous = p;
    }

    public static int getSize(){
        return size;
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

    public static addPos(Coordinates c){
        initialPos.add(c);
    }

    public boolean collide(int x, int y, int z){
        return pos.getX() == x && pos.getY() == y && pos.getZ() == z;
    }

    public boolean isValid(int x, int y, int z){
        if ( x >= size    || x < 0
            || y >= size  || y < 0
            || z >= size  || z < 0 ){
        
            return false;
        }
    
        if (previous == null && !collide(x, y, z)){
            return true;
        }

        if (collide(x, y, z)){
            return false;
        }

        return previous.isValid(x, y, z);
    }   

    public boolean moveOn(){
        if (previous == null)
            return false;
        
        while (!previous.isValid(pos.getX(), pos.getY(), pos.getZ())){
            boolean removed = possibleMoves.remove(direction);
            if (possibleMoves.size() == 0){
                boolean existsSolution =  previous.nextSolution();
                if (existsSolution){
                    possibleMoves = getPossibleMoves();
                }
                else {
                    return false;
                }
            }
            Random random = new Random();
            int randomIndex = random.nextInt(possibleMoves.size());
            direction = possibleMoves.get(randomIndex);
            pos = directionToPos();
        }

        return true;
    }

    public boolean findSolution(){
        if (previous == null)
            return true;
        
        if (!moveOn())
            return false;
        
        return true;
    }

    public boolean nextSolution(){
        if (previous == null){
            return true;
        }
        boolean removed = possibleMoves.remove(direction);
        if (possibleMoves.size() > 0){
            Random random = new Random();
            int randomIndex = random.nextInt(possibleMoves.size());
            direction = possibleMoves.get(randomIndex);
            pos = directionToPos();
            return moveOn() && findSolution();
        }
                
        if (previous.nextSolution()){
            possibleMoves = getPossibleMoves();
            return true;

        }
        return false;

    }

    public ArrayList<Direction> getPossibleMoves(){
        if (previous.type == CubeType.STRAIGHT){
            possibleMoves.add(previous.direction);
            }
        
        else if (previous.type == CubeType.ANGLE){
            switch(previous.direction){
                case UP :
                case DOWN:
                    possibleMoves.add(Direction.LEFT);
                    possibleMoves.add(Direction.RIGHT);
                    possibleMoves.add(Direction.BACK);
                    possibleMoves.add(Direction.FRONT);
                    break;

                case RIGHT:
                case LEFT:
                    possibleMoves.add(Direction.UP);
                    possibleMoves.add(Direction.DOWN);
                    possibleMoves.add(Direction.FRONT);
                    possibleMoves.add(Direction.BACK);
                    break;
                case FRONT :
                case BACK :
                    possibleMoves.add(Direction.UP);
                    possibleMoves.add(Direction.DOWN);
                    possibleMoves.add(Direction.LEFT);
                    possibleMoves.add(Direction.RIGHT);
                    break;

                default:
                break;
            }
        }

        else {
            possibleMoves.add(Direction.UP);
            possibleMoves.add(Direction.DOWN);
            possibleMoves.add(Direction.LEFT);
            possibleMoves.add(Direction.RIGHT);
            possibleMoves.add(Direction.BACK);
            possibleMoves.add(Direction.FRONT);
        }
        return possibleMoves;
    }

 

    public Coordinates directionToPos(){
        Coordinates c = new Coordinates(previous.pos.getX(), previous.pos.getY(), previous.pos.getZ());
        switch (direction){
            case UP :
                c.translate(-1 , 0, 0);
                break;
            
            case DOWN :
                c.translate(1, 0, 0);
                break;    

            case LEFT :
                c.translate(0, -1, 0);
                break;

            case RIGHT :
                c.translate(0, 1, 0);
                break;

            case BACK :
                c.translate(0, 0, -1);
                break;

            case FRONT :
                c.translate(0, 0, 1);
                break;
            
            default :
                break;
        }
        return c;
    }

    public void display(){
        if (previous != null){
            previous.display();
        }
        System.out.println(pos.toString());
        
    }
}



























// public Coordinates directionToPos(){
//     Coordinates c = new Coordinates();
//     // c = previous.pos;
//     c.setX(previous.pos.getX());
//     c.setY(previous.pos.getY());
//     c.setZ(previous.pos.getZ());
//     switch (direction){
//         case UP :
//             // System.out.print("UP : ");
//             // c.setX(previous.pos.getX() - 1);
//             // c.setY(previous.pos.getY());
//             // c.setZ(previous.pos.getZ());
//             c.translate(-1 , 0, 0);
//             break;
        
//         case DOWN :
//         // System.out.print("DOWN : ");
//             // c.setX(previous.pos.getX() + 1);
//             // c.setY(previous.pos.getY());
//             // c.setZ(previous.pos.getZ());
//             c.translate(1, 0, 0);
//             break;    

//         case LEFT :
//             // System.out.print("LEFT : ");
//             // c.setX(previous.pos.getX());
//             // c.setY(previous.pos.getY() - 1);
//             // c.setZ(previous.pos.getZ());
//             c.translate(0, -1, 0);
//             break;

//         case RIGHT :
//             // System.out.print("RIGHT : ");
//             // c.setX(previous.pos.getX());
//             // c.setY(previous.pos.getY() + 1);
//             // c.setZ(previous.pos.getZ());
//             c.translate(0, 1, 0);
//             break;

//         case BACK :
//             // System.out.print("BACK   : ");
//             // c.setX(previous.pos.getX());
//             // c.setY(previous.pos.getY());
//             // c.setZ(previous.pos.getZ() - 1);
//             c.translate(0, 0, -1);
//             break;

//         case FRONT :
//             // System.out.print("FRONT : ");
//             // c.setX(previous.pos.getX());
//             // c.setY(previous.pos.getY());
//             // c.setZ(previous.pos.getZ() + 1);
//             c.translate(0, 0, 1);
//             break;
        
//         default :
//             break;
//     }
//     return c;
// }