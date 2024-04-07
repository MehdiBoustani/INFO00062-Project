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
        checkString();
        convertInput();        
    }

    private void checkString(){

        double n = Math.cbrt(input.length());
        Cube.setSize((int)(n)); // set Cube size to n
        
        int count = 0;

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == 'E'){
                count++;
            }
        }

        if((!input.matches("^[EAS]+$")) || n > 1 && (count != 2 || input.charAt(0) != 'E' || input.charAt(input.length() - 1) != 'E') || Math.floor(n) != n){
            throw new IllegalArgumentException("Invalid input data");
        }
    }

    private void convertInput(){
        for(int i = 0; i < input.length(); i++){
            switch (input.charAt(i)) {
                case 'E':
                    types.add(CubeType.ENDPOINT);
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
    }

    public List<CubeType> getTypes() {
       
        return types;
    }

    
}
