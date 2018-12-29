package View.Vacations;

import Controller.Controller;
import View.RowsForTables.RequestForSellerRow;
import View.RowsForTables.TradeRequestForSellerRow;
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

public class RequestsForMyVacationsView implements Initializable {


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
    public TableColumn<RequestForSellerRow, Button> details;
    public TableColumn<RequestForSellerRow, String> destination;


    //second table:
    public TableView<TradeRequestForSellerRow> trade_req_Table;
    public TableColumn<TradeRequestForSellerRow, String> requestID1;
    public TableColumn<TradeRequestForSellerRow, String> vacationID1;
    public TableColumn<TradeRequestForSellerRow, String> destination1;
    public TableColumn<TradeRequestForSellerRow, String> trader;
    public TableColumn<TradeRequestForSellerRow, Button> offer;
    public TableColumn<TradeRequestForSellerRow, String> status1;
    public TableColumn<TradeRequestForSellerRow, Button> approve1;
    public TableColumn<TradeRequestForSellerRow, Button> decline1;
    public TableColumn<TradeRequestForSellerRow, Button> details1;









    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //first table:
        requestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        vacationID.setCellValueFactory(new PropertyValueFactory<>("vacationID"));
        buyer.setCellValueFactory(new PropertyValueFactory<>("BuyerUserName"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        approve.setCellValueFactory(new PropertyValueFactory<>("approve"));
        decline.setCellValueFactory(new PropertyValueFactory<>("decline"));
        details.setCellValueFactory(new PropertyValueFactory<>("details"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        ObservableList<RequestForSellerRow> requests = controller.getRequestsForSellerTable();
        requestsTable.setItems(requests);

        //second table:
        requestID1.setCellValueFactory(new PropertyValueFactory<>("RequestID"));
        vacationID1.setCellValueFactory(new PropertyValueFactory<>("VacationID"));
        destination1.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        trader.setCellValueFactory(new PropertyValueFactory<>("TraderUserName"));
        offer.setCellValueFactory(new PropertyValueFactory<>("Offer"));
        status1.setCellValueFactory(new PropertyValueFactory<>("Status"));
        approve1.setCellValueFactory(new PropertyValueFactory<>("Approve"));
        decline1.setCellValueFactory(new PropertyValueFactory<>("Decline"));
        details1.setCellValueFactory(new PropertyValueFactory<>("Details"));
        ObservableList<TradeRequestForSellerRow> trade_requests = controller.getTradeRequestsForSellerTable();
        trade_req_Table.setItems(trade_requests);





    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }

    public void refresh(ActionEvent actionEvent) {
        requestsTable.setVisible(false);
        ObservableList<RequestForSellerRow> requests = controller.getRequestsForSellerTable();
        requestsTable.setItems(requests);
        requestsTable.setVisible(true);

        trade_req_Table.setVisible(false);
        ObservableList<TradeRequestForSellerRow> trade_requests = controller.getTradeRequestsForSellerTable();
        trade_req_Table.setItems(trade_requests);
        trade_req_Table.setVisible(true);
    }
}
