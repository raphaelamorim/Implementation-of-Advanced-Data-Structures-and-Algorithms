package project6;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Snc120030MultiDimesnsionalSearch {
	
	
	private static Map<Long, Product> productMap = new HashMap<Long, Product>();
	private static Map<String, List<Product>> productNameMap = new HashMap<String, List<Product>>();
	private static Map<String, Product> nameMap = new HashMap<String, Product>();

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Enter the name of the file");
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
		
		long start = System.currentTimeMillis();
		String line = bufferedReader.readLine().trim();
		float sum = 0;
		//executes each line
		while(line != null) {
			if (!line.contains("#")) {
				line = line.trim();
				if (line.toLowerCase().contains("insert")) {
					sum = sum + insertItem(line);
				} else if (line.toLowerCase().startsWith("find ")) {
					sum = sum + findItem(line);
				} else if (line.toLowerCase().contains("delete")) {
					sum = sum + deleteItem(line);
				} else if (line.toLowerCase().startsWith("findmin")) {
					sum = sum + findMin(line);
				} else if (line.toLowerCase().startsWith("findmax")) {
					sum = sum + findMax(line);
				} else if (line.toLowerCase().startsWith("findprice")) {
					sum = sum + findPriceRange(line);
				} else if (line.toLowerCase().startsWith("price")) {
					sum = sum + priceHike(line);
				}
				
				System.out.println(line + "->" + sum);
				
			}
			
			line = bufferedReader.readLine();
		}
		System.out.println(sum);
		long end = System.currentTimeMillis();
		System.out.println("Time is " + (end - start));
		
	}

	/**
	 * 
	 * @param line
	 * @return
	 */
	private static float priceHike(String line) {
		// TODO Auto-generated method stub
		String[] lineSplit = line.split(" ");
		int i = 1;
		if (lineSplit[1].trim().length() == 0) {
			i = 2;
		}
		long l = Long.parseLong(lineSplit[i++]);
		long h = Long.parseLong(lineSplit[i++]);
		int r = Integer.parseInt(lineSplit[i++]);
		float sum = 0;
		List<Long> keyList = new ArrayList<Long>(productMap.keySet());
		//checks whether the name set of the product lies within the range
		for (long id : keyList) {
			if (l <= id && id <= h) {
				Product product = productMap.get(id);
				float oldPrice = product.getPrice();
				float newPrice = oldPrice + (oldPrice * r/100);
//				int p = (int) (newPrice*100);
//				float finalPrice = (float)p/(float)100;
//				double diff = finalPrice - oldPrice;
//				DecimalFormat df = new DecimalFormat("#.00");
//				float d = Float.parseFloat(df.format(diff));
//				product.setPrice(finalPrice);
//				sum = sum + d;
				
				//calculates the price hike
				float diff = newPrice - oldPrice;
				product.setPrice(newPrice);
				sum = sum + diff;
				
				//removes the product form the list
				//and adds it at the exact position
				sortProductLinkedList(product);
				
			}
		}
		
		return sum;
	}

	private static void sortProductLinkedList(Product product) {
		// TODO Auto-generated method stub
		   
		//removes the product from name map
		deleteItemFromNameMap(product.getId(), product.getName());
		
		String[] nameArray = product.getName().split(" ");
		for (String nm : nameArray) {
			product.getProductNextMap().put(nm, null);
		}
		
		
		//adds it again at the exact position to keep it in sorted order
		for (String name : nameArray) {
		   
		   Product root = nameMap.get(name);
		   if (root == null) {
			   nameMap.put(name, product);
		   } else {
			   addToProductLinkedList(root, product, name);
		   }
			   
		
	   }
		
	}

	private static float findPriceRange(String line) {
		// TODO Auto-generated method stub
		String[] lineSplit = line.split(" ");
		String name = lineSplit[1];
		float low = Float.parseFloat(lineSplit[2]);
		float high = Float.parseFloat(lineSplit[3]);
		int count = 0;
		Product product = nameMap.get(name);
		Product temp = product;
		//get the count of items which lies in the range
		while(temp != null) {
			if (low <= temp.getPrice()) {
				if (temp.getPrice() <= high) {
					count++;
				} else {
					break;
				}
			}
			temp = temp.getProductNextMap().get(name);
		}
		return count;
	}

	private static float findMax(String line) {
		// TODO Auto-generated method stub
		String[] lineSplit = line.split(" ");
		String name = lineSplit[1];
		Product product = nameMap.get(name.trim());
		Product temp = product;
		//returns the last element in the file as the value is in sorted order
		while(temp != null && temp.getProductNextMap().get(name.trim()) != null) {
			temp = temp.getProductNextMap().get(name.trim());
		}
		
		if (temp != null) {
			return temp.getPrice();
		}
		return 0;
	}

	private static float findMin(String line) {
		// TODO Auto-generated method stub
		String[] lineSplit = line.split(" ");
		String name = lineSplit[1];
		Product product = nameMap.get(name.trim());
		//returns the fist element in the value as the value is in sorted order
		if(product != null) {
			return product.getPrice();
		}
		
		return 0;
	}

	private static float deleteItem(String line) {
		// TODO Auto-generated method stub
		String[] lineSplit = line.split(" ");
		long id = Long.parseLong(lineSplit[1]);
		Product product = productMap.get(id);
		if (product != null) {
			String name = product.getName();
			String[] nameArray = name.split(" ");
			long sum = 0;
			for (String nm : nameArray) {
				sum = sum + Long.parseLong(nm);
			}
			
			//deletes the product from the main map
			productMap.remove(id);
			//deletes the item from name map
			deleteItemFromNameMap(id, name);
			return sum;
			
		}
		
		return 0;
	}

	private static float findItem(String line) {
		// TODO Auto-generated method stub
		//find item from the main map
		String[] lineSplit = line.split(" ");
		long id = Long.parseLong(lineSplit[1]);
		Product product = productMap.get(id);
		if (product != null) {
			return product.getPrice();
		}
		return (float)0.0;
	}

	private static float insertItem(String line) {
		// TODO Auto-generated method stub
		// insert item into the main map
		String[] lineSplit = line.split(" ");
		long id = Long.parseLong(lineSplit[1]);
		float price = Float.parseFloat(lineSplit[2]);
		int i = 3;
		StringBuilder name= new StringBuilder();
		while(!lineSplit[i].trim().equals("0")) {
			name.append(lineSplit[i] + " ");
			i++;
		}
		String nameValue = name.toString().trim();
		if (productMap.get(id) == null) {
			Product product = new Product(id, nameValue, price);
			productMap.put(id, product);

			String[] nameArray = nameValue.split(" ");
			for (String nm : nameArray) {
				product.getProductNextMap().put(nm, null);
			}
			
			//insert item into name map
			insertItemInNameMap(product);
//			String[] nameArray = nameValue.split(" ");
//			for (String nm : nameArray) {
//				if (productNameMap.containsKey(nm)) {
//					List<Product> productList = productNameMap.get(nm);
//					productList.add(product);
//				} else {
//					List<Product> productList = new ArrayList<Product>();
//					productList.add(product);
//					productNameMap.put(nm, productList);
//				}
//			}
			return (float) 1.0;
		} else {
			Product product = productMap.get(id);
			product.setPrice(price);
			String nameItem = product.getName();
			if (nameValue.length() > 0 && !product.getName().equals(nameValue)) {
				product.setName(nameValue);
			
				//delete item from the name map
				deleteItemFromNameMap(id, nameItem);
				
				//insert item into the name map
				insertItemInNameMap(product);
//				String[] nameArray = nameValue.split(" ");
//				for (String nm : nameArray) {
//					if (productNameMap.containsKey(nm)) {
//						List<Product> productList = productNameMap.get(nm);
//						productList.add(product);
//					} else {
//						List<Product> productList = new ArrayList<Product>();
//						productList.add(product);
//						productNameMap.put(nm, productList);
//					}
//				}
			}
			return (float) 0.0;
		}
		
	}

	private static void insertItemInNameMap(Product product) {
		// TODO Auto-generated method stub
		String[] nameArray = product.getName().split(" ");
		for (String nm : nameArray) {
			if (nameMap.containsKey(nm)) {
				Product root = nameMap.get(nm);
				//add the product to the name map with value as linked list
				addToProductLinkedList(root, product, nm);
			} else {
				nameMap.put(nm, product);
			}
		}
	}

	private static void addToProductLinkedList(Product root, Product product, String name) {
		// TODO Auto-generated method stub
		Product prev = null;
		Product temp = root;
		while(temp != null && temp.getPrice() <= product.getPrice()) {
			if (temp.getPrice() == product.getPrice() && temp.getId() == product.getId()) {
				return ;
			}
			
			prev = temp;
			temp = temp.getProductNextMap().get(name);
		}
		
		if (prev == null) {
			product.getProductNextMap().put(name, root);
			nameMap.put(name, product);
		} else {
			Product var = prev.getProductNextMap().get(name);
			prev.getProductNextMap().put(name, product);
			product.getProductNextMap().put(name, var);
		}
	}

	private static void deleteItemFromNameMap(long id, String name) {
		// TODO Auto-generated method stub
		String[] nameArray = name.split(" ");
		//delete item from name map
		for (String nm : nameArray) {
//			List<Product> productList = productNameMap.get(nm);
//			
//			if (productList.size() == 1) {
//				productNameMap.remove(nm);
//			} else {
//				int index = 0;
//				for (int i = 0; i < productList.size(); i++) {
//					if (id == productList.get(i).getId()) {
//						index = i;
//						break;
//					}
//				}
//				productList.remove(index);
//			}
			
			
			deleteItemFromProductLinkedList(id, nm);
		}
		
	}

	private static void deleteItemFromProductLinkedList(long id, String nm) {
		// TODO Auto-generated method stub
		Product root = nameMap.get(nm);

		//delete the product form the product linked list in name map
		if (root != null) {
			Product temp = root;
			Product prev = null;
			while(temp != null && temp.getId() != id) {
				prev = temp;
				temp = temp.getProductNextMap().get(nm);
			}
			
			if (prev == null) {
				root.getProductNextMap().remove(nm);
				if (root.getProductNextMap().get(nm) == null) {
					nameMap.remove(nm);
				} else {
					nameMap.put(nm, root.getProductNextMap().get(nm));
				}
			} else {
				Product curr = prev.getProductNextMap().get(nm);
				if (curr != null) {
					Product next = curr.getProductNextMap().get(nm);
					curr.getProductNextMap().remove(nm);
					prev.getProductNextMap().put(nm, next);
				}
			}
		}
	}

}
