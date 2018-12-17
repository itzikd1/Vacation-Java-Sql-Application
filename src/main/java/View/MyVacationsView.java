package View;
import Controller.Controller;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MyVacationsView implements Initializable {

    private Controller controller = Controller.getInstance();
    public Button BackButton;


    public TableView<MyVacations> vacationsTable;
    public TableColumn<MyVacations, String> from;
    public TableColumn<MyVacations, String> to;
    public TableColumn<MyVacations, LocalDate> departDate;
    public TableColumn<MyVacations, LocalDate> returnDate;
    public TableColumn<MyVacations, Button> moreDetails;
    public TableColumn<MyVacations, Button> edit;
    public TableColumn<MyVacations, Button> delete;

    //public Button b = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        moreDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        // TODO: 17/12/2018 edit  Itzik
        ObservableList<MyVacations> vacations = controller.getMyVacations();
        //vacationsTable.setItems(tryme());
        vacationsTable.setItems(vacations);
    }

    private ObservableList<Vacation> tryme() {
        ObservableList<Vacation> v = FXCollections.observableArrayList();
        v.add(new Vacation("0","1","1","12-12-1990","1","1","12-12-1990",
                "1","12-12-1990","1","1","1","1","1",
                "1","1","1","Not"));
        return v;
    }

    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
    }
}
