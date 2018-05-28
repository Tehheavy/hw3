package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField PasswordTF;

    @FXML
    private TextField UsernameTB;


    @FXML
    void Login(ActionEvent event) throws IOException {

//    	Stage stage = new Stage();
//    	Pane myPane = null;
//    	myPane = FXMLLoader.load(getClass().getResource("CustomerWindow.fxml"));
//    	Scene scene=new Scene(myPane);
//    	stage.setScene(scene);
//    	stage.show();
    	
    	Parent next = FXMLLoader.load(getClass().getResource("CustomerWindow.fxml"));

    	Scene scene = new Scene(next);
    	Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();

    }

    @FXML
    void initialize() {
        assert LoginButton != null : "fx:id=\"LoginButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert PasswordTF != null : "fx:id=\"PasswordTF\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert UsernameTB != null : "fx:id=\"UsernameTB\" was not injected: check your FXML file 'LoginWindow.fxml'.";


    }

}
