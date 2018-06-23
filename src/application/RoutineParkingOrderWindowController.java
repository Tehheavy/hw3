package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

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

public class RoutineParkingOrderWindowController {
	String AccountID;
	ClientClass client;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> ArrivalTimeMinuteBox;

	@FXML
	private Button OrderButton;

	@FXML
	private ComboBox<String> RequestedMallMENU;

	@FXML
	private ComboBox<String> ArrivalTimeHourBox;

	@FXML
	private TextField IDTB;

	@FXML
	private DatePicker ArrivalDateBox;

	@FXML
	private TextField EmailTB;

	@FXML
	private Label PriceLabel;

	@FXML
	private TextField CarIDTB;
	
	private float price;

	public void Load(){
		ArrivalDateBox.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();

				setDisable(empty || date.compareTo(today) < 0 );
			}
		});

		ArrivalTimeMinuteBox.getItems().addAll( // 00->59
				"00","01","02","03","04","05","06","07","08","09",
				"10","11","12","13","14","15","16","17","18","19",
				"20","21","22","23","24","25","26","27","28","29",
				"30","31","32","33","34","35","36","37","38","39",
				"40","41","42","43","44","45","46","47","48","49",
				"50","51","52","53","54","55","56","57","58","59"
				);
		ArrivalTimeHourBox.getItems().addAll( // 00->23
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
			String prices=client.sendmessage("request price 2");
			split = prices.split(" ");
			PriceLabel.setText("Price: "+String.valueOf((float)36*Float.parseFloat(split[1]))+" ILS");
			price=36*Float.parseFloat(split[1]);
			RequestedMallMENU.setVisible(false);
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


	@FXML
	void OrderButtonClick(ActionEvent event) {
		//    	String date1 = ArrivalDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//    	String date2 = LeaveDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//    	String[] dateSplit1=date1.split("-");
		//    	String[] dateSplit2=date2.split("-");
		if(ArrivalDateBox.getValue()==null||
				EmailTB.getText().isEmpty()||CarIDTB.getText().isEmpty()||IDTB.getText().isEmpty()
				||ArrivalTimeMinuteBox.getValue().isEmpty()||ArrivalTimeHourBox.getValue().isEmpty()
				){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Not all data filled correctly");
			alert.showAndWait();
			return; //Alex <3 

		}
		if(!isValidEmailAddress(EmailTB.getText())){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Not all data filled correctly");
			alert.showAndWait();
			return; //Alex <3 
		}

		try { //check if ID 's ARE INTEGERS
			if(IDTB.getText().length()!=9)
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("IDs are incorrect");
				alert.showAndWait();
				return;
			}
			Integer.parseInt(IDTB.getText());
			Integer.parseInt(CarIDTB.getText());

		}
		catch(Exception ex){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("IDs are incorrect");
			alert.showAndWait();
			return;

		}



		LocalDate ArrivalDate= ArrivalDateBox.getValue();
		LocalDate LeaveDate = ArrivalDate.plusDays(14);
		LocalTime timeArrival = LocalTime.parse(ArrivalTimeHourBox.getValue()+":"+ArrivalTimeMinuteBox.getValue());
		LocalDateTime testArrive=LocalDateTime.parse(ArrivalDate.toString()+"T"+timeArrival.toString());
    	LocalDateTime curr= LocalDateTime.now();
		if(testArrive.isBefore(curr)){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("one or more of the dates is before current time");
			alert.showAndWait();
			return;
			
		}
		System.out.println("order "+"3 "+AccountID+" "+IDTB.getText()+" "+CarIDTB.getText()+
				" "+ArrivalDateBox.getValue().toString()+" "+
				ArrivalTimeHourBox.getValue()+":"+ArrivalTimeMinuteBox.getValue()+" "+
				LeaveDate.toString()+" "+ArrivalTimeHourBox.getValue()+":"+ArrivalTimeMinuteBox.getValue()+" "+
				EmailTB.getText()+" "+String.valueOf(price));


		// everything after this is =>  the data was entered correctly


		    	try{
					//192.168.1.17
					//11.1.4.79
		
					if(!client.sendmessage("order "+"3 "+AccountID+" "+IDTB.getText()+" "+CarIDTB.getText()+
							" "+ArrivalDateBox.getValue().toString()+" "+
							ArrivalTimeHourBox.getValue()+":"+ArrivalTimeMinuteBox.getValue()+" "+
							LeaveDate.toString()+" "+ArrivalTimeHourBox.getValue()+":"+ArrivalTimeMinuteBox.getValue()+" "+
							EmailTB.getText()+" "+String.valueOf(price)).equals("acceptedorder"))
					{
						System.out.println("order failed");
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setContentText("order Failed");
						alert.showAndWait();
						
						return;
					}
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("Order Successful");
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
		assert ArrivalTimeMinuteBox != null : "fx:id=\"ArrivalTimeMinuteBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
		assert OrderButton != null : "fx:id=\"OrderButton\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
		assert RequestedMallMENU != null : "fx:id=\"RequestedMallMENU\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
		assert ArrivalTimeHourBox != null : "fx:id=\"ArrivalTimeHourBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
		assert IDTB != null : "fx:id=\"IDTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
		assert ArrivalDateBox != null : "fx:id=\"ArrivalDateBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
		assert EmailTB != null : "fx:id=\"EmailTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
		assert PriceLabel != null : "fx:id=\"PriceLabel\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
		assert CarIDTB != null : "fx:id=\"CarIDTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";

	}
}
