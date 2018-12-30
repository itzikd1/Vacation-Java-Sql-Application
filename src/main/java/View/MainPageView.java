package View;

import Controller.Controller;
import Model.Excpetions.WrongDetailsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainPageView implements Initializable {
    public ImageView image_background;
    public Button sign_up_button;
    public Button sign_out;
    public Button update_button;
    public Button search_button;
    public Button delete_button;
    public Button login_button;
    public AnchorPane account_settings;
    public Pane login_pane;
    public Button sell_vacation_button;
    public Button search_vacation_button;
    public Button my_vacations_as_seller;
    public Button my_vacations_as_buyer;
    public Button my_vacations_b;
    public Button purchases_sells_button;


    public TextField tf_username;
    public PasswordField tf_password;
    private Controller controller = Controller.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void create_user(ActionEvent actionEvent) {
        Stage s = (Stage) sign_up_button.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CreateUser.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
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
                "Are you sure you want to delete your account?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            boolean flag = controller.delete_user();
            if (flag) {
                //DisableButtons();
                //sign_up_button.setDisable(false);
                //sign_out.setVisible(false);
                //enableLoginInfo();
                cleanTextFields();
                switchToLogout();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to delete_user connected_user");
                alert.showAndWait();
            }
        }
    }



    public void update_user(ActionEvent actionEvent) {
        Stage s = (Stage) update_button.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UpdateUser.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Update Info");
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
            stage.setResizable(false);
            stage.setTitle("Search User");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sign_in(ActionEvent actionEvent) {
        String user, password;
        user = tf_username.getText();
        password = tf_password.getText();
        boolean flag = controller.confirmPassword("Users", user, password);
        if (flag) {
            //enableButtons();
            switchToLogged();
            //disable_loginInfo();
        } else {
            try {
                throw new WrongDetailsException();
            } catch (WrongDetailsException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();

            }
        }
        //login_pane.setVisible(false);
    }




    public void log_out(ActionEvent actionEvent) {
       // DisableButtons();

        //enableLoginInfo();
        controller.log_out();
        switchToLogout();

    }

    void cleanTextFields() {
        tf_password.clear();
        tf_username.clear();
    }



    private void switchToLogged(){
        login_pane.setVisible(false);
        login_button.setVisible(false);
        sign_up_button.setVisible(false);
        sign_out.setVisible(true);
        //sell_vacation_button.setDisable(false);
        account_settings.setVisible(true);
        my_vacations_as_seller.setDisable(false);
        my_vacations_as_buyer.setDisable(false);
        my_vacations_b.setDisable(false);
        purchases_sells_button.setDisable(false);
    }
    private void switchToLogout() {
        account_settings.setVisible(false);
        login_button.setVisible(true);
        sign_up_button.setVisible(true);
        cleanTextFields();
        sign_out.setVisible(false);
        //sell_vacation_button.setDisable(true);
        login_pane.setVisible(true);
        my_vacations_as_seller.setDisable(true);
        my_vacations_as_buyer.setDisable(true);
        my_vacations_b.setDisable(true);
        purchases_sells_button.setDisable(true);
    }


    public void search_vacation(ActionEvent actionEvent) {
        Stage s = (Stage) search_vacation_button.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FindMeADeal.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Search Vacation");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sell_vacation(ActionEvent actionEvent) {
        if (controller.get_connected_user_id() ==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Log in");
            alert.setHeaderText("Only signed users are allowing to public vacations for sell\nPlease log in and try again");
            alert.showAndWait();
            return;
        }
        Stage s = (Stage) sell_vacation_button.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SellVacationWindow.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Sell Vacation");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void my_vacations_seller_command(ActionEvent actionEvent) {
        Stage s = (Stage) my_vacations_as_seller.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RequestsForMyVacations.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Requests for my Vacations");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void my_vacations_buyer_command(ActionEvent actionEvent) {
        Stage s = (Stage) my_vacations_as_seller.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyRequests.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("My Requests");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void my_vacations(ActionEvent actionEvent) {
        Stage s = (Stage) my_vacations_b.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyVacations.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("My Vacations");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void purchases_sells_command(ActionEvent actionEvent) {
        Stage s = (Stage) my_vacations_b.getScene().getWindow();
//        s.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PurchasesAndSells.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Purchases & Sells");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


