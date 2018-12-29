package View.Vacations;

import Controller.Controller;
import Model.TradeRequest;
import View.RowsForTables.PurchaseForBuyerRow;
import View.RowsForTables.PurchaseForSellerRow;
import View.RowsForTables.RequestForBuyerRow;
import View.RowsForTables.TradeRequestForBuyerRow;
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

public class MyRequestsView implements Initializable {


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
    public TableView<TradeRequestForBuyerRow> trade_req_Table;
    public TableColumn<TradeRequestForBuyerRow, String> requestID2;
    public TableColumn<TradeRequestForBuyerRow, String> vacationID2;
    public TableColumn<TradeRequestForBuyerRow, String> seller2;
    public TableColumn<TradeRequestForBuyerRow, String> destination2;
    public TableColumn<TradeRequestForBuyerRow, String> status2;
    public TableColumn<TradeRequestForBuyerRow, Button> details2;
    public TableColumn<TradeRequestForBuyerRow, Button> cancel2;
    public TableColumn<TradeRequestForBuyerRow, Button> offer;

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
        requestID2.setCellValueFactory(new PropertyValueFactory<>("RequestID"));
        vacationID2.setCellValueFactory(new PropertyValueFactory<>("VacationID"));
        seller2.setCellValueFactory(new PropertyValueFactory<>("SellerUserName"));
        destination2.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        status2.setCellValueFactory(new PropertyValueFactory<>("Status"));
        details2.setCellValueFactory(new PropertyValueFactory<>("Details"));
        cancel2.setCellValueFactory(new PropertyValueFactory<>("Cancel"));
        offer.setCellValueFactory(new PropertyValueFactory<>("Offer"));
        ObservableList<TradeRequestForBuyerRow> trade_requsts = controller.getTradeRequestsForBuyerTable();
        trade_req_Table.setItems(trade_requsts);



    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }

    public void refresh(ActionEvent actionEvent) {
        requestsTable.setVisible(false);
        ObservableList<RequestForBuyerRow> requests = controller.getRequestsForBuyerTable();
        requestsTable.setItems(requests);
        requestsTable.setVisible(true);

        trade_req_Table.setVisible(false);
        ObservableList<TradeRequestForBuyerRow> trade_requsts = controller.getTradeRequestsForBuyerTable();
        trade_req_Table.setItems(trade_requsts);
        trade_req_Table.setVisible(true);


    }
}
