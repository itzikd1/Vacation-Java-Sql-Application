package View;

import Controller.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;

public class MyVacationsAsSellerView implements Initializable {

    private Controller controller = Controller.getInstance();

    public TableView<VacationsForSearchColumn> requestsTable;
    public TableColumn<RequestForSellerColumn, String> requestID;
    public TableColumn<VacationsForSearchColumn, String> vacationID;
    public TableColumn<VacationsForSearchColumn, String> destination;
    public TableColumn<VacationsForSearchColumn, String> buyer;
    public TableColumn<VacationsForSearchColumn, Button> approve;
    public TableColumn<VacationsForSearchColumn, Button> decline;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        requestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        vacationID.setCellValueFactory(new PropertyValueFactory<>("vacationID"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        buyer.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        approve.setCellValueFactory(new PropertyValueFactory<>("approve"));
        decline.setCellValueFactory(new PropertyValueFactory<>("decline"));

        ObservableList<RequestForSellerColumn> requests = controller.getRequestsForSellerTable();

    }
}
