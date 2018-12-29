package View.RowsForTables;

import Controller.Controller;
import Model.TradeRequest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class TradeRequestForSellerRow {

    public String RequestID;
    public String Destination;
    public String VacationID;
    public String TraderUserName;
    public String Status;
    public Button Offer;
    public Button Approve;
    public Button Decline;
    public Button Details;

    public TradeRequestForSellerRow(TradeRequest tr, String Destination, Button offer, Button approve, Button decline, Button details) {
        RequestID = tr.getRequestID();
        this.Destination = Destination;
        VacationID = tr.getVacationID();
        TraderUserName = tr.getBuyerUserName();
        Status = tr.getIsApproved();
        Offer = offer;
        Approve = approve;
        Decline = decline;
        Details = details;

        String trade_vac_id = tr.getTradeID();

        //set text of Buttons
        Offer.setText("Offer");
        Approve.setText("Approve");
        Decline.setText("Decline");
        Details.setText("Details");

        //set functionality of buttons
        Approve.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            if (Status.equals("Cancelled")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Approve");
                alert.setHeaderText("The Trader has been cancelled his request. \nYou can't approve cancelled requests");
                alert.showAndWait();
                return;
            } else if (Status.equals("Declined")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Approve");
                alert.setHeaderText("You already not approved this request. \nSorry, but this is too late to regret");
                alert.showAndWait();
                return;
            }
            boolean flag = false;
            Alert alert2 = new Alert(Alert.AlertType.WARNING,
                    "Are you sure you want to approve this trade?\n this means you'll get the vacation of " +
                            tr.getBuyerUserName() + " and " + tr.getBuyerUserName() + " will get your vacation\nyou will" +
                            " not be able to regret after that without confirmation from " + tr.getBuyerUserName(),
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == ButtonType.YES) {
                flag = controller.updateTradeRequest(RequestID, "Approved");
                if (flag) {
                    controller.setCurrent_buying_request(tr);
                    boolean flag2 = controller.insert_purchase("tr");
                    if (flag2) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Trading Request Approved");
                        alert.setHeaderText("Your confirmation has been sent to "  + tr.getBuyerUserName() +"\nso this is means you switched your vacations!\nEnjoy in your new vacation!");
                        alert.showAndWait();
                    } else {
                        System.out.println("huge problem in creating two purchases! if this ever will be printed " +
                                "go to conteoller and add terms to if trade case on line 104");
                    }
                }
            }

        });


        decline.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            if (!Status.equals("Waiting")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Decline");
                alert.setHeaderText("Only waiting request can be declined. \nYou can't regret now, sorry! too late");
                alert.showAndWait();
                return;
            }
            boolean flag = false;
            flag = controller.updateTradeRequest(RequestID, "Declined");
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Trade Request Declined");
                alert.setHeaderText("Your Decline has been sent to " + this.TraderUserName);
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

    public String getTraderUserName() {
        return TraderUserName;
    }

    public void setTraderUserName(String traderUserName) {
        TraderUserName = traderUserName;
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

    public Button getDetails() {
        return Details;
    }

    public void setDetails(Button details) {
        Details = details;
    }

}
