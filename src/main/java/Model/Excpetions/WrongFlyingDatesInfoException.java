package Model.Excpetions;

public class WrongFlyingDatesInfoException extends V4UException {
    public WrongFlyingDatesInfoException(){
        super("The flying dates you inserted are wrong. \n Please try again");
    }
}
