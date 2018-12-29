package View.Vacations;
import Controller.Controller;
import View.RowsForTables.MyVacationForTradeRow;
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

public class MyVacationsForTradeView implements Initializable {

    private Controller controller = Controller.getInstance();
    public Button BackButton;


    public TableView<MyVacationForTradeRow> vacationsTable;
    public TableColumn<MyVacationForTradeRow, String> from;
    public TableColumn<MyVacationForTradeRow, String> to;
    public TableColumn<MyVacationForTradeRow, LocalDate> departDate;
    public TableColumn<MyVacationForTradeRow, LocalDate> returnDate;
    public TableColumn<MyVacationForTradeRow, Button> moreDetails;
    public TableColumn<MyVacationForTradeRow, Button> trade;


    //public Button b = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        moreDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        trade.setCellValueFactory(new PropertyValueFactory<>("Choose"));

        ObservableList<MyVacationForTradeRow> vacations = controller.getMyVacationsForTrade();
        vacationsTable.setItems(vacations);
    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }
}
