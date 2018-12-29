package Model;

public class TradeRequest extends BuyingRequest {


    public String tradeID; //vacation ID of traded offered vacation

    public TradeRequest(String requestID, String vacationID, String sellerUserName, String buyerUserName, String isApproved, String trade_id) {
        super(requestID,vacationID,sellerUserName,buyerUserName,isApproved);
        this.tradeID=trade_id;
    }


    public TradeRequest(String[] data) {
        super(data);
        this.tradeID = data[5];
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


    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }
}

