package View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchVacation implements Initializable {
    @FXML
    public VBox items;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        items.getChildren().add(new AnchorPane());
    }
}
