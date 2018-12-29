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

public class RequestForBuyerRow {

    public String RequestID;
    public String VacationID;
    public String SellerUserName;
    public String Status;
    public Button Buy;
    public Button Cancel;
    public String destination;
    public Button Details;

    //<editor-fold desc="Get and Set">
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
    //</editor-fold>

    public RequestForBuyerRow(BuyingRequest br, String destination, Button b, Button c, Button details) {
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
                if (!Status.equals("Buyer Paid")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Can't Buy");
                    alert.setHeaderText("Seller doesn't approved your request yet.\nYou can buy only approved vacations");
                    alert.showAndWait();
                    return;
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Contact seller");
                    alert.setHeaderText("Seller doesn't approved your payment yet.\nIf you really paid for this vacation, contact him ASAP");
                    alert.showAndWait();
                    return;
                }

            }
            controller.setCurrent_buying_request(br);
            Stage s = (Stage) details.getScene().getWindow();
                boolean flag2 = false;
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Are you promise to pay to " +
                        br.getSellerUserName() + " immediately?",ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> result = alert2.showAndWait();
                if (result.get() == ButtonType.YES) {
                    Controller.vacationID = VacationID;
                }
                    flag2 = controller.updateRequest(RequestID, "Buyer Paid");
                    if (flag2) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Paid");
                        alert.setHeaderText("You will get the vacation only after seller will approve the cash payment by you\n" +
                                "Please check your Purchases page soon!");
                        alert.showAndWait();
                    }




//
//                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BuyVacationWindow.fxml"));
//                Stage stage = new Stage();
//                stage.initModality(Modality.APPLICATION_MODAL);
//                stage.setResizable(false);
//                stage.setTitle("Buy");
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
       // });
//            boolean flag = false;
        });

        Cancel.setOnAction(event -> {
            Controller controller = Controller.getInstance();
            boolean flag = false;
            flag = controller.updateRequest(RequestID, "Cancelled");
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Buying Request Cancelled");
                alert.setHeaderText("Your Cancellation has been sent to " + SellerUserName);
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