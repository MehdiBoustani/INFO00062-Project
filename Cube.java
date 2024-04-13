/*
 * INFO0062 - Object-Oriented Programming
 * Programming Project -- Academic Year 2023-2024
 *
 * This Cube class implements a program that solves a Snake Cube puzzle
 * given an initial configuration using a sequence of characters and searching for a valid combination of rotations,
 * and displays a possible valid solution with the final coordinates of cubes.
 * 
 * @author: ALBASHITYALSHAIER Abdelkader & BOUSTANI Mehdi 
 * @date : 14/04/2024
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.List;


public class Cube {
    private static int size; 

    private ArrayList<Move> possibleMoves = new ArrayList<>();
    private Move move;
    private Coordinates pos;
    private Cube previous;
    private CubeType type;

    /**
     * Creates a cube using its type and its previous cube with a random possible move 
     * @param t cube type 
     * @param p previous cube
     * 
     */
    private Cube(CubeType t, Cube p){

        type = t;
        previous = p;

        if(previous == null)
            pos = new Coordinates();
    
        else{
            possibleMoves = getPossibleMoves();

            Random random = new Random();

            int randomIndex = random.nextInt(possibleMoves.size());

            move = possibleMoves.get(randomIndex);

            pos = moveToPos();
        }  
    }

    /**
     * Checks if the initial cube (Endpoint) can still move to a next valid position of the puzzle
     *  and moves it to the next position if possible
     * 
     * @return true if the initial cube can move to the next position
     *         false if there is no more possible moves 
     */
    private boolean moveFirstCube(){

        if(pos.getX() == size && pos.getY() == size && pos.getZ() == size){
            // all possible moves used
            return false;
        }

        if(pos.getY() < size){ // next y coordinate
            pos.translate(0, 1, 0);
            return true;
        }

        pos.translate(0, - (size - 1), 0); // next x coordinate
        if (pos.getX() < size){
            pos.translate(1, 0, 0);
            return true;
        }

        pos.translate(-(size - 1), 0, 0); // next z coordinate
        if (pos.getZ() < size){
            pos.translate(0, 0, 1);
            return true;
        }

        return false;
    }

    /**
     * Gets the size n of the puzzle Cube (2 for 2x2x2, 3 for 3x3x3, etc..)
     * @return size (int), the size of the puzzle
     * 
     */
    public static int getSize(){
        return size;
    }

    /**
     * Accessor for setting the size of the puzzle
     * @param n (int), new size
     * 
     */
    public static void setSize(int n){
        size = n;
    }

    /**
     * Checks if a puzzle has a valid solution with the current cube
     * 
     * @return true if the puzzle does have a solution
     *         false otherwise
     */
    private boolean findSolution(){

        if (previous == null)
            return true;
            
        while (!previous.isValid(pos.getX(), pos.getY(), pos.getZ())){
            possibleMoves.remove(move);

            if(!moveOn())
                return false;
        }

        return true;
    }

    /**
     * Checks if a the coordinates of a cube collides/overlaps with a given position
     * 
     * @param x coordinate x
     * @param y coordinate y
     * @param z coordinate z
     * 
     * @return true if the cube overlaps
     *         false otherwise
     */
    private boolean collide(int x, int y, int z){

        return pos.getX() == x && pos.getY() == y && pos.getZ() == z;
    }

    /**
     * Checks if a given positon does not exceed the borders of the cube
     * 
     * @param x coordinate x
     * @param y coordinate y
     * @param z coordinate z
     * 
     * @return true if the position is in borders
     *         false otherwise
     */
    private boolean isInBorders(int x, int y, int z){

        return x <= size && y <= size && z <= size &&
               x >= 1    && y >= 1    && z >= 1;
    }

    /**
     * Picks a random move for a cube
     */
    private void RandomMove(){

        Random random = new Random();

        int randomIndex = random.nextInt(possibleMoves.size());

        move = possibleMoves.get(randomIndex);
        
        pos = moveToPos();
    }

    /**
     * Checks if a given position is valid <=> is in borders, and does not overlap with previous cubes (can not threaten previous cubes)
     * 
     * @param x coordinate x
     * @param y coordinate y
     * @param z coordinate z
     * 
     * @return true if the given position is valid
     *         false otherwise
     */
    private boolean isValid(int x, int y, int z){

        if(!isInBorders(x, y, z) || collide(x, y, z))
            return false;

        if(previous == null && !collide(x, y, z))
            return true;

        return previous.isValid(x, y, z);
    }   

    /**
     * Makes the next move(random) for a cube and backtracks until we find new valid moves
     *  for previous cubes if the current cube does not have anymore possible moves
     * 
     * @return true if there is a possible valid move (possible partial solution)
     *         false if there is no way to find any solution or moves
     */
    private boolean moveOn(){

        if(previous == null)
            return moveFirstCube();

        if(possibleMoves.size() > 0){
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

    /**
     * Searches for a next solution for a cube
     *
     * @return true if there exists another solution
     *         false otherwise
     */
    private boolean nextSolution(){

        possibleMoves.remove(move);

        return moveOn() && findSolution();
    }

    /**
     * Gets all possible moves for a cube depending on the previous cube (or 2 previous cubes) 
     * 
     * @return possibleMoves, a list of all possible moves for the cube
     */
    private ArrayList<Move> getPossibleMoves(){

        if(previous.type == CubeType.STRAIGHT)
            possibleMoves.add(previous.move);
        
        else if(previous.type == CubeType.ANGLE){
            switch(previous.move){
                case UP :
                case DOWN:
                    possibleMoves.add(Move.LEFT);
                    possibleMoves.add(Move.RIGHT);
                    possibleMoves.add(Move.BACK);
                    possibleMoves.add(Move.FRONT);
                    break;

                case RIGHT:
                case LEFT:
                    possibleMoves.add(Move.UP);
                    possibleMoves.add(Move.DOWN);
                    possibleMoves.add(Move.FRONT);
                    possibleMoves.add(Move.BACK);
                    break;

                case FRONT :
                case BACK :
                    possibleMoves.add(Move.UP);
                    possibleMoves.add(Move.DOWN);
                    possibleMoves.add(Move.LEFT);
                    possibleMoves.add(Move.RIGHT);
                    break;

                default:
                break;
            }
        }

        else{
            possibleMoves.add(Move.UP);
            possibleMoves.add(Move.DOWN);
            possibleMoves.add(Move.LEFT);
            possibleMoves.add(Move.RIGHT);
            possibleMoves.add(Move.BACK);
            possibleMoves.add(Move.FRONT);
        }

        return possibleMoves;
    }
 
    /**
     * Translates a move to a position (Coordinates) starting from the previous cube Coordinates
     * 
     * @return c, the coordinates corresponding to the move
     */
    private Coordinates moveToPos(){

        Coordinates c = new Coordinates(previous.pos.getX(), previous.pos.getY(), previous.pos.getZ());

        switch (move){
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

    /**
     * Displays the coordinates of all created cubes
     * used to display a possible solution for the puzzle in form of coordinates (x, y, z) for each cube
     */
    private void display(){

        if(previous != null){
            previous.display();
        }

        System.out.println(pos.toString());   
    }

    /**
     * Solve the Snake Cube Puzzle with a given configuration
     * 
     * @param snake the configuration of the puzzle
     * 
     * @return c, the coordinates corresponding to the move
     */
    public static void solvePuzzle(Configuration snake){

        Cube c = null;

        List<CubeType> types = snake.getTypes();

        for (int i = 0; i < types.size(); i++){
            c = new Cube(types.get(i), c);
           
            if (!c.findSolution()){
                System.out.println("No solution found\n");
                return;
            }
        }

        c.display();
    }
    
}