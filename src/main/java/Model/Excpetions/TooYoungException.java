package Model.Excpetions;

public class TooYoungException extends V4UException{
    public TooYoungException() {
        super("You are not 18. come back when you will be older!");
    }
}
