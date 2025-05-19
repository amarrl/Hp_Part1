package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Teststart {
	
	public WebDriver driver;
  
    public Properties p;
	
	

	
	
	@Test(priority=1)
	public void luanch() throws IOException {
		FileInputStream fi=new FileInputStream("C:\\Users\\Amar\\eclipse-workspace\\new 2024 ide\\H_Automation\\src\\test\\resources\\config.properties");
		p.load(fi);
		driver=new ChromeDriver();
		
		/*
		String appurl=p.getProperty("URL"); 
		
		System.out.println(appurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(appurl);
		
		*/
		
	
	
	}
	
	@Test
	public void tearDown() {
		driver.close();
		
	}

}

