/**
 * Sample Skeleton for 'ParkingStatusWindow.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;

public class ParkingStatusWindowController {

	String AccountID;
	ClientClass client;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="MallSpotsTilePane"
	private TilePane MallSpotsTilePane; // Value injected by FXMLLoader

	@FXML // fx:id="MallChoiceComboBox"
	private ComboBox<String> MallChoiceComboBox; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert MallSpotsTilePane != null : "fx:id=\"MallSpotsTilePane\" was not injected: check your FXML file 'ParkingStatusWindow.fxml'.";
		assert MallChoiceComboBox != null : "fx:id=\"MallChoiceComboBox\" was not injected: check your FXML file 'ParkingStatusWindow.fxml'.";

	}
	public void setAccountID(String rhs)
	{
		AccountID=rhs;
	}
	public void setClient(ClientClass rhs)
	{
		client=rhs;
	}
	String malls=null;
	public void load(){
		try {
			malls=client.sendmessage("request malls");
			System.out.println("Requested malls: "+malls);
			String[] split = malls.split(" ");
			ArrayList<String> MallList=new ArrayList<String>();
			for(int i=1;i<split.length;i++)
			{
				MallList.add(split[i]);
			}
			MallChoiceComboBox.getItems().addAll(MallList);
			MallChoiceComboBox.valueProperty().addListener(new ChangeListener<String>() {
				@Override public void changed(ObservableValue ov, String t, String mallname) {
					//		          System.out.println(ov);
					//		            System.out.println(t);
					System.out.println(mallname);
					try {
						String[][] rs=null;
						System.out.println("Rsbfore");
						rs=(String[][])client.sendmessage2("request "+"parkingspots"+" "+mallname);
						System.out.println("rsafter");
						if(rs!=null){
							System.out.println("is not null");
							MallSpotsTilePane.getChildren().clear();
//							MallSpotsTilePane.setPadding(new Insets(10, 10, 10, 10));
//							MallSpotsTilePane.setVgap(10);
//							MallSpotsTilePane.setHgap(10);
							MallSpotsTilePane.setPrefColumns(6);
							System.out.println("rs.length is:"+rs.length);
							for(int i = 0;i<rs.length;i++){
								String username = null;
								String Carid = null;
								String spot=null;
								for(int j=0;j<rs[i].length;j++){
									System.out.println("rs[i].length is:"+rs[i].length);
									//user,car,spot is the array vuels
									username=rs[i][0];
									Carid=rs[i][1];
									spot=rs[i][2];
									
								}
								if(username!=null&&Carid!=null){
									Label button = new Label(spot);
									button.setStyle("-fx-border-color:red; -fx-background-color: yellow;");
//									button.setPadding(new Insets(10,10,10,10));
									button.setPrefSize(30, 30);
									button.setTooltip(new Tooltip((username==null)?"free spot":username));
									button.setAlignment(Pos.BASELINE_CENTER);
									
									MallSpotsTilePane.getChildren().add(button);
									System.out.println("test");
								}
								else{
									Label button = new Label(spot);
									button.setStyle("-fx-border-color:red; -fx-background-color: green;");
//									button.setPadding(new Insets(10,10,10,10));
									button.setPrefSize(30, 30);
									button.setTooltip(new Tooltip((username==null)?"free spot":username));
									button.setAlignment(Pos.BASELINE_CENTER);
									MallSpotsTilePane.getChildren().add(button);
									System.out.println("test");
								}
							}
							//							MallChoiceComboBox.getItems().addAll(MallList);
							//							Label button1 = new Label("1");
							//							Label button2 = new Label("2");
							//							Label button3 = new Label("3");
							//							Label button4 = new Label("4");
							//							Label button5 = new Label("5");
							//							Label button6 = new Label("6");
							//							button1.setStyle("-fx-border-color:red; -fx-background-color: yellow;");
							//							button1.setPadding(new Insets(10,10,10,10));
							//							MallSpotsTilePane.setPadding(new Insets(10, 10, 10, 10));
							//							MallSpotsTilePane.setVgap(5);
							//							MallSpotsTilePane.setHgap(5);
							//							MallSpotsTilePane.setPrefColumns(2);
							//
							//							MallSpotsTilePane.getChildren().add(button1);
							//							MallSpotsTilePane.getChildren().add(button2);
							//							MallSpotsTilePane.getChildren().add(button3);
							//							MallSpotsTilePane.getChildren().add(button4);
							//							MallSpotsTilePane.getChildren().add(button5);
							//							MallSpotsTilePane.getChildren().add(button6);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
	}

}
