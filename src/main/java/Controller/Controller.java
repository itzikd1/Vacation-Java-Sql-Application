package Controller;

import Model.Model;
import View.MainPageView;

import java.util.Calendar;

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


        return model.insert(table_name, data);
    }

    public boolean delete(String table_name, String id) {
        return model.delete(table_name, id);
    }

    public String [] read(String table_name, String id) {
        String [] details = model.read(table_name, id);
        return details;//TODO SHEKER
    }

    public boolean update(String table_name, Object[] data, String id) {
        return model.update(table_name, data, id);
    }

    public boolean confirmPassword(String table_name, String user, String password) {
        try {
            return model.confirm(table_name, user, password);
        } catch (Exception e) {
            return false;
        }
    }

    public String[] readConnectedUser(){
        String [] details = model.readConnectedUser();
        return details;
    }

    public void saveUser(String user) {
        model.read("Users",user);
    }

}
