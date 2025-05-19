package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
	

public class BaseClass {
	 
	public WebDriver driver;
	public Properties p;
	public FileInputStream fi;
	public Logger logger;
	
	String br=null;
	
	@BeforeClass
	@Parameters({"os","browser"})

	public void setUp(String os,String browser) throws IOException{ 
		
		fi=new FileInputStream("C:\\Users\\Amar\\eclipse-workspace\\new 2024 ide\\FireFlink\\src\\test\\resources\\config.properties");
		p=new Properties(); 
		p.load(fi);
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_envirnment").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			
		//os and browser
			if(os.equalsIgnoreCase("windows")) {
				
			cap.setPlatform(Platform.WIN11);
			
		} 
			
			else if(os.equalsIgnoreCase("mac")) {
				
				cap.setPlatform(Platform.MAC);
				
			}
			
			else if(os.equalsIgnoreCase("Null")){
				System.out.println("no matching os");
				return;
			}
			else {
		
	switch("chrome") { 
	case "chrome":driver=new ChromeDriver() ;break;
	case "edge":driver=new  EdgeDriver();break;
	case "firefox":driver=new FirefoxDriver();break;
	}
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
	
	
		String appURL=p.getProperty("appURL"); 
		System.out.println(appURL);
		driver.get(appURL); 

			}
			
			  
		

		
	 /*
		wait.until(ExpectedConditions.visibilityOf(l.siginPage));
		
		String sigintext=l.getTextofSiginPage();
		Assert.assertEquals(sigintext, "Sign in or create account");
	
		l.clickOnContinue();
	
		l.clickOnsignin();
	
		*/
		
		
		
		
		
		
	}}}

	