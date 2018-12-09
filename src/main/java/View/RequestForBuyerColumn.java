package View;

import Controller.Controller;
import Model.BuyingRequest;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class RequestForBuyerColumn {

    public String RequestID;
    public String VacationID;
    public String SellerUserName;
    public String Status;
    public Button Buy;
    public Button Cancel;

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

    public String getSellerUserName() {
        return SellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        SellerUserName = sellerUserName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Button getBuy() {
        return Buy;
    }

    public void setBuy(Button buy) {
        Buy = buy;
    }

    public Button getCancel() {
        return Cancel;
    }

    public void setCancel(Button cancel) {
        Cancel = cancel;
    }

    public RequestForBuyerColumn(BuyingRequest br, Button b, Button c) {
        RequestID = br.getRequestID();
        VacationID = br.getVacationID();
        SellerUserName = br.getSellerUserName();
        Status = br.getIsApproved();
        Buy = b;
        Cancel = c;

        Buy.setText("Buy");
        Cancel.setText("Cancel");

        Buy.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            boolean flag = false;
            //todo: open buy window & updateRequestToBuy as Bought (if bought we should not see this on req table) & sent to Purchase SQL table.
        });

        Cancel.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            boolean flag = false;
            flag = controller.updateRequest(RequestID,"Cancelled");
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buying Request Cancelled");
                alert.setHeaderText("Your Cancellation has been sent to " + SellerUserName);
                alert.showAndWait();
            }
        });
    }
}
