package Controller;

import Model.Model;
import View.MainPageView;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

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


    //call the model basic functions:
    //functions:

    public boolean insert(String table_name, Object[] data) {
        String user;
        String password;
        String fn;
        DatePicker bd;
        String ln;
        String city;
        Date date=null;

            user = (String) data[0];
            password = (String) data[1];
            DatePicker x = (DatePicker)(data[2]);
            if(x.getValue()!=null) {
                bd = (DatePicker) data[2];
                date = java.sql.Date.valueOf((bd).getValue());
            }
            else{
                return false;
            }

            fn = (String) data[3];
            ln = (String) data[4];
            city = (String) data[5];

        if (user.isEmpty() || password.isEmpty() || city.isEmpty() || ln.isEmpty() || fn.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all the info");
            alert.showAndWait();
            return false;
        } else {
            //user's birthdate to java format


            java.util.Date javaDate = new Date(date.getTime());
            LocalDate birthdate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            Period p = Period.between(birthdate, now);


        }
        data[2]=date;
        return model.insert(table_name, data);
    }

    public boolean delete (String table_name, String id){
        return model.delete(table_name, id);
    }

    public String[] read (String table_name, String id){
        String[] details = model.read(table_name, id);
        return details;
    }

    public boolean update (String table_name, Object[]data, String id){
        return model.update(table_name, data, id);
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

    public void saveUser (String user){
        model.read("Users", user);
    }
}
