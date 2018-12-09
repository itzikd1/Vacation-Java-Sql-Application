package View;

import Controller.Controller;
import Model.Purchase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MyVacationsAsBuyerView implements Initializable {


    private Controller controller = Controller.getInstance();

    public Button BackButton;

    //first table:
    public TableView<RequestForBuyerColumn> requestsTable;
    public TableColumn<RequestForBuyerColumn, String> requestID;
    public TableColumn<RequestForBuyerColumn, String> vacationID;
    public TableColumn<RequestForBuyerColumn, String> destination;
    public TableColumn<RequestForBuyerColumn, String> seller;
    public TableColumn<RequestForBuyerColumn, String> status;
    public TableColumn<RequestForBuyerColumn, Button> buy;
    public TableColumn<RequestForBuyerColumn, Button> cancel;

    //second table:
    public TableView<PurchaseForBuyerColumn> purchasesTable;
    public TableColumn<PurchaseForBuyerColumn, String> PurchaseID;
    public TableColumn<PurchaseForBuyerColumn, String> VacationID2;
    public TableColumn<PurchaseForBuyerColumn, String> seller2;
    public TableColumn<PurchaseForBuyerColumn, String> destination2;
    public TableColumn<PurchaseForBuyerColumn, String> price;
    public TableColumn<PurchaseForBuyerColumn, String> confirmation;
    //todo: destination - to that somwhow from SQL

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //first table:
        requestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        vacationID.setCellValueFactory(new PropertyValueFactory<>("vacationID"));
        seller.setCellValueFactory(new PropertyValueFactory<>("SellerUserName"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        buy.setCellValueFactory(new PropertyValueFactory<>("buy"));
        cancel.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination")); //todo: to get the destination from SQL
        ObservableList<RequestForBuyerColumn> requests = controller.getRequestsForBuyerTable();
        requestsTable.setItems(requests);

        //second table:
        PurchaseID.setCellValueFactory(new PropertyValueFactory<>("PurchaseID"));
        VacationID2.setCellValueFactory(new PropertyValueFactory<>("VacationID"));
        seller2.setCellValueFactory(new PropertyValueFactory<>("SellerUserName"));
        destination2.setCellValueFactory(new PropertyValueFactory<>("destination"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        confirmation.setCellValueFactory(new PropertyValueFactory<>("Confirmation"));
        ObservableList<PurchaseForBuyerColumn> purchases = controller.getPurchasesForBuyerTable();
        purchasesTable.setItems(purchases);

    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }
}
