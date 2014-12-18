package project6;
import java.util.HashMap;
import java.util.Map;


public class Product {
	
	long id;
	String name;
	float price;
	
	Map<String, Product> productNextMap = new HashMap<String, Product>();
	
	
	
	
	public Product(long id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}


	public Map<String, Product> getProductNextMap() {
		return productNextMap;
	}
	
	

}
