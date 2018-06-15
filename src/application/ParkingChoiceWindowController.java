/**
 * Sample Skeleton for 'ParkingChoiceWindow.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ParkingChoiceWindowController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ParkingChoicesListView"
    private ListView<ParkingOrder> ParkingChoicesListView; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ParkingChoicesListView != null : "fx:id=\"ParkingChoicesListView\" was not injected: check your FXML file 'ParkingChoiceWindow.fxml'.";


    }
    void load(){
    	ObservableList<ParkingOrder> items =FXCollections.observableArrayList();
    	items.add((new ParkingOrder(1,1,1,"weed","email","usrname",5,
    			Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()))));
    	ParkingChoicesListView.setItems(items);
    	ParkingChoicesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

    	    @Override
    	    public void handle(MouseEvent click) {

    	        if (click.getClickCount() == 2) {
    	           //Use ListView's getSelected Item
    	           ParkingOrder currentItemSelected = ParkingChoicesListView.getSelectionModel().getSelectedItem();
    	           System.out.println(currentItemSelected);
    	           Stage stage = (Stage)ParkingChoicesListView.getScene().getWindow();
    	           stage.close();
    	           //use this to do whatever you want to. Open Link etc.
    	        }
    	    }
    	});
    }
}
