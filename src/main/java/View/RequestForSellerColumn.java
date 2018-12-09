package View;

import Model.BuyingRequest;
import Model.Vacation;
import javafx.scene.control.Button;

public class RequestForSellerColumn {

    public String RequestID;
    public String VacationID;
    public String BuyerUserName;
    public String Status;
    public Button Approve;
    public Button Decline;

    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String requestID) {
        RequestID = requestID;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Button getApprove() {
        return Approve;
    }

    public void setApprove(Button approve) {
        Approve = approve;
    }

    public Button getDecline() {
        return Decline;
    }

    public void setDecline(Button decline) {
        Decline = decline;
    }



    public RequestForSellerColumn(BuyingRequest br, Button approve, Button decline) {
        RequestID = br.getRequestID();
        VacationID = br.getVacationID();
        BuyerUserName = br.getBuyerUserName();
        Status = br.getIsApproved();
        Approve = approve;
        Decline = decline;

        Approve.setText("Approve");
        Decline.setText("Decline");

        Approve.setOnAction(event -> {
            System.out.println("todo");
        });

        decline.setOnAction(event -> {
            System.out.println("todo");
        });
    }
}
