/**
 * Sample Skeleton for 'ParkingChoiceWindow.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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


	public void setAccountID(String rhs)
	{
		AccountID=rhs;
	}
	public void setClient(ClientClass rhs)
	{
		client=rhs;
	}

	@FXML // fx:id="ParkingChoicesListView"
	private ListView<ParkingOrder> ParkingChoicesListView; // Value injected by FXMLLoader

	@FXML // fx:id="mallchoiceComboBox"
	private ComboBox<String> mallchoiceComboBox; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert mallchoiceComboBox != null : "fx:id=\"mallchoiceComboBox\" was not injected: check your FXML file 'ParkingChoiceWindow.fxml'.";
		assert ParkingChoicesListView != null : "fx:id=\"ParkingChoicesListView\" was not injected: check your FXML file 'ParkingChoiceWindow.fxml'.";

	}
	public enum modes{park,exit,cancel};
	modes curmode;
	void load(modes choicetype){
		curmode=choicetype;
		ObservableList<ParkingOrder> items =FXCollections.observableArrayList();
		String[][] rs=null;
		mallchoiceComboBox.setVisible(false);
		mallchoiceComboBox.setManaged(false);
		
		switch(curmode){
		case park:
			try {
				rs=(String[][])client.sendmessage2("request "+"parking"+" "+AccountID);
				String malls=client.sendmessage("request malls");
				System.out.println("Requested malls: "+malls);
				String[] split = malls.split(" ");
				ArrayList<String> MallList=new ArrayList<String>();
				for(int i=1;i<split.length;i++)
				{
					MallList.add(split[i]);
				}
				mallchoiceComboBox.getItems().addAll(MallList);
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
							Timestamp.valueOf(rs[i][8]),Timestamp.valueOf(rs[i][9]))));
				}  	
				//    	items.add((new ParkingOrder(1,1,1,"weed","email","usrname",5,
				//    			Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()))));
				ParkingChoicesListView.setItems(items);
				ParkingChoicesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent click) {
						ParkingOrder currentItemSelected = ParkingChoicesListView.getSelectionModel().getSelectedItem();
						if (click.getClickCount() == 2) {
							//Use ListView's getSelected Item
							System.out.println(currentItemSelected);
							try {
								if(currentItemSelected.getType()!=3&&currentItemSelected.getType()!=4)
									System.out.println(client.sendmessage("request parkmyvehicle "+Integer.toString(currentItemSelected.getId())));
								else{
									if( mallchoiceComboBox.getSelectionModel().isEmpty()){
										Alert alert = new Alert(AlertType.WARNING);
										alert.setTitle("Warning Dialog");
										alert.setHeaderText("You havent selected a mall!");
										alert.setContentText("Please select a mall!");

										alert.showAndWait();
										return;
									}
									else{
										System.out.println("nice, u select da mall:" + mallchoiceComboBox.getSelectionModel().getSelectedItem().toString());
										System.out.println(client.sendmessage("request parkmyvehicle "+Integer.toString(currentItemSelected.getId())
										+" "+ mallchoiceComboBox.getSelectionModel().getSelectedItem().toString()));
									}
								}
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							Stage stage = (Stage)ParkingChoicesListView.getScene().getWindow();
							stage.close();
							//use this to do whatever you want to. Open Link etc.
						}
						else{
							if(currentItemSelected.getType()==3||currentItemSelected.getType()==4){
								mallchoiceComboBox.setVisible(true);
								mallchoiceComboBox.setManaged(true);
							}
							else{
								mallchoiceComboBox.setVisible(false);
								mallchoiceComboBox.setManaged(false);
							}
						}
						
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case exit:
//			System.out.println("sexy boy");
			try {
				rs=(String[][])client.sendmessage2("request "+"parkedcars"+" "+AccountID);
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
							Timestamp.valueOf(rs[i][8]),Timestamp.valueOf(rs[i][9]))));
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
							Timestamp time1=currentItemSelected.getArrivetime();
							Timestamp time2=currentItemSelected.getLeavetime();
							Timestamp curtime = new Timestamp(System.currentTimeMillis());
							LocalDateTime LeavTime=LocalDateTime.parse(time1.toString().substring(0,10)+"T"+time1.toString().substring(11, 19));
							LocalDateTime CurrTime=LocalDateTime.parse(curtime.toString().substring(0,10)+"T"+curtime.toString().substring(11, 19));
							long hours=LeavTime.until(CurrTime, ChronoUnit.HOURS);
							//curtime.tos
							System.out.println(currentItemSelected);
							try {
								String res=client.sendmessage("request unparkmyvehicle "+Integer.toString(currentItemSelected.getCarID())+" "+
							AccountID);
								System.out.println(res);
								if(!(currentItemSelected.getType()>2)) {
									
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Information Dialog");
								alert.setHeaderText("Request succesful");
								alert.setContentText("Please wait till the robot brings the car, total price is:"+res
										+"\n you've spent "+hours+" hours parked ");
								Button bt =(Button) alert.getDialogPane().lookupButton(ButtonType.OK);
								bt.setText("Click here to pay up");

								alert.showAndWait();
								}
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Stage stage = (Stage)ParkingChoicesListView.getScene().getWindow();
							stage.close();
							//use this to do whatever you want to. Open Link etc.
						}
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case cancel:
			System.out.println("very sexy boy");
			try {
				rs=(String[][])client.sendmessage2("request "+"parking"+" "+AccountID);
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
							Timestamp.valueOf(rs[i][8]),Timestamp.valueOf(rs[i][9]))));
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
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
