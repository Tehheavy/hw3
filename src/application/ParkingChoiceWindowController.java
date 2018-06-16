/**
 * Sample Skeleton for 'ParkingChoiceWindow.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ParkingChoiceWindowController {
	
	String AccountID;
	ClientClass client;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ParkingChoicesListView"
    private ListView<ParkingOrder> ParkingChoicesListView; // Value injected by FXMLLoader
    
    public void setAccountID(String rhs)
    {
    	AccountID=rhs;
    }
    public void setClient(ClientClass rhs)
    {
    	client=rhs;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ParkingChoicesListView != null : "fx:id=\"ParkingChoicesListView\" was not injected: check your FXML file 'ParkingChoiceWindow.fxml'.";


    }
    void load(){
    	ObservableList<ParkingOrder> items =FXCollections.observableArrayList();
    	String[][] rs=null;
    	try {
			rs=(String[][])client.sendmessage2("request "+"parking"+" "+AccountID);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(rs!=null)
    	for(int i=0;i<rs.length;i++){
			for(int j= 0;j<rs[i].length;j++)
				System.out.print(rs[i][j]);
			System.out.println();
		}

		if(rs==null)
		{
			return;
		}
		for(int i=0;i<rs.length;i++)
		{
			
			
	    	items.add((new ParkingOrder(Integer.parseInt(rs[i][0]),Integer.parseInt(rs[i][1]),
	    			Integer.parseInt(rs[i][2]),Integer.parseInt(rs[i][3]),rs[i][4],rs[i][5],rs[i][6],
	    			Integer.parseInt(rs[i][7]),
	    			Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()))));
		}
    	
    	
    	
    	
    	
//    	items.add((new ParkingOrder(1,1,1,"weed","email","usrname",5,
//    			Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()))));
    	ParkingChoicesListView.setItems(items);
    	ParkingChoicesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

    	    @Override
    	    public void handle(MouseEvent click) {

    	        if (click.getClickCount() == 2) {
    	           //Use ListView's getSelected Item
    	           ParkingOrder currentItemSelected = ParkingChoicesListView.getSelectionModel().getSelectedItem();
    	           System.out.println(currentItemSelected);
    	           Stage stage = (Stage)ParkingChoicesListView.getScene().getWindow();
    	           stage.close();
    	           //use this to do whatever you want to. Open Link etc.
    	        }
    	    }
    	});
    }
}
