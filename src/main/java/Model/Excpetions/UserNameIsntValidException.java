package Model.Excpetions;

public class UserNameIsntValidException extends V4UException{
        public UserNameIsntValidException(){
            super ("This Username is already taken. \n please try another one");
        }
    }
