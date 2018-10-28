package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DeleteUserView {
    public PasswordField tf_password;
    public TextField tf_username;
    private Controller controller = Controller.getInstance();


    public void delete_info(ActionEvent actionEvent) {
        String user, password;

        user = tf_username.getText();
        password = tf_password.getText();

        System.out.println("user deleted : " + user);
        Object[] user_date = new Object[]{user, password};
        //get username from db and delete it
        boolean flag = controller.delete("Users", user);
        // TODO: 28/10/2018 Delete this page Itzik
    }


}
