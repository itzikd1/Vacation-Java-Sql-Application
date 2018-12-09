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
            //todo: open buy window & updateRequestToBuy
        });

        Cancel.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            boolean flag = false;
            flag = controller.updateRequest(RequestID,"Cancelled");
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buying Request Cancelled");
                alert.setHeaderText("Your Decline has been sent to " + SellerUserName);
                alert.showAndWait();
            }
        });
    }
}
