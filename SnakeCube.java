

public class SnakeCube {
    public static void main(String[] args) throws Exception{
        try {
            Configuration config = new Configuration(args);
        } catch (IllegalArgumentException e) {
            System.err.println("Configuration error : " + e.getMessage());
        }
    }
}
