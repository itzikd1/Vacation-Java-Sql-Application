package View;

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
import java.util.*;

public class CreateUserView implements Initializable {
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
        user = tf_username.getText();
        city = tf_city.getText();
        ln = tf_lastName.getText();
        fn = tf_firstName.getText();
        password = tf_password.getText();

//            java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
//            java.util.Date todayJavaDate = new Date(today.getTime());
//            LocalDate localTodayDate = todayJavaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();



            //TODO if(p.getYears()>=18) + errormsg
        Object[] user_details = new Object[]{user, password, bd, fn, ln, city};
                boolean flag = controller.insert("Users", user_details);
                if (!flag) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please Put Valid Information");
                    alert.showAndWait();
                    System.out.println("error");
                } else {
                    Stage s = (Stage) BackButton.getScene().getWindow();
                    s.close();
                    //ChangeScene();
                    System.out.println(user + " has been added");
                    updateLoginOnGui();

                }


// TODO: to add eceptions -> catch them here, if DateEcepction will happen, we will show alert
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Error");
//            alert.setHeaderText("Please Put A Valid Date");
//            alert.setContentText("gfddf");
//            alert.showAndWait();

    }

    private void updateLoginOnGui() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Security Alert");
        alert.setHeaderText("You have been signed up!\nPlease log in using your new account information");
        alert.showAndWait();

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MainPage.fxml"));
//        Parent root2 = null;
//        try {
//            root2 = (Parent) fxmlLoader.load();
//            MainPageView mainPageView = fxmlLoader.getController();
//
//            Stage s  = (Stage)mainPageView.login_button.getScene().getWindow();
//            s.close();
//            mainPageView.disable_loginInfo();
//            mainPageView.login_pane.setVisible(true);
//            //TODO: catch the controller and hide the login (in the future the login will be on different page)
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //nothing
    }
}
