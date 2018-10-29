package Model.Excpetions;

public class WrongDetailsException extends V4UException{
        public WrongDetailsException(){
            super ("Invalid password or username, please try again");
        }
    }
