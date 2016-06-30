package org.docx4j.zhou;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class removeDuplicateFromArrayList {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList removeDuplicateWithOrder(ArrayList list) {
	        Set set = new HashSet(list.size()); 
	        set.addAll(list);
	        ArrayList newList = new ArrayList(set.size());
	        newList.addAll(set);
	        return newList;
	    }
	   
 /*
	public static void main(String[] args) {  
	    
		Integer i = 0;
		
		ArrayList<String> itemAll = new ArrayList<String>();
		itemAll.add("1");
		itemAll.add("2");
		itemAll.add("3");
		itemAll.add("3");
		
        for(i=0;i<itemAll.size();i++){ 
        	System.out.println(itemAll.get(i));
        }
		
	    new removeDuplicateFromArrayList();
	    	
		@SuppressWarnings("rawtypes")
		ArrayList cc = removeDuplicateFromArrayList.removeDuplicateWithOrder(itemAll);
	    
        for(i=0;i<cc.size();i++){ 
        	System.out.println(cc.get(i));
        }
	    
	} 
*/
}
