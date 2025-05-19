package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class loginPage extends BasePage  {

	public loginPage(WebDriver driver){
		super(driver);
	}
	
	 @FindBy(xpath="//input[@id='ap_email_login']")WebElement emailTextfiled;
	@FindBy(xpath="//span[@id='continue']")WebElement continuew;
	@FindBy(xpath="//input[@id='ap_password']") WebElement passwordTextfield;
	@FindBy(xpath="//input[@id='signInSubmit']")WebElement sign;
	@FindBy(xpath="//input[@id='captchacharacters']")WebElement captchTextfield;
	
	public @FindBy(xpath="(//h4[text()='Type the characters you see in this image:']/following::div/img)[1]")WebElement imagPath;
	
	public @FindBy(xpath="//div[@class='nav-line-1-container']/following::span[text()='Account & Lists']")WebElement HelloSigin;
	
	public @FindBy(xpath="//div[@id='claim-collection-container']//h1") WebElement siginPage;
	
	public void enterEmailId(String emailId) {
		emailTextfiled.sendKeys(emailId);
	}
	
	public void enterPassword(String pass) {
		passwordTextfield.sendKeys(pass);
	}
	
	public void clickOnContinue() {
		continuew.click();
	}
	
	public void clickOnsignin() {
		sign.click();
	}
	
	public String getTextofSiginPage() {
		String sigintext=siginPage.getText();
		return sigintext;
	}
	
	public String getImagepathAttribute() {
		String path=imagPath.getDomAttribute("src");
		return path;
	}
	
	public void heloSignin() {
		Actions ac=new Actions(driver);
		ac.moveToElement(HelloSigin);
		ac.build().perform();
	}
	
	public void enterCaptchaText(String text) {
		captchTextfield.sendKeys(text);
	}

}
