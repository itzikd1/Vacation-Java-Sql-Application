package View.RowsForTables;

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
            try {
                flag = controller.insertBuyingRequest(vacation.getVacationID(),"will be changed"); //will be changed on model because it has been setted from outside
            } catch (V4UException e) {
                e.printStackTrace();
            }
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Trade request");
                alert.setHeaderText("your request for trade has been sent to seller!\n You cancel this request anytime until the seller will approve this,\nif the seller will approve this -> you will switch your vacations!\n to be updated, check your purchases / requests page from time to time!");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Why asking Trading again?!");
                alert.setHeaderText("You already request to trade this vacation. \nPlease keep calm and check your trade requests page soon");
                alert.showAndWait();
                System.out.println("should never prints this line. so if this happen check the problem on line 144 pon MyVacationForTradeRow.java");
            }
            s.close();
        });
    }
}


