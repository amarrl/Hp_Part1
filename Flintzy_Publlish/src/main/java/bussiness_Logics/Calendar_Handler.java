package bussiness_Logics;





import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;




@Component("LIC18077_PJT1001_PE_NLPd00871fc-65b6-4515-9f82-3efab0fba07c")
public class Calendar_Handler implements Nlp {
	@InputParams({
			@InputParam(name = "month", type = "java.lang.String"),
			@InputParam(name = "date", type = "java.lang.Integer"),
			@InputParam(name = "hours", type = "java.lang.Integer"),
			@InputParam(name = "year", type = "java.lang.Integer"),
			@InputParam(name = "mins", type = "java.lang.Integer")})
	

	@Override
	public List<String> getTestParameters() throws NlpException {
		List<String> params = new ArrayList<>();
		return params;
	}

	@Override
	public StringBuilder getTestCode() throws NlpException {
		StringBuilder sb = new StringBuilder();
		return sb;
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		 WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();

		Duration impWait = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		String month = (String) attributes.get("month");
		Integer date = (Integer) attributes.get("date");
		Integer hours = (Integer) attributes.get("hours");
		Integer mins = (Integer) attributes.get("mins");
		Integer year = (Integer) attributes.get("year");
		
		try {
			 driver.findElement(By.xpath("//button[@title=\"pick-date-and-time\"]")).click();
			 driver.findElement(By.xpath("//*[@data-testid=\"ArrowDropDownIcon\"]")).click();
			 driver.findElement(By.xpath("//button[text()='"+year+"']")).click();
			  for(int i=0;i<=12;i++) {
		        	List<WebElement> requiredmonth = driver.findElements(By.xpath("//div[contains(text(),'"+month+"')]"));
		        if(requiredmonth.size()>0) {
		        	driver.findElement(By.xpath("//button[text()='"+date+"']")).click();
		        	 Thread.sleep(2000);
		        	String element="//span[text()='"+hours+"']";
		        	 Actions action=new Actions(driver);
		        	 action.moveToElement(driver.findElement(By.xpath(element))).click().perform();
		        	 String element1="//span[text()='"+mins+"']";
		        	 Thread.sleep(1000);
		        	 action.moveToElement(driver.findElement(By.xpath(element1))).click().perform();
		        	 break;
		        }else {
		        	driver.findElement(By.xpath("//*[@data-testid=\"ArrowRightIcon\"]"));
		        	
		        }
		        }
			  nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Selected required required date");
		
		} catch (Exception e) {
			
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("selecting required date failed"+ e);

		}
		finally {
			driver.manage().timeouts().implicitlyWait(impWait);
		}

		
		return nlpResponseModel;
	}
}



