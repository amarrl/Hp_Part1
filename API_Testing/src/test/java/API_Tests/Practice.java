package API_Tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class Practice {

	public static void main(String[] args) throws IOException {
	
		String path="C:\\Users\\Amar\\Downloads\\Jsontesting [MConverter.eu].json";
		
		JsonPath js=new JsonPath(new String(Files.readAllBytes(Paths.get(path))));
		
		int size=js.getInt("courses.size()");
		
		for(int i=0;i<size;i++) {
			System.out.println("Titles is "+js.getString("courses["+i+"].title"));
		}
		
		
		File fi=new File("user\\path\\amar");
		RestAssured.baseURI="";
		
		given()
		.header("Content-Type","application/json") 
		.pathParam("key", size)
		.body("")
		.multiPart("File",fi)  //if tou want to send an attchmment
		.when().post("jnsjjs/{key}/attachements")      // when any path param we are giving means we need to use { } symbole
		.then().assertThat().statusCode(201).extract().response().asString();
		
		
	}

}
