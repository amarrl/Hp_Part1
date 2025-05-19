package stepDefinations;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import utils.TestContexSetUp;

public class LandingPageStepdefination {
	public WebDriver driver;
	public String productName;
	public String OffertsPageproductName;
	public LandingPage lp;
	
	TestContexSetUp testContexSetUp; 
	
	public LandingPageStepdefination(TestContexSetUp testContexSetUp) {
		this.testContexSetUp=testContexSetUp;   
	}
	
	@Given("User is on GreenCart Landing page") 
	public void user_is_on_green_cart_landing_page() throws IOException {
		
	 // Hear no code is required to run bcz before coming to method selenium will run constrctor LandingPageStepdefination after running this TestContexSetUp executed in that webdrivermanager is there from there browser will be launched 

	  
	} 
	@When("^User searched with short name (.+) and extract actual name of product$")
	public void user_searched_with_short_name_and_extract_actual_name_of_product(String itemName) throws InterruptedException {
		//testContexSetUp.driver.findElement(By.xpath("//input[@type='search']")).sendKeys(itemName);
		  lp=  testContexSetUp.pageObjectManager.getLandingPage(); 
	
		 
			lp.enterinputIntoSearchTextField(itemName);
	   Thread.sleep(2000);
	   
	   testContexSetUp.productName =lp.getSearchedProductName();
	   
	   testContexSetUp.productName= testContexSetUp.productName.split("-")[0].trim();
		Thread.sleep(2000);
	    
	   System.out.println(testContexSetUp.productName+" Procuname is extracted");
	   
	     
	}
	
	@When("added {int} items of selected product to cart") 
	public void addeditems_of_selected_product_to_cart(int qnty) {
		
		lp.incrementQnty(qnty);
		lp.addTOCart(); 
	}
	

}
