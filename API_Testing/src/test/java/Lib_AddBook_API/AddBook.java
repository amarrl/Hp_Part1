package Lib_AddBook_API;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Lib_AddBook_API_Body.BodayData;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class AddBook {
	
	@BeforeTest
	public void BaseUri() {
		RestAssured.baseURI="http://216.10.245.166";
	}
	
	@Test
	public void MethodA() {
		BodayData B=new BodayData(); 
		String res=given().log().all().header("Content-Type","application/json")
		.body(B.Boday())
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		
		System.out.println(res);
		 
		
	}
	

}
