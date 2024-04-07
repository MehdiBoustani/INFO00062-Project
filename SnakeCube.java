import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

public class SnakeCube {   

    public static void main(String[] args) throws Exception{
        Configuration snake = null;
        try {
            snake = new Configuration(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

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
        // TEST DUPLICATES
        System.out.println("ArrayList contains duplicates: " + Cube.containsDuplicates());


                
    }

}
