/**
 * Sample Skeleton for 'AllComplaintsWindow.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class AllComplaintsWindowController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="SearchByUsernameMenuItem"
    private MenuItem SearchByUsernameMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="SearchByUnrespondedMenuItem"
    private MenuItem SearchByUnrespondedMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="SearchByIDMenuItem"
    private MenuItem SearchByIDMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="ResponseTextArea"
    private TextArea ResponseTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="SendButton"
    private Button SendButton; // Value injected by FXMLLoader

    @FXML // fx:id="ComplaintsTable"
    private TableView<?> ComplaintsTable; // Value injected by FXMLLoader

    @FXML
    void SearchByIDMenuItemClick(ActionEvent event) {

    }

    @FXML
    void SearchByUsernameMenuItemClick(ActionEvent event) {

    }

    @FXML
    void SearchByUnrespondedMenuItemClick(ActionEvent event) {

    }



    @FXML
    void SendButtonClick(ActionEvent event) {
    	System.out.println("response is:\n"+ResponseTextArea.getText());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert SearchByUsernameMenuItem != null : "fx:id=\"SearchByUsernameMenuItem\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert SearchByUnrespondedMenuItem != null : "fx:id=\"SearchByUnrespondedMenuItem\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert SearchByIDMenuItem != null : "fx:id=\"SearchByIDMenuItem\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert ResponseTextArea != null : "fx:id=\"ResponseTextArea\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert SendButton != null : "fx:id=\"SendButton\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert ComplaintsTable != null : "fx:id=\"ComplaintsTable\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";

    }
    private ObservableList<Complaint> data;
    
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
	void load() {
		try {
			data=FXCollections.observableArrayList();
			String[][] rs=null;
			rs=(String[][])client.sendmessage2("request "+"allcomplaints");
			for(int i=0;i<rs.length;i++){
				String[] info = new String[rs[i].length];
    			for(int j= 0;j<rs[i].length;j++)
    				info[j]=rs[i][j];
    			if(rs[i].length==6)
    				data.add(new Complaint(info[0],info[1],info[2],info[3],info[4],info[5],info[6]));
    		}
//			ComplaintsTable.setItems(data);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
