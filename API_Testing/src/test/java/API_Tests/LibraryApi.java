package API_Tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import PayloadFiles.ReUsableMethodOFJsonParse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryApi {
	static 	String id;
	@BeforeTest
	public void baseUri() {
		
	 RestAssured.baseURI="http://216.10.245.166/"; 
	
	}
	
	@Test(priority=1,dataProvider="dp")
	 void addBook(String isbn,String numb) {

		
		String libResponse=given().log().all()
		.header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+numb+"\",\r\n"
				+ "\"author\":\"Raju SIgn\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();	
		
		JsonPath js1=ReUsableMethodOFJsonParse.rawToJason(libResponse);
		 id=js1.get("ID");
		
	} 
	
	@Test(priority=2,dataProvider="dp")
	void deleteBook() {
		String deleteResponse=given().log().all().header("Content-Type", "application/json").body("{\r\n"
				+ "\"ID\":"+id+"\r\n"
				+ "}").
		when().post("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(deleteResponse); 
	}
	
	
	@DataProvider(name="dp")
	 Object[][] data() {
			
			Object[][]dpvalues= {
					{"abcd","123456"},
					{"efgh","123456"}
					
			};
			
			return dpvalues;
	}
	


}
