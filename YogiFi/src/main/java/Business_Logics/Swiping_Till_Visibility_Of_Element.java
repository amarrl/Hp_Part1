
package Business_Logics;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;


public class Swiping_Till_Visibility_Of_Element implements Nlp {
	@InputParams({@InputParam(name = "Xpath", type = "java.lan.String"),
		@InputParam(name = "Container Xpath", type = "java.lan.String")
	,@InputParam(name = "Swipe count", type = "java.lang.Integer")})

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
		String xpath = (String) attributes.get("Xpath");
		String cxpath = (String) attributes.get("Container Xpath");
		int maxSwipeCount = (int) attributes.get("Swipe count");
		AndroidDriver androidDriver=null;
		IOSDriver iosDriver=null;
		try {
			androidDriver=nlpRequestModel.getAndroidDriver();
			iosDriver=nlpRequestModel.getIosDriver();
		} catch (Exception e) {
			iosDriver=nlpRequestModel.getIosDriver();
		}
		
		try
		{
			if (androidDriver!=null) {
				org.openqa.selenium.Dimension screenSize = androidDriver.manage().window().getSize();//Identify screen dimension
				WebElement el = androidDriver.findElement(By.xpath(cxpath));
				int start=(el.getLocation().getY())+(el.getRect().height/2);
				int screenCenter = (int) (screenSize.getWidth()*0.6);//Identify center point of screen for Y axis
				int startPoint = (int) (screenSize.getWidth()*0.8);//Identify beginning point of scroll for X axis
				int endPoint = (int) (screenSize.getWidth()*0.6);//Identify ending point of scroll
				int count = 0;	
				//			Integer maxSwipeCount =30;

				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

				//Search Element until it is available on screen. If no, then perform below operations
				while (count<=maxSwipeCount) {
					Sequence swipe =  new Sequence(finger, 1);

					//Move finger into starting position
					swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startPoint, start));


					//Finger goes up into contact with screen
					swipe.addAction(finger.createPointerDown(0));

					//Finger moves to End Position
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endPoint, start));

					//Take out finger from screen
					swipe.addAction(finger.createPointerUp(0));

					androidDriver.perform(Arrays.asList(swipe));
					Thread.sleep(200);
					try {
						if ( androidDriver.findElement(By.xpath(xpath)).isDisplayed()==true) {
							
							break;
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
					count++;

					System.out.println(count);
				}
			}
			else {
				org.openqa.selenium.Dimension screenSize = iosDriver.manage().window().getSize();//Identify screen dimension
				WebElement el = iosDriver.findElement(By.xpath(cxpath));
				int start=(el.getLocation().getY())+(el.getRect().height/2);
				int screenCenter = (int) (screenSize.getWidth()*0.5);//Identify center point of screen for Y axis
				int startPoint = (int) (screenSize.getWidth()*0.8);//Identify beginning point of scroll for X axis
				int endPoint = (int) (screenSize.getWidth()*0.6);
				int count = 0;	

				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

				//Search Element until it is available on screen. If no, then perform below operations
				while (count<=maxSwipeCount) {
					Sequence swipe =  new Sequence(finger, 1);

					//Move finger into starting position
					swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startPoint, start));
					//Finger goes up into contact with screen
					swipe.addAction(finger.createPointerDown(0));
					//Finger moves to End Position
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endPoint, start));
					//Take out finger from screen
					swipe.addAction(finger.createPointerUp(0));

					iosDriver.perform(Arrays.asList(swipe));

					Thread.sleep(200);
					try {
						if ( iosDriver.findElement(By.xpath(xpath)).isDisplayed()==true) {
					
							break;
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
					count++;

					System.out.println(count);
				}
			}


			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully performed Click Operation ");	
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			//Log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform action "+exceptionAsString);	
		}
		return nlpResponseModel;
	}
} 