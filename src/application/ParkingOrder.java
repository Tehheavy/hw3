package application;

import java.sql.Timestamp;

public class ParkingOrder {

	private int id;
	private int PersonID;
	private int type;
	private String RequestMall;
	private String Email;
	private String Username;
	private int price;
	private Timestamp arrivetime;
	private Timestamp leavetime;
	public ParkingOrder(int id, int personID, int type, String requestMall, String email, String username, int price,
			Timestamp arrivetime, Timestamp leavetime) {
		super();
		this.id = id;
		PersonID = personID;
		this.type = type;
		RequestMall = requestMall;
		Email = email;
		Username = username;
		this.price = price;
		this.arrivetime = arrivetime;
		this.leavetime = leavetime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
//		return "ParkingOrder [id=" + id + ", PersonID=" + PersonID + ", type=" + type + ", "
//				+ (RequestMall != null ? "RequestMall=" + RequestMall + ", " : "")
//				+ (Email != null ? "Email=" + Email + ", " : "")
//				+ (Username != null ? "Username=" + Username + ", " : "") + "price=" + price + ", "
//				+ (arrivetime != null ? "arrivetime=" + arrivetime + ", " : "")
//				+ (leavetime != null ? "leavetime=" + leavetime : "") + "]";
		return "gimme crack daddy";
	}
	
}
