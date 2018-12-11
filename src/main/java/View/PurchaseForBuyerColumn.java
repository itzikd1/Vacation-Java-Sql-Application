package View;

import Controller.Controller;
import Model.Purchase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseForBuyerColumn {
    //todo: delete the vacation from Vacations SQL table while user buyed it

    public String PurchaseID;
    public String VacationID;
    public String BuyerUserName;
    public String SellerUserName;
    public String Price;
    public String destination;

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public Button cancel;
    public Button Details;






    public Button getDetails() {
        return Details;
    }

    public void setDetails(Button details) {
        Details = details;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public String getPurchaseID() {
        return PurchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        PurchaseID = purchaseID;
    }

    public String getVacationID() {
        return VacationID;
    }

    public void setVacationID(String vacationID) {
        VacationID = vacationID;
    }

    public String getBuyerUserName() {
        return BuyerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        BuyerUserName = buyerUserName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getSellerUserName() {
        return SellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        SellerUserName = sellerUserName;
    }

    public PurchaseForBuyerColumn(Purchase p, String Destination, Button Cancel, Button details) {
        cancel = Cancel;
        PurchaseID = p.getPurchaseID();
        VacationID = p.getVacationID();
        BuyerUserName = p.getBuyerUserName();
        Price = p.getPrice();
        Details = details;
        destination = Destination;
        SellerUserName = p.getSellerUserName();


        cancel.setText("Cancel");


        cancel.setOnAction(event -> {
            //todo: add cancel option on button Cancel
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Implemented");
            alert.setHeaderText("Will do that on next part of work.\n you can buy vacations so enjoy!!! bye");
            alert.showAndWait();
        });
        Details.setText("Details");
        Details.setOnAction(event -> {

            Stage s = (Stage) details.getScene().getWindow();
//        s.close();
            try {
                Controller.vacationID = VacationID;
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("VacationDetailsWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(true);
                stage.setTitle("Details Vacation");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
