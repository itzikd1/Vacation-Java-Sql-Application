package Model.Excpetions;

public class WrongBaggageInfoException extends V4UException {


    public WrongBaggageInfoException(){
        super("If you marked baggage checkbox, please write the details.\n" +
                "Otherwise, leave the textbox empty.");
    }
}
