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
import javafx.stage.Stage;


public class SelectParkingTypeWindowController {

	String AccountID;
	ClientClass client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CasualParkingButton;

    @FXML
    private Button FullSubscriptionButton;

    @FXML
    private Button OneTimeParkingButton;

    @FXML
    private Button RoutineSubscriptionButton;
    public void setAccountID(String rhs)
    {
    	AccountID=rhs;
    }
    public void setClient(ClientClass rhs)
    {
    	client=rhs;
    }


    @FXML
    void CasualParkingButtonClick(ActionEvent event) {
    }

    @FXML
    void FullSubscriptionButtonClick(ActionEvent event) {
    }

    @FXML
    void OneTimeParkingButtonClick(ActionEvent event) {
    	try{
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("OneTimeParkingOrderWindow.fxml"));
            Parent root = (Parent) loader.load();
            OneTimeParkingOrderWindowController RegisterControl = loader.getController();
            RegisterControl.setClient(client);
            RegisterControl.setAccountID(AccountID);
            Stage stage = new Stage();
            stage.setTitle("Order");
            stage.setScene(new Scene(root));
        	stage.show();
    		Stage stage2 = (Stage) OneTimeParkingButton .getScene().getWindow();
        	stage2.close();
    		//SEXY TIME BABY!
        	}
        	catch(IOException e){
        		e.printStackTrace();
        	}
    }

    @FXML
    void RoutineSubscriptionButtonClick(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert CasualParkingButton != null : "fx:id=\"CasualParkingButton\" was not injected: check your FXML file 'SelectParkingTypeWindow.fxml'.";
        assert FullSubscriptionButton != null : "fx:id=\"FullSubscriptionButton\" was not injected: check your FXML file 'SelectParkingTypeWindow.fxml'.";
        assert OneTimeParkingButton != null : "fx:id=\"OneTimeParkingButton\" was not injected: check your FXML file 'SelectParkingTypeWindow.fxml'.";
        assert RoutineSubscriptionButton != null : "fx:id=\"RoutineSubscriptionButton\" was not injected: check your FXML file 'SelectParkingTypeWindow.fxml'.";


    }

}
