package testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Practice {
	
	public static void main(String[]args) throws TesseractException, IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://images-na.ssl-images-amazon.com/captcha/imzrkqqo/Captcha_fbevtftefi.jpg");
		
		WebElement img=driver.findElement(By.xpath("//img[@src='https://images-na.ssl-images-amazon.com/captcha/imzrkqqo/Captcha_fbevtftefi.jpg']"));
		
		TakesScreenshot tc=(TakesScreenshot)driver;
		
		File Sourcefile=img.getScreenshotAs(OutputType.FILE);
		
		File targetfile=new File(System.getProperty("user.dir")+"\\screenshots\\specificelement.png");  
		
		//Sourcefile.renameTo(targetfile); 
        ImageIO.write(ImageIO.read(Sourcefile), "png", targetfile);
		 
		 ITesseract tesseract = new Tesseract();
		 
		 tesseract.setDatapath("C:\\Users\\Amar\\Desktop\\exe\\tessdata"); // Set the path to Tesseract data
		String name= tesseract.doOCR(targetfile);
		
		System.out.println(name);      
		
		 
	}

}
