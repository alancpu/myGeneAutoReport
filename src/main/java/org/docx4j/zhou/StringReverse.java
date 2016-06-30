package org.docx4j.zhou;

/*
 * 字符串反转
 */

public class StringReverse {

	public String StrgRev(String s) {
		  int length = s.length();
		  if (length <= 1)
			  return s;
		  String left = s.substring(0, length / 2);
		  String right = s.substring(length / 2, length);
		  return StrgRev(right) + StrgRev(left);
	}

   /*
    public static void main(String[] args) {  
        String str = "ACFV";
        new StringReverse();
		System.out.println(StringReverse(str));
    } 
     	
	*/
}
