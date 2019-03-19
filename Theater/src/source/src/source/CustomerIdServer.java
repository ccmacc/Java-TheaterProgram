package source.src.source;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Generates member ids.
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class CustomerIdServer implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private  int idCounter;
  private static CustomerIdServer server;
  /*
   * Private constructor for singleton pattern
   * 
   */
  private CustomerIdServer() {
    idCounter = 1;
  }
  /**
   * Supports the singleton pattern
   * 
   * @return the singleton object
   */
  public static CustomerIdServer instance() {
    if (server == null) {
      return (server = new CustomerIdServer());
    } else {
      return server;
    }
  }
  /**
   * Getter for id
   * @return id of the member
   */
  public int getId() {
    return idCounter++;
  }
  /** 
   * String form of the collection
   * 
  */
  @Override
  public String toString() {
    return ("IdServer" + idCounter);
  }
  /**
   * Retrieves the server object
   * 
   * @param input inputstream for deserialization
   */
  public static void retrieve(ObjectInputStream input) {
    try {
      server = (CustomerIdServer) input.readObject();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  
  public void save(){
		try{
			FileOutputStream FOS = new FileOutputStream("ClientList.txt");
			ObjectOutputStream OOS = new ObjectOutputStream(FOS);
			
			OOS.writeObject(server);
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
      output.writeObject(server);
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
      if (server == null) {
        server = (CustomerIdServer) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  
}