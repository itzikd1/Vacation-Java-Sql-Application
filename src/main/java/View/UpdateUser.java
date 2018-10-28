package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
        tf_city.setPromptText("test");
    }
    // TODO: 28/10/2018 i want to pull user from DB and put info in the text boxs when initialized Itzik
    // TODO: 28/10/2018 Maybe like this? Itzik
//    public void start(Stage primaryStage) {
//        tf_username.setText("test");
//
//    }
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//    }
//

    public void update_info(ActionEvent actionEvent) {
        String [] details = controller.readConnectedUser();
        String user, city, ln, fn, password;
        Date date = new Date();
        // TODO: 28/10/2018 i want to pull user from DB and put info in the text boxs when initialized Itzik
        //TODO this does this only after pressing the button update info, i want it to happen before
        tf_username.setText(details[0]);
        tf_city.setPromptText(details[1]);
        tf_firstName.setText(details[2]);
        tf_lastName.setText(details[3]);
        tf_city.setText(details[4]);
        tf_password.setText(details[5]);
        // TODO: 28/10/2018 now we go to the database and do delete and create, or update Itzik
    }


    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
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


}
