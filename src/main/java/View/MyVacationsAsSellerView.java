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
    public TableView<RequestForSellerColumn> requestsTable;
    public TableColumn<RequestForSellerColumn, String> requestID;
    public TableColumn<RequestForSellerColumn, String> vacationID;
    public TableColumn<RequestForSellerColumn, String> buyer;
    public TableColumn<RequestForSellerColumn, String> status;
    public TableColumn<RequestForSellerColumn, Button> approve;
    public TableColumn<RequestForSellerColumn, Button> decline;
    public TableColumn<RequestForSellerColumn, Button> details1;

    //second table:
    public TableView<PurchaseForSellerColumn> purchasesTable;
    public TableColumn<PurchaseForSellerColumn, String> PurchaseID;
    public TableColumn<PurchaseForSellerColumn, String> VacationID2;
    public TableColumn<PurchaseForSellerColumn, String> BuyerUserName2;
    public TableColumn<PurchaseForSellerColumn, String> price;
    public TableColumn<PurchaseForSellerColumn, String> confirmation;
    public TableColumn<PurchaseForSellerColumn, Button> details2;

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
        ObservableList<RequestForSellerColumn> requests = controller.getRequestsForSellerTable();
        requestsTable.setItems(requests);

        //second table:
        PurchaseID.setCellValueFactory(new PropertyValueFactory<>("PurchaseID"));
        VacationID2.setCellValueFactory(new PropertyValueFactory<>("VacationID"));
        BuyerUserName2.setCellValueFactory(new PropertyValueFactory<>("BuyerUserName"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        confirmation.setCellValueFactory(new PropertyValueFactory<>("Confirmation"));
        details2.setCellValueFactory(new PropertyValueFactory<>("details"));
        ObservableList<PurchaseForSellerColumn> purchases = controller.getPurchasesForSellerTable();
        purchasesTable.setItems(purchases);


    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }

    public void refresh(ActionEvent actionEvent) {
        requestsTable.setVisible(false);
        ObservableList<RequestForSellerColumn> requests = controller.getRequestsForSellerTable();
        requestsTable.setItems(requests);
        ObservableList<PurchaseForSellerColumn> purchases = controller.getPurchasesForSellerTable();
        purchasesTable.setItems(purchases);
        requestsTable.setVisible(true);
    }
    // TODO: 10/12/2018 refresh button workd, need to update automatily after ALERT message  Itzik
}
