package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegisterWindowController {

	public ClientClass client;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField PasswordTB;

	@FXML
	private Button RegisterButton;

	@FXML
	private TextField UsernameTB;


	@FXML
	void RegisterButtonClick(ActionEvent event) {
		
		
		if(UsernameTB.getText().isEmpty()||PasswordTB.getText().isEmpty())
			return;
		String LoginID=UsernameTB.getText().toLowerCase();
		String PasswordID=PasswordTB.getText().toLowerCase();
		try{
			//192.168.1.17
			//11.1.4.79

			if(!client.sendmessage("register "+LoginID+" "+PasswordID).equals("acceptedregistery"))
			{
				System.out.println("registry failed");
				CreatePopupWindow popup = new CreatePopupWindow("Registration Failed");
				
				return;
			}
			CreatePopupWindow popup = new CreatePopupWindow("Registration Successful");
			
			

		}
		catch(Exception e)
		{
			System.out.println("registration failed");
		}
		Stage stage2 = (Stage) RegisterButton .getScene().getWindow();
    	stage2.close();
		
		
		
	}

	@FXML
	void initialize() {
		assert PasswordTB != null : "fx:id=\"PasswordTB\" was not injected: check your FXML file 'RegisterWindow.fxml'.";
		assert RegisterButton != null : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'RegisterWindow.fxml'.";
		assert UsernameTB != null : "fx:id=\"UsernameTB\" was not injected: check your FXML file 'RegisterWindow.fxml'.";


	}

}
