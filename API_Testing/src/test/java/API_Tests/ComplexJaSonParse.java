package API_Tests;

import org.testng.Assert;

import PayloadFiles.ComplexJson;
import io.restassured.path.json.JsonPath;

public class ComplexJaSonParse {

	public static void main(String[] args) {
		JsonPath js=new JsonPath(ComplexJson.cousesName());
		
		
	//	1. Print No of courses returned by API
		
		int totalCourses=js.getInt("courses.size()"); 
		
		System.out.println(totalCourses);
		
	
	// 2. Print Purchase Amount
		  
		int purchasrAmount=js.getInt("dashboard.purchaseAmount");
		
		System.out.println(purchasrAmount);
		
	// 3. Print Title of the first course
		
		String firstTittle=js.getString("courses[0].title");
		System.out.println(firstTittle);
		
	// 4. Print All course titles and their respective Prices
		
		
		for(int i=0;i<totalCourses;i++) { 
			System.out.println("Course Title is " +js.getString("courses["+i+"].title")+ " Prices is "+js.getInt("courses["+i+"].price"));
			//+" Price is "+js.getInt("courses["+i+"].price)")); 
		}
		
	// 5. Print no of copies sold by RPA Course
		
		String course="RPA";
		for(int i=0;i<totalCourses;i++) { 
			if(js.getString("courses["+i+"].title").equals(course)) {
				System.out.println("No of copies sold by "+course+ " is "+js.getInt("courses["+i+"].price"));
			} 
		}
		
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		
		int sumOfAllCourcesPrice = 0;
		for(int i=0;i<totalCourses;i++) { 
			sumOfAllCourcesPrice+=js.getInt("courses["+i+"].price"); 
 
		}
 
		Assert.assertEquals(sumOfAllCourcesPrice, purchasrAmount); 
		
	}  

}

