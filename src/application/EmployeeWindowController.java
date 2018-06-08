/**
 * Sample Skeleton for 'EmployeeWindow.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EmployeeWindowController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="VehicleLeaveButton"
    private Button VehicleLeaveButton; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingCancelButton"
    private Button ParkingCancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="VehicleEnterButton"
    private Button VehicleEnterButton; // Value injected by FXMLLoader

    @FXML
    void VehicleEnterMethod(ActionEvent event) {

    }

    @FXML
    void VehicleLeaveMethod(ActionEvent event) {

    }

    @FXML
    void ParkingCancelMethod(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert VehicleLeaveButton != null : "fx:id=\"VehicleLeaveButton\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert ParkingCancelButton != null : "fx:id=\"ParkingCancelButton\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert VehicleEnterButton != null : "fx:id=\"VehicleEnterButton\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";

    }
}
