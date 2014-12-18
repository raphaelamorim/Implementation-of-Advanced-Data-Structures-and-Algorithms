package test;

import java.math.BigInteger;
import java.util.ArrayList;


/*
 * Authors : 
 * Shawnawaz Jani basha : snb130230
 * 
 */
public class Dictionary {
	public long ID;
	public ArrayList<Long> name;
	public float price;
	
	public Dictionary() {
		
		this.ID = 0;

		this.name =new ArrayList<Long>();
		this.price = 0;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public ArrayList<Long> getName() {
		return name;
	}
	public void setName(ArrayList<Long> name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}
