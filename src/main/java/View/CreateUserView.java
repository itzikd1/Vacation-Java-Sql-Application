package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class CreateUserView {
    public TextField tf_city;
    public TextField tf_lastName;
    public TextField tf_firstName;
    public PasswordField tf_password;
    public DatePicker bd;
    public TextField tf_username;
    public Button send_button;
    public Button BackButton;

    private Controller controller = Controller.getInstance();

    public void send_info(ActionEvent actionEvent) {
        String user, city, ln, fn, password;
        Date date;
        user = tf_username.getText();
        city = tf_city.getText();
        ln = tf_lastName.getText();
        fn = tf_firstName.getText();
        password = tf_password.getText();
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
                Object[] user_date = new Object[]{user, password, date, fn, ln, city};
                boolean flag = controller.insert("Users", user_date);
                if (!flag) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please Put Valid Information");
                    alert.showAndWait();
                    System.out.println("error");
                } else {
                    Stage s = (Stage) BackButton.getScene().getWindow();
                    s.close();
                    ChangeScene();
                    System.out.println(user + "has been added");
                }



        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please Put A Valid Date");
//            alert.setContentText("gfddf");
            alert.showAndWait();
        }
    }

    private void updateLoginOnGui() {
//     //   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MainPage.fxml"));
//
//        fxmlLoader.getController();
//        MainPageView mainPageView = fxmlLoader.getController();
//        mainPageView.disable_loginInfo();
        //TODO: catch the controller
    }

    private void ChangeScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainPage.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(true);
            stage.setTitle("Vacation4U");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
//        ChangeScene();
    }
}
