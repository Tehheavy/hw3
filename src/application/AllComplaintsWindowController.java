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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllComplaintsWindowController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="SearchByUsernameMenuItem"
    private MenuItem SearchByUsernameMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="SearchByUnrespondedMenuItem"
    private MenuItem SearchByUnrespondedMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="responseColumn"
    private TableColumn<Complaint,String> responseColumn; // Value injected by FXMLLoader

    @FXML // fx:id="SearchByIDMenuItem"
    private MenuItem SearchByIDMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="usernameColumn"
    private TableColumn<Complaint,String> usernameColumn; // Value injected by FXMLLoader

    @FXML // fx:id="resolvedColumn"
    private TableColumn<Complaint,String> resolvedColumn; // Value injected by FXMLLoader

    @FXML // fx:id="seenColumn"
    private TableColumn<Complaint,String> seenColumn; // Value injected by FXMLLoader

    @FXML // fx:id="dateColumn"
    private TableColumn<Complaint,String> dateColumn; // Value injected by FXMLLoader

    @FXML // fx:id="ResponseTextArea"
    private TextArea ResponseTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="SendButton"
    private Button SendButton; // Value injected by FXMLLoader

    @FXML // fx:id="ComplaintsTable"
    private TableView<Complaint> ComplaintsTable; // Value injected by FXMLLoader

    @FXML // fx:id="complaintColumn"
    private TableColumn<Complaint,String> complaintColumn; // Value injected by FXMLLoader

    @FXML // fx:id="idColumn"
    private TableColumn<Complaint,String> idColumn; // Value injected by FXMLLoader

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
//    	System.out.println(ComplaintsTable.getSelectionModel().getSelectedItem().getId());
    	String id=ComplaintsTable.getSelectionModel().getSelectedItem().getId();
    	try {
//    		System.out.println("complaint resolve "+id+" "+ ResponseTextArea.getText());
			System.out.println(client.sendmessage("complaint resolve "+id+" "+ ResponseTextArea.getText()));
			ComplaintsTable.getSelectionModel().getSelectedItem().setResponse(ResponseTextArea.getText());
			ResponseTextArea.setText(null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert SearchByUsernameMenuItem != null : "fx:id=\"SearchByUsernameMenuItem\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert SearchByUnrespondedMenuItem != null : "fx:id=\"SearchByUnrespondedMenuItem\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert responseColumn != null : "fx:id=\"responseColumn\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert SearchByIDMenuItem != null : "fx:id=\"SearchByIDMenuItem\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert usernameColumn != null : "fx:id=\"usernameColumn\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert resolvedColumn != null : "fx:id=\"resolvedColumn\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert seenColumn != null : "fx:id=\"seenColumn\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert ResponseTextArea != null : "fx:id=\"ResponseTextArea\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert SendButton != null : "fx:id=\"SendButton\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert ComplaintsTable != null : "fx:id=\"ComplaintsTable\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert complaintColumn != null : "fx:id=\"complaintColumn\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'AllComplaintsWindow.fxml'.";

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
//    			System.out.println(rs[i].length);
    			if(rs[i].length==7){
    				
    				Complaint cuscomplaint = new Complaint(info[0],info[1],info[2],info[3],info[4],info[5],info[6]);
//    				System.out.println(cuscomplaint);
    				data.add(cuscomplaint);
    			}
    		}
			seenColumn.setCellValueFactory(new PropertyValueFactory<>("seen"));
			resolvedColumn.setCellValueFactory(new PropertyValueFactory<>("resolved"));
	    	responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
	    	dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
	    	complaintColumn.setCellValueFactory(new PropertyValueFactory<>("complaint"));
	    	idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
	    	ComplaintsTable.setItems(null);
			ComplaintsTable.setItems(data);
//			ComplaintsTable.setItems(data);
			
//			ComplaintsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//	            if (newSelection != null) {
////	                CustomerComplaintBox.setText(newSelection.getComplaint());
////	                ResponseBox.setText(newSelection.getResponse());
//	                Alert alert = new Alert(null,"Our response:\n"+newSelection.getResponse(), ButtonType.OK);
////	                alert.setContentText("giv weed");
////	                newSelection.getUsername()+":\n"+ newSelection.getComplaint()
//	                alert.setHeaderText(newSelection.getUsername()+":\n"+ newSelection.getComplaint());
//	                alert.showAndWait();
//	            }
//	        });
			ComplaintsTable.setRowFactory( tv -> {
			    TableRow<Complaint> row = new TableRow<Complaint>();
			    row.setOnMouseClicked(event -> {
			        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			            Complaint rowData = row.getItem();
			            Alert alert = new Alert(null,"Our response:\n"+rowData.getResponse(), ButtonType.OK);
			            alert.setHeaderText(rowData.getUsername()+":\n"+ rowData.getComplaint());
			            alert.showAndWait();
////	                alert.setContentText("giv weed");
			    }});
			    return row ;
			});
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
