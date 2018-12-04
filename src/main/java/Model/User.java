package Model;

public class User {

    private String Username;
    private String Password;
    private String BDay;
    private String FName;
    private String LName;
    private String City;

    protected User(String username, String password, String BDay, String FName, String LName, String city) {
        this.Username = username;
        this.Password = password;
        this.BDay = BDay;
        this.FName = FName;
        this.LName = LName;
        City = city;
    }

    protected User(String username, String password) {
        Username = username;
        Password = password;
        this.BDay = null;
        this.FName = null;
        this.LName = null;
        City = null;
    }

    protected String getUsername() {
        return Username;
    }

    protected void setUsername(String username) {
        Username = username;
    }

    protected String getPassword() {
        return Password;
    }

    protected void setPassword(String password) {
        Password = password;
    }

    protected String getBDay() {
        return BDay;
    }

    public void setBDay(String BDay) {
        this.BDay = BDay;
    }

    protected String getFName() {
        return FName;
    }

    protected void setFName(String FName) {
        this.FName = FName;
    }

    protected String getLName() {
        return LName;
    }

    protected void setLName(String LName) {
        this.LName = LName;
    }

    protected String getCity() {
        return City;
    }

    protected void setCity(String city) {
        City = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", BDay='" + BDay + '\'' +
                ", FName='" + FName + '\'' +
                ", LName='" + LName + '\'' +
                ", City='" + City + '\'' +
                '}';
    }
}
