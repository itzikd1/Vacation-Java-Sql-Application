package Model;

public class Purchase {

    private String PurchaseID;
    private String VacationID;
    private String BuyerUserName;
    private String SellerUserName;
    private String Price;

    private String DateOfPurchase;

    public Purchase(String purchaseID, String vacationID, String buyerUserName, String sellerUserName, String price, String dateOfPurchase) {
        PurchaseID = purchaseID;
        VacationID = vacationID;
        BuyerUserName = buyerUserName;
        SellerUserName = sellerUserName;
        Price = price;

        DateOfPurchase = dateOfPurchase;
    }

    public Purchase(String[] details) {
        PurchaseID = details[0];
        VacationID = details[1];
        BuyerUserName = details[2];
        SellerUserName = details[3];
        Price = details[4];
        DateOfPurchase = details[5];
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

    public String getSellerUserName() {
        return SellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        SellerUserName = sellerUserName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDateOfPurchase() {
        return DateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        DateOfPurchase = dateOfPurchase;
    }
}

