/**
 * Sample Skeleton for 'ParkingChoiceWindow.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ParkingChoiceWindowController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ParkingChoicesListView"
    private ListView<?> ParkingChoicesListView; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ParkingChoicesListView != null : "fx:id=\"ParkingChoicesListView\" was not injected: check your FXML file 'ParkingChoiceWindow.fxml'.";

    }
}
