package Model.Excpetions;

public class NotFilledAllFieldsException extends V4UException{
        public NotFilledAllFieldsException(){
            super ("Please fill all fields");
        }
    }
