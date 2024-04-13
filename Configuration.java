/*
 * INFO0062 - Object-Oriented Programming
 * Programming Project -- Academic Year 2023-2024
 *
 * This Configuration class represents the configuration of a snake cube puzzle. 
 * It validates the input data (string with E (endpoint), A (angle) and S (straight)) and converts it into
 * a list of cube types.
 * 
 * @author: ALBASHITYALSHAIER Abdelkader & BOUSTANI Mehdi 
 * @date : 14/04/2024
 */

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private final String input;
    private List<CubeType> types = new ArrayList<>();

    Configuration(String[] args){

        if(args.length != 1){
            throw new IllegalArgumentException("Invalid input data");
        }
        
        input = args[0];

        convertInput();        
    }

    /**
     * Check if the given input data is valid and convert it into a list of cube types
     */
    private void convertInput(){

        double n = Math.cbrt(input.length());

        Cube.setSize((int)(n)); // set Cube size to n

        if(Cube.getSize() == 1 && input.charAt(0) != 'E'){
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

    /**
     * Gets the list of cube types based on the puzzle's configuration
     * @return types, the list of cube types
     * 
     */
    public List<CubeType> getTypes() {
        return types;
    }
}