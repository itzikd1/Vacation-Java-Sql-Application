package View;
import javafx.util.*;
import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdateUser implements Initializable {
    public TextField tf_city;
    public TextField tf_lastName;
    public TextField tf_firstName;
    public PasswordField tf_password;
    public DatePicker bd;
    public TextField tf_username;
    public Button update_button;
    public Button BackButton;

    private Controller controller = Controller.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] details = controller.readConnectedUser();
        DatePicker dpbd = new DatePicker();
        tf_username.setText(details[0]);
        tf_password.setText(details[1]);
        tf_firstName.setText(details[3]);
        tf_lastName.setText(details[4]);
        tf_city.setText(details[5]);
        //birthday
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(details[2], formatter);
        bd.setValue(localDate);

    }

    public void update_info(ActionEvent actionEvent) {
        /*
        show current user's details by default
         */

        String user, city, ln, fn, password;
        Date date = new Date();

        user = tf_username.getText();
        city = tf_city.getText();
        ln = tf_lastName.getText();
        fn = tf_firstName.getText();
        password = tf_password.getText();

        //TODO: to fix the case username is already exists
        if (user.isEmpty() || password.isEmpty() || city.isEmpty() || ln.isEmpty() || fn.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all the info");
            alert.showAndWait();
        } else if (bd.getValue() != null) {
            //user's birthdate to java format
            date = java.sql.Date.valueOf(bd.getValue());
            java.util.Date javaDate = new Date(date.getTime());
            LocalDate birthdate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
//            java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
//            java.util.Date todayJavaDate = new Date(today.getTime());
//            LocalDate localTodayDate = todayJavaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


            Period p = Period.between(birthdate,now);
            //TODO if(p.getYears()>=18) + errormsg
            System.out.println("user created : " + user + " " + fn + " " + ln + " " + city + " " + date.toString() + " ");
            Object[] updatedDetails = new Object[]{ user, password, date, fn, ln, city};
            boolean flag = controller.update("Users", updatedDetails, (String)updatedDetails[0]);
            if (!flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("update failed");
                alert.showAndWait();
                System.out.println("error");
            } else {
                Stage s = (Stage) BackButton.getScene().getWindow();
                System.out.println(user + " has been updated");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(user + " has been updated!");
                alert.showAndWait();
                s.close();
            }



        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please Put A Valid Date");
//            alert.setContentText("gfddf");
            alert.showAndWait();
        }
    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
//        try {
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainPage.fxml"));
//            Stage stage = new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setResizable(true);
//            stage.setTitle("Vacation4U");
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}
