import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private String input;
    private List<Movement> moves = new ArrayList<>();

    Configuration(String[] args){
        if(args.length != 1){
            throw new IllegalArgumentException("Invalid input data");
        }
        
        input = args[0];
        checkString();
        convertInput();
        
    }

    private void checkString(){

        double n = Math.cbrt(input.length());
        int count = 0;
        
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == 'E'){
                count++;
            }
        }

        if((!input.matches("^[EAS]+$")) || (count != 2 || input.charAt(0) != 'E' || input.charAt(input.length() - 1) != 'E') || Math.floor(n) != n){
            throw new IllegalArgumentException("Invalid input data");
        }
        
    }

    private void convertInput(){

        for(int i = 0; i < input.length(); i++){
            switch (input.charAt(i)) {
                case 'E':
                    moves.add(Movement.ENDPOINT);
                    break;
                case 'A':
                    moves.add(Movement.ANGLE);
                    break;
                case 'S':
                    moves.add(Movement.STRAIGHT);
                    break;
            
                default:
                    break;
            }
        }
    }

    public List<Movement> getMoves() {
        return moves;
    }

    
}
