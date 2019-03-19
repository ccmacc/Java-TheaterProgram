package source.src.source;
import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface {
	
	private static UserInterface userInterface;	
	private static Theater theater;
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			
	private static final int EXIT_APPLICATION = 0;
	private static final int ADD_CLIENT = 1;
	private static final int REMOVE_CLIENT = 2;
	private static final int LIST_CLIENTS = 3;
	private static final int ADD_CUSTOMER = 4;
	private static final int REMOVE_CUSTOMER = 5;
	private static final int ADD_CREDITCARD = 6;
	private static final int REMOVE_CREDITCARD = 7;
	private static final int LIST_CUSTOMERS = 8;
	private static final int ADD_SHOW = 9;
	private static final int LIST_SHOWS = 10;
	private static final int STORE_DATA = 11;
	private static final int RETRIEVE_DATA = 12;
	private static final int DISPLAY_COMMANDS = 13;
	
	
	private UserInterface() {
		if (yesOrNo("Would you like to load saved data?")) {
			retrieveData();
		}
		else {
			theater = Theater.instance();
		}
		
	}
	
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		}
		else {
			return userInterface;
		}
	}
	
	public String getToken (String input) {
		do {
			try {
				System.out.println(input);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
		
	}
	
	public int getCommand() {
		do {
			try {
				System.out.println();
				int value = Integer.parseInt(getToken("Enter an integer from 0 - 13 to make a command: "));
				if (value >= EXIT_APPLICATION && value <= DISPLAY_COMMANDS) {
					return value;
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Enter a valid number.");
			}
		} while (true);
	}
	
	private boolean yesOrNo(String prompt) {
	    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
	    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
	      return false;
	    }
	    return true;
	  }
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat
						.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}
	
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	
	//*********************************COMMANDS
	public void addClient()
	{
		String name = getToken("Enter a name: ");
		String address = getToken("Enter an address: " );
		String phone = getToken("Enter phone number (no spaces or dashes): ");
				
		Client result;
		result = theater.addClient(name, address, phone);
		if (result == null)
		{
			System.out.println("Client could not be added.");
		}
		else
		{
			System.out.println("Client successfully added: " + result.toString());
		}
		
	}
	
	private void retrieveData() {
		// TODO Auto-generated method stub
		try {
			Theater temp = Theater.retrieveData();
			if (temp != null) {
				System.out.println(" The theater has been successfully retrieved from the file TheaterData \n");
				theater = temp;
			} 
			else {
				System.out.println("Nothing saved on file, creating new TheaterData. ");
				theater = Theater.instance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void storeData() {
		// TODO Auto-generated method stub
		if (Theater.storeData()) {
			System.out.println(" The theater has been successfully saved in the file TheaterData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
		
	}

	private void removeShow() {
		// TODO Auto-generated method stub
		
	}

	private void addShow() {
		// TODO Auto-generated method stub
		
	}

	private void listCustomers() {
		// TODO Auto-generated method stub
		System.out.println(theater.listCustomers());
	}

	private void removeCreditCard() {
		// TODO Auto-generated method stub
		int cc = getNumber("Enter credit card number you wish to remove: ");
		

		
		
	}

	private void addCreditCard() {
		// TODO Auto-generated method stub
		String id = getToken("Enter customer id: ");
		int cc = getNumber("Enter credit card number: " );
		Calendar ce = getDate("Enter expiration date: " );
		
		CreditCard card = theater.addCreditCard(cc, ce, id);
	
		if (card != null)
		{
			System.out.println("Card was successfully added.");
		}
		else
		{
			System.out.println("Unable to add credit card.");
		}
	}

	private void removeCustomer() {
		// TODO Auto-generated method stub
		String id = getToken("Enter the customer ID you wish to remove : ");
		int result = theater.removeCustomer(id);
		switch (result){
		case Theater.CLIENT_NOT_FOUND:
			System.out.println("Invalid client id");
			break;
		case Theater.OPERATION_COMPLETED:
			System.out.println("Client has been successfully removed.");
			break;
		default:
			System.out.println("An error has occurred.");
		}
		
	}

	private void addCustomer() {
		// TODO Auto-generated method stub
		String name = getToken("Enter a name: ");
		String address = getToken("Enter an address: " );
		String phone = getToken("Enter phone number (no spaces or dashes): ");
		String cc = getToken("Enter credit card number: " );
		Calendar ce = getDate("Enter expiration date: " );
		
		CreditCard card = new CreditCard(Integer.parseInt(cc),ce);
		Customer result = theater.addCustomer(name, address, phone, cc, card);
		if (result != null) {
			
		
			System.out.println("Customer successfully added: " + result.toString());
		}
		else {
			System.out.println("Customer could not be added.");
		}

		
	}

	private void listClients() {
		// TODO Auto-generated method stub
		System.out.println(theater.listClients());
		
	}

	private void removeClient() {
		String id = getToken("Enter the client ID you wish to remove : ");
		int result = theater.removeClient(id);
		switch (result){
		case Theater.CLIENT_NOT_FOUND:
			System.out.println("Invalid client id");
			break;
		case Theater.OPERATION_COMPLETED:
			System.out.println("Client has been successfully removed.");
			break;
		default:
			System.out.println("An error has occurred.");
		}
		
		
		
	}
	
	public void displayCommands() {

		System.out.println(EXIT_APPLICATION + " to close application.");
		System.out.println(ADD_CLIENT + " to add a new client.");
		System.out.println(REMOVE_CLIENT + " to remove an existing client.");
		System.out.println(LIST_CLIENTS + " to dislay current list of clients.");
		System.out.println(ADD_CUSTOMER + " to add a new customer.");
		System.out.println(REMOVE_CUSTOMER + " to remove an existing customer.");
		System.out.println(ADD_CREDITCARD + " to add a new credit card.");
		System.out.println(REMOVE_CREDITCARD + " to remove an existing creditcard.");
		System.out.println(LIST_CUSTOMERS + " to display curent list of customers.");
		System.out.println(ADD_SHOW + " to add a new show.");
		System.out.println(LIST_SHOWS + " to dislay current list of shows.");
		System.out.println(STORE_DATA + " to store current data.");
		System.out.println(RETRIEVE_DATA + " to retrieve existing data.");
		System.out.println(DISPLAY_COMMANDS + " to display options.");

	}
	
	public void run() {
		int input;
		displayCommands();
		while ((input = getCommand()) != EXIT_APPLICATION) {
			switch(input) {
			case ADD_CLIENT: 
				addClient();
				displayCommands();

				break;
			
			case REMOVE_CLIENT: 
				removeClient();
				displayCommands();
				break;
				
			case LIST_CLIENTS:
				listClients();
				displayCommands();
				
				break;
			case ADD_CUSTOMER:
				addCustomer();
				displayCommands();

				break;
			case REMOVE_CUSTOMER:
				removeCustomer();
				displayCommands();

				break;
			case ADD_CREDITCARD:
				addCreditCard();
				displayCommands();

				break;
			case REMOVE_CREDITCARD:
				removeCreditCard();
				displayCommands();

				break;
			case LIST_CUSTOMERS:
				listCustomers();
				displayCommands();

				break;
			case ADD_SHOW:
				addShow();
				displayCommands();

				break;
			case LIST_SHOWS:
				removeShow();
				displayCommands();

				break;
			case STORE_DATA:
				storeData();
				displayCommands();

				break;
			case RETRIEVE_DATA:
				retrieveData();
				displayCommands();

				break;
			case DISPLAY_COMMANDS:
				displayCommands();
				break;
			
			}

		}
	}
	
	

	public static void main (String[] args)
	{
		UserInterface.instance().run();
	}
	
	

}
