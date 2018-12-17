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

public class MyVacationForTradeRow {
    public Vacation vacation;
    public Button details;
    public Button choose;

    public Button getDetails() {
        return details;
    }

    public void setDetails(Button details) {
        this.details = details;
    }

    public Button getChoose() {
        return choose;
    }

    public void setChoose(Button choose) {
        this.choose = choose;
    }

    public String from;
    public String destination;

    public LocalDate departureDate;


    // public static String get_static_vacationID(){
    //return vacationID;
    // }
    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
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


    public MyVacationForTradeRow(Vacation vacation, Button details, Button choose) {
        this.vacation = vacation;
        this.departureDate = LocalDate.parse(vacation.getDepartureDate());
        this.returnDate = LocalDate.parse(vacation.getReturnDate());
        this.from = vacation.getFrom();
        this.destination = vacation.getDestination();
        this.details = details;
        this.choose=choose;

        choose.setText("Choose");
        details.setText("Details");
//        buy.maxWidth(Double.MAX_VALUE);
//        buy.maxHeight(Double.MAX_VALUE);


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
        choose.setOnAction(event -> {
            boolean flag = false;
            Stage s = (Stage) choose.getScene().getWindow();
            controller.setTradeId(vacation.getVacationID());
            try {
                flag = controller.insertBuyingRequest(vacation.getVacationID(),vacation.getUserName());
            } catch (V4UException e) {
                e.printStackTrace();
            }
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Trade request");
                alert.setHeaderText("your request for trade has been sent to seller!\n please check soon your requests page!");
                alert.showAndWait();
            }
            else {
                System.out.println("problem in inserting trade request");
            }
            s.close();
        });
    }
}

/**
 todo:
 יוזר שולח בקשה לקנייה או החלפה
 אם קנייה:
 סלר מאשר
 יוזר מאמת ששילם
 סלר מ..  אשר קבלת תשלום
 העסקה התבצעה

 אם החלפה:
 סלר מאשר
**/

//todo: to open a new trades requests table & move the history of purchases to another page.