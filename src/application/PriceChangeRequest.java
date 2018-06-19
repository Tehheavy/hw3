package application;

public class PriceChangeRequest {
	int id;
	int type;
	int price;
	public PriceChangeRequest(int id, int type, int price) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
	}
	@Override
	public String toString() {
		return id + " " + type + " " + price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	

}
