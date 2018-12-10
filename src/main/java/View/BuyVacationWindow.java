package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class BuyVacationWindow implements Initializable {
    public ToggleButton toggleButton;
    public RadioButton r2;
    public RadioButton r1;
    public Button BackButton;
    public boolean buttonStatus=false;
    private Controller controller = Controller.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void go_main(ActionEvent actionEvent) {
        // TODO: 10/12/2018  doesnt not work for some reason  Itzik
//        Stage s = (Stage) BackButton.getScene().getWindow();
//        s.close();
    }

    public void pay(ActionEvent actionEvent) {
    }

    public void toggleChange(ActionEvent actionEvent) {

        if (buttonStatus==false)
        {
            buttonStatus=true;
            System.out.println("false");
        }
        else {
            buttonStatus=false;
            System.out.println("true");
        }
        // TODO: 10/12/2018 if this works (wait for maor to finshe connection) then here we chose
        // TODO: between paypal and regular payment, button diable...enable..  Itzik

    }
}
