/**
 * Sample Skeleton for 'EmployeeWindow.fxml' Controller Class
 */

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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class EmployeeWindowController {
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ParkingSpotsMenuItem"
    private MenuItem ParkingSpotsMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="VehicleLeaveButton"
    private Button VehicleLeaveButton; // Value injected by FXMLLoader

    @FXML // fx:id="ChangeUserMenuItem"
    private MenuItem ChangeUserMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="OrderCasualParkingMenuItem"
    private MenuItem OrderCasualParkingMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="OrderOneTimeParkingMenuItem"
    private MenuItem OrderOneTimeParkingMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="CustomerComplainsMenuItem"
    private MenuItem CustomerComplainsMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingCancelButton"
    private Button ParkingCancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="NameMenuBar"
    private Menu NameMenuBar; // Value injected by FXMLLoader

    @FXML // fx:id="EditPricesMenuItem"
    private MenuItem EditPricesMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="VehicleEnterButton"
    private Button VehicleEnterButton; // Value injected by FXMLLoader

    @FXML // fx:id="RoutineSubscriptionMenuItem"
    private MenuItem RoutineSubscriptionMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="ExitMenuItem"
    private MenuItem ExitMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="FullTimeSubscriptionMenuItem"
    private MenuItem FullTimeSubscriptionMenuItem; // Value injected by FXMLLoader

    @FXML
    void OrderCasualParkingMenuItemClick(ActionEvent event) {

    }

    @FXML
    void OrderOneTimeParkingMenuItemClick(ActionEvent event) {
    	try{
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("OneTimeParkingOrderWindow.fxml"));
            Parent root = (Parent) loader.load();
            OneTimeParkingOrderWindowController RegisterControl = loader.getController();
            RegisterControl.setClient(client);
            RegisterControl.setAccountID(AccountID);
            RegisterControl.Load();
            Stage stage = new Stage();
            stage.setTitle("Order");
            stage.setScene(new Scene(root));
        	stage.show();
//    		Stage stage2 = (Stage) OrderOneTimeParkingMenuItem .getScene().getWindow();
//        	stage2.close();
    		//test
        	}
        	catch(IOException e){
        		e.printStackTrace();
        	}
    }

    @FXML
    void RoutineSubscriptionMenuItemClick(ActionEvent event) {

    }

    @FXML
    void FullTimeSubscriptionMenuItemClick(ActionEvent event) {

    }

    @FXML
    void VehicleEnterMethod(ActionEvent event) {

    }

    @FXML
    void VehicleLeaveMethod(ActionEvent event) {

    }

    @FXML
    void ParkingCancelMethod(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ParkingSpotsMenuItem != null : "fx:id=\"ParkingSpotsMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert VehicleLeaveButton != null : "fx:id=\"VehicleLeaveButton\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert ChangeUserMenuItem != null : "fx:id=\"ChangeUserMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert OrderCasualParkingMenuItem != null : "fx:id=\"OrderCasualParkingMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert OrderOneTimeParkingMenuItem != null : "fx:id=\"OrderOneTimeParkingMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert CustomerComplainsMenuItem != null : "fx:id=\"CustomerComplainsMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert ParkingCancelButton != null : "fx:id=\"ParkingCancelButton\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert NameMenuBar != null : "fx:id=\"NameMenuBar\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert EditPricesMenuItem != null : "fx:id=\"EditPricesMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert VehicleEnterButton != null : "fx:id=\"VehicleEnterButton\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert RoutineSubscriptionMenuItem != null : "fx:id=\"RoutineSubscriptionMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert ExitMenuItem != null : "fx:id=\"ExitMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";
        assert FullTimeSubscriptionMenuItem != null : "fx:id=\"FullTimeSubscriptionMenuItem\" was not injected: check your FXML file 'EmployeeWindow.fxml'.";

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
	void SetAccountName(String rhs)
	{
		if(rhs==null)
			System.out.println("rhs is null");
		if(NameMenuBar==null)
			System.out.println("NMB is null");
		else 
			this.NameMenuBar.setText(rhs);
	}
	void load(String acctype){
		if(acctype.equals("1"))
		{
			CustomerComplainsMenuItem.setVisible(false);
			EditPricesMenuItem.setVisible(false);
		}
		if(acctype.equals("2")){
			//    		VehicleEnterButton.setVisible(false);
			//    		NameMenuBar.hide();
		}
	}
}
