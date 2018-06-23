/**
 * Sample Skeleton for 'CompaintWindow.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ComplaintWindowController {

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
    	try {
			System.out.println(client.sendmessage("complaint "+ AccountID+" "+ComplaintText.getText()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	System.out.println(ComplaintText.getText());
    	Stage stage2 = (Stage) SendComplaintButton .getScene().getWindow();
    	stage2.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert chooseComplaintMenuButton != null : "fx:id=\"chooseComplaintMenuButton\" was not injected: check your FXML file 'CompaintWindow.fxml'.";
        assert ComplaintText != null : "fx:id=\"ComplaintText\" was not injected: check your FXML file 'CompaintWindow.fxml'.";
        assert SendComplaintButton != null : "fx:id=\"SendComplaintButton\" was not injected: check your FXML file 'CompaintWindow.fxml'.";
        chooseComplaintMenuButton.setVisible(false);

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
