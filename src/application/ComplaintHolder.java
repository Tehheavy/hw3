package application;
import java.io.Serializable;

public class ComplaintHolder implements Serializable {
	
	
	public ComplaintHolder(String id, String complaint, String date, String response, String resolved) {
		super();
		this.id = id;
		this.complaint = complaint;
		this.date = date;
		this.response = response;
		this.resolved = resolved;
	}
	private String id;
	private String complaint;
	private String date;
	private String response;
	private String resolved;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
	}
	
	/////////////////////////////////////////////////

}
