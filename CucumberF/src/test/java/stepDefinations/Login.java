package stepDefinations;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import DriverFactory.DriverFactoryB;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class Login {
	WebDriver driver;
	
    public Login(Hooks.MyHooks hooks) { 
        this.driver = hooks.driver;
    }

@Given("User has navigated to Login Page")
public void user_has_navigated_to_login_page() {
	
	
	
	
    driver.findElement(By.xpath("//span[text()='My Account']")).click();
    driver.findElement(By.xpath("//a[text()='Login']")).click();
    
}

@When("User has entered valid email address {string} into email filed")
public void user_has_entered_valid_email_address_into_email_filed(String string) {
driver.findElement(By.xpath("//input[@name='email']")).sendKeys("amarraj8050@mailinator.com");
}

@When("User has entered valid password {string} into password textfield")
public void user_has_entered_valid_password_into_password_textfield(String string) {
  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Password@123");
}

@When("User clicks on Lpgin button")
public void user_clicks_on_lpgin_button() {
    driver.findElement(By.xpath("//input[@value='Login']")).click();
}

@Then("User should scssefully get login")
public void user_should_scssefully_get_login() {
   Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Edit your account information']")).isDisplayed());
}

@When("User has entered invalid email address {string} into email filed") 
public void user_has_entered_invalid_email_address_into_email_filed(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("User has entered invalid password {string} into password textfield")
public void user_has_entered_invalid_password_into_password_textfield(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("User should not be able login and should get warring message")
public void user_should_not_be_able_login_and_should_get_warring_message() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}



		
}
