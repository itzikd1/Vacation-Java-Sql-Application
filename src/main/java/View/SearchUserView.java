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

public class SearchUserView implements Initializable {

    public Button search_button;
    public Button BackButton;
    public Label Bday;
    public Label Fname;
    public Label Lname;
    public Label City;
    public TextField tf_username;
    public Label lbl_Bday;
    public Label lbl_Fname;
    public Label lbl_Lname;
    public Label lbl_City;

    private Controller controller = Controller.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void search(ActionEvent actionEvent) {
        String user = tf_username.getText();
        String[] details = controller.read("Users", user);

        if (details != null) {
            //set labels
            Bday.setText(details[2]);
            Fname.setText(details[3]);
            Lname.setText(details[4]);
            City.setText(details[5]);

            enable_visible();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("There is no match here !");
            alert.showAndWait();
        }
    }

    private void enable_visible() {
        lbl_Bday.setVisible(true);
        lbl_City.setVisible(true);
        lbl_Fname.setVisible(true);
        lbl_Lname.setVisible(true);
    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }
}
