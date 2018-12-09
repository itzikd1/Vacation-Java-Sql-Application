package Controller;

import Model.Excpetions.NotFilledAllFieldsException;
import Model.Excpetions.TooYoungException;
import Model.Excpetions.V4UException;
import Model.Model;
import Model.User;
import Model.Vacation;
import View.RequestForSellerColumn;
import View.VacationsForSearchColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
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

    public Period getPeriod(Date date) throws TooYoungException {
        java.util.Date javaDate = new Date(date.getTime());
        LocalDate birthdate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        Period p = Period.between(birthdate, now);
        if (p.getYears() < 18)
            throw new TooYoungException();
        return p;
    }


    public void insertNewVacation(String tableName, Object[] vacation_details) throws V4UException {
        DatePicker departure_date, arrival_date, return_date;
        Date date = null;
        String[] details = new String[17];
        details[0] = String.valueOf(model.getNextVacationID());
        details[1] = model.connected_user.getDetails()[0];
        details[2] = (String)vacation_details[0]; // from
        departure_date = (DatePicker) vacation_details[1];
        if(departure_date.getValue()!=null) {
            departure_date = (DatePicker) vacation_details[1];
            date = java.sql.Date.valueOf((departure_date).getValue());
        }
        details[3] = date.toString();// departure date
        details[4] = (String)vacation_details[2]; //departure time
        details[5] = (String)vacation_details[3]; // destination
        arrival_date = (DatePicker) vacation_details[4];
        if(arrival_date.getValue()!=null) {
            arrival_date = (DatePicker) vacation_details[4];
            date = java.sql.Date.valueOf((arrival_date).getValue());
        }
        details[6] = date.toString(); // arrival date
        details[7] = (String) vacation_details[5]; // arrival time
        return_date = (DatePicker) vacation_details[6];
        if(return_date.getValue()!=null) {
            return_date = (DatePicker) vacation_details[6];
            date = java.sql.Date.valueOf((return_date).getValue());
        }
        details[8] = date.toString(); // return date
        details[9] = (String)vacation_details[7]; // return time
        details[10] = (String)vacation_details[8]; //ticket type
        details[11] = (String)vacation_details[9]; //company
        details[12] = (String)vacation_details[10]; //connection country
        boolean isBaggage = ((CheckBox)vacation_details[11]).isSelected();
        details[13] = String.valueOf(isBaggage); //boolean baggageinclude
        details[14] = (String)vacation_details[12]; //baggage options
        details[15] = (String)vacation_details[13]; //class type
        details[16] = (String)vacation_details[14]; //price

        model.insert(tableName, details);
    }

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

    public boolean delete_user(){
        return model.delete_user();
    }

    public String[] readUser (String id) {
        User user = (User) model.read("Users", id);
        String[] details = user.getDetails();
        return details;
    }

    public String[] readVacation (String id){
        Vacation vacation = (Vacation)model.read("Vacations", id);
        String[] details = vacation.getDetails();
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

    public Object[] readAll(String tableName){
        Object[] data = model.readAll(tableName);
        return null;
    }


    public ObservableList<VacationsForSearchColumn> getVacationsForSearch() {
        ObservableList<VacationsForSearchColumn> vacations = FXCollections.observableArrayList();
        Object[] o = model.readAll("Vacations");
        for (int i = 0; i < o.length; i++) {
            if (o[i] instanceof Vacation) {
                Vacation v = (Vacation) o[i];
                if (chceckVacationDate(v) == true)
                    vacations.add(new VacationsForSearchColumn(v, new Button(), new Button()));
            } else System.out.println("wrong table in controller getVacationsForSearch");
        }
        return vacations;
    }


    private boolean chceckVacationDate(Vacation v) {
        LocalDate localDate = LocalDate.now();
        String[] TodayDate = localDate.toString().split("-");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date Depart = null;
        Date current = null;
        try {
            Depart = format.parse(v.getDepartureDate());
            current = format.parse(localDate.toString());
            if (Depart.compareTo(current) >= 0)
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String get_connected_user_id() {
        return model.get_connected_user_id();
    }

    public boolean insertBuyingRequest(String vacationID, String Seller_user_name) throws V4UException  {
        String bit = "0"; //because this not approved yet
        String buyerID = get_connected_user_id();
        String req_id = String.valueOf(model.getNextRequestID());
        String[] details = {req_id,vacationID,Seller_user_name,buyerID,bit};
        return model.insertBuyingRequest(details);
    }

    public ObservableList<RequestForSellerColumn> getRequestsForSellerTable() {
        //todo: edit this function.
        return null;

    }
}

