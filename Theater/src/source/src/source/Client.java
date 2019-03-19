package source.src.source;

import java.io.Serializable;

public class Client implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String name;
	private String address;
	private String phone;
	private String id;
	private static final String MEMBER_STRING = "Cl";
	private double balance;
	
	
	
	


	public Client(String name, String adderess, String phone) {
		
		this.name = name;
		this.address = adderess;
		this.phone = phone;
		balance = 0;
		id =  MEMBER_STRING + (ClientIdServer.instance()).getId();
	
}
	
	
	
	



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAdderess(String adderess) {
		this.address = adderess;
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




	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	public static String getMemberString() {
		return MEMBER_STRING;
	}



	/**
	   * Checks whether the member is equal to the one with the given id
	   * @param id of the member who should be compared
	   * @return true iff the member ids match
	   */
	  public boolean equals(String id) {
	    return id.equals(id);
	  }
	  
	  @Override
		public String toString() {
			return "Client [name=" + name + ", adderess=" + address + ", phone=" + phone + ", id=" + id + ", balance="
					+ balance + "]";
		}

	
	
}
