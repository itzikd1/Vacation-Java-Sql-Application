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

import javax.print.attribute.standard.Destination;
import java.io.IOException;

public class RequestForSellerRow {

    public String RequestID;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String destination;
    public String VacationID;
    public String BuyerUserName;
    public String Status;
    public Button Approve;
    public Button Decline;
    public Button Details;

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
            if (Status.equals("Cancelled")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Approve");
                alert.setHeaderText("The Buyer has been cancelled his request. \nYou can't approve cancelled requests");
                alert.showAndWait();
                return;
            }
            else if (Status.equals("Approved")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Approve");
                alert.setHeaderText("You already approved this request. \nYou can't approve the same request more than once");
                alert.showAndWait();
                return;
            }
            else if (Status.equals("Declined")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Can't Approve");
                alert.setHeaderText("You already not approved this request. \nSorry, but this is too late to regret");
                alert.showAndWait();
                return;
            }
            boolean flag = false;
            flag = controller.updateRequest(RequestID,"Approved");
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buying Request Approved");
                alert.setHeaderText("Your confirmation has been sent to Buyer. \nPlease check your requests page soon to see if he paid");
                alert.showAndWait();
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
            flag = controller.updateRequest(RequestID,"Declined");
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buying Request Declined");
                alert.setHeaderText("Your Decline has been sent to " + BuyerUserName);
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
