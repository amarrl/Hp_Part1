package API_Tests;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestForpractice {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		String res=	given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.formParam("grant_type", "client_credentials")
			.formParam("scope", "trust")
			.when().post("oauthapi/oauth2/resourceOwner/token")
			.then().log().all().assertThat().statusCode(200).assertThat().extract().response().asString();

		JsonPath js=new JsonPath(res);

		String acesstoken=js.getString("access_token");  

		System.out.println(acesstoken);
 
		String getResponse=given().log().all().queryParam("access_token", ""+acesstoken+"") 
		.when().get("oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401).extract().response().asString();
		
		System.out.println(getResponse);

	}

}
