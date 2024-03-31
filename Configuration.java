public class Configuration {

    private String input;

    Configuration(String[] args){
        if(args.length != 1){
            throw new IllegalArgumentException("Exactly one argument expected");
        }
        
        input = args[0];
        checkString();
    }

    private void checkString(){
        if(input == null || input.isEmpty()){
            throw new IllegalArgumentException("Exactly one argument expected");
        }
        else if(!input.matches("^[EAS]+$")){
            throw new IllegalArgumentException("Invalid characters detected. Only use 'E', 'A', or 'S'.");
        }
        else if(input.charAt(0) != 'E' || input.charAt(input.length() - 1) != 'E'){
            throw new IllegalArgumentException("Your configuration should start and end with an Endpoint : 'E'");
        }
    }

    
}
