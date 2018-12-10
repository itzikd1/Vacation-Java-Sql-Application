package View;

import Controller.Controller;
import Model.BuyingRequest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RequestForBuyerColumn {

    public String RequestID;
    public String VacationID;
    public String SellerUserName;
    public String Status;
    public Button Buy;
    public Button Cancel;
    public String destination;
    public Button Details;

    public Button getDetails() {
        return Details;
    }

    public void setDetails(Button details) {
        Details = details;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

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

    public RequestForBuyerColumn(BuyingRequest br, String destination, Button b, Button c, Button details) {
        RequestID = br.getRequestID();
        VacationID = br.getVacationID();
        SellerUserName = br.getSellerUserName();
        Status = br.getIsApproved();
        Buy = b;
        Cancel = c;
        this.destination = destination;
        Details = details;

        Details.setText("Details");
        Buy.setText("Buy");
        Cancel.setText("Cancel");

        Buy.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            if (!Status.equals("Approved")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Buy");
                alert.setHeaderText("Seller doesn't approved your request yet.\nYou can buy only approved vacations");
                alert.showAndWait();
                //blabla
                return;
            }
            controller.setCurrent_buying_request(br);
            Stage s = (Stage) details.getScene().getWindow();
//        s.close();
            try {
                Controller.vacationID = VacationID;
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BuyVacationWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("Buy");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        });
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

        details.setOnAction(event -> {

            Stage s = (Stage) details.getScene().getWindow();
//        s.close();
            try {
                Controller.vacationID = VacationID;
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("VacationDetailsWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(true);
                stage.setTitle("Details Vacation");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
