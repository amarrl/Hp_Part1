package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class login_page {
	
	//constuctor 
	WebDriver driver;
	
	public login_page(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//locators
	@FindBy(xpath="")WebElement logo;
	@FindBy(xpath="//input[@id='emailId']")WebElement emailis;
	@FindBy(xpath="//input[@type='password']")WebElement password;
	
	
	//action methods
	
	public void senEmail(String email) {
		emailis.sendKeys(email);
		
		
	}
	
	public void sendPassword(String pass) {
		password.sendKeys(pass);
	}
	
	
	
}
