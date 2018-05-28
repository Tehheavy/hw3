package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class CustomerWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ComplaintButton;

    @FXML
    private Label LabelBox;

    @FXML
    private Button OrderButton;

    @FXML
    private Button barkButton;


    @FXML
    void Order(ActionEvent event) {
    }

    @FXML
    void SumbitComplaint(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert ComplaintButton != null : "fx:id=\"ComplaintButton\" was not injected: check your FXML file 'CustomerWindow.fxml'.";
        assert LabelBox != null : "fx:id=\"LabelBox\" was not injected: check your FXML file 'CustomerWindow.fxml'.";
        assert OrderButton != null : "fx:id=\"OrderButton\" was not injected: check your FXML file 'CustomerWindow.fxml'.";
        assert barkButton != null : "fx:id=\"barkButton\" was not injected: check your FXML file 'CustomerWindow.fxml'.";


    }

}
