package API_Tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import PayloadFiles.PlayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

//Add place -> Update palce with new Address -> Get Place to validate Address -> Delete the Place
public class ApiTests {
	
	public static void main (String[]args) {
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com/"; 
		
	String response=given().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
		.body(PlayLoad.addPlaceBoady()). 
		
		when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("status",equalTo("OK")).extract().response().asString();
	
	  
	
		//System.out.println(response);
	
	JsonPath js=new JsonPath(response);  //it will take String as input and convert that as json and it will heple to parse the json	
	String placeId=js.get("place_id"); 
	
	System.out.println(placeId);
	
	
	//Update place
	 
			String newAddress="70 winter walk, USA";
			
			given().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
			.body("{\r\n"
					+ "  \"place_id\": \""+placeId+"\",\r\n"
				+ "  \"address\": \""+newAddress+"\",\r\n"
					+ "  \"key\": \"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
			.when().put("maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
			
			
			
				//Get
				
				String getResponse=given().log().all().queryParam("Key", "qaclick123").queryParam("place_Id", ""+placeId+"")
				//.header("Content-Type", "application/json")
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
				
				JsonPath jsGet=new JsonPath(getResponse); 
				
				System.out.println(getResponse); 
				
				
				//String nameIS=jsGet.get("name"); 
				
				//System.out.println(nameIS);
				
				//Assert.assertEquals("Amar house", nameIS);  
	 
	
		
		
	}

}
