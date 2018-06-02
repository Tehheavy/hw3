/**
 * Sample Skeleton for 'LoginWindow.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
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
    	//if(UsernameTB.getText().isEmpty()||PasswordTF.getText().isEmpty())
    		//return;	
    	try{
//    		int num;
//    		String temp;
//    		Socket mainSocket=new Socket("192.168.1.17",4138);
//    		
//    		Scanner recieved=new Scanner(mainSocket.getInputStream());
//    		PrintStream stream=new PrintStream(mainSocket.getOutputStream());
//    		stream.println("login");
//    		temp=recieved.nextLine();
//    		System.out.println(temp+" first message");
//    		if(temp.compareTo("accepted")==0){
//    			stream.println(UsernameTB.getText());
//        		temp=recieved.nextLine();
//        		System.out.println(temp+" second message");
//        		if(temp.compareTo("accepted")!=0)
//        			return;
//    		}
//    		else{
//    			return;
//    		}
    		ClientClass client=new ClientClass("192.168.1.17","4138");
    		System.out.println(client.sendmessage("1"));
    		System.out.println(client.sendmessage("alex"));

    			
    		
    		
    		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerWindow.fxml"));
        Parent root = (Parent) loader.load();
        CustomerWindowController CusControl = loader.getController();
        CusControl.SetAccountName(UsernameTB.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(UsernameTB.getText());
    	stage.show();
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
