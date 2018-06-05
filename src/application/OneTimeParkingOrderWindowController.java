package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OneTimeParkingOrderWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> ArrivalTimeMinuteBox;

    @FXML
    private ComboBox<?> LeaveTimeHourBox;

    @FXML
    private ComboBox<?> LeaveTimeMinuteBox;

    @FXML
    private Button OrderButton;

    @FXML
    private ComboBox<?> RequestedMallMENU;

    @FXML
    private ComboBox<?> ArrivalTimeHourBox;

    @FXML
    private DatePicker LeaveDateBox;

    @FXML
    private TextField IDTB;

    @FXML
    private DatePicker ArrivalDateBox;

    @FXML
    private TextField EmailTB;

    @FXML
    private Label PriceLabel;

    @FXML
    private TextField CarIDTB;

    @FXML
    void OrderButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert ArrivalTimeMinuteBox != null : "fx:id=\"ArrivalTimeMinuteBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert LeaveTimeHourBox != null : "fx:id=\"LeaveTimeHourBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert LeaveTimeMinuteBox != null : "fx:id=\"LeaveTimeMinuteBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert OrderButton != null : "fx:id=\"OrderButton\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert RequestedMallMENU != null : "fx:id=\"RequestedMallMENU\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert ArrivalTimeHourBox != null : "fx:id=\"ArrivalTimeHourBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert LeaveDateBox != null : "fx:id=\"LeaveDateBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert IDTB != null : "fx:id=\"IDTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert ArrivalDateBox != null : "fx:id=\"ArrivalDateBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert EmailTB != null : "fx:id=\"EmailTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert PriceLabel != null : "fx:id=\"PriceLabel\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert CarIDTB != null : "fx:id=\"CarIDTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";

    }
}
