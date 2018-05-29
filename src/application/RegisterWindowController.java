package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class RegisterWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PasswordTB;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField UsernameTB;


    @FXML
    void RegisterButtonClick(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert PasswordTB != null : "fx:id=\"PasswordTB\" was not injected: check your FXML file 'RegisterWindow.fxml'.";
        assert RegisterButton != null : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'RegisterWindow.fxml'.";
        assert UsernameTB != null : "fx:id=\"UsernameTB\" was not injected: check your FXML file 'RegisterWindow.fxml'.";


    }

}
