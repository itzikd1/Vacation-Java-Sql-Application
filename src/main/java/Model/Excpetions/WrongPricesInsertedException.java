package Model.Excpetions;

public class WrongPricesInsertedException extends V4UException {
    public WrongPricesInsertedException() {
        super("Please insert only digits on Price field.");
    }
}


