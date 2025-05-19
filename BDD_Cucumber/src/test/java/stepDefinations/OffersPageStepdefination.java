package stepDefinations;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContexSetUp;

public class OffersPageStepdefination {
	public WebDriver driver;
	public String productName;
	public String OffertsPageproductName;
	TestContexSetUp testContexSetUp;
	PageObjectManager pageObjectManager;
	//single responsibility principle
	//loosly coupled 
	//factory design Pattern
	public OffersPageStepdefination(TestContexSetUp testContexSetUp) {
		this.testContexSetUp=testContexSetUp;   
	}
	@Then("^User searches for (.+) the same short name in offers page to check if product exists$")
	public void user_searches_for_the_same_short_name_in_offers_page_to_check_if_product_exists(String itemName) throws InterruptedException {
		OffersPage op= testContexSetUp.pageObjectManager.getOffersPage();             //  new OffersPage(testContexSetUp.driver); 
		
		op.clickOnTopDealsLink();


		//testContexSetUp.driver.findElement(By.linkText("Top Deals")).click(); 

		testContexSetUp.genrixUtils.switchToOfferPage();
		
		op.enterinputIntoSearchTextField(itemName); 
		//testContexSetUp.driver.findElement(By.xpath("//input[@id='search-field']")).sendKeys(itemName);
		Thread.sleep(2000);
		OffertsPageproductName=op.getSerachedProductATOffersPage().trim();
		 //OffertsPageproductName=testContexSetUp.driver.findElement(By.xpath("//tbody//tr//td[text()='Tomato']")).getText().trim();
		System.out.println("Driver swicthe to expected Url "+testContexSetUp.testBase.driver.getCurrentUrl()); 
		Thread.sleep(2000);  
	 

	 }
	 
	
	
	
	
	
	@Then ("Validate product name matches with Landing Page in offers page")
	public void Validate_product_name_matches_with_Landing_Page_in_offers_page() {
		Assert.assertEquals(OffertsPageproductName,testContexSetUp.productName);
	}

}
