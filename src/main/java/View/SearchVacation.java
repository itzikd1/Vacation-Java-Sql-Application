package View;
import Controller.Controller;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchVacation implements Initializable {

    private Controller controller = Controller.getInstance();

    public TableView<VacationsForSearchTable> vacationsTable;
    public TableColumn<VacationsForSearchTable, String> from;
    public TableColumn<VacationsForSearchTable, String> to;
    public TableColumn<VacationsForSearchTable, LocalDate> departDate;
    public TableColumn<VacationsForSearchTable, LocalDate> returnDate;
    public TableColumn<VacationsForSearchTable, Button> moreDetails;
    public TableColumn<VacationsForSearchTable, Button> buycolumn;

    //public Button b = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        moreDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        buycolumn.setCellValueFactory(new PropertyValueFactory<>("buy"));

        ObservableList<VacationsForSearchTable> vacations = controller.getVacationsForSearch();
        //vacationsTable.setItems(tryme());
        vacationsTable.setItems(vacations);
    }

    private ObservableList<Vacation> tryme() {
        ObservableList<Vacation> v = FXCollections.observableArrayList();
        v.add(new Vacation("0","1","1","12-12-1990","1","1","12-12-1990",
                "1","12-12-1990","1","1","1","1","1",
                "1","1","1"));
        return v;
    }
}
