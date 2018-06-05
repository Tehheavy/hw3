package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    
    public void Load(){
    	ArrivalTimeMinuteBox.getItems().addAll( // 00->59
    		    "14",
    		    "06",
    		    "01"
    		);
    	ArrivalTimeHourBox.getItems().addAll( // 00->24
    		    "14",
    		    "06",
    		    "01"
    		);
    	LeaveTimeHourBox.getItems().addAll(  //00->24
    		    "14",
    		    "06",
    		    "01"
    		);
    	LeaveTimeMinuteBox.getItems().addAll(  //00->59
    		    "14",
    		    "06",
    		    "01"
    		);
    	RequestedMallMENU.getItems().addAll(
    		    "Kenyon 8",
    		    "Kenyon nehemia",
    		    "Watermelon mall"
    		);
    	
    }

    @FXML
    void OrderButtonClick(ActionEvent event) {
//    	String date1 = ArrivalDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//    	String date2 = LeaveDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//    	String[] dateSplit1=date1.split("-");
//    	String[] dateSplit2=date2.split("-");
    	if(ArrivalDateBox.getValue()==null||LeaveDateBox.getValue()==null||
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
    	LocalDate ArrivalDate= ArrivalDateBox.getValue();
    	LocalDate LeaveDate=LeaveDateBox.getValue();
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
    		LocalTime timeArrival = LocalTime.parse(ArrivalTimeMinuteBox.getValue()+":"+ArrivalTimeHourBox.getValue());
    		LocalTime timeLeave = LocalTime.parse(LeaveTimeMinuteBox.getValue()+":"+LeaveTimeHourBox.getValue());
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
