package source.src.source;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
/**
 * Stores a list of members
 * @author Samuel Hough
 *
 */
public class CustomerList extends LinkedList<Customer> implements Serializable{
	private static final long serialVersionUID = 1L;
	private static CustomerList customers;
	/*
	 * Private constructor for singleton pattern
	 */
	private CustomerList(){
		
	}
	/**
	 * Supports the singleton pattern
	 * @return the singleton object
	 */
	public static CustomerList instance(){
		if(customers == null){
			customers = new CustomerList();
		}
		return customers;
	}
	/**
	 * supports serialization
	 * @param output the stream to be written
	 */
	public void save(){
		try{
			FileOutputStream FOS = new FileOutputStream("CustomerList.txt");
			ObjectOutputStream OOS = new ObjectOutputStream(FOS);
			
			OOS.writeObject(customers);
			OOS.flush();
			OOS.close();
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	
	public Customer search(String ID){
		for (Customer customer: this.customers)
		{
			if (customer.getId().equals(ID))
			{
				return customer;
			}
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		String result = "";
		for (int i = 0; i < customers.size(); i++)
		{
			result += customers.get(i).toString() + "\n";
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
	      output.writeObject(customers);
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
	      if (customers == null) {
	        customers = (CustomerList) input.readObject();
	      } else {
	        input.readObject();
	      }
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	    }
	  }
}
