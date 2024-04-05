

public class SnakeCube {
    public static void main(String[] args) throws Exception{
        try {
            Configuration snake = new Configuration(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }


        // TESTS 

        Coordinates pos = new Coordinates(0, 0, 0);

        Cube c = new Cube(CubeType.ENDPOINT, pos, null);
        System.out.println("\nCube 1");
        System.out.println(Cube.getSize());
        // System.out.println(c.getPos().toString());
        System.out.println(c.moveOn());
        System.out.println(c.getPos().toString());

        Cube c2 = new Cube(CubeType.ANGLE, pos, c);

        System.out.println("\nCube 2");
        // System.out.println(c2.getPos().toString());
        System.out.println(c2.moveOn());
        System.out.println(c2.getPos().toString());

        Cube c3 = new Cube(CubeType.ANGLE, pos, c2);
        System.out.println("\nCube 3");
        // System.out.println(c3.getPos().toString());
        System.out.println(c3.moveOn());
        System.out.println(c3.getPos().toString());

        Cube c4 = new Cube(CubeType.ANGLE, pos, c3);
        System.out.println("\nCube 4");
        // System.out.println(c4.getPos().toString());
        System.out.println(c4.moveOn());
        System.out.println(c4.getPos().toString());

        Cube c5 = new Cube(CubeType.ANGLE, pos, c4);
        System.out.println("\nCube 5");
        // System.out.println(c4.getPos().toString());
        System.out.println(c5.moveOn());
        System.out.println(c5.getPos().toString());


        Cube c6 = new Cube(CubeType.ANGLE, pos, c5);
        System.out.println("\nCube 6");
        // System.out.println(c4.getPos().toString());
        System.out.println(c6.moveOn());
        System.out.println(c6.getPos().toString());

        Cube c7 = new Cube(CubeType.ANGLE, pos, c6);
        System.out.println("\nCube 7");
        // System.out.println(c4.getPos().toString());
        System.out.println(c7.moveOn());
        System.out.println(c7.getPos().toString());

        Cube c8 = new Cube(CubeType.ENDPOINT, pos, c7);
        System.out.println("\nCube 8");
        // System.out.println(c4.getPos().toString());
        System.out.println(c8.moveOn());
        System.out.println(c8.getPos().toString());

        


        


        // System.out.println(c.isValid(0, 0, 0));


        
    }
}
