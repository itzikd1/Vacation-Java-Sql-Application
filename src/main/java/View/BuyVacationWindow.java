package View;

import Controller.Controller;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyVacationWindow implements Initializable {
    public Button ToggleButton;
    public Button BackButton;
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
        // TODO: 10/12/2018 if this works (wait for maor to finshe connection) then here we chose
        // TODO: between paypal and regular payment, button diable...enable..  Itzik
        ToggleButton.addActionListener(e -> System.out.println("hello"));
    }
}
