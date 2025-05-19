import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Boday_Send_Using_Map {
	

	@Test
	public void addBook() 
	{
		
		
		HashMap<String, Object>  map = new HashMap<>();
		map.put("name", "Amar");
		map.put("isbn", 1);
		map.put("aisle", 1733);
		map.put("author", "Ravindra");
		
	/*	HashMap<String, Object>  map2 = new HashMap<>();
		map.put("lat", "12");
		map.put("lng", "34");
		map.put("location", map2);*/
		
		
		RestAssured.baseURI="http://216.10.245.166";
		String resp=given().
				header("Content-Type","application/json").
		body(map).
		when().
		post("/Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response().asString();
		
		System.out.println(resp);
		
		 JsonPath js= new JsonPath(resp);
		 String id=js.getString("ID");
		   System.out.println(id);
		
		
	
		
		
		
	// Create a place =response (place id)
		
		// delete Place = (Request - Place id)	
		

	}


}
