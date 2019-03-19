
package source.src.source;
/**
 * @author Clint McCarthy
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;


public class Theater implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Theater theater;
	public static final int OPERATION_COMPLETED = 1;
	public static final int OPERATION_FAILED = 2;
	public static final int CUSTOMER_NOT_FOUND = 3;
	public static final int CLIENT_NOT_FOUND = 4;
	public static final int CREDITCARD_NOT_FOUND = 5;
	private CustomerList customers;
	private ClientList clients;
	private CreditCardList creditCardList;
	private ShowList shows;
	private static ObjectOutputStream output;
	  
	  /**
	   * Private for the singleton pattern
	   * Creates the customerList, clientList, clinetIdServer, creditCardList, datesTakenCalender, and and showList collection objects
	   */
	  
	 
	  
	  public static Theater instance() {
		  if (theater == null){
			  theater = new Theater();
			  ClientIdServer.instance();
			  return (theater = new Theater());
		  }else {
		  return theater;
		  }
	  }
	  
	  private Theater()
	  {
		  clients = ClientList.instance();
		  customers = CustomerList.instance();
		  creditCardList = CreditCardList.instance();
		  shows = ShowList.instance();
	  }
	  
	  /**
	   * Organizes the operations for adding a cusotmer
	   * @param name of customer
	   * @param address of customer
	   * @param phoneNumber of customer
	   * @param ccNumber of customer
	   * @param card of customer
	   * @return the customer object created
	   */
	  
	  public Customer addCustomer(String name, String address, String phoneNumber, String ccNumber, CreditCard card) {
		  Customer customer = new Customer(name, address, phoneNumber, card);
		  if (customers.add(customer)){
		  return (customer);
	  } 
	  return null;
	  
	  }
	  
	  /**
	   * Organizes the operations for adding a cusotmer
	   * @param name of client
	   * @param address of client
	   * @param phoneNumber of client
	   * @param balance of client
	   * @return the client object created
	   */
	  
	  public Client addClient(String name, String address, String phoneNumber) {
		  Client client = new Client(name, address, phoneNumber);
		  if(clients.insertClient(client)){
			  return (client);
		  }
		  return null;
}

	  /**
	   * Organizes the operations for adding a cusotmer
	   * @param title of show
	   * @param date of show
	   * @param id of show
	   * @param dateRange of show
	   * @return the show object created
	   */
	  
	public Show addShow(String title, String time, String date, String id, DatesTakenCalender datesTaken) {
		Show show = new Show(title, time, date,id, datesTaken);
			if (shows.add(show)){
				return (show);
			}
			return null;
	}
	
	/**
	   * Organizes the operations for adding a customer
	   * @param ccNumber of card
	   * @param expiration of card
	   * @param customerID of customer
	   * @return the show object created
	   */
	
	public CreditCard addCreditCard(int ccNumber, Calendar expiration, String customerID) {
		CreditCard creditCard = new CreditCard(ccNumber, expiration);
		creditCard.setCustomerID(customerID);
		
		if (customers.search(customerID) != null)
		{
			creditCardList.add(creditCard);
			customers.search(customerID).addCard(creditCard);
			return creditCard;
		}
	return null;
	}

	/**
	   * Removes a specific customer from the catalog
	   * @param ID of the customer
	   * @return a code representing the outcome
	   */
	
	public int removeCustomer(String ID) {
		Customer customer = customers.search(ID);
		if (customer == null){
			return(CUSTOMER_NOT_FOUND);
		}
		if (customers.remove(customer)) {
			return (OPERATION_COMPLETED);
		}
		return (OPERATION_FAILED);
	}
	
	/**
	   * Removes a specific client from the catalog
	   * @param ID of the client
	   * @return a code representing the outcome
	   */
	
		public int removeClient(String ID) {
			Client client = clients.search(ID);
			if (client == null){
				return(CLIENT_NOT_FOUND);
			}
			else if (clients.remove(client)) {
				return (OPERATION_COMPLETED);
			}
			else
			{
				return (OPERATION_FAILED);
			}
				
		}
		
		public String listClients()
		{
			return clients.toString();
		}
		
		public String listCustomers()
		{
			return customers.toString();
		}
		/**
		   * Removes a specific client from the catalog
		   * @param ID of the client
		   * @return a code representing the outcome
		   */
		
			public int removeCreditCard(int ccNum) {
				CreditCard creditCard = creditCardList.search(ccNum);
//				Customer cardOwner = search
				if (creditCard == null){
					return(CREDITCARD_NOT_FOUND);
				}
				if (creditCardList.remove(ccNum) != null) {
					return (OPERATION_COMPLETED);
				}
				return (OPERATION_FAILED);
			}
			
			/**
			   * Retrieves a deserialized version of the theater from disk
			   * @return a Theater object
			   */
			
			public static Theater retrieveData() {
				try{
					  FileInputStream file = new FileInputStream("TheaterData.txt");
				      ObjectInputStream input = new ObjectInputStream(file);
				      input.readObject();
				      ClientIdServer.retrieve(input);
				      return theater;
				} catch(IOException ioe) {
					ioe.printStackTrace();
					return null;
				} catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				return null;
				}
			}
				
				/**
				   * Serializes the Theater object
				   * @return true if the data could be saved
				   */
				
				public static  boolean storeData() {
				    try {
				      FileOutputStream file = new FileOutputStream("TheaterData.txt");
				      output = new ObjectOutputStream(file);
				      output.writeObject(theater);
				      output.writeObject(ClientIdServer.instance());
				      output.close();
				      ClientList.instance().save();
				      CustomerList.instance().save();
				      ShowList.instance().save();
				      return true;
				    } catch(IOException ioe) {
				      ioe.printStackTrace();
				      return false;
				    }
				}
				
				private void readObject(java.io.ObjectInputStream in) {
					try {
						in.defaultReadObject();
						if (theater == null) {
							theater = (Theater) in.readObject();
					} else {
						in.readObject();
					}
					
					} catch(IOException ioe) {
						ioe.printStackTrace();
					} catch(Exception e) {
						e.printStackTrace();
					}
						
				}
					
				private void writeObject(java.io.ObjectOutputStream out) {
					try {
						out.defaultWriteObject();
						output.writeObject(theater); 
					} catch (IOException ioe) {
						System.out.println(ioe);
					}
				}
				
}
















