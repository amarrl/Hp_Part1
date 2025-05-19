
package Bussiness_logics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DatePicker implements Nlp {
	@InputParams({@InputParam(name = "Date", type = "java.lang.String")})
//	@ReturnType(name = "Sum of Two numbers", type = "java.lang.Integer")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		String date = (String) programElementsInput.get("Date");
		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		try {
			AndroidDriver androidDriver=null;
			IOSDriver iOsDriver=null;
			try {
				androidDriver=nlpRequestModel.getAndroidDriver();
				iOsDriver=nlpRequestModel.getIosDriver();
			} catch (Exception e) {
				iOsDriver=nlpRequestModel.getIosDriver();
			}
			if (androidDriver!=null) {
				try {
					androidDriver.findElement(By.xpath("//android.view.View[@content-desc='"+date+"']")).click();
				} catch (Exception e) {
					// TODO: handle exception
					androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Next month']")).click();
					Thread.sleep(500);
					androidDriver.findElement(By.xpath("//android.view.View[@content-desc='"+date+"']")).click();
				}
				androidDriver.findElement(By.xpath("//android.widget.Button[contains(@text,'OK')]")).click();

				
			} else {
				int a=date.indexOf(" ");
//				date=date.substring(0, date.indexOf(" "));
//				date.substring(a+1, date.indexOf(" ", a));
				 iOsDriver.findElement(By.xpath("(//XCUIElementTypePickerWheel[contains(@value,'')])[1]")).sendKeys(Integer.parseInt(date.substring(0, date.indexOf(" ")))+"");
				 Thread.sleep(200);
				 iOsDriver.findElement(By.xpath("(//XCUIElementTypePickerWheel[contains(@value,'')])[2]")).sendKeys(date.replaceAll("[^a-zA-Z]", ""));
				 iOsDriver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Select Date\"]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[2]")).click();
			}

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully selected the date");
		}
		catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to select the date"+e.getMessage());
		}
		return nlpResponseModel;
	}

} 