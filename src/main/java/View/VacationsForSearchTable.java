package View;

import Controller.Controller;
import Model.Excpetions.V4UException;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class VacationsForSearchTable {
    public Vacation vacation;
    public Button details;
    public Button buy;
    public String from;
    public String destination;
    public LocalDate departureDate;
    private static String vacationID;


    public static String get_static_vacationID(){
        return vacationID;
    }
    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public Button getDetails() {
        return details;
    }

    public void setDetails(Button details) {
        this.details = details;
    }

    public Button getBuy() {
        return buy;
    }

    public void setBuy(Button buy) {
        this.buy = buy;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate returnDate;


    public VacationsForSearchTable(Vacation vacation, Button details, Button buy) {
        this.vacation = vacation;
        this.departureDate = LocalDate.parse(vacation.getDepartureDate());
        this.returnDate = LocalDate.parse(vacation.getReturnDate());
        this.from = vacation.getFrom();
        this.destination = vacation.getDestination();
        this.details = details;
        this.buy = buy;

        buy.setText("Buy");
//        buy.maxWidth(Double.MAX_VALUE);
//        buy.maxHeight(Double.MAX_VALUE);



        details.setOnAction(event -> {

            Stage s = (Stage) details.getScene().getWindow();
//        s.close();
            try {
                vacationID = vacation.getVacationID();
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
        //todo:// check if user_id

        details.setText("Details");
//        details.maxWidth(Double.MAX_VALUE);
//        details.maxHeight(Double.MAX_VALUE);

        Controller controller = Controller.getInstance();

        buy.setOnAction(event -> {
            boolean connected_user  = true;
            if (controller.get_connected_user_id()==null)
                connected_user=false;
            if (connected_user) {
                boolean flag = false;
                try {
                    flag = controller.insertBuyingRequest(vacation.getVacationID());
                } catch (V4UException e) {
                    System.out.println("error in insert");
                }
                if (flag) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Request Sent");
                    alert.setHeaderText("Your request to buy has been sent to Buyer. \nPlease check your requests page soon");
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Log in");
                alert.setHeaderText("Only signed users are allowing to request to buy vacations\nPlease close the window, log in and try again");
                alert.showAndWait();
            }

        });

    }


}
