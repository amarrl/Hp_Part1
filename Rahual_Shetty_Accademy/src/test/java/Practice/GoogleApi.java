package Practice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class GoogleApi {
	
	public static void main(String[]args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		String h=RandomStringUtils.randomAlphanumeric(5);
	String name=h;
		Add_PLaceGooglepojo ll=new Add_PLaceGooglepojo();
		
		ll.setAccuracy(50);
		ll.setName(name);
		ll.setAddress("29, side layout, cohen 09");
		ll.setPhone_number("(+91) 983 893 3937");
		ll.setWebsite("http://google.com");
		ll.setLanguage("French-IN");
		Locations li=new Locations();
		li.setLat(-38.383494);
		li.setLng(33.427362);
		
		ll.setLocation(li);
		
		List<String>myList1=new ArrayList();
		myList1.add("shoe park");
		myList1.add("shop");
		
		ll.setTypes(myList1);
		
		String res=given().queryParam("Key", "qaclick123").body(ll).log().all() 
		.when().log().all().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
	System.out.println(res);  
		
		
		
	}

}
