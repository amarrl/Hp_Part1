package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pageObjects.PageObjectManager;

public class TestContexSetUp {
	public WebDriver driver;
	public String productName; 
	public TestBase testBase;
	
	public PageObjectManager pageObjectManager;
	public GenricUtils genrixUtils;
	
	public TestContexSetUp() throws IOException{
		testBase=new TestBase(); 
		//testBase.WebdriverManager(); 
		pageObjectManager=new PageObjectManager(testBase.WebdriverManager());
		genrixUtils=new GenricUtils(testBase.WebdriverManager());
	}
}
 