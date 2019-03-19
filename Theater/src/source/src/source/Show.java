package source.src.source;


import java.io.Serializable;
import java.util.*;

public class Show implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String time;
	private String date;
	private String id;
	private List dateRange = new LinkedList();
	
	
	
	public Show(String title, String time, String date, String id, DatesTakenCalender datesTaken) {
		super();
		this.title = title;
		this.time = time;
		this.date = date;
//		this.id = Client.getId();
		this.dateRange = dateRange;
	}
	
	
	@Override
	public String toString() {
		return "Show [title=" + title + ", time=" + time + ", date=" + date + ", id=" + id + ", dateRange=" + dateRange
				+ "]";
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List getDateRange() {
		return dateRange;
	}
	public void setDateRange(List dateRange) {
		this.dateRange = dateRange;
	}
	
	

	
	
}
