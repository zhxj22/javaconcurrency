package javatest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class hashtable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("2", "2.2");
		map.put("1", "1.2");
		System.out.println(map);
		System.out.println(map.keySet());
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		m.put("2", "22");
		m.put("3", "33");
		m.put("1", "11");
		System.out.println(m);
		
		
		
	}

}
