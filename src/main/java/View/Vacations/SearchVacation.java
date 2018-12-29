package View.Vacations;

import Controller.Controller;
import View.RowsForTables.VacationsForSearchRow;
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

public class SearchVacation implements Initializable {

    private Controller controller = Controller.getInstance();
    public Button BackButton;

    public TableView<VacationsForSearchRow> vacationsTable;
    public TableColumn<VacationsForSearchRow, String> from;
    public TableColumn<VacationsForSearchRow, String> to;
    public TableColumn<VacationsForSearchRow, LocalDate> departDate;
    public TableColumn<VacationsForSearchRow, LocalDate> returnDate;
    public TableColumn<VacationsForSearchRow, Button> moreDetails;
    public TableColumn<VacationsForSearchRow, Button> buycolumn;
    public TableColumn<VacationsForSearchRow, String> seller;
    public TableColumn<VacationsForSearchRow, Button> tradecolumn;

    //public Button b = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        moreDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        buycolumn.setCellValueFactory(new PropertyValueFactory<>("buy"));
        seller.setCellValueFactory(new PropertyValueFactory<>("SellerUserName"));
        tradecolumn.setCellValueFactory(new PropertyValueFactory<>("trade"));

        ObservableList<VacationsForSearchRow> vacations = controller.getVacationsForSearch();
        //vacationsTable.setItems(tryme());
        vacationsTable.setItems(vacations);
    }

//    private ObservableList<Vacation> tryme() {
//        ObservableList<Vacation> v = FXCollections.observableArrayList();
//        v.add(new Vacation("0","1","1","12-12-1990","1","1","12-12-1990",
//                "1","12-12-1990","1","1","1","1","1",
//                "1","1","1","Not"));
//        return v;
//    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }
}