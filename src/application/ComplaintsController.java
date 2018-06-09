/**
 * Sample Skeleton for 'Valery_Window.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ComplaintsController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="chooseComplaintMenuButton"
	private MenuButton chooseComplaintMenuButton; // Value injected by FXMLLoader

	@FXML // fx:id="ComplaintText"
	private TextArea ComplaintText; // Value injected by FXMLLoader

	@FXML // fx:id="SendComplaintButton"
	private Button SendComplaintButton; // Value injected by FXMLLoader

	@FXML
	void sendButton(ActionEvent event) {

		String  complaintText = ComplaintText.getText();
		if(complaintText.length() < 255 && complaintText.length() != 0 ){
			//client.sendmessage(message)
			Stage stage2 = (Stage) SendComplaintButton .getScene().getWindow();
	    	stage2.close();
		}


	}

	
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {

		assert chooseComplaintMenuButton != null : "fx:id=\"chooseComplaintMenuButton\" was not injected: check your FXML file 'Valery_Window.fxml'.";
		assert ComplaintText != null : "fx:id=\"ComplaintText\" was not injected: check your FXML file 'Valery_Window.fxml'.";
		assert SendComplaintButton != null : "fx:id=\"SendComplaintButton\" was not injected: check your FXML file 'Valery_Window.fxml'.";

	}


	String AccountID;
	ClientClass client;
	void setAccountID(String rhs)
	{
		this.AccountID=rhs;
	}
	void setClient(ClientClass rhs)
	{
		this.client=rhs;
	}

}
