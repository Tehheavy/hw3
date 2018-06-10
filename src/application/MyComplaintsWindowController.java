package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    	ArrayList<ComplaintHolder> rs=null;
    	try {
    		System.out.println("alex test");
    		rs=(ArrayList<ComplaintHolder>)client.sendmessage2("request "+"complaints"+" "+AccountID);
    		if(rs==null)
    		{
    			System.out.println("test alex banana");
    			return;
    		}
			for(int i=0;i<rs.size();i++)
			{
			//	data.add((ComplaintHolder)rs.get(i));
				//data.add(new Complaint(String.valueOf(rs.getInt(1)),rs.getString(3),rs.getTimestamp(4).toString(),rs.getString(5),String.valueOf(rs.getString(6))));
				data.add(new Complaint(rs.get(i).getId(),rs.get(i).getComplaint(),rs.get(i).getDate(),rs.get(i).getResponse(),rs.get(i).getResolved()));
				//data.add(new Complaint("test","test","test","test","test"));
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
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert responseColumn != null : "fx:id=\"responseColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert ComplaintsTable != null : "fx:id=\"ComplaintsTable\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert dateColumn != null : "fx:id=\"userColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert complaintColumn != null : "fx:id=\"complaintColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'MyComplaintsWindow.fxml'.";

    }
}
