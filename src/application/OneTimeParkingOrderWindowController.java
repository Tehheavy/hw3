package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OneTimeParkingOrderWindowController {
	String AccountID;
	ClientClass client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ArrivalTimeMinuteBox;

    @FXML
    private ComboBox<String> LeaveTimeHourBox;

    @FXML
    private ComboBox<String> LeaveTimeMinuteBox;

    @FXML
    private Button OrderButton;

    @FXML
    private ComboBox<String> RequestedMallMENU;

    @FXML
    private ComboBox<String> ArrivalTimeHourBox;

    @FXML
    private DatePicker LeaveDateBox;

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
    
    private float pricePerHour;
    private float totalPrice;
    
    public void Load(){
    	ArrivalDateBox.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
    	LeaveDateBox.setDayCellFactory(picker -> new DateCell() {
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
    	LeaveTimeHourBox.getItems().addAll(  //00->23
    		   "00","01","02","03","04","05","06","07","08","09",
       		   "10","11","12","13","14","15","16","17","18","19",
       		   "20","21","22","23"
    		);
    	LeaveTimeMinuteBox.getItems().addAll(  //00->59
    		   "00","01","02","03","04","05","06","07","08","09",
     		   "10","11","12","13","14","15","16","17","18","19",
     		   "20","21","22","23","24","25","26","27","28","29",
     		   "30","31","32","33","34","35","36","37","38","39",
     		   "40","41","42","43","44","45","46","47","48","49",
     		   "50","51","52","53","54","55","56","57","58","59"
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
			PriceLabel.setText("Price: "+split[1]+"ILS");
			pricePerHour=Float.parseFloat(split[1]);
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
    	if(ArrivalDateBox.getValue()==null||LeaveDateBox.getValue()==null||RequestedMallMENU.getValue()==null||
    			EmailTB.getText().isEmpty()||CarIDTB.getText().isEmpty()||IDTB.getText().isEmpty()
    			||ArrivalTimeMinuteBox.getValue().isEmpty()||ArrivalTimeHourBox.getValue().isEmpty()||
    			LeaveTimeMinuteBox.getValue().isEmpty()||LeaveTimeHourBox.getValue().isEmpty()){
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

    
    	LocalDate ArrivalDate= ArrivalDateBox.getValue();
    	LocalDate LeaveDate=LeaveDateBox.getValue();
    	LocalTime timeArrival = LocalTime.parse(ArrivalTimeHourBox.getValue()+":"+ArrivalTimeMinuteBox.getValue());
    	LocalTime timeLeave = LocalTime.parse(LeaveTimeHourBox.getValue()+":"+LeaveTimeMinuteBox.getValue());
    	if(ArrivalDate.isAfter(LeaveDate))
    	{
    		try {
				CreatePopupWindow newpop = new CreatePopupWindow("Arrival Date is Older than Leave Date!@!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return; //Alex <3 
    	}
    	if(ArrivalDate.getYear()==LeaveDate.getYear()&&ArrivalDate.getMonth()==LeaveDate.getMonth()&&
    			ArrivalDate.getDayOfMonth()==LeaveDate.getDayOfMonth()){// IF DAY IS THE SAME CHECK TIME 
    		if(timeArrival.isAfter(timeLeave))
    		{
    			try {
    				CreatePopupWindow newpop = new CreatePopupWindow("Arrival Time is Older than Leave Date!@!");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return; //Alex <3 
    		}
    	}
    	
    	LocalDateTime testArrive=LocalDateTime.parse(ArrivalDate.toString()+"T"+timeArrival.toString());
    	LocalDateTime testLeave=LocalDateTime.parse(LeaveDate.toString()+"T"+timeLeave.toString());
    	Timestamp timearrive= Timestamp.valueOf(testArrive);
    	System.out.println(testArrive.toString());
    	System.out.println(testLeave.toString());
    	long hours=testArrive.until(testLeave, ChronoUnit.HOURS);
    	System.out.println("Total price is : "+hours*pricePerHour+" = "+hours+" hours * "+pricePerHour+"ILS");
    	float price=hours*pricePerHour;
    	
    	System.out.println("order "+"2 "+AccountID+" "+IDTB.getText()+" "+CarIDTB.getText()+
					" "+RequestedMallMENU.getValue()+" "+ArrivalDateBox.getValue().toString()+" "+
				  	ArrivalTimeHourBox.getValue()+":"+ArrivalTimeMinuteBox.getValue()+" "+
			     		LeaveDateBox.getValue().toString()+" "+LeaveTimeHourBox.getValue()+":"+LeaveTimeMinuteBox.getValue()+" "+
			        	EmailTB.getText()+ " "+String.valueOf(price));
    	// everything after this is =>  the data was entered correctly
    	
    	try{
			//192.168.1.17
			//11.1.4.79
    		
    		String resultA=client.sendmessage("order "+"2 "+AccountID+" "+IDTB.getText()+" "+CarIDTB.getText()+
					" "+RequestedMallMENU.getValue()+" "+ArrivalDateBox.getValue().toString()+" "+
				  	ArrivalTimeHourBox.getValue()+":"+ArrivalTimeMinuteBox.getValue()+" "+
			     		LeaveDateBox.getValue().toString()+" "+LeaveTimeHourBox.getValue()+":"+LeaveTimeMinuteBox.getValue()+" "+
			        	EmailTB.getText()+" "+String.valueOf(price));
			if(!resultA.equals("acceptedorder"))
			{
				if(resultA.equals("nospaceorder"))
				{
					CreatePopupWindow popup = new CreatePopupWindow("No space in selected mall, please \n try a different one");
					return;
				}
				else{
				System.out.println("order failed");
				CreatePopupWindow popup = new CreatePopupWindow("order Failed");
				
				return;
				}
			}
			CreatePopupWindow popup = new CreatePopupWindow("Order Successful");
			
			

		}
		catch(Exception e)
		{
			System.out.println("Order failed");
		}
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
        assert LeaveTimeHourBox != null : "fx:id=\"LeaveTimeHourBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert LeaveTimeMinuteBox != null : "fx:id=\"LeaveTimeMinuteBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert OrderButton != null : "fx:id=\"OrderButton\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert RequestedMallMENU != null : "fx:id=\"RequestedMallMENU\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert ArrivalTimeHourBox != null : "fx:id=\"ArrivalTimeHourBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert LeaveDateBox != null : "fx:id=\"LeaveDateBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert IDTB != null : "fx:id=\"IDTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert ArrivalDateBox != null : "fx:id=\"ArrivalDateBox\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert EmailTB != null : "fx:id=\"EmailTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert PriceLabel != null : "fx:id=\"PriceLabel\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";
        assert CarIDTB != null : "fx:id=\"CarIDTB\" was not injected: check your FXML file 'OneTimeParkingOrderWindow.fxml'.";

    }
}
