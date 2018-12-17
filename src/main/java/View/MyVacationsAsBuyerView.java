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
import java.util.ResourceBundle;

public class MyVacationsAsBuyerView implements Initializable {


    private Controller controller = Controller.getInstance();

    public Button BackButton;

    //first table:
    public TableView<RequestForBuyerRow> requestsTable;
    public TableColumn<RequestForBuyerRow, String> requestID;
    public TableColumn<RequestForBuyerRow, String> vacationID;
    public TableColumn<RequestForBuyerRow, String> destination;
    public TableColumn<RequestForBuyerRow, String> seller;
    public TableColumn<RequestForBuyerRow, String> status;
    public TableColumn<RequestForBuyerRow, Button> buy;
    public TableColumn<RequestForBuyerRow, Button> cancel;
    public TableColumn<RequestForBuyerRow, Button> details1;

    //second table:
    public TableView<PurchaseForBuyerRow> purchasesTable;
    public TableColumn<PurchaseForBuyerRow, String> PurchaseID;
    public TableColumn<PurchaseForBuyerRow, String> VacationID2;
    public TableColumn<PurchaseForBuyerRow, String> seller2;
    public TableColumn<PurchaseForBuyerRow, String> destination2;
    public TableColumn<PurchaseForBuyerRow, String> price;
    public TableColumn<PurchaseForSellerRow, Button> details2;
    public TableColumn<PurchaseForBuyerRow, Button> cancel2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //first table:
        requestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        vacationID.setCellValueFactory(new PropertyValueFactory<>("vacationID"));
        seller.setCellValueFactory(new PropertyValueFactory<>("SellerUserName"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        buy.setCellValueFactory(new PropertyValueFactory<>("buy"));
        cancel.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        details1.setCellValueFactory(new PropertyValueFactory<>("Details"));
        ObservableList<RequestForBuyerRow> requests = controller.getRequestsForBuyerTable();
        requestsTable.setItems(requests);

        //second table:
        PurchaseID.setCellValueFactory(new PropertyValueFactory<>("PurchaseID"));
        VacationID2.setCellValueFactory(new PropertyValueFactory<>("VacationID"));
        seller2.setCellValueFactory(new PropertyValueFactory<>("SellerUserName"));
        destination2.setCellValueFactory(new PropertyValueFactory<>("destination"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        details2.setCellValueFactory(new PropertyValueFactory<>("Details"));
        cancel2.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        ObservableList<PurchaseForBuyerRow> purchases = controller.getPurchasesForBuyerTable();
        purchasesTable.setItems(purchases);

    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }

    public void refresh(ActionEvent actionEvent) {
        requestsTable.setVisible(false);
        ObservableList<RequestForBuyerRow> requests = controller.getRequestsForBuyerTable();
        requestsTable.setItems(requests);
        ObservableList<PurchaseForBuyerRow> purchases = controller.getPurchasesForBuyerTable();
        purchasesTable.setItems(purchases);
        requestsTable.setVisible(true);
    }
}
