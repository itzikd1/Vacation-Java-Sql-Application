package View.Vacations;
import Controller.Controller;
import Model.Vacation;
import View.RowsForTables.MyVacationsRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MyVacationsView implements Initializable {

    private Controller controller = Controller.getInstance();
    public Button BackButton;
    public Button refresh;


    public TableView<MyVacationsRow> vacationsTable;
    public TableColumn<MyVacationsRow, String> from;
    public TableColumn<MyVacationsRow, String> to;
    public TableColumn<MyVacationsRow, LocalDate> departDate;
    public TableColumn<MyVacationsRow, LocalDate> returnDate;
    public TableColumn<MyVacationsRow, Button> moreDetails;
    public TableColumn<MyVacationsRow, Button> delete;

    //public Button b = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        moreDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        ObservableList<MyVacationsRow> vacations = controller.getMyVacations();
        //vacationsTable.setItems(tryme());
        vacationsTable.setItems(vacations);
    }

    private ObservableList<Vacation> tryme() {
        ObservableList<Vacation> v = FXCollections.observableArrayList();
        v.add(new Vacation("0","1","1","12-12-1990","1","1","12-12-1990",
                "1","12-12-1990","1","1","1","1","1",
                "1","1","1","Not"));
        return v;
    }

    public void refresh(ActionEvent actionEvent){
        vacationsTable.setVisible(false);
        ObservableList<MyVacationsRow> requests = controller.getMyVacations();
        vacationsTable.setItems(requests);
        vacationsTable.setVisible(true);
    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }
}
