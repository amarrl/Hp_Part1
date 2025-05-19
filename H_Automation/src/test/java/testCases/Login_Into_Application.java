package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageObjects.login_page;
import testBase.Teststart;

public class Login_Into_Application extends Teststart{

	
	
	@Test
	public void Login() {
		
		login_page j=new login_page(driver);
		j.senEmail("sbjhs");
		
		
		
	}
}
