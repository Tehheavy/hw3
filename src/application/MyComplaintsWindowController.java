package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyComplaintsWindowController {
	String AccountID;
	ClientClass client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Complaint, String> statusColumn;

    @FXML
    private TableColumn<Complaint, String> responseColumn;

    @FXML
    private TableView<Complaint> ComplaintsTable;

    @FXML
    private TextArea ResponseBox;

    @FXML
    private TextArea CustomerComplaintBox;

    @FXML
    private TableColumn<Complaint, String> dateColumn;

    @FXML
    private TableColumn<Complaint, String> complaintColumn;

    @FXML
    private TableColumn<Complaint, String> idColumn;
    
    private ObservableList<Complaint> data;
    
    public void setAccountID(String rhs)
    {
    	AccountID=rhs;
    }
    public void setClient(ClientClass rhs)
    {
    	client=rhs;
    }
    
    public void Load(){
    	data=FXCollections.observableArrayList();
    	String[][] rs=null;
    	String[][] rs2=null;
    	try {
    		rs=(String[][])client.sendmessage2("request "+"complaints"+" "+AccountID);
    		rs2=(String[][])client.sendmessage2("request "+"allcomplaints");
    		System.out.println("test");
    		for(int i=0;i<rs2.length;i++){
    			for(int j= 0;j<rs2[i].length;j++)
    				System.out.print(rs2[i][j]);
    			System.out.println();
    		}

    		if(rs==null)
    		{
    			return;
    		}
			for(int i=0;i<rs.length;i++)
			{
				data.add(new Complaint(rs[i][0],rs[i][1],rs[i][2],rs[i][3],rs[i][4]));
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error serialize");
			e.printStackTrace();
		}
    	statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    	responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
    	dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    	complaintColumn.setCellValueFactory(new PropertyValueFactory<>("complaint"));
    	idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    	ComplaintsTable.setItems(null);
    	ComplaintsTable.setItems(data);
    }

    @FXML
    void initialize() {
        assert ResponseBox != null : "fx:id=\"ResponseBox\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert responseColumn != null : "fx:id=\"responseColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert CustomerComplaintBox != null : "fx:id=\"CustomerComplaintBox\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert ComplaintsTable != null : "fx:id=\"ComplaintsTable\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert complaintColumn != null : "fx:id=\"complaintColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        ComplaintsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                CustomerComplaintBox.setText(newSelection.getComplaint());
                ResponseBox.setText(newSelection.getResponse());
            }
        });

    }
}
