package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableParkingOrder {
	private final StringProperty id;
	private  StringProperty price;
	private  StringProperty arrivedate;
	private  StringProperty leavedate;
	private  StringProperty mall;
	
	public TableParkingOrder(String id,String arrivedate, String leavedate,
			String mall,String price) {
		super();
		this.id = new SimpleStringProperty(id);
		this.arrivedate = new SimpleStringProperty(arrivedate);
		this.leavedate = new SimpleStringProperty(leavedate);
		this.mall = new SimpleStringProperty(mall);
		this.price= new SimpleStringProperty(price);
	}


	public StringProperty getPrice() {
		return price;
	}


	public void setPrice(StringProperty price) {
		this.price = price;
	}


	public StringProperty getArrivedate() {
		return arrivedate;
	}


	public void setArrivedate(StringProperty arrivedate) {
		this.arrivedate = arrivedate;
	}


	public StringProperty getLeavedate() {
		return leavedate;
	}


	public void setLeavedate(StringProperty leavedate) {
		this.leavedate = leavedate;
	}


	public StringProperty getMall() {
		return mall;
	}


	public void setMall(StringProperty mall) {
		this.mall = mall;
	}


	public StringProperty getId() {
		return id;
	}


	public StringProperty idProperty(){return id;}
	public StringProperty arrivedateProperty(){return arrivedate;}
	public StringProperty leavedateProperty(){return leavedate;}
	public StringProperty mallProperty(){return mall;}
	public StringProperty priceProperty(){return price;}

	@Override
	public String toString() {
		return "nothing";
	}

}
