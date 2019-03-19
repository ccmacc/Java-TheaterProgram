package source.src.source;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
/**
 * Stores a list of credit cards
 * @author Samuel Hough
 *
 */
public class CreditCardList extends LinkedList<CreditCard> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static CreditCardList creditCards;
	public LinkedList<CreditCard> showList = new LinkedList<CreditCard>();
	/*
	 * Private constructor for singleton pattern
	 */
	private CreditCardList(){
		
	}
	/**
	 * Supports the singleton pattern
	 * @return the singleton object
	 */
	public static CreditCardList instance(){
		if(creditCards == null){
			creditCards = new CreditCardList();
		}
		return creditCards;
	}
	/**
	 * supports serialization
	 * @param output the stream to be written
	 */
	public void save(){
		try{
			FileOutputStream FOS = new FileOutputStream("CreditCardList.txt");
			ObjectOutputStream OOS = new ObjectOutputStream(FOS);
			
			OOS.writeObject(creditCards);
			OOS.flush();
			OOS.close();
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	public CreditCard search(int ccNum){
		for (CreditCard card: this.creditCards)
		{
			if (card.getCcNumber() == ccNum)
			{
				return card;
			}
		}
		return null;
	}
	
}

