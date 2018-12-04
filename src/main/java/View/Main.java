package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // TODO: 28/10/2018 sign up gives an alert that sucess and logs in Itzik
    // TODO: 28/10/2018 when creating a user with same name, need to return the right error (user taken) and not general error Itzik
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainPage.fxml"));

//        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        Controller controller = Controller.getInstance();
        //db.connect();
        //dropTable("Users");
        //db.delete("Users","itzik");
        //db.delete("Users", "Anael");
//        Object[] data = new Object[]{"itzik", "1234", new Date(2010 - 01 - 01), "itzik", "d", "haifa"};
//        System.out.println(db.insertNewUser("Users", data));
//        data = new Object[]{"Anael", "9876", new Date(2010 - 04 - 10), "Anael", "gorfinkel", "b7"};
//    //    System.out.println(db.insertNewUser("Users", data));
//        //db.print_table("Users");
//        System.out.println("-------------------------------------------");
//        //db.print_table("Users");
//        System.out.println("-------------------------------------------");
//        data = new Object[]{"Anael", "8080", new Date(2010 - 04 - 10), "itzik", "itzik", "b7"};
//       // db.update("Users", data, "Anael");
        //  db.print_table("Users");
    }

    public static void main(String[] args) {

        launch(args);
    }
}

