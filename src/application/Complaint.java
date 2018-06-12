package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Complaint {
	
	
	private final StringProperty id;
	private  StringProperty username;
	private final StringProperty complaint;
	private final StringProperty date;
	private final StringProperty response;
	private final StringProperty resolved;
	private  StringProperty seen;
	
	/////////////////////////////////////////////////
	public Complaint(String id, String complaint, String date, String response,
			String resolved) {
		super();
		this.id = new SimpleStringProperty(id);
		this.complaint = new SimpleStringProperty(complaint);
		this.date = new SimpleStringProperty(date);
		this.response = new SimpleStringProperty(response);
		this.resolved = new SimpleStringProperty(resolved);
	}
	public Complaint(String id,String username, String complaint, String date, String response,
			String resolved,String seen) {
		super();
		this.id = new SimpleStringProperty(id);
		this.setUsername(new SimpleStringProperty(username));
		this.complaint = new SimpleStringProperty(complaint);
		this.date = new SimpleStringProperty(date);
		this.response = new SimpleStringProperty(response);
		this.resolved = new SimpleStringProperty(resolved);
		this.setSeen(new SimpleStringProperty(seen));
	}

	public String getId() {
		return id.get();
	}

	public String getComplaint() {
		return complaint.get();
	}

	public String getDate() {
		return date.get();
	}

	public String getResponse() {
		return response.get();
	}

	public String getResolved() {
		return resolved.get();
	}
	
	
	public void setId(String value) {
		id.set(value);
	}

	public void setComplaint(String value) {
		complaint.set(value);
	}

	public void setDate(String value) {
		date.set(value);
	}

	public void setResponse(String value) {
		response.set(value);
	}

	public void setResolved(String value) {
			resolved.set(value);
	}
	public StringProperty idProperty(){return id;}
	public StringProperty complaintProperty(){return complaint;}
	public StringProperty dateProperty(){return date;}
	public StringProperty responseProperty(){return response;}
	public StringProperty resolvedProperty(){return resolved;}
	public StringProperty usernameProperty(){return username;}
	public StringProperty seenProperty(){return seen;}
	public String getSeen() {
		return seen.get();
	}
	public void setSeen(StringProperty seen) {
		this.seen = seen;
	}
	public String getUsername() {
		return username.get();
	}
	public void setUsername(StringProperty username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Complaint [id=" + id + ", username=" + username + ", complaint=" + complaint + ", date=" + date
				+ ", response=" + response + ", resolved=" + resolved + ", seen=" + seen + "]";
	}
	

}
