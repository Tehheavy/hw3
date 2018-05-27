/**
 * Sample Skeleton for "hello.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Controller"
    private AnchorPane Controller; // Value injected by FXMLLoader

    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader

    @FXML // fx:id="counter"
    private TextArea counter; // Value injected by FXMLLoader

    @FXML // fx:id="helloTF"
    private TextArea helloTF; // Value injected by FXMLLoader
    private int counterman=0;

    // Handler for Button[fx:id="button"] onAction
    @FXML
    void sayhello(ActionEvent event) {
        // handle the event here
    	counterman++;
    	if(counterman%2==1)
    	helloTF.setText("Hello World");
    	else
    		helloTF.setText("");
    	counter.setText(Integer.toString(counterman));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Controller != null : "fx:id=\"Controller\" was not injected: check your FXML file 'hello.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'hello.fxml'.";
        assert counter != null : "fx:id=\"counter\" was not injected: check your FXML file 'hello.fxml'.";
        assert helloTF != null : "fx:id=\"helloTF\" was not injected: check your FXML file 'hello.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

    }

}
