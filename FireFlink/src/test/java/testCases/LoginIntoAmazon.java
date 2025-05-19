package testCases;

import org.testng.annotations.Test;

import pageObjects.AmazonHome;
import pageObjects.loginPage;
import testBase.BaseClass;

public class LoginIntoAmazon extends BaseClass {
	
	@Test
	public void loginIntoAmazon() { 
		loginPage lt=new loginPage(driver);
		lt.heloSignin();  
		
		AmazonHome ht=new AmazonHome(driver);  
		ht.logoDisplay();  
	
	
	
	}
	
	

}
