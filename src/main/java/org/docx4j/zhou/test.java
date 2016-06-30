package org.docx4j.zhou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class test {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) { 
		

		Map<String, String> map = new HashMap<String, String>();
		
		map.put("语文" , "1");
		map.put("数学" , "1");   
		map.put("英语" , "3");
		
		for (Entry<String, String> entry : map.entrySet()) {
			entry.getKey();
			System.out.println(entry.getValue());
			
			
		}
		
	}
	
}
