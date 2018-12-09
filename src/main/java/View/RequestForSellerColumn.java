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

    public RequestForSellerColumn(BuyingRequest br, Button approve, Button decline) {
        RequestID = br.getRequestID();
        VacationID = br.getVacationID();
        BuyerUserName = br.getBuyerUserName();
        Status = br.getIsApproved();
        Approve = approve;
        Decline = decline;

        Approve.setText("Approve");
        Decline.setText("Decline");
        
    }
}
