package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactoryB {
	
	static WebDriver  driver; 
	
	
	public static  WebDriver initialiseBrowser(String BrowserName) {
		
		


		        // Use the existing class variable `driver`
		        switch (BrowserName.toLowerCase()) {
		            case "chrome":
		                driver = new ChromeDriver();
		                break;
		            case "firefox":
		                driver = new FirefoxDriver();
		                break;
		            case "edge":
		                driver = new EdgeDriver();
		                break;
		            default:
		                throw new IllegalArgumentException("Browser not supported");
		        }
		        return driver;
		    }

		    public static WebDriver getDriver() {
		        return driver;
		    }
		
	}
	
	

