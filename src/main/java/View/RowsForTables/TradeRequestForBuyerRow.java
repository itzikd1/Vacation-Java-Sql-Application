package View.RowsForTables;

import Controller.Controller;
import Model.TradeRequest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TradeRequestForBuyerRow {


    public String RequestID;
    public String Destination;
    public String VacationID;
    public String SellerUserName;
    public String Status;
    public Button Offer;
    public Button Cancel;
    public Button Details;

    public TradeRequestForBuyerRow(TradeRequest tr, String Destination, Button offer, Button cancel, Button details) {
        RequestID = tr.getRequestID();
        this.Destination = Destination;
        VacationID = tr.getVacationID();
        SellerUserName = tr.getSellerUserName();
        Status = tr.getIsApproved();
        Offer = offer;
        Cancel = cancel;
        Details = details;

        String trade_vac_id = tr.getTradeID();

        //set text of Buttons
        Offer.setText("Offer");
        Cancel.setText("Cancel");
        Details.setText("Details");

        //set functionality of buttons


        Cancel.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            if (!Status.equals("Waiting")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Cancel");
                alert.setHeaderText("Only waiting request can be cancelled. \nYou can't regret now, sorry! too late");
                alert.showAndWait();
                return;
            }
            boolean flag = false;
            flag = controller.updateTradeRequest(RequestID, "Cancelled");
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Trade Request Cancelled");
                alert.setHeaderText("Your Cancellation has been sent to " + tr.getSellerUserName());
                alert.showAndWait();
            }
        });


        details.setOnAction(event -> {
            Stage s = (Stage) details.getScene().getWindow();
            try {
                Controller.vacationID = VacationID;
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("VacationDetailsWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("Details Vacation");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        offer.setOnAction(event -> {
            Stage s = (Stage) details.getScene().getWindow();
            try {
                Controller.vacationID = trade_vac_id;
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("VacationDetailsWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("Details of offered Vacation");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String requestID) {
        RequestID = requestID;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
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

    public Button getOffer() {
        return Offer;
    }

    public void setOffer(Button offer) {
        Offer = offer;
    }

    public Button getCancel() {
        return Cancel;
    }

    public void setCancel(Button cancel) {
        Cancel = cancel;
    }

    public Button getDetails() {
        return Details;
    }

    public void setDetails(Button details) {
        Details = details;
    }

}