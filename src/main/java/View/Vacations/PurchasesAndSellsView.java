package View.Vacations;

import Controller.Controller;
import View.RowsForTables.PurchaseForBuyerRow;
import View.RowsForTables.PurchaseForSellerRow;
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

public class PurchasesAndSellsView implements Initializable {

    private Controller controller = Controller.getInstance();

    public Button BackButton;

    //first table:
    public TableView<PurchaseForBuyerRow> purchasesTable;
    public TableColumn<PurchaseForBuyerRow, String> PurchaseID;
    public TableColumn<PurchaseForBuyerRow, String> VacationID;
    public TableColumn<PurchaseForBuyerRow, String> seller;
    public TableColumn<PurchaseForBuyerRow, String> destination;
    public TableColumn<PurchaseForBuyerRow, String> price;
    public TableColumn<PurchaseForBuyerRow, Button> details;
    public TableColumn<PurchaseForBuyerRow, Button> cancel;

    //second table:
    public TableView<PurchaseForSellerRow> sold_table;
    public TableColumn<PurchaseForSellerRow, String> PurchaseID2;
    public TableColumn<PurchaseForSellerRow, String> VacationID2;
    public TableColumn<PurchaseForSellerRow, String> buyer2;
    public TableColumn<PurchaseForSellerRow, String> price2;
    public TableColumn<PurchaseForSellerRow, Button> details2;
    public TableColumn<PurchaseForSellerRow, String> destination2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //first table:
        PurchaseID.setCellValueFactory(new PropertyValueFactory<>("PurchaseID"));
        VacationID.setCellValueFactory(new PropertyValueFactory<>("VacationID"));
        seller.setCellValueFactory(new PropertyValueFactory<>("SellerUserName"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        details.setCellValueFactory(new PropertyValueFactory<>("Details"));
        cancel.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        ObservableList<PurchaseForBuyerRow> purchases = controller.getPurchasesForBuyerTable();
        purchasesTable.setItems(purchases);

        //second table:
        PurchaseID2.setCellValueFactory(new PropertyValueFactory<>("PurchaseID"));
        VacationID2.setCellValueFactory(new PropertyValueFactory<>("VacationID"));
        buyer2.setCellValueFactory(new PropertyValueFactory<>("BuyerUserName"));
        price2.setCellValueFactory(new PropertyValueFactory<>("Price"));
        details2.setCellValueFactory(new PropertyValueFactory<>("details"));
        destination2.setCellValueFactory(new PropertyValueFactory<>("destination"));
        ObservableList<PurchaseForSellerRow> purchases2 = controller.getPurchasesForSellerTable();
        sold_table.setItems(purchases2);


    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }

    public void refresh(ActionEvent actionEvent) {
        //first table:
        purchasesTable.setVisible(false);
        ObservableList<PurchaseForBuyerRow> purchases = controller.getPurchasesForBuyerTable();
        purchasesTable.setItems(purchases);
        purchasesTable.setVisible(true);

        //second table:
        sold_table.setVisible(false);
        ObservableList<PurchaseForSellerRow> purchases2 = controller.getPurchasesForSellerTable();
        sold_table.setItems(purchases2);
        sold_table.setVisible(true);



    }
}
