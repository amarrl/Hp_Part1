package stepDefinations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import utils.TestContexSetUp;

public class Hooks {
	TestContexSetUp testContexSetUp;
	
	public Hooks(TestContexSetUp testContexSetUp) {
		this.testContexSetUp=testContexSetUp;   
		
	}
	
//@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() throws IOException {
		testContexSetUp.testBase.WebdriverManager().quit();
	}
	
	@AfterStep
	public void AddScreenshot(Scenario scenario ) throws IOException {
		WebDriver driver=testContexSetUp.testBase.WebdriverManager();
		if(scenario.isFailed()) {
			File SourcePath=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			byte[]fileContent=FileUtils.readFileToByteArray(SourcePath);
			
			scenario.attach(fileContent, "image/png", "image");
		}
	}

}
