package View;

import Model.Purchase;
import javafx.scene.control.Button;

public class PurchaseForSellerColumn {

    public String PurchaseID;
    public String VacationID;
    public String BuyerUserName;
    public String Price;
    public Button Confirmation;


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

    public PurchaseForSellerColumn(Purchase p, Button c) {
        Confirmation = c;
        PurchaseID = p.getPurchaseID();
        VacationID = p.getVacationID();
        BuyerUserName = p.getBuyerUserName();
        Price = p.getPrice();

        Confirmation.setText("Details");

        Confirmation.setOnAction(event -> {
            System.out.println("todo");
        });

    }
}
