import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.lang.Math;
import java.util.HashSet;


public class Cube {
    private static int size; // final keyword? 

    
    private Direction direction;
    private Coordinates pos;
    private Cube previous;
    private CubeType type;

    public Cube(CubeType t, Coordinates c, Cube p){
        type = t;
        pos = c;
        previous = p;
    }

    public static int getSize(){
        return size;
    }
    // public List<Coordinates> getVisited(){
    //     return filledPositions;
    // }

    public Coordinates getPos(){
        return pos;
    }
    public Cube getPrevious(){
        return previous;

    }

    public static void setSize(int n){
        size = n;
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
        
        if (previous == null){
            return true;
        }

        if (collide(x, y, z)){
            System.out.println("here\n");
            // return true;
            return false;
        }

        return previous.isValid(x, y, z);
        // return true;
    }   


    public HashSet<Direction> getPossibleMoves(){
        HashSet<Direction> possibleMoves = new HashSet<>();
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

    public boolean moveOn(){
        if (previous == null){
            return false;
        }
        HashSet <Direction> possibleMoves = getPossibleMoves();

        boolean removed = possibleMoves.remove(direction); // returns false if element does not exist
        Coordinates nextMove = new Coordinates();
        for (Direction move : possibleMoves) {
            // System.out.println(move);
            direction = move;
            nextMove = directionToPos(nextMove);
            // System.out.println("pos = " + pos.toString());
            if (isValid(nextMove.getX(), nextMove.getY(), nextMove.getZ())){
                pos = nextMove;
                return true;
            }
            // return false;
        }
        return previous.moveOn();
    }

    public Coordinates directionToPos(Coordinates c){
        switch (direction){
            case UP :
                // System.out.print("UP : ");
                c.setX(previous.pos.getX() - 1);
                c.setY(previous.pos.getY());
                c.setZ(previous.pos.getZ());
                // pos.translate(1 , 0, 0);
                break;
            
            case DOWN :
            // System.out.print("DOWN : ");
                c.setX(previous.pos.getX() + 1);
                c.setY(previous.pos.getY());
                c.setZ(previous.pos.getZ());
                // pos.translate(-1, 0, 0);
                break;    

            case LEFT :
                // System.out.print("LEFT : ");
                c.setX(previous.pos.getX());
                c.setY(previous.pos.getY() - 1);
                c.setZ(previous.pos.getZ());
                // pos.translate(0, 1, 0);
                break;

            case RIGHT :
                // System.out.print("RIGHT : ");
                c.setX(previous.pos.getX());
                c.setY(previous.pos.getY() + 1);
                c.setZ(previous.pos.getZ());
                // pos.translate(0, -1, 0);
                break;

            case BACK :
                // System.out.print("BACK   : ");
                c.setX(previous.pos.getX());
                c.setY(previous.pos.getY());
                c.setZ(previous.pos.getZ() - 1);
                // pos.translate(0, 0, 1);
                break;

            case FRONT :
                // System.out.print("FRONT : ");
                c.setX(previous.pos.getX());
                c.setY(previous.pos.getY());
                c.setZ(previous.pos.getZ() + 1);
                // pos.translate(0, 0, -1);
                break;
            
            default :
                break;
        }
        return c;
    }

    // List<Coordinates> getPossibleMoves(){
    //     int dx = 0, dy = 0, dz = 0;
    //     List<Coordinates> possibleMoves = new ArrayList<>();
    //     if (previous != null && previous.previous != null){
    //         dx = previous.pos.getX() - previous.previous.pos.getX();
    //         dy = previous.pos.getY() - previous.previous.pos.getY();
    //         dz = previous.pos.getZ() - previous.previous.pos.getZ();
    //     }

    //     switch (previous.type){
    //         case STRAIGHT :
    //             possibleMoves.add(new Coordinates(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz));
    //             break;
            
    //         case ANGLE : 
    //             if (dx != 0){
    //                 possibleMoves.add(new Coordinates(pos.getX(), pos.getY() + 1, pos.getZ())); // RIGHT
    //                 possibleMoves.add(new Coordinates(pos.getX(), pos.getY() - 1, pos.getZ())); // LEFT
    //                 possibleMoves.add(new Coordinates(pos.getX(), pos.getY(), pos.getZ() + 1)); // FRONT
    //                 possibleMoves.add(new Coordinates(pos.getX(), pos.getY(), pos.getZ() - 1)); // BACK
    //             }
    //             else if (dy != 0){
    //                 possibleMoves.add(new Coordinates(pos.getX() + 1, pos.getY(), pos.getZ())); // UP
    //                 possibleMoves.add(new Coordinates(pos.getX() - 1, pos.getY(), pos.getZ())); // DOWN
    //                 possibleMoves.add(new Coordinates(pos.getX(), pos.getY(), pos.getZ() + 1)); // FRONT
    //                 possibleMoves.add(new Coordinates(pos.getX(), pos.getY(), pos.getZ() - 1)); // BACK
    //             }

    //             else if (dz != 0){
    //                 possibleMoves.add(new Coordinates(pos.getX() + 1, pos.getY(), pos.getZ())); // UP
    //                 possibleMoves.add(new Coordinates(pos.getX() - 1, pos.getY(), pos.getZ())); // DOWN
    //                 possibleMoves.add(new Coordinates(pos.getX(), pos.getY() + 1, pos.getZ())); // RIGHT
    //                 possibleMoves.add(new Coordinates(pos.getX(), pos.getY() - 1, pos.getZ())); // LEFT
    //             }
    //             break;

    //         case ENDPOINT :
    //             possibleMoves.add(new Coordinates(pos.getX() + 1, pos.getY(), pos.getZ()));
    //             possibleMoves.add(new Coordinates(pos.getX() - 1, pos.getY(), pos.getZ()));
    //             possibleMoves.add(new Coordinates(pos.getX(), pos.getY() + 1, pos.getZ()));
    //             possibleMoves.add(new Coordinates(pos.getX(), pos.getY() - 1, pos.getZ()));
    //             possibleMoves.add(new Coordinates(pos.getX(), pos.getY(), pos.getZ() + 1));
    //             possibleMoves.add(new Coordinates(pos.getX(), pos.getY(), pos.getZ() - 1));
    //             break;

    //         default : 
    //             break;
    //     }

    //     return possibleMoves;
    // }

 


    public boolean findSolution(){
        



        return false;

    }



   
    
}
