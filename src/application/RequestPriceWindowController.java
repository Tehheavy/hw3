package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RequestPriceWindowController {

	String AccountID;
	ClientClass client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RequestButton;

    @FXML
    private ComboBox<String> RequestedParkingTypeMENU;

    @FXML
    private TextField PriceChangeTextBox;
    
	public void setAccountID(String rhs)
	{
		AccountID=rhs;
	}
	public void setClient(ClientClass rhs)
	{
		client=rhs;
	}


    public void load(){
    	RequestedParkingTypeMENU.getItems().addAll( // 00->23
     		   "Casual","One-time"
     		);
    }
    public String totype(String str){
    	if(str.equals("Casual"))
    		return "1";
    	return "2";
    }
    @FXML
    void RequestClick(ActionEvent event) {
    	 try{
    		 if(PriceChangeTextBox.getText()==null){
    				Alert alert = new Alert(AlertType.WARNING);
    				alert.setTitle("Warning Dialog");
    				alert.setHeaderText("Date filled incorrectly!");
    				alert.setContentText("Please fill the form correctly.");

    				alert.showAndWait();
    				return;
    		 }
    	        Float.parseFloat(PriceChangeTextBox.getText());
    	    }catch(NumberFormatException e){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Date filled incorrectly!");
				alert.setContentText("Please fill the form correctly.");

				alert.showAndWait();
				return;
    	    }
    	if(RequestedParkingTypeMENU.getValue()==null)
    	{
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Date filled incorrectly!");
			alert.setContentText("Please fill the form correctly.");

			alert.showAndWait();
			return;
    	}
    	System.out.println("request pricechange "+
			totype(RequestedParkingTypeMENU.getValue())+
					" "+PriceChangeTextBox.getText());
    	try {
			if(client.sendmessage("request pricechange "+
			totype(RequestedParkingTypeMENU.getValue())+
					" "+PriceChangeTextBox.getText()).equals("requestaccepted")){
				CreatePopupWindow newpop = new CreatePopupWindow("Request sent!");
		    	Stage stage2 = (Stage) RequestButton .getScene().getWindow();
		    	stage2.close();
				
			}
			else{
				CreatePopupWindow newpop = new CreatePopupWindow("Request failed!");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
        assert RequestButton != null : "fx:id=\"RequestButton\" was not injected: check your FXML file 'RequestPriceWindow.fxml'.";
        assert RequestedParkingTypeMENU != null : "fx:id=\"RequestedParkingTypeMENU\" was not injected: check your FXML file 'RequestPriceWindow.fxml'.";
        assert PriceChangeTextBox != null : "fx:id=\"PriceChangeTextBox\" was not injected: check your FXML file 'RequestPriceWindow.fxml'.";

    }
}
