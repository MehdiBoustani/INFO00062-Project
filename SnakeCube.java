

public class SnakeCube {
    public static void main(String[] args) throws Exception{
        try {
            Configuration snake = new Configuration(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        
    }
}
