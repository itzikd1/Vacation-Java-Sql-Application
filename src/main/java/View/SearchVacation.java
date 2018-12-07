package View;

import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.objects.annotations.Property;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchVacation implements Initializable {

    public TableView<Vacation> vacationsTable;
    public TableColumn<Vacation, String> from;
    public TableColumn<Vacation, String> to;
    public TableColumn<Vacation, LocalDate> departDate;
    public TableColumn<Vacation, LocalDate> returnDate;
    public TableColumn<Button, String> moreDetails;
    public TableColumn<Vacation, String> buycolumn;

    //public Button b = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<Vacation, String>("from"));
        to.setCellValueFactory(new PropertyValueFactory<Vacation, String>("destination"));
        departDate.setCellValueFactory(new PropertyValueFactory<Vacation, LocalDate>("departureDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<Vacation, LocalDate>("returnDate"));
        //moreDetails.setCellValueFactory(new PropertyValueFactory<Button, String>("details"));
        //moreDetails.setCellValueFactory(new PropertyValueFactory<Button, String>("buy"));

        vacationsTable.setItems(tryme());
    }

    private ObservableList<Vacation> tryme() {
        ObservableList<Vacation> v = FXCollections.observableArrayList();
        v.add(new Vacation("0","1","1","12-12-1990","1","1","12-12-1990",
                "1","12-12-1990","1","1","1","1","1",
                "1","1","1"));
        return v;
    }
}
