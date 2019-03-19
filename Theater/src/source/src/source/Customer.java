package source.src.source;

import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String adderess;
	private String phone;
	private String id;
	private static final String MEMBER_STRING = "Cr";
	private LinkedList<CreditCard> cards = new LinkedList<>();
	
	public Customer(String name, String adderess, String phone, CreditCard card) {
		super();
		this.name = name;
		this.adderess = adderess;
		this.phone = phone;
		this.cards.add(card);
		id =  MEMBER_STRING + (CustomerIdServer.instance()).getId();
		card.setCustomerID(id);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdderess() {
		return adderess;
	}

	public void setAdderess(String adderess) {
		this.adderess = adderess;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public  String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addCard(CreditCard c)
	{
		cards.add(c);
	}
	
	public int getNumCards()
	{
		return cards.size();
	}

	public static String getMemberString() {
		return MEMBER_STRING;
	}
	
	 /**
	   * Checks whether the member is equal to the one with the given id
	   * @param id of the member who should be compared
	   * @return true if the member ids match
	   */
	  public boolean equals(String id) {
	    return this.id.equals(id);
	  }

	@Override
	public String toString() {
		return "Customer [name=" + name + ", adderess=" + adderess + ", phone=" + phone + ", id=" + id + ", card="
				+ cards.toString() + "]";
	}

	

}
