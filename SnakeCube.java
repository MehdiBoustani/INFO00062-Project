

public class SnakeCube {
    public static void main(String[] args) throws Exception{
        try {
            Configuration snake = new Configuration(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }


        // TESTS 

        Coordinates pos = new Coordinates(1, 1, 1);
        Cube c = new Cube(pos, null);
        System.out.println(Cube.getSize());
        System.out.println(c.isValid(c.getPos()));


        
    }
}
