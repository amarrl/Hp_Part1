package API_Tests;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class CaptchSas {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://sastasundar.com/");
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10)); 

		//WebElement WlcLogin=driver.findElement(By.xpath(""));
		WebElement deliveryPopUp=driver.findElement(By.xpath("//h2[text()='Where do you want the delivery?']/../../../child::a[@id='js_location_cancel']"));
		
		if(deliveryPopUp.isDisplayed()) {
			deliveryPopUp.click();
		} 
		
		By loginBtn = By.xpath("//span[text()='Login']");

		int attempts = 0;
		while (attempts < 3) {
		    try {
		        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		        break; // Exit loop if successful
		    } catch (StaleElementReferenceException e) {
		        System.out.println("Retrying due to stale element...");
		    }
		    attempts++;
		}
		 
		
		driver.findElement(By.xpath("//a[text()='LOGIN']")).click();
		WebElement ele = driver.findElement(By.xpath("//div[@class=\"captchablock\"]//img"));
		String xpath="//div[@class=\"captchablock\"]//img";
		Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@name=\"email_mobileno\"]")).sendKeys("9916331913");
		int count=0;
		while(count<5) {
		String responseString= takeElementScreenshot(driver,xpath);
		driver.findElement(By.xpath("//input[@placeholder='Enter captcha *']")).clear();
		
		driver.findElement(By.xpath("//input[@placeholder='Enter captcha *']")).sendKeys(fixAllText(responseString));
		driver.findElement(By.xpath("//button[@class='proceedBtn js_login_btn']")).click();
		Thread.sleep(4000);
		int size=driver.findElements(By.xpath("//div[text()='Please enter valid captcha value']")).size();
		System.out.println("Size "+size);
		if(size>0) {
			driver.findElement(By.xpath("//i[@class=\"icon-refresh2\"]")).click();
			Thread.sleep(3000);
		}else {
			break;
		}
		count++;
		}
	}
	public static String takeElementScreenshot(WebDriver driver, String xpath) throws IOException{
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println(screenshot);
		BufferedImage fullImg = ImageIO.read(screenshot);
        WebElement ele=driver.findElement(By.xpath(xpath));
		Point point = ele.getLocation();
 
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();
 
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		
		File screenshotLocation = new File("C:\\Users\\Amar\\Downloads\\recaptcha.png");
		FileUtils.copyFile(screenshot, screenshotLocation);
        String responseText=GetTextFromImage(screenshot.toString());
        System.out.println(responseText);
		return responseText;
	}
 
	public static String GetTextFromImage(String filePath) {
		String responseText = "";
		try {
			byte[] bodyBytes = readFileToBytes(filePath);
			Response response = RestAssured.given().header("apikey", "6yPMEmT5SjZ04U7NMAsGOIpHOpXqMVMS")
					.contentType("application/octet-stream")
					.body(bodyBytes).post("https://api.apilayer.com/image_to_text/upload");
			responseText = response.jsonPath().get("all_text");
			System.out.println("Fetched text from Image");
  
		} catch (Exception e) {
			System.out.println("failed to Fetch text from Image");
 
		}
		System.out.println("responsetextis "+responseText);
		return responseText;
	}
 
	public static byte[] readFileToBytes(String filePath) {
		try {
			return Files.readAllBytes(Paths.get(filePath));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String fixAllText(String allText) {
	    if (allText.contains("\n")) {
	        int newlineIndex = allText.indexOf("\n");
	        String beforeNewline = allText.substring(0, newlineIndex);
	        String afterNewline = allText.substring(newlineIndex + 1); // e.g., "L"
 
	        int lastSpaceIndex = beforeNewline.lastIndexOf(" ");
	        if (lastSpaceIndex != -1) { 
	            String part1 = beforeNewline.substring(0, lastSpaceIndex);   
	            String part2 = beforeNewline.substring(lastSpaceIndex + 1); 
	            return part1 + afterNewline + part2;                        
	        } else {
	            return allText.replace("\n", ""); 
	        }
	    }
	    return allText;
	}
 
}