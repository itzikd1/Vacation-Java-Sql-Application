package View;

import Controller.Controller;
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
import java.util.Observable;
import java.util.ResourceBundle;

public class MyVacationsAsSellerView implements Initializable {


    private Controller controller = Controller.getInstance();

    public Button BackButton;
    public TableView<RequestForSellerColumn> requestsTable;
    public TableColumn<RequestForSellerColumn, String> requestID;
    public TableColumn<VacationsForSearchColumn, String> vacationID;
    public TableColumn<VacationsForSearchColumn, String> buyer;
    public TableColumn<VacationsForSearchColumn, String> status;
    public TableColumn<VacationsForSearchColumn, Button> approve;
    public TableColumn<VacationsForSearchColumn, Button> decline;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        requestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        vacationID.setCellValueFactory(new PropertyValueFactory<>("vacationID"));
        buyer.setCellValueFactory(new PropertyValueFactory<>("BuyerUserName"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        approve.setCellValueFactory(new PropertyValueFactory<>("approve"));
        decline.setCellValueFactory(new PropertyValueFactory<>("decline"));
        //todo - to edit the SQL query to "wHere user id = .. " 
        ObservableList<RequestForSellerColumn> requests = controller.getRequestsForSellerTable();

        requestsTable.setItems(requests);
    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }
}
