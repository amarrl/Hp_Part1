package utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class GenricUtils { 
	public WebDriver driver;

	
	public GenricUtils(WebDriver driver) {
		this.driver=driver;  
	}
	

	public void switchToOfferPage() {
		 Set<String>windows=driver.getWindowHandles();
		 
		 
		 
		 for(String w1:windows) {
			String url= driver.switchTo().window(w1).getCurrentUrl();
			 
			if(url.contains("offers")) {
			System.out.println("Driver succssfully switched to child window");
		}
			
			else {
				System.out.println("Tittle not found as current Tittle is  "+driver.getCurrentUrl());
			}
			
	}

}
}
