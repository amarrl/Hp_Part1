package API_Tests;

import io.restassured.RestAssured;
import static  io.restassured.RestAssured.*;

import java.util.List;

import org.testng.Assert;

import Oauth_Serialization_DeSerialization.CoursesNames;
import Oauth_Serialization_DeSerialization.GetCourses;
import Oauth_Serialization_DeSerialization.TokenFromAuthorozation_Server;
import Oauth_Serialization_DeSerialization.WebAutomation;

public class OauthTests_Client_credentials {
	
	public static void main (String[]args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		TokenFromAuthorozation_Server res=given().log().all().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W") 
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all().post("oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().response().as(TokenFromAuthorozation_Server.class);
		
		String accesstoekn=res.getAccess_token();  
		
		GetCourses coursesResponse=given().log().all().queryParam("access_token", ""+accesstoekn+"") 
		.when().log().all().get("oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401).extract().response().as(GetCourses.class);
		
		 
		System.out.println(coursesResponse.getExpertise());  
		 
		
	//1. Print No of courses returned by API
	int numberOfCourses=coursesResponse.getCourses().getWebAutomation().size()+
						coursesResponse.getCourses().getApi().size()+
						coursesResponse.getCourses().getMobile().size(); 
	
	System.out.println("Totle number of courses in each is "+numberOfCourses);
	
	Assert.assertEquals(numberOfCourses, 6);
	Assert.assertEquals(coursesResponse.getInstructor(), "RahulShetty");
	
	
	//2. Validate Price of a Specific Course
	List<WebAutomation>list=coursesResponse.getCourses().getWebAutomation();
	
	for(WebAutomation kk:list) {
		if(kk.getCourseTitle().equals("Cypress")) {
			System.out.println("Cypress price is "+kk.getPrice());
		} 
	}
	
	
	
	System.out.println("---");
	
	//System.out.println(list.get(0).getCourseTitle()); 
	int pp=0;
	for(int i=0;i<list.size();i++) {
		pp+=Integer.parseInt(list.get(i).getPrice());
	}
	
	Assert.assertEquals(pp, 130);
	
	
 /*
	String price1="0";
	int price2=Integer.parseInt(price1);
	
	 price2+=(Integer.parseInt(coursesResponse.getCourses().getWebAutomation().get(0).getPrice()));
	 price2+=(Integer.parseInt(coursesResponse.getCourses().getApi().get(0).getPrice()));
	 price2+=(Integer.parseInt(coursesResponse.getCourses().getMobile().get(0).getPrice())); 

 
System.out.println(price2);

		

	for(int i=0;i<2;i++) {
		
		 price2+=(Integer.parseInt(coursesResponse.getCourses().getWebAutomation().get(i).getPrice()));
		 price2+=(Integer.parseInt(coursesResponse.getCourses().getApi().get(i).getPrice()));
		 //price2+=(Integer.parseInt(coursesResponse.getCourses().getMobile().get(0).getPrice())); 
	


	}
	 
	System.out.println(price2);
	}
	*/
	}
}
