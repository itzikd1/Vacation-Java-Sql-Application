package View.RowsForTables;

import Controller.Controller;
import Model.BuyingRequest;
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

public class RequestForSellerRow {



    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String RequestID;
    public String destination;
    public String VacationID;
    public String BuyerUserName;
    public String Status;
    public Button Approve;
    public Button Decline;
    public Button Details;

    //<editor-fold desc="Get and Set">
    public Button getDetails() {
        return Details;
    }

    public void setDetails(Button details) {
        Details = details;
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
    //</editor-fold>

    public RequestForSellerRow(BuyingRequest br, String Destination, Button approve, Button decline, Button details) {
        RequestID = br.getRequestID();
        VacationID = br.getVacationID();
        BuyerUserName = br.getBuyerUserName();
        Status = br.getIsApproved();
        Approve = approve;
        Decline = decline;
        Details = details;
        destination = Destination;
        Approve.setText("Approve");
        Decline.setText("Decline");
        Details.setText("Details");

        Approve.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            boolean flag = false;
            if (Status.equals("Cancelled")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Approve");
                alert.setHeaderText("The Buyer has been cancelled his request. \nYou can't approve cancelled requests");
                alert.showAndWait();
                return;
            } else if (Status.equals("Buyer Paid")) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Are you approve " + br.getBuyerUserName() + "paid to you?\n" +
                        "please note: if you will click YES this the vacation will be marked as sold on our system\n" +
                        "if you choose no, nothing will happen for now", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> result = alert2.showAndWait();
                if (result.get() == ButtonType.YES) {
                    controller.setCurrent_buying_request(br);
                    flag = controller.insert_purchase("br");
                    if (flag) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Buying Request Approved");
                        alert.setHeaderText("Your confirmation has been sent to " + br.getBuyerUserName()+ " \nYour vacation has been sold!");
                        alert.showAndWait();
                    }
                    return;
                }

            } else if (Status.equals("Declined")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Approve");
                alert.setHeaderText("You already not approved this request. \nSorry, but this is too late to regret");
                alert.showAndWait();
                return;
            }
            else { //case of "Approved" or "Waiting"
                    Alert alert3 = new Alert(Alert.AlertType.WARNING, "Are you approve " + br.getBuyerUserName() + " paid to you?\n" +
                            "please note: if you will click YES this vacation will be marked as sold on our system\n" +
                            "if you will click NO, you will approve to " + br.getBuyerUserName() + " to buy, so he will probably pay to you soon",
                            ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    Optional<ButtonType> result2 = alert3.showAndWait();
                    if (result2.get() == ButtonType.YES) {
                        controller.setCurrent_buying_request(br);
                        flag = controller.insert_purchase("br");
                        if (flag) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Buying Request Approved");
                            alert.setHeaderText("Your confirmation has been sent to Seller. \nYour vacation has been sold!");
                            alert.showAndWait();
                        }
                        } else if (result2.get() == ButtonType.NO) {
                            flag = controller.updateRequest(RequestID, "Approved");
                            if (flag) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Buying Request Approved");
                                alert.setHeaderText("Your confirmation has been sent to Buyer. \nPlease contact him to be paid and then approve it here");
                                alert.showAndWait();
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
            flag = controller.updateRequest(RequestID, "Declined");
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buying Request Declined");
                alert.setHeaderText("Your Decline has been sent to " + BuyerUserName);
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
    }
}