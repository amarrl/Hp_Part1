package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

public WebDriver driver;



	
	public WebDriver WebdriverManager() throws IOException {
		
	
	
		FileInputStream fi=new FileInputStream("C:\\Users\\Amar\\eclipse-workspace\\new 2024 ide\\BDD_Cucumber\\Test Data\\Global.properties");
		
		Properties 	p=new Properties();
		p.load(fi);
		
		String url=p.getProperty("QAURL");
		
		System.out.println(url);
		
		if(driver==null) { 
			
		
		driver=new ChromeDriver(); 
		
			
			driver.get(url); 
			 
		}
		

		
		return driver;
		
	}

} 
