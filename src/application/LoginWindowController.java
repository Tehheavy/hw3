/**
 * Sample Skeleton for 'LoginWindow.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="RegisterButton"
    private Button RegisterButton; // Value injected by FXMLLoader

    @FXML // fx:id="UsernameTB"
    private TextField UsernameTB; // Value injected by FXMLLoader

    @FXML // fx:id="PasswordTF"
    private TextField PasswordTF; // Value injected by FXMLLoader

    @FXML // fx:id="LoginButton"
    private Button LoginButton; // Value injected by FXMLLoader

    @FXML
    void Login(ActionEvent event) {
    	if(UsernameTB.getText().isEmpty()||PasswordTF.getText().isEmpty())
    		return;
//    	Stage stage = new Stage();
//    	Pane myPane = null;
//    	myPane = FXMLLoader.load(getClass().getResource("CustomerWindow.fxml")); // test
//    	Scene scene=new Scene(myPane);
//    	stage.setScene(scene);
//    	stage.show();
    	try{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerWindow.fxml"));
        Parent root = (Parent) loader.load();
        CustomerWindowController CusControl = loader.getController();
        CusControl.SetAccountName(UsernameTB.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(UsernameTB.getText());
    	stage.show();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    	Stage stage2 = (Stage) LoginButton .getScene().getWindow();
    	stage2.close();
    }

    @FXML
    void RegisterClick(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert RegisterButton != null : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert UsernameTB != null : "fx:id=\"UsernameTB\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert PasswordTF != null : "fx:id=\"PasswordTF\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert LoginButton != null : "fx:id=\"LoginButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";

    }
}
