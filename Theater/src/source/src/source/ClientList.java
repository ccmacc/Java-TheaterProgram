package source.src.source;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
/**
 * Stores a list of clients
 * @author Samuel Hough
 *
 */
public class ClientList extends LinkedList<Client> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ClientList clients;
	/*
	 * Private constructor for singleton pattern
	 */
	private ClientList(){
		
	}
	/**
	 * Supports the singleton pattern
	 * @return the singleton object
	 */
	public static ClientList instance(){
		if(clients == null){
			clients = new ClientList();
		}
		return clients;
	}
	/**
	 * supports serialization
	 * @param output the stream to be written
	 */
	public void save(){
		try{
			FileOutputStream FOS = new FileOutputStream("ClientList.txt");
			ObjectOutputStream OOS = new ObjectOutputStream(FOS);
			
			OOS.writeObject(clients);
			OOS.flush();
			OOS.close();
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	
	public Client search(String ID){
		for (Client client: this.clients)
		{
			if (client.getId().equals(ID))
			{
				return client;
			}
		}
		return null;
	}
	
	public boolean insertClient(Client client) {
		return super.add(client);
	}
	
	@Override
	public String toString()
	{
		String result = "";
		for (int i = 0; i < clients.size(); i++)
		{
			Client c = clients.get(i);
			result += c.toString() + "\n";
		}
		return result;
	}
	
	/*
	   * Supports serialization
	   * @param output the stream to be written to
	   */
	  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
	    try {
	      output.defaultWriteObject();
	      output.writeObject(clients);
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
	      if (clients == null) {
	        clients = (ClientList) input.readObject();
	      } else {
	        input.readObject();
	      }
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	    }
	  }
}
