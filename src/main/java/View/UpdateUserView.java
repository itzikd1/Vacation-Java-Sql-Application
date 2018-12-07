package View;

import Controller.Controller;
import Model.Excpetions.V4UException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdateUserView implements Initializable {
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
        tf_username.setText(details[0]);
        tf_password.setText(details[1]);
        tf_firstName.setText(details[3]);
        tf_lastName.setText(details[4]);
        tf_city.setText(details[5]);
        //birthday
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //todo: not disconnent of db sometimes
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(details[2], formatter);
        bd.setValue(localDate);

    }

    public void update_info(ActionEvent actionEvent) {
        /*
        show current connected_user's details by default
         */

        String user, city, ln, fn, password;
        Date date = new Date();

        user = tf_username.getText();
        city = tf_city.getText();
        ln = tf_lastName.getText();
        fn = tf_firstName.getText();
        password = tf_password.getText();


        Object[] updatedDetails = new Object[]{user, password, date, fn, ln, city};
        updatedDetails[2] = bd.getValue();
        boolean flag = false;
        try {
            flag = controller.update("Users", updatedDetails, (String) updatedDetails[0]);
        } catch (V4UException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        }
        if (flag) {
            Stage s = (Stage) BackButton.getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Confirm");
            alert.setHeaderText("Your details have been updated!");
            alert.showAndWait();
            s.close();
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
