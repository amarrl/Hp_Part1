package Hooks;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import DriverFactory.DriverFactoryB;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class MyHooks {
	public WebDriver driver;
	@Before
	public void setup() {
		driver= DriverFactoryB.getDriver();
		
	 driver=DriverFactoryB.initialiseBrowser("chrome");
	
		 
			
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://tutorialsninja.com/demo");
		} 
		
	

	@After
	public void tearDown() {
	driver.quit();
	}
}
