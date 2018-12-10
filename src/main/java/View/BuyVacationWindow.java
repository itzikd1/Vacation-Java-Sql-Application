package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BuyVacationWindow implements Initializable {
    public ToggleButton toggleButton = new ToggleButton("Card");
    public Button BackButton;
    public TextField CardCompany;
    public TextField CardNumber;
    public TextField PayPalUser;
    public boolean buttonStatus = false;
    private Controller controller = Controller.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }

    public void pay(ActionEvent actionEvent) {
        String Cardnumber = "";
        String Paypaluser = "";
        String payMentMethod = "";
        String CreditCard = "";
        if (buttonStatus == false) {
            if (CardNumber.getLength() != 0 && CardCompany.getLength() != 0) {
                Cardnumber = CardNumber.getText();
                payMentMethod = "Credit Card";
                CreditCard = CardCompany.getText();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Missing Fields");
                alert.setHeaderText("Please Fill Your Credit Card Number And Company");
                alert.showAndWait();
            }
        } else {
            if (PayPalUser.getLength() != 0) {
                Paypaluser = PayPalUser.getText();
                payMentMethod = "PayPal";
                // TODO: 10/12/2018 update database with request  Itzik
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Missing PayPal User");
                alert.setHeaderText("Please Fill Your PayPal UserName");
                alert.showAndWait();
            }
        }
        // TODO: 10/12/2018 after getting maors code,fix here and add alert  Itzik
//        controller.insert_purchase(Cardnumber,payMentMethod,Paypaluser);
    }

    public void toggleChange(ActionEvent actionEvent) {

        if (buttonStatus == false) {
            buttonStatus = true;
            PayPalUser.setDisable(false);
            toggleButton.setText("PayPal");
            CardNumber.setDisable(true);
            CardCompany.setDisable(true);
        } else {
            buttonStatus = false;
            toggleButton.setText("Credit Card");
            PayPalUser.setDisable(true);
            CardNumber.setDisable(false);
            CardCompany.setDisable(false);
        }
    }

    public void priceUpdate(ActionEvent actionEvent) {
        // TODO: 10/12/2018 update price here of vacation threw bind id  Itzik
    }
}
