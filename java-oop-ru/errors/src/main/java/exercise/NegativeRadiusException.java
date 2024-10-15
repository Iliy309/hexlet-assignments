package exercise;

// BEGIN
public class NegativeRadiusException extends Exception{

    public String text;

    public NegativeRadiusException(String text){
        this.text = text;
    }

    public NegativeRadiusException(){}
}
// END
