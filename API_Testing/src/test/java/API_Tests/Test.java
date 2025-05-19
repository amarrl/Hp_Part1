package API_Tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com/"; 
		String response=given().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
			+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n" 
				+ "").when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
		System.out.println("-->"+response); 
		  
		JsonPath js=new JsonPath(response);
		String placeid=js.getString("place_id");
		
		System.out.println(placeid);

		//Update place
	 
		String newAddress="70 winter walk, USA";
		
		given().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "  \"place_id\": \""+placeid+"\",\r\n"
			+ "  \"address\": \""+newAddress+"\",\r\n"
				+ "  \"key\": \"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

		//Get Place
		
	String getResp=	given().log().all().queryParam("Key", "qaclick123").queryParam("place_id", placeid)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
System.out.println("=======");
	System.out.println(getResp);  
	

	}

}
