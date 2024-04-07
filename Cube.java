/*
 * INFO0062 - Object-Oriented Programming
 * Programming Project -- Academic Year 2023-2024
 *
 * This Cube class implements a program that solves a Snake Cube puzzle
 *  given an initial configuration using a sequence of characters and searching for a valid combination of rotations,
 *  and displays a possible valid solution with the final coordinates of cubes.
 * 
 * 
 *
 * @author: ALBASHITYALSHAIER Abdelkader & BOUSTANI Mehdi 
 * @date : 14/04/2024
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.lang.Math;
import java.util.Random;


public class Cube {
    private static int size; // final keyword? 

    private ArrayList<Direction> possibleMoves = new ArrayList<>();
    private Direction direction;
    private Coordinates pos;
    private Cube previous;
    private CubeType type;


    // -------------------------------- FOR TEST --------------------------------------- //
    private static ArrayList<Coordinates> initialPos = new ArrayList<>(); // FOR TEST -- REMOVE AFTER TESTS

    public static void addPos(Coordinates c){
        initialPos.add(c);
    }

    public static ArrayList getINIT(){
        return initialPos;
    }

    public static boolean containsDuplicates() {
        for (int i = 0; i < initialPos.size() - 1; i++) {
            for (int j = i + 1; j < initialPos.size(); j++) {
                if (initialPos.get(i).isEqual(initialPos.get(j))) {
                    System.out.println("indices " + i + " and " + j + "are duplicates");
                    return true; // Found a duplicate
                }
            }
        }
        return false; // No duplicates found
    }

    // ----------------------------------------------------------------------------- //

    public Cube(CubeType t, Cube p){
        type = t;
        previous = p;
        if (previous == null)
            pos = new Coordinates();
    
        else {
            possibleMoves = getPossibleMoves();
            Random random = new Random();
            int randomIndex = random.nextInt(possibleMoves.size());
            direction = possibleMoves.get(randomIndex);
            pos = directionToPos();
        }  
    }

    public static int getSize(){
        return size;
    }

    public static void setSize(int n){
        size = n;
    }

    public boolean findSolution(){
        if (previous == null)
            return true;
            
        while (!previous.isValid(pos.getX(), pos.getY(), pos.getZ())){
            boolean removed = possibleMoves.remove(direction);
            if (!moveOn())
                return false;
        }
        return true;
    }

    private boolean collide(int x, int y, int z){
        return pos.getX() == x && pos.getY() == y && pos.getZ() == z;
    }

    private boolean isInBorders(int x, int y, int z){
        return x <= size && y <= size && z <= size &&
               x >= 1    && y >= 1    && z >= 1;
    }

    private void RandomMove(){
        Random random = new Random();
        int randomIndex = random.nextInt(possibleMoves.size());
        direction = possibleMoves.get(randomIndex);
        pos = directionToPos();
    }

    private boolean isValid(int x, int y, int z){
        if (!isInBorders(x, y, z) || collide(x, y, z))
            return false;

        if (previous == null && !collide(x, y, z))
            return true;

        return previous.isValid(x, y, z);
    }   

    private boolean moveOn(){
        if (previous == null)
            return false;

        if (possibleMoves.size() > 0){
            RandomMove();
            return true;
        }

        boolean existsNextSolution = previous.nextSolution();
        if (!existsNextSolution){
            return false;
        }
        possibleMoves = getPossibleMoves();
        RandomMove();
        return true;
    }

    private boolean nextSolution(){
        boolean removed = possibleMoves.remove(direction);
        return moveOn() && findSolution();
    }

    private ArrayList<Direction> getPossibleMoves(){
        if (previous.type == CubeType.STRAIGHT)
            possibleMoves.add(previous.direction);
        
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
 
    private Coordinates directionToPos(){
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
        addPos(pos);
        System.out.println(pos.toString());
        
    }
    
}