import java.lang.Math;

public class Configuration {

    private String input;

    Configuration(String[] args){
        if(args.length != 1){
            throw new IllegalArgumentException("Invalid input data");
        }
        
        input = args[0];
        checkString();
    }

    private void checkString(){

        double n = Math.cbrt(input.length());
        int count = 0;
        
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == 'E'){
                count++;
            }
        }

        if(input == null || input.isEmpty() || (!input.matches("^[EAS]+$")) || (count != 2 || input.charAt(0) != 'E' || input.charAt(input.length() - 1) != 'E') || Math.floor(n) != n){
            throw new IllegalArgumentException("Invalid input data");
        }
        
    }

    
}
