package stepDefinations;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.CheckoutPage;
import utils.TestContexSetUp;

public class CheckoutStepDefination {
	public WebDriver driver;
	public String productName;
	public String OffertsPageproductName;
	public CheckoutPage checkouPage;
	TestContexSetUp testContexSetUp; 
	
	public CheckoutStepDefination(TestContexSetUp testContexSetUp) {
		this.testContexSetUp=testContexSetUp;   
		checkouPage=testContexSetUp.pageObjectManager.getcheckoutPage();   //we are keeping this hear as contuctor will excute before any method in our stepdefination class
	}
	
	
	@Then("Verify User has ability to enter promo code and place the order")
	public void User_proceeds_to_Checkout_page(){
		
		Assert.assertTrue(checkouPage.VerifyPlaceOrder());
		Assert.assertTrue(checkouPage.VerifypromoBtn());
	}
	
	@Then("^User proceeds to Checkout page and validate  for (.+) exists$")
	public void  User_proceeds_to_Checkout_page_and_validate(String name) throws InterruptedException {
		checkouPage.checkOutItems(); 
		
		Thread.sleep(2000);
	}

	

}
