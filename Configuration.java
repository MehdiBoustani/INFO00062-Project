import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private String input;
    private List<CubeType> types = new ArrayList<>();

    Configuration(String[] args){
        if(args.length != 1){
            throw new IllegalArgumentException("Invalid input data");
        }
        
        input = args[0];
        convertInput();        
    }

    private void convertInput(){

        double n = Math.cbrt(input.length());
        Cube.setSize((int)(n)); // set Cube size to n

        if (Cube.getSize() == 1 && input.charAt(0) != 'E'){
            throw new IllegalArgumentException("Invalid input data");
        }
        
        int count = 0;

        for(int i = 0; i < input.length(); i++){
            switch (input.charAt(i)) {
                case 'E':
                    types.add(CubeType.ENDPOINT);
                    count++;
                    break;
                case 'A':
                    types.add(CubeType.ANGLE);
                    break;
                case 'S':
                    types.add(CubeType.STRAIGHT);
                    break;
            
                default:
                    break;
            }
        }

        if((!input.matches("^[EAS]+$")) || n > 1 && (count != 2 || input.charAt(0) != 'E' || input.charAt(input.length() - 1) != 'E') || Math.floor(n) != n){
            throw new IllegalArgumentException("Invalid input data");
        }
    }

    public List<CubeType> getTypes() {
       
        return types;
    }

    
}