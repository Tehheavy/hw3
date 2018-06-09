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
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class CustomerWindowController  {

	String AccountID;
	ClientClass client;
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
    	try{
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectParkingTypeWindow.fxml"));
            Parent root = (Parent) loader.load();
            SelectParkingTypeWindowController RegisterControl = loader.getController();
            RegisterControl.setClient(client);
            RegisterControl.setAccountID(AccountID);
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root));
        	stage.show();
        	}
        	catch(IOException e){
        		e.printStackTrace();
        	}
    }

    @FXML
    void SumbitComplaint(ActionEvent event) {
    	
    	try{
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Valery_Window.fxml"));
            Parent root = (Parent) loader.load();
           ComplaintsController complaintsController = loader.getController();
           complaintsController.setClient(client);
           complaintsController.setAccountID(AccountID);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
        	stage.show();
        	}
        	catch(IOException e){
        		e.printStackTrace();
        	} 
    	  
    }
    void setAccountID(String rhs)
    {
    	AccountID=rhs;
    }
    void setClient(ClientClass rhs)
    {
    	client=rhs;
    }
    void SetAccountName(String rhs)
    {
    	LabelBox.setText("Welcome "+rhs);
    }

    @FXML
    void initialize() {
        assert ComplaintButton != null : "fx:id=\"ComplaintButton\" was not injected: check your FXML file 'CustomerWindow.fxml'.";
        assert LabelBox != null : "fx:id=\"LabelBox\" was not injected: check your FXML file 'CustomerWindow.fxml'.";
        assert OrderButton != null : "fx:id=\"OrderButton\" was not injected: check your FXML file 'CustomerWindow.fxml'.";
        assert barkButton != null : "fx:id=\"barkButton\" was not injected: check your FXML file 'CustomerWindow.fxml'.";


    }

    
    
}
