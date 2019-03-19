package source.src.source;

import java.util.*;
import java.io.*;
/**
 * Stores a list of dates reserved for various plays
 * @author Clint McCarthy
 *
 */
public class DatesTakenCalender extends LinkedList<GregorianCalendar> implements Serializable {
	private static final long serialVersionUID = 1L;
	static DatesTakenCalender datesTaken;
	/*
	 * Private constructor for singleton pattern
	 */
	private DatesTakenCalender(){
		
	}
	/**
	 * Supports the singleton pattern
	 * @return the singleton object
	 */
	public static DatesTakenCalender instance(){
		if(datesTaken == null){
			datesTaken = new DatesTakenCalender();
		}
		return datesTaken;
	}
	/**
	 * supports serialization
	 * @param output the stream to be written
	 */
	public void save(){
		try{
			FileOutputStream FOS = new FileOutputStream("DatesTakenCalender.txt");
			ObjectOutputStream OOS = new ObjectOutputStream(FOS);
			
			OOS.writeObject(datesTaken);
			OOS.flush();
			OOS.close();
			FOS.close();
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	
	/*
	   * Supports serialization
	   * @param output the stream to be written to
	   */
	  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
	    try {
	      output.defaultWriteObject();
	      output.writeObject(datesTaken);
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	    }
	  }
	  /*
	   * Supports serialization
	   * @param input the stream to be read from
	   */
	  private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
	    try {
	      input.defaultReadObject();
	      if (datesTaken == null) {
	        datesTaken = (DatesTakenCalender) input.readObject();
	      } else {
	        input.readObject();
	      }
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	    }
	  }
}
