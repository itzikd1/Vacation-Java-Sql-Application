package Model;

public class BuyingRequest {


    public String RequestID;
    public String VacationID;
    public String SellerUserName;
    public String BuyerUserName;
    public String isApproved;

    public BuyingRequest(String requestID, String vacationID, String sellerUserName, String buyerUserName, String isApproved) {
        RequestID = requestID;
        VacationID = vacationID;
        SellerUserName = sellerUserName;
        BuyerUserName = buyerUserName;
        this.isApproved = isApproved; // "Waiting" / "Approved" / "Not Approved"
}

    public BuyingRequest(String[] data) {
        RequestID = data[0];
        VacationID = data[1];
        SellerUserName = data[2];
        BuyerUserName = data[3];
        this.isApproved = data[4];
    }


    public void setRequestID(String requestID) {
        RequestID = requestID;
    }

    public void setVacationID(String vacationID) {
        VacationID = vacationID;
    }

    public void setSellerUserName(String sellerUserName) {
        SellerUserName = sellerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        BuyerUserName = buyerUserName;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getRequestID() {
        return RequestID;
    }

    public String getVacationID() {
        return VacationID;
    }

    public String getSellerUserName() {
        return SellerUserName;
    }

    public String getBuyerUserName() {
        return BuyerUserName;
    }

    public String getIsApproved() {
        return isApproved;
    }
}
