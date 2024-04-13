/*
 * INFO0062 - Object-Oriented Programming
 * Programming Project -- Academic Year 2023-2024
 *
 * This SnakeSube class serves as the entry point to solve the snake cube puzzle. 
 * It initializes the puzzle configuration and invokes the solver.
 * 
 * @author: ALBASHITYALSHAIER Abdelkader & BOUSTANI Mehdi 
 * @date : 14/04/2024
 */

public class SnakeCube {   
    public static void main(String[] args) throws Exception{

        Configuration snake = null;
        
        try{
            snake = new Configuration(args);

        }catch (IllegalArgumentException e){

            System.err.println(e.getMessage());

            return;
        }

        Cube.solvePuzzle(snake); 
    }
}
