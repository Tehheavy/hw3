package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.sound.midi.Synthesizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CasualParkingOrderWindowController {
	String AccountID;
	ClientClass client;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> LeaveTimeMinuteBox;

	@FXML
	private Button OrderButton;

	@FXML
	private ComboBox<String> RequestedMallMENU;

	@FXML
	private ComboBox<String> LeaveTimeHourBox;

	@FXML
	private TextField IDTB;

	@FXML
	private DatePicker LeaveDateBox;

	@FXML
	private TextField EmailTB;

	@FXML
	private Label PriceLabel;

	@FXML
	private TextField CarIDTB;

	private float price;

	public void Load(){
		LeaveDateBox.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();

				setDisable(empty || date.compareTo(today) < 0 );
			}
		});

		LeaveTimeMinuteBox.getItems().addAll( // 00->59
				"00","01","02","03","04","05","06","07","08","09",
				"10","11","12","13","14","15","16","17","18","19",
				"20","21","22","23","24","25","26","27","28","29",
				"30","31","32","33","34","35","36","37","38","39",
				"40","41","42","43","44","45","46","47","48","49",
				"50","51","52","53","54","55","56","57","58","59"
				);
		LeaveTimeHourBox.getItems().addAll( // 00->23
				"00","01","02","03","04","05","06","07","08","09",
				"10","11","12","13","14","15","16","17","18","19",
				"20","21","22","23"
				);
		try {
			String malls=client.sendmessage("request malls");
			System.out.println("Requested malls: "+malls);
			String[] split = malls.split(" ");
			ArrayList<String> MallList=new ArrayList<String>();
			for(int i=1;i<split.length;i++)
			{
				MallList.add(split[i]);
			}
			RequestedMallMENU.getItems().addAll(MallList);
			String prices=client.sendmessage("request price 1");
			split = prices.split(" ");
			PriceLabel.setText("Price: "+String.valueOf((float)Float.parseFloat(split[1]))+"ILS/H");
			price=Float.parseFloat(split[1]);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	
	  public void showalert(String msg){
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setContentText(msg);
	    	alert.showAndWait();
	    }


	@FXML
	void OrderButtonClick(ActionEvent event) {
		//    	String date1 = ArrivalDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//    	String date2 = LeaveDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//    	String[] dateSplit1=date1.split("-");
		//    	String[] dateSplit2=date2.split("-");
		if(LeaveDateBox.getValue()==null||
				EmailTB.getText().isEmpty()||CarIDTB.getText().isEmpty()||IDTB.getText().isEmpty()
				||LeaveTimeMinuteBox.getValue().isEmpty()||LeaveTimeHourBox.getValue().isEmpty()
				){
			try {
				CreatePopupWindow newpop = new CreatePopupWindow("Not all data filled correctly");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return; //Alex <3 

		}
		if(!isValidEmailAddress(EmailTB.getText())){
			try {
				CreatePopupWindow newpop = new CreatePopupWindow("Not all data filled correctly");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return; //Alex <3 
		}

		try { //check if ID 's ARE INTEGERS
			if(IDTB.getText().length()!=9)
			{
				CreatePopupWindow newpop = new CreatePopupWindow("IDs are incorrect");
				return;
			}
			Integer.parseInt(IDTB.getText());
			Integer.parseInt(CarIDTB.getText());

		}
		catch(Exception ex){
			try {
				CreatePopupWindow newpop = new CreatePopupWindow("IDs are incorrect");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;

		}
		//LocalDateTime.now()
		LocalDateTime curr= LocalDateTime.now();
		String min,hour;
		if(curr.getMinute()<10)
		{
			min="0"+String.valueOf(curr.getMinute());
		}
		else{
			min = String.valueOf(curr.getMinute());
		}
		if(curr.getHour()<10){
			hour="0"+String.valueOf(curr.getHour());

		}
		else{
			hour = String.valueOf(curr.getHour());
		}

		LocalDate LeaveDate=LeaveDateBox.getValue();
		LocalTime timeLeave = LocalTime.parse(LeaveTimeHourBox.getValue()+":"+LeaveTimeMinuteBox.getValue());
		LocalDateTime testLeave=LocalDateTime.parse(LeaveDate.toString()+"T"+timeLeave.toString());
		System.out.println(curr.toString());
		System.out.println(testLeave.toString());
		long hours=curr.until(testLeave, ChronoUnit.HOURS);
		System.out.println("Total price is : "+hours*price+" = "+hours+" hours * "+price+"ILS");
		float priceTotal=hours*price;

		//LocalDate LeaveDate = ArrivalDate.plusDays(28);
		System.out.println("order "+"1 "+AccountID+" "+IDTB.getText()+" "+CarIDTB.getText()+
				" "+RequestedMallMENU.getValue()+" "+curr.toLocalDate().toString()+" "+
				hour+":"+min+" "+
				LeaveDateBox.getValue().toString()+" "+LeaveTimeHourBox.getValue()+":"+LeaveTimeMinuteBox.getValue()+" "+
				EmailTB.getText()+ " "+String.valueOf(priceTotal));


		// everything after this is =>  the data was entered correctly


	    try{
		//192.168.1.17
		//11.1.4.79

		String resultA=client.sendmessage("order "+"1 "+AccountID+" "+IDTB.getText()+" "+CarIDTB.getText()+
				" "+RequestedMallMENU.getValue()+" "+curr.toLocalDate().toString()+" "+
				hour+":"+min+" "+
				LeaveDateBox.getValue().toString()+" "+LeaveTimeHourBox.getValue()+":"+LeaveTimeMinuteBox.getValue()+" "+
				EmailTB.getText()+" "+String.valueOf(priceTotal));
		String[] splited = resultA.split(" ");
		System.out.println("ORDER RESULT IS "+resultA);
		System.out.println("zzz");
		if(!splited[0].equals("acceptedorder"))
		{
			if(splited[0].equals("nospaceorder"))
			{
				showalert("No space in selected mall, please \n try a different one");
				return;
			}
			else{
				System.out.println("order failed");
				showalert("order Failed");

				return;
			}
		}
		String ParkingResult=client.sendmessage("request parkmyvehicle "+splited[1]);
		System.out.println(ParkingResult);
		if(!ParkingResult.equals("accepted"))
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Parking Failed");
			alert.showAndWait();
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Parking Succesful");
		alert.showAndWait();



	}
	catch(Exception e)
	{
		System.out.println("Order failed");
	}
			    	Stage stage2 = (Stage) OrderButton .getScene().getWindow();
		    	stage2.close();
	return;
}

public void setAccountID(String rhs)
{
	AccountID=rhs;
}
public void setClient(ClientClass rhs)
{
	client=rhs;
}
@FXML
void initialize() {
	assert LeaveTimeMinuteBox != null : "fx:id=\"ArrivalTimeMinuteBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
	assert OrderButton != null : "fx:id=\"OrderButton\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
	assert RequestedMallMENU != null : "fx:id=\"RequestedMallMENU\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
	assert LeaveTimeHourBox != null : "fx:id=\"ArrivalTimeHourBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
	assert IDTB != null : "fx:id=\"IDTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
	assert LeaveDateBox != null : "fx:id=\"ArrivalDateBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
	assert EmailTB != null : "fx:id=\"EmailTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
	assert PriceLabel != null : "fx:id=\"PriceLabel\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
	assert CarIDTB != null : "fx:id=\"CarIDTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";

}
}
