/**
 * Sample Skeleton for 'LoginWindow.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController {
	

	public ClientClass client;
	public LoginWindowController() {
		try {
			client=new ClientClass("11.1.0.222","4138");
		} catch(Exception e){
			System.out.println("Could Not Connect to server");
		}
	}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="RegisterButton"
    private Button RegisterButton; // Value injected by FXMLLoader

    @FXML // fx:id="UsernameTB"
    private TextField UsernameTB; // Value injected by FXMLLoader

    @FXML // fx:id="PasswordTF"
    private TextField PasswordTF; // Value injected by FXMLLoader

    @FXML // fx:id="LoginButton"
    private Button LoginButton; // Value injected by FXMLLoader

    
    @FXML
    void Login(ActionEvent event) {
    	if(UsernameTB.getText().isEmpty()||PasswordTF.getText().isEmpty())
    		return;
    		String LoginID=UsernameTB.getText().toLowerCase();
    		String PasswordID=PasswordTF.getText().toLowerCase();
    	try{
    		//192.168.1.17
    		//11.1.4.79
    		System.out.println("login "+LoginID+" "+PasswordID);
    		String recieved=client.sendmessage("login "+LoginID+" "+PasswordID);
    		if(!recieved.equals("acceptedlogin"))
    		{
    			System.out.println("login failed, recieved: "+ recieved);
    			//client.CloseConnection(); CHECK THIS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    			return;
    		}

    			
    		
    		
    		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerWindow.fxml"));
        Parent root = (Parent) loader.load();
        CustomerWindowController CusControl = loader.getController();
        CusControl.SetAccountName(LoginID);
        CusControl.setClient(client);
        CusControl.setAccountID(LoginID);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(LoginID);
    	stage.show();
    	}
    	catch(ConnectException e){
    		System.out.println("Could not connect to server exception");
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	Stage stage2 = (Stage) LoginButton .getScene().getWindow();
    	stage2.close();
    }

    @FXML
    void RegisterClick(ActionEvent event) {
    	try{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterWindow.fxml"));
        Parent root = (Parent) loader.load();
        RegisterWindowController RegisterControl = loader.getController();
        RegisterControl.client=client;
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(new Scene(root));
    	stage.show();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert RegisterButton != null : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert UsernameTB != null : "fx:id=\"UsernameTB\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert PasswordTF != null : "fx:id=\"PasswordTF\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert LoginButton != null : "fx:id=\"LoginButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";

    }
}
