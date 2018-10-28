package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainPageView implements Initializable {
    public ImageView image_background;
    public Button create_button;
    public Button sign_out;
    public Button update_button;
    public Button read_button;
    public Button login_button;
    public Button delete_button;
    public TextField tf_username;
    public PasswordField tf_password;
    private Controller controller = Controller.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void create_user(ActionEvent actionEvent) {
        Stage s = (Stage) create_button.getScene().getWindow();
        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CreateUser.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(true);
            stage.setTitle("Create User");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete_user(ActionEvent actionEvent) {
        String user, password;
        user = tf_username.getText();
        password = tf_password.getText();
        Object[] user_date = new Object[]{user, password};
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Are you sure you want to delete the user?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            boolean flag = controller.delete("Users", user);
            if (flag == true) {
                DisableButtons();
                create_button.setDisable(false);
                sign_out.setVisible(false);
                login_button.setDisable(false);
            }
            else
            {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to delte user");
                alert.showAndWait();
            }
        }
    }

    public void sign_in(ActionEvent actionEvent) {
        //TODO: log in the user by model. we will be needed to add USERNAME member who will update
        String user, password;
        user = tf_username.getText();
        password = tf_password.getText();
        boolean flag = controller.confirmPassword("Users", user, password);
        if (flag == true) {
            enableButtons();
            create_button.setDisable(true);
            sign_out.setVisible(true);
            login_button.setDisable(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Login Failed Username Or Password Incorrect");
            alert.showAndWait();
        }
    }

    // TODO: 28/10/2018  Itzik
    public void update_user(ActionEvent actionEvent) {
        Stage s = (Stage) update_button.getScene().getWindow();
        s.close();
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

    // TODO: 28/10/2018 TODO this Itzik
    public void read_user(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("we havnt done this yet");
        alert.showAndWait();
    }

    public void log_out(ActionEvent actionEvent) {
        DisableButtons();
        create_button.setDisable(false);
        sign_out.setVisible(false);
        login_button.setDisable(false);
    }

    private void enableButtons() {
        //enable buttons
        read_button.setDisable(false);
        update_button.setDisable(false);
        delete_button.setDisable(false);
    }

    private void DisableButtons() {
        //disable buttons
        read_button.setDisable(true);
        update_button.setDisable(true);
        delete_button.setDisable(true);
    }
}


