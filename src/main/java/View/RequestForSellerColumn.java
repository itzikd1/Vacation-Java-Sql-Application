package View;

import Model.Vacation;
import javafx.scene.control.Button;

public class RequestForSellerColumn {
    public Vacation vacation;
    public String RequestID;
    public String VacationID;
    public String Destination;
    public String BuyerUserName;
    public String Status;
    public Button Approve;
    public Button Decline;

    public RequestForSellerColumn(String requestID, String status, String buyerID, Vacation vacation, Button approve, Button decline) {
        vacation = vacation;
        RequestID = requestID;
        VacationID = vacation.getVacationID();
        Destination = vacation.getDestination();
        BuyerUserName = buyerID;
        Status = status;
        Approve = approve;
        Decline = decline;

        Approve.setText("Approve");
        Decline.setText("Decline");
        
    }
}
