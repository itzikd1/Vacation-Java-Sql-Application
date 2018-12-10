package View;

import Controller.Controller;
import Model.Excpetions.WrongPricesInsertedException;
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
    public TextField price;
    public boolean buttonStatus = false;
    private Controller controller = Controller.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        price.setText(controller.getPriceForCurrentVacation()+"$");
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
                return;
            }
        } else {
            if (PayPalUser.getLength() != 0) {
                Paypaluser = PayPalUser.getText();
                payMentMethod = "PayPal";
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Missing PayPal User");
                alert.setHeaderText("Please Fill Your PayPal UserName");
                alert.showAndWait();
                return;
            }
        }
        try{
            Integer.parseInt((String)Cardnumber);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please insert only digits in credit card field");
            alert.showAndWait();
        }
           boolean flag = controller.insert_purchase(Cardnumber,payMentMethod,Paypaluser);
        if (flag) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("The vacation is yours! enjoy it!");
            alert.showAndWait();
            Stage s = (Stage) BackButton.getScene().getWindow();
            s.close();
        }
        else {
            System.out.println("problem in inserting purchase");
            return;
        }


    }

    public void toggleChange(ActionEvent actionEvent) {

        if (buttonStatus == false) {
            buttonStatus = true;
            PayPalUser.setDisable(false);
            toggleButton.setText("Credit card");
            CardNumber.setDisable(true);
            CardCompany.setDisable(true);

        } else {
            buttonStatus = false;
            toggleButton.setText("PayPal");
            PayPalUser.setDisable(true);
            CardNumber.setDisable(false);
            CardCompany.setDisable(false);
        }
    }


}
