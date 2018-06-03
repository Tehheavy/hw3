package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class NotificationWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea NotificationText;
    public void SetText(String text){
    	NotificationText.setText(text);
    }


    @FXML
    void initialize() {
        assert NotificationText != null : "fx:id=\"NotificationText\" was not injected: check your FXML file 'NotificationWindow.fxml'.";


    }

}
