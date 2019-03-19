package source.src.source;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
/**
 * Stores a list of shows
 * @author Samuel Hough
 *
 */
public class ShowList extends LinkedList<Show> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ShowList shows;
	/*
	 * Private constructor for singleton pattern
	 */
	private ShowList(){
		
	}
	/**
	 * Supports the singleton pattern
	 * @return the singleton object
	 */
	public static ShowList instance(){
		if(shows == null){
			shows = new ShowList();
		}
		return shows;
	}
	/**
	 * supports serialization
	 * @param output the stream to be written
	 */
	public void save(){
		try{
			FileOutputStream FOS = new FileOutputStream("ShowList.txt");
			ObjectOutputStream OOS = new ObjectOutputStream(FOS);
			
			OOS.writeObject(shows);
			OOS.flush();
			OOS.close();
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
	      output.writeObject(shows);
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
	      if (shows == null) {
	        shows = (ShowList) input.readObject();
	      } else {
	        input.readObject();
	      }
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	    }
	  }
}
