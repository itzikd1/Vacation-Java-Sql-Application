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

public class MyVacationsAsSellerView implements Initializable {


    private Controller controller = Controller.getInstance();

    public Button BackButton;

    //first table:
    public TableView<RequestForSellerRow> requestsTable;
    public TableColumn<RequestForSellerRow, String> requestID;
    public TableColumn<RequestForSellerRow, String> vacationID;
    public TableColumn<RequestForSellerRow, String> buyer;
    public TableColumn<RequestForSellerRow, String> status;
    public TableColumn<RequestForSellerRow, Button> approve;
    public TableColumn<RequestForSellerRow, Button> decline;
    public TableColumn<RequestForSellerRow, Button> details1;
    public TableColumn<RequestForSellerRow, String> destination;


    //second table:
    public TableView<PurchaseForSellerRow> purchasesTable;
    public TableColumn<PurchaseForSellerRow, String> PurchaseID;
    public TableColumn<PurchaseForSellerRow, String> VacationID2;
    public TableColumn<PurchaseForSellerRow, String> buyer2;
    public TableColumn<PurchaseForSellerRow, String> price;
    public TableColumn<PurchaseForSellerRow, Button> details2;
    public TableColumn<PurchaseForSellerRow, String> destination2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //first table:
        requestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        vacationID.setCellValueFactory(new PropertyValueFactory<>("vacationID"));
        buyer.setCellValueFactory(new PropertyValueFactory<>("BuyerUserName"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        approve.setCellValueFactory(new PropertyValueFactory<>("approve"));
        decline.setCellValueFactory(new PropertyValueFactory<>("decline"));
        details1.setCellValueFactory(new PropertyValueFactory<>("details"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        ObservableList<RequestForSellerRow> requests = controller.getRequestsForSellerTable();
        requestsTable.setItems(requests);

        //second table:
        PurchaseID.setCellValueFactory(new PropertyValueFactory<>("PurchaseID"));
        VacationID2.setCellValueFactory(new PropertyValueFactory<>("VacationID"));
        buyer2.setCellValueFactory(new PropertyValueFactory<>("BuyerUserName"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        details2.setCellValueFactory(new PropertyValueFactory<>("details"));
        destination2.setCellValueFactory(new PropertyValueFactory<>("destination"));
        ObservableList<PurchaseForSellerRow> purchases = controller.getPurchasesForSellerTable();
        purchasesTable.setItems(purchases);


    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }

    public void refresh(ActionEvent actionEvent) {
        requestsTable.setVisible(false);
        ObservableList<RequestForSellerRow> requests = controller.getRequestsForSellerTable();
        requestsTable.setItems(requests);
        ObservableList<PurchaseForSellerRow> purchases = controller.getPurchasesForSellerTable();
        purchasesTable.setItems(purchases);
        requestsTable.setVisible(true);
    }
}
