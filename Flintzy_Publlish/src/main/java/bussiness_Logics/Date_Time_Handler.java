package bussiness_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import org.springframework.stereotype.Component;

@Component("LIC18077_PJT1001_PE_NLP6bc95e8c-b55c-484f-b43b-8ac00577ff76")
public class Date_Time_Handler implements Nlp {
	@InputParams({ @InputParam(name = "requiredMonth", type = "java.lang.String"),
			@InputParam(name = "hour", type = "java.lang.Integer"),
			@InputParam(name = "minute", type = "java.lang.Integer"),
			@InputParam(name = "date", type = "java.lang.Integer"),
			@InputParam(name = "year", type = "java.lang.Integer") })

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

		WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();

		Duration impWait = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		String requiredMonth = (String) attributes.get("requiredMonth");
		Integer hour = (Integer) attributes.get("hour");
		Integer minute = (Integer) attributes.get("minute");
		Integer year = (Integer) attributes.get("year");
		Integer date = (Integer) attributes.get("date");

		try {
			driver.findElement(By.xpath("//button[@aria-label='calendar view is open, switch to year view']")).click();
			driver.findElement(By.xpath("//button[text()='" + year + "']")).click();
			for (int i = 1; i <= 12; i++) {
				String monthYear = driver
						.findElement(By.xpath("//div[@class=\"MuiPickersCalendarHeader-label css-1v994a0\"]"))
						.getText();
				System.out.println(monthYear);
				if (monthYear.contains(requiredMonth)) {
					Thread.sleep(1000);
					driver.findElement(By.xpath("//button[text()='" + date + "']")).click();
					Thread.sleep(2000);
					Actions action = new Actions(driver);
					action.moveToElement(driver.findElement(By.xpath("//ul[@aria-label='Select hours']")));
					String hoursXPath = "//ul[@aria-label='Select hours']/li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters MuiMultiSectionDigitalClockSection-item css-1ox9k5z' and @aria-label='"
							+ hour + " hours']";
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(hoursXPath)));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();",
							driver.findElement(By.xpath(hoursXPath)));

					Thread.sleep(1000);
					action.moveToElement(driver.findElement(By.xpath("//ul[@aria-label='Select minutes']")));
					String minutesXPath = "//div[@class='MuiMultiSectionDigitalClock-root css-2dy21e']/ul[@aria-label='Select minutes']/li[text()='"
							+ minute + "']";
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(minutesXPath)));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();",
							driver.findElement(By.xpath(minutesXPath)));
					break;
				} else {
					driver.findElement(By.xpath("//*[@data-testid=\"ArrowRightIcon\"]")).click();

				}
			}

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Passed");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed");
		} finally {
			driver.manage().timeouts().implicitlyWait(impWait);
		}

		return nlpResponseModel;
	}
}
