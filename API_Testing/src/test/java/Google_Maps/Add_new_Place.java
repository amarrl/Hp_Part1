package Google_Maps;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static  io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Add_new_Place {

	public static void main(String[] args) {
		 
		RequestSpecification req=new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com").build();
		
		ResponseSpecification responspeci=new ResponseSpecBuilder()
				.expectStatusCode(200)
			
		        .build();
		
		
		LocationPojo lo=new LocationPojo(); 
		lo.setLat("-38.383494");
		lo.setlng("33.427362");
		
		List<String>type=new ArrayList<>(); 
		type.add("shoe park");
		type.add("shop");
		
		
		Add_new_Place_Pojo pojo=new Add_new_Place_Pojo();
		pojo.setAccuracy("50");
		pojo.setLocation(lo);
		pojo.setName("Amar House");
		pojo.setAddress("29, side layout, cohen 09");
		
		pojo.setPhone_number("(+91) 983 893 3937");
		
		pojo.setWebsite("http://google.com");
		pojo.setLanguage("French-IN");
		pojo.setTypes(type);
		
		  
	
		  
		String addplaceResponse=given().spec(req).log().all().queryParam("key","qaclick123")
		.body(pojo).log().all()
		.when().log().all().post("/maps/api/place/add/json")
		.then().spec(responspeci).extract().response().asString();
		
		System.out.println(addplaceResponse);  
		

	}

}
