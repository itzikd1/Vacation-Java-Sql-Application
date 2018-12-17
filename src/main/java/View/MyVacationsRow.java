package View;

import Controller.Controller;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class MyVacationsRow {
    public Vacation vacation;
    public Button details;
    public Button delete;
    public String from;
    public String destination;
    public String SellerUserName;
    public LocalDate departureDate;


    // public static String get_static_vacationID(){
    //return vacationID;
    // }
    public Vacation getVacation() {
        return vacation;
    }

    //<editor-fold desc="GetSet">
    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public Button getDetails() {
        return details;
    }

    public void setDetails(Button details) {
        this.details = details;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
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

    public String getSellerUserName() {
        return SellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        SellerUserName = sellerUserName;
    }
    //</editor-fold>


    public MyVacationsRow(Vacation vacation, Button details, Button delete) {
        this.vacation = vacation;
        this.departureDate = LocalDate.parse(vacation.getDepartureDate());
        this.returnDate = LocalDate.parse(vacation.getReturnDate());
        this.from = vacation.getFrom();
        this.destination = vacation.getDestination();
        this.details = details;
        this.delete = delete;

        delete.setText("Delete");
        details.setText("Details");


        details.setOnAction(event -> {

            Stage s = (Stage) details.getScene().getWindow();
//        s.close();
            try {
                Controller.vacationID = vacation.getVacationID();
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

        Controller controller = Controller.getInstance();


        delete.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Are you sure you want to delete this vacation?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                controller.delete_myVacation(vacation.getVacationID());
            }
        });


    }


}
