package Practice;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;

import org.apache.commons.lang3.RandomStringUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

	public static void main(String[] args) {
		
		String name=RandomStringUtils.randomAlphanumeric(5);
		Add_PLaceGooglepojo j=new Add_PLaceGooglepojo();
		Locations l=new Locations();
		
		j.setAccuracy(50);
		j.setName(name);
		j.setAddress("29, side layout, cohen 09");
		j.setPhone_number("(+91) 983 893 3937");
		j.setWebsite("http://google.com");
		j.setLanguage("French-IN");
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		j.setLocation(l);
		List<String>admylist=new ArrayList();
		
		admylist.add("shoe park");
		admylist.add("shop");
		
		j.setTypes(admylist);
		
		RequestSpecification rs=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("Key", "qaclick123").setContentType(ContentType.JSON).build();
	
	ResponseSpecification rc=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	RequestSpecification gs=given().spec(rs).body(j);
	Response ress=gs.when().log().all().post("maps/api/place/add/json")
	.then().spec(rc).extract().response();
	
	System.out.println(ress.asString());
		 

	}

}
