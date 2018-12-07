package View;

import Controller.Controller;
import Model.Excpetions.V4UException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class SellVacationView implements Initializable {

    String user_name = null;
    public DatePicker bd;
    public Button publish_for_sale_button;
    public Button BackButton;

    private Controller controller = Controller.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] details = controller.readConnectedUser();
        user_name = details[0];

        //birthday
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //todo: not disconnent of db sometimes
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(details[2], formatter);
        bd.setValue(localDate);

    }

//    public void update_info(ActionEvent actionEvent) {
//        /*
//        show current connected_user's details by default
//         */
//
//        String user, city, ln, fn, password;
//        Date date = new Date();
//
////        updatedDetails[2] = bd.getValue();
//        boolean flag = false;
//        try {
//            flag = controller.update("Users", updatedDetails, (String) updatedDetails[0]);
//        } catch (V4UException e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Error");
//            alert.setHeaderText(e.getMessage());
//            alert.showAndWait();
//
//        }
//        if (flag) {
//            Stage s = (Stage) BackButton.getScene().getWindow();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Update Confirm");
//            alert.setHeaderText("Your details have been updated!");
//            alert.showAndWait();
//            s.close();
//        }
//    }


    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
//        try {
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainPage.fxml"));
//            Stage stage = new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setResizable(true);
//            stage.setTitle("Vacation4U");
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}
