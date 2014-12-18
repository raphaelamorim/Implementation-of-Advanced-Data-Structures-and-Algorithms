package test;

import java.util.HashMap;
import java.util.Map;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Map<Integer, Dictionary> map = new HashMap<Integer, Dictionary>();
		Map<Integer, Dictionary> map1 = new HashMap<Integer, Dictionary>();
		
		
		Dictionary dictionary = new Dictionary();
		dictionary.ID = 1;
		dictionary.price = 2;
		
		
		map.put(1, dictionary);
		map1.put(1, dictionary);
		
		
		System.out.println("map " + map.get(1).price);
		System.out.println("map1 " + map1.get(1).price);
		
		Dictionary d = map.get(1);
		d.price = 3;
		
		
		System.out.println("map " + map.get(1).price);
		System.out.println("map1 " + map1.get(1).price);
		
		
	}

}
