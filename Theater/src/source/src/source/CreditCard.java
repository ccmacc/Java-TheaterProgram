package source.src.source;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreditCard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ccNumber;
	private Calendar expiration;
	private String customerID;
	
	
	
	
	public CreditCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CreditCard(int ccNumber, Calendar expiration) {
		super();
		this.ccNumber = ccNumber;
		this.expiration = expiration;
//		this.customerID = Customer.getId();
	}



	public int getCcNumber() {
		return ccNumber;
	}
	
	public void setCcNumber(int ccNumber) {
		this.ccNumber = ccNumber;
	}
	public Calendar getExpiration() {
		return expiration;
	}
	public void setExpiration(Calendar expiration) {
		this.expiration = expiration;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	
	
	public boolean equals(String customerId) {
	    return this.customerID.equals(customerId);
	  }



	@Override
	public String toString() {
		return "CreditCard [ccNumber=" + ccNumber + ", expiration=" + getDate(expiration) + ", customerID=" + customerID + "]";
	}
	
	private String getDate(Calendar c)
	{
        Date date = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format( date );
	}
	
}
