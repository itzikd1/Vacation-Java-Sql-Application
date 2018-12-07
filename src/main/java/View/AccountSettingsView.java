package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccountSettingsView implements Initializable {

    public Button update_button;
    public Button search_button;
    public Button delete_button;
    public Button BackButton;
    private Controller controller = Controller.getInstance();


    //pointers to buttons and textfields from Main Controller:
    public Button ref_create_user_button;
    public Button ref_log_in_button;
    public Button ref_sign_out_button;
    public TextField ref_user_name_tf;
    public PasswordField ref_password_tf;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButtons();

    }


    public void update_user(ActionEvent actionEvent) {
        Stage s = (Stage) update_button.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UpdateUser.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(true);
            stage.setTitle("UpdateInfo");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search_user(ActionEvent actionEvent) {
        Stage s = (Stage) update_button.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SearchUser.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(true);
            stage.setTitle("Search User");
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
    }

    public void delete_user(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Are you sure you want to delete_user your account?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            boolean flag = controller.delete_user();
            if (flag) {
                delete_command_to_main_page();

            } else {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to delete_user connected_user");
                alert.showAndWait();
            }
        }
    }

    public void delete_command_to_main_page(){

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MainPage.fxml"));
        try {
            AnchorPane frame = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainPageView c = (MainPageView) fxmlLoader.getController();
        ref_create_user_button = c.create_button;
        ref_sign_out_button = c.sign_out;
        ref_log_in_button = c.login_button;

        DisableButtons();
        ref_create_user_button.setDisable(false);
        ref_sign_out_button.setVisible(false);
        c.enableLoginInfo();
        ref_log_in_button.setDisable(false);
        c.cleanTextFields();
    }

    private void enableButtons() {
        //enable buttons
        search_button.setDisable(false);
        update_button.setDisable(false);
        delete_button.setDisable(false);
    }

    private void DisableButtons() {
        //disable buttons
        search_button.setDisable(true);
        update_button.setDisable(true);
        delete_button.setDisable(true);
    }




}
