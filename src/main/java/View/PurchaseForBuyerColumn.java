package View;

import Controller.Controller;
import Model.Purchase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseForBuyerColumn {

    public String PurchaseID;
    public String VacationID;
    public String BuyerUserName;
    public String Price;
    public String destination;
    public Button Confirmation;
    public Button Cancel;
    public Button Details;
    //todo: add cancel option on button Cancel


    public Button getCancel() {
        return Cancel;
    }

    public void setCancel(Button cancel) {
        Cancel = cancel;
    }

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

    public Button getConfirmation() {
        return Confirmation;
    }

    public void setConfirmation(Button confirmation) {
        Confirmation = confirmation;
    }

    public PurchaseForBuyerColumn(Purchase p, Button confirmation, Button cancel, Button details) {
        Confirmation = confirmation;
        Cancel = cancel;
        PurchaseID = p.getPurchaseID();
        VacationID = p.getVacationID();
        BuyerUserName = p.getBuyerUserName();
        Price = p.getPrice();
        Details = details;

        Confirmation.setText("Details");

        Confirmation.setOnAction(event -> {
            System.out.println("todo");
        });

        Cancel.setText("Cancel");

        Cancel.setOnAction(event -> {
            System.out.println("todo");
        });

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
