package View;

import Model.Purchase;
import javafx.scene.control.Button;

public class PurchaseForBuyerColumn {

    public String PurchaseID;
    public String VacationID;
    public String BuyerUserName;
    public String Price;
    public String destination;
    public Button Confirmation;
    public Button Cancel;
    //todo: add cancel option on button Cancel



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

    public PurchaseForBuyerColumn(Purchase p, Button confirmation, Button cancel) {
        Confirmation = confirmation;
        Cancel = cancel;
        PurchaseID = p.getPurchaseID();
        VacationID = p.getVacationID();
        BuyerUserName = p.getBuyerUserName();
        Price = p.getPrice();

        Confirmation.setText("Details");

        Confirmation.setOnAction(event -> {
            System.out.println("todo");
        });

        Cancel.setText("Cancel");

        Cancel.setOnAction(event -> {
            System.out.println("todo");
        });

    }
}
