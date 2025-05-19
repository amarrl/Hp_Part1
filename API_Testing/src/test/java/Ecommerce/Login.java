package Ecommerce;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import EcommercePojo2.LoginRequest;
import EcommercePojo2.LoginResponse1;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType; // ✅ Keep only this
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Login {
	LoginRequest lp;
	RequestSpecification req;
	ResponseSpecification res;

    @BeforeTest
    public void methodLogin() { 
         req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com/")
                .setContentType(ContentType.JSON)
                .build();
  
    	
    	 res=new ResponseSpecBuilder().expectStatusCode(200).expectStatusLine("HTTP/1.1 200 OK").build(); 
    			 
		 
    	
    }
    
    
    @Test
    public void LoginTest() {
        lp = new LoginRequest();
        lp.setUserEmail("amarraj8050@mailinator.com");
        lp.setUserPassword("Amar@1400"); 
        
         
 
        RequestSpecification    req1=given().log().all().spec(req)
        .body(lp);
        
        LoginResponse1 tt= req1.when().log().all().post("api/ecom/auth/login")
        .then().log().all().spec(res).extract().response().as(LoginResponse1.class);
        
        System.out.println("Token is "+tt.getToken()); 
      
       
    }
}
    
    

