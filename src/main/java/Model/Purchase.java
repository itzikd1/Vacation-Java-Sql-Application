package Model;

public class Purchase {

    private String PurchaseID;
    private String VacationID;
    private String BuyerUserName;
    private String SellerUserName;
    private String Price;
    private String PaymentMethod;
    private String CreditCardNum;
    private String PayPalUserName;

    public Purchase(String purchaseID, String vacationID, String buyerUserName, String sellerUserName, String price, String paymentMethod, String creditCardNum, String payPalUserName) {
        PurchaseID = purchaseID;
        VacationID = vacationID;
        BuyerUserName = buyerUserName;
        SellerUserName = sellerUserName;
        Price = price;
        PaymentMethod = paymentMethod;
        CreditCardNum = creditCardNum;
        PayPalUserName = payPalUserName;
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

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public String getCreditCardNum() {
        return CreditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        CreditCardNum = creditCardNum;
    }

    public String getPayPalUserName() {
        return PayPalUserName;
    }

    public void setPayPalUserName(String payPalUserName) {
        PayPalUserName = payPalUserName;
    }
}
