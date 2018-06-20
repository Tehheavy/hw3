package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class PriceChangeRequestsWindowController {

	String AccountID;
	ClientClass client;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> RequestsBox;

	@FXML
	private Button DeclineButton;

	@FXML
	private Button AcceptButton;

	public void setAccountID(String rhs)
	{
		AccountID=rhs;
	}
	public void setClient(ClientClass rhs)
	{
		client=rhs;
	}
	public String Hashit(String str){
		if(str.equals("1"))
			return "casual-parking";
		else if(str.equals("2"))
			return "one-time-parking";
		return null;
	}
	public String Hashit2(String str){
		if(str.equals("casual-parking"))
			return "1";
		else if(str.equals("one-time-parking"))
			return "2";
		return null;
	}
	public String UnHashit(String str){
		String[] split = str.split(" ");
		return split[0]+" "+Hashit2(split[2])+" "+split[5];
	}

	public void load(){
		try {

			String malls=client.sendmessage("request pricerequests");
			if(malls!=null){
				System.out.println("Requested requests: "+malls);
				String[] split = malls.split(" ");
				ArrayList<String> MallList=new ArrayList<String>();
				for(int i=0;i<split.length;i+=3)
				{
					String str1=split[i];
					String str2=split[i+1];
					String str3=split[i+2];
					//	MallList.add(split[i]+" "+split[i+1]+" "+split[i+2]);
					MallList.add(split[i]+" change "+Hashit(split[i+1])+" price to "+split[i+2]);
				}
				RequestsBox.getItems().addAll(MallList);
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void AcceptButtonClick(ActionEvent event) {
		if(RequestsBox.getValue()!=null)
		{
			String s=RequestsBox.getValue();
			System.out.println(UnHashit(RequestsBox.getValue()));
			try {
				String result=client.sendmessage("request changeprice "+UnHashit(RequestsBox.getValue()));
				System.out.println(result);
				RequestsBox.getItems().remove(s);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			return;
		}

	}

	@FXML
	void DeclineButtonClick(ActionEvent event) {
		if(RequestsBox.getValue()!=null)
		{
			String s=RequestsBox.getValue();
			System.out.println(UnHashit(RequestsBox.getValue()));
			try {
				String result=client.sendmessage("request pricedelete "+UnHashit(RequestsBox.getValue()));
				System.out.println(result);
				RequestsBox.getItems().remove(s);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			return;
		}

	}

	@FXML
	void initialize() {
		assert RequestsBox != null : "fx:id=\"RequestsBox\" was not injected: check your FXML file 'PriceChangeRequestsWindow.fxml'.";
		assert DeclineButton != null : "fx:id=\"DeclineButton\" was not injected: check your FXML file 'PriceChangeRequestsWindow.fxml'.";
		assert AcceptButton != null : "fx:id=\"AcceptButton\" was not injected: check your FXML file 'PriceChangeRequestsWindow.fxml'.";

	}
}
