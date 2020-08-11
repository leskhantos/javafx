package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField SignUploginField;

    @FXML
    private PasswordField SIgnUpPasswordField;

    @FXML
    private Button SignUp;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpSurname;

    @FXML
    private TextField signUpLocation;

    @FXML
    private CheckBox checkMan;

    @FXML
    private CheckBox CheckWoman;

    @FXML
    void initialize() {
        SignUp.setOnAction(event -> {
            signUpNewUser();
    });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String firstName =signUpName.getText();
        String lastName =signUpSurname.getText();
        String userName =SignUploginField.getText();
        String password =SIgnUpPasswordField.getText();
        String location =signUpLocation.getText();
        String gender ="";
        if (checkMan.isSelected())
            gender="Male";
        else
            gender="Female";
        User user = new User(firstName,lastName,userName,password,location,gender);

        dbHandler.signUpUser(user);

    }
}
