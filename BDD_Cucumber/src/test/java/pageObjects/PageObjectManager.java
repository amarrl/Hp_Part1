package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager extends BasePage {
	
	public PageObjectManager(WebDriver driver) {
		super(driver);
		
	}
	public LandingPage landingPage;
	public OffersPage offersPage;
	public CheckoutPage checkoutPage;
	public LandingPage getLandingPage() {
		landingPage=new LandingPage(driver);
		
		return landingPage;
	}
	
	public OffersPage getOffersPage() {
		offersPage=new OffersPage(driver);
		return offersPage;
	}
	
	public CheckoutPage getcheckoutPage() {
		checkoutPage=new CheckoutPage(driver);
		
		return checkoutPage;
	}
	
	

}
