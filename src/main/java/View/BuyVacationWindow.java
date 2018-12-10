package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BuyVacationWindow implements Initializable {
    public ToggleButton toggleButton;
    public RadioButton r2;
    public RadioButton r1;
    public Button BackButton;
    public TextField CardCompany;
    public TextField CardNumber;
    public TextField PayPalUser;
    public boolean buttonStatus=false;
    private Controller controller = Controller.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }

    public void pay(ActionEvent actionEvent) {
        // TODO: 10/12/2018 update database with request  Itzik
    }

    public void toggleChange(ActionEvent actionEvent) {

        if (buttonStatus==false)
        {
            buttonStatus=true;
            PayPalUser.setDisable(false);
            CardNumber.setDisable(true);
            CardCompany.setDisable(true);
        }
        else {
            buttonStatus=false;
            PayPalUser.setDisable(true);
            CardNumber.setDisable(false);
            CardCompany.setDisable(false);
        }
    }
}
