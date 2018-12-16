package Model.Excpetions;

public class WrongFlyingDatesInfoException extends V4UException {
    public WrongFlyingDatesInfoException(){
        super("The flying dates or times you inserted are in wrong format or make no sense. \n Please check it try again" + "\ntime should be in hh:mm format and return/arrival date should be AFTER departure date" );
    }
}
