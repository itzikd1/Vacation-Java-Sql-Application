package Model;

public abstract class V4UException extends Exception {
    public V4UException(String s) {
        super(s);

    }
    public static class TooYoungException extends V4UException{
        public TooYoungException() {
            super("You are not 18. come back when you will be older!");
        }
    }
    public static class NotFilledAllFieldsException extends V4UException{
        public NotFilledAllFieldsException(){
            super ("Please fill all fields");
        }
    }
    public static class UserNameIsntValidException extends V4UException{
        public UserNameIsntValidException(){
            super ("This Username is already taken./n please try another one");
        }
    }
    public static class WrongDetailsException extends V4UException{
        public WrongDetailsException(){
            super ("Invalid password or username, please try again");
        }
    }
}


