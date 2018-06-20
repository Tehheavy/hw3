package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyOrdersWindowController {

	String AccountID;
	ClientClass client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<TableParkingOrder, String> leaveColumn;

    @FXML
    private TableColumn<TableParkingOrder, String> mallColumn;

    @FXML
    private TableColumn<TableParkingOrder, String> arriveColumn;

    @FXML
    private TableView<TableParkingOrder> ComplaintsTable;

    @FXML
    private TableColumn<TableParkingOrder, String> priceColumn;

    @FXML
    private TableColumn<TableParkingOrder, String> idColumn;
    
    private ObservableList<TableParkingOrder> data;
    
    public void setAccountID(String rhs)
    {
    	AccountID=rhs;
    }
    public void setClient(ClientClass rhs)
    {
    	client=rhs;
    }
    
    public void load(){
    	data=FXCollections.observableArrayList();
    	String[][] rs=null;
    	try {
    		rs=(String[][])client.sendmessage2("request "+"myorders"+" "+AccountID);
    		if(rs==null)
    		{
    			return;
    		}
    		System.out.println("test");
    		for(int i=0;i<rs.length;i++){
    			for(int j= 0;j<rs[i].length;j++)
    				System.out.print(rs[i][j]);
    			System.out.println();
    		}

			for(int i=0;i<rs.length;i++)
			{
				data.add(new TableParkingOrder(rs[i][0],rs[i][1],rs[i][2],rs[i][3],rs[i][4]));
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error serialize");
			e.printStackTrace();
		}
    	priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    	arriveColumn.setCellValueFactory(new PropertyValueFactory<>("arrivedate"));
    	leaveColumn.setCellValueFactory(new PropertyValueFactory<>("leavedate"));
    	mallColumn.setCellValueFactory(new PropertyValueFactory<>("mall"));
    	idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    	ComplaintsTable.setItems(null);
    	ComplaintsTable.setItems(data);
    }

    @FXML
    void initialize() {
        assert leaveColumn != null : "fx:id=\"leaveColumn\" was not injected: check your FXML file 'MyOrdersWindow.fxml'.";
        assert mallColumn != null : "fx:id=\"mallColumn\" was not injected: check your FXML file 'MyOrdersWindow.fxml'.";
        assert arriveColumn != null : "fx:id=\"arriveColumn\" was not injected: check your FXML file 'MyOrdersWindow.fxml'.";
        assert ComplaintsTable != null : "fx:id=\"ComplaintsTable\" was not injected: check your FXML file 'MyOrdersWindow.fxml'.";
        assert priceColumn != null : "fx:id=\"priceColumn\" was not injected: check your FXML file 'MyOrdersWindow.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'MyOrdersWindow.fxml'.";

    }
}
