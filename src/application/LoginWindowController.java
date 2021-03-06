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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginWindowController {
	
	public String ip;
	public ClientClass client;
	public LoginWindowController() {
//		try {
//			client=new ClientClass("192.168.1.17","4138");
//		} catch(Exception e){
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setContentText("Could no reach server, please try again later");
//			alert.showAndWait();
//			Platform.exit();
//			System.out.println("Could Not Connect to server");
//		}
	}
	public void load(String str){
		ip=str;
		try {
			client=new ClientClass(str,"4138");
			System.out.println("in load");
		} catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Could no reach server, please try again later");
			alert.showAndWait();
			Platform.exit();
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
    private PasswordField PasswordTF; // Value injected by FXMLLoader

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
    		System.out.println("recieved:"+recieved);
    		if(recieved.equals("0"))
    		{
    			System.out.println("login failed, recieved: "+ recieved);
    			//client.CloseConnection(); CHECK THIS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setContentText("Username or password is incorrect");
    			alert.showAndWait();
    			return;
    		}
    		if(recieved.equals("-1"))
    		{
    			System.out.println("login failed, recieved: "+ recieved);
    			//client.CloseConnection(); CHECK THIS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setContentText("User is already online");
    			alert.showAndWait();
    			return;
    		}
    			
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeWindow.fxml"));
    		Parent root = (Parent) loader.load();
    		EmployeeWindowController CusControl = loader.getController();
    		CusControl.SetAccountName(LoginID);
    		CusControl.setClient(client);
    		CusControl.setAccountID(LoginID);
    		CusControl.ip=ip;
    		Stage stage = new Stage();
    		stage.setScene(new Scene(root));
    		stage.setTitle(LoginID);
    		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    			public void handle(WindowEvent we) {
    				System.out.println(CusControl.AccountID);
    				try {
    					CusControl.client.CloseConnection();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		});        
    		
    		if(recieved.equals("1")){	
    			CusControl.load("1");
		    	stage.show();
    		}
    		else if(recieved.equals("2")){
		        CusControl.load("2");
		    	stage.show();
    		}
    		else if(recieved.equals("3")){
		        CusControl.load("3");
		    	stage.show();
    		}
    		else if(recieved.equals("4")){
		        CusControl.load("4");
		    	stage.show();
    		}
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
