package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authSignIn;

    @FXML
    private Button loginSignupButton;

    @FXML
    void initialize() {
        authSignIn.setOnAction(event -> {
        String logintxt = loginField.getText().trim();
        String loginPass = passwordField.getText().trim();
            if (!logintxt.equals("") && !loginPass.equals("")){
                loginUser(logintxt,loginPass);
            }else {
                System.out.println("Login and password is empty");
            }
        });

        loginSignupButton.setOnAction(event -> {
            openNewScene("/sample/signUp.fxml");
        });

    }

    private void loginUser(String logintxt, String loginPass) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(logintxt);
        user.setPassword(loginPass);
        ResultSet result = dbHandler.getUser(user);
        int counter =0;
        while (true){
            try {
                if (!result.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;
        }
        if (counter>=1){
            openNewScene("/sample/app.fxml");
        }else {
            Shake shLogin =new Shake(loginField);
            Shake shPass =new Shake(passwordField);
            shLogin.playAnim();
            shPass.playAnim();
        }
    }
    public void openNewScene(String window){
        loginField.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}

