package Oauth20;

public class test {
//Yes, in regex, the | symbol is mandatory when you want to combine multiple patterns using logical OR.


	public static void main(String[] args) {
	     String s="https://rahulshettyacademy.com/getCourse.php?state=anystring&code=4%2F0Ab_5qlnOB9tVwFf6V8V7t8Caobg50WmOtyhqgkkayWI5TQ13Dt3dDxY9LL06VI0Vlt21fg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=consent";
	    
	     /*
	     String s = "https://rahulshettyacademy.com/getCourse.php?state=anystring&";
	        String[] result = s.split("a", 5);  
	      
	        
	        for (String part : result) {
	            System.out.println(part);
	        }
	        
	        
	        String deli[]=s.splitWithDelimiters("com", 4); 
	        
	        
	        System.out.println("-------------");
	        
	        for(String dd:deli) {
	        	System.out.println(dd);
	        }
	        */
		
	     
	    String arr[] =s.split("code=|&scope", 3);
	 System.out.println(   arr[1]);
	    
	    for(String dd:arr) {
        	System.out.println(dd);
        }
	    
	}

}
