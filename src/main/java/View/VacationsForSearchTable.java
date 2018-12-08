package View;

import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        buy.setOnAction(event -> {
            Stage s = (Stage) buy.getScene().getWindow();
//        s.close();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BuyVacationWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(true);
                stage.setTitle("Buy Vacation");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        buy.setOnAction(event -> {
//            Stage s = (Stage) details.getScene().getWindow();
////        s.close();
//            try {
//                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("VacationDetailsWindow.fxml"));
//                Stage stage = new Stage();
//                stage.initModality(Modality.APPLICATION_MODAL);
//                stage.setResizable(true);
//                stage.setTitle("Details Vacation");
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        //todo:// remove the notes from this and do this.

        details.setText("Details");
//        details.maxWidth(Double.MAX_VALUE);
//        details.maxHeight(Double.MAX_VALUE);

    }
}
