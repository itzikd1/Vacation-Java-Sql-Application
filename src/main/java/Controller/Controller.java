package Controller;

import Model.Excpetions.V4UException;
import Model.Model;
import Model.User;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import Model.Excpetions.*;

public class Controller {

    private static Controller singleton = null;
    private Model model;

    private Controller() {
        this.model = Model.getInstance();
    }

    public static Controller getInstance() {
        if (singleton == null)
            singleton = new Controller();
        return singleton;
    }

    public Period getPeriod (Date date) throws TooYoungException {
        java.util.Date javaDate = new Date(date.getTime());
        LocalDate birthdate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        Period p = Period.between(birthdate, now);
        if (p.getYears() < 18)
            throw new TooYoungException();
        return p;
    }

    //call the model basic functions:
    //functions:

    public void insertNewUser(String table_name, Object[] data) throws V4UException {
        DatePicker bd;

        Date date=null;
        String[] details = new String[6];
        details[0] = (String) data[0]; //user_name
        details[1] = (String) data[1]; //password
        DatePicker x = (DatePicker)(data[2]);
        if(x.getValue()!=null) {
            bd = (DatePicker) data[2];
            date = java.sql.Date.valueOf((bd).getValue());
        }

        details[3] = (String) data[3]; //first name
        details[4] = (String) data[4]; //last name
        details[5] = (String) data[5]; //city

        if (details[1].isEmpty() || details[0].isEmpty() || details[5].isEmpty() || details[4].isEmpty() || details[3].isEmpty()) {
            throw new NotFilledAllFieldsException();

        } else { //connected_user's birthdate to java format
            Period p =getPeriod(date);
        }
        details[2] = date.toString(); //date string YYYY-MM-DD
        model.insert(table_name, details);
    }

    public boolean delete (String table_name, String id){
        return model.delete(id,table_name);
    }

    public String[] readUser (String table_name, String id){
        User user = (User)model.read(table_name, id);
        String[] details = user.getDetails();
        return details;
    }

    public boolean update (String table_name, Object[]data, String id) throws V4UException{

//        DatePicker x = (DatePicker)(data[2]);

        String user;
        String password;
        String fn;
        DatePicker bd;
        String ln;
        String city;
        Date date=null;

        user = (String) data[0];
        password = (String) data[1];

//        DatePicker x = (DatePicker)(data[2]);
        date = java.sql.Date.valueOf((data[2]).toString());
        fn = (String) data[3];
        ln = (String) data[4];
        city = (String) data[5];

        if (user.isEmpty() || password.isEmpty() || city.isEmpty() || ln.isEmpty() || fn.isEmpty()) {
            throw new NotFilledAllFieldsException();

        } else {
            //connected_user's birthdate to java format


            java.util.Date javaDate = new Date(date.getTime());
            LocalDate birthdate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            Period p = Period.between(birthdate, now);
            if (p.getYears() < 18)
                throw new TooYoungException();


        }
        data[2] = date.toString();

        String[] string_data = Arrays.copyOf(data, data.length, String[].class);


        return model.update(table_name, string_data);
    }

    public boolean confirmPassword (String table_name, String user, String password){
        try {
            return model.confirm(table_name, user, password);
        } catch (Exception e) {
            return false;
        }
    }

    public String[] readConnectedUser () {
        String[] details = model.readConnectedUser();
        return details;
    }

    public void log_out() {
        model.log_out();
    }
}
