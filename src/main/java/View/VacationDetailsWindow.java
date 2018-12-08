package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VacationDetailsWindow implements Initializable {

    public Button BackButton;
    public Label l_from;
    public Label l_departDate;
    public Label l_departTime;
    public Label l_destination;
    public Label l_arrivelDate;
    public Label l_arrivelTime;
    public Label l_returnDate;
    public Label l_returnTime;
    public Label l_ticketType;
    public Label l_company;
    public Label l_connectionCity;
    public Label l_classType;
    public Label l_price;
    public Label l_baggage;

    private Controller controller = Controller.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: 08/12/2018 pass here the info of what id we want to search and put it in vacation  Itzik
        String vacation = VacationsForSearchTable.vacationID;
        String[] details = controller.readVacation(vacation);
        l_from.setText(details[2]);
        l_departDate.setText(details[3]);
        l_departTime.setText(details[4]);
        l_destination.setText(details[5]);
        l_arrivelDate.setText(details[6]);
        l_arrivelTime.setText(details[7]);
        l_returnDate.setText(details[8]);
        l_returnTime.setText(details[9]);
        l_ticketType.setText(details[10]);
        l_company.setText(details[11]);
        l_connectionCity.setText(details[12]);
        l_baggage.setText(details[14]);
        l_classType.setText(details[15]);
        l_price.setText(details[16] + "$");
    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }
}
