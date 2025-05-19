package Ecommerce;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Ecommerce.Pojo.LoginRequest;
import Ecommerce.Pojo.LoginResonse;
import Ecommerce.Pojo.OrdersDetails;
import Ecommerce.Pojo.Orderspojo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Ecommerce_Api {

	public static void main(String[] args) {
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();
 
		LoginRequest l=new LoginRequest();
		l.setUserEmail("amarraj8050@mailinator.com");
		l.setUserPassword("Amar@1400");
		
		RequestSpecification reqlogin=given().spec(req).body(l); 
		LoginResonse loginresponse=reqlogin.when().log().all().post("api/ecom/auth/login").then().extract().as(LoginResonse.class);
		  
		
		System.out.println(loginresponse.getToken());
		
		String token =loginresponse.getToken();
		String userId=loginresponse.getUserId();
		System.out.println(token);
		System.out.println(userId);
		 
		
		
		 
		
		//Add Product
		
		RequestSpecification addProductRequestSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("authorization",token)
				
				.build();
		File ii=new File("C:\\Users\\Amar\\Pictures\\Screenshots\\Screenshot (143).png");
		RequestSpecification reqAddProduct=given().log().all().spec(addProductRequestSpec)
		.param("productName", "kUMARmobile")
		.param("productAddedBy", userId)
		.param("productCategory", "fashion")
		.param("productSubCategory", "mobile")
		.param("productPrice", "1232")
		.param("productDescription", "iphone")
		.param("productFor", "Amat")
		 
		 
	 
		.multiPart("productImage", ii);
		
		String productSetails=reqAddProduct.when().log().all().post("api/ecom/product/add-product").then().log().all().extract().response().asString();
		
 
JsonPath js=new JsonPath(productSetails);
String produc_id=js.get("productId");
 
System.out.println(produc_id);


// Create Order

RequestSpecification createOrderReqes=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
.addHeader("authorization",token)
.setContentType(ContentType.JSON).build();

OrdersDetails orderDetails=new OrdersDetails();
orderDetails.setCountry("India");
orderDetails.setProductOrderId(produc_id);


List<OrdersDetails> orderDetailsList=new ArrayList<OrdersDetails>();
orderDetailsList.add(orderDetails); 

Orderspojo orders=new Orderspojo();
orders.setOrders(orderDetailsList);


RequestSpecification creategivenreq=given().log().all().spec(createOrderReqes).body(orders);


String respo=creategivenreq.when().post("/api/ecom/order/create-order")
.then().log().all().extract().response().asString();

System.out.println(respo);  


/*

//Delete Product

RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
.addHeader("authorization", token).setContentType(ContentType.JSON)
.build();

RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("product_id",produc_id);

String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{produc_id}").then().log().all().
extract().response().asString();

JsonPath js1 = new JsonPath(deleteProductResponse); 
 
Assert.assertEquals("Product Deleted Successfully",js1.get("message"));
 
		*/ 
		
	}
}
