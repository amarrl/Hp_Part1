package Business_Logics;




import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;



public class Swipe_handle2 implements Nlp {
	@InputParams({@InputParam(name = "Xpath", type = "java.lan.String")})
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
		AndroidDriver androidDriver=null;
		IOSDriver iosDriver=null;
		try {
			androidDriver=nlpRequestModel.getAndroidDriver();
			iosDriver=nlpRequestModel.getIosDriver();
		} catch (Exception e) {
			iosDriver=nlpRequestModel.getIosDriver();
		}
		try{
			if (androidDriver!=null) {
				Duration time = androidDriver.manage().timeouts().getImplicitWaitTimeout();
				 androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
				org.openqa.selenium.Dimension screenSize = androidDriver.manage().window().getSize();//Identify screen dimension
				int screenCenter = (int) (screenSize.getHeight()*0.9);
				int startPoint = (int) (screenSize.getWidth()*0.6);
				int endPoint = (int) (screenSize.getWidth()*0.4);
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				int max=10;
				int count=0;
				while (count<max) {
					try {
						androidDriver.findElement(By.xpath(xpath));
						break;
					} catch (Exception e) {
						
					}
//					androidDriver.findElement(By.xpath(xpath));
					Sequence swipe =  new Sequence(finger, 1);
					swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startPoint, screenCenter));
					swipe.addAction(finger.createPointerDown(0));
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), endPoint, screenCenter));
					swipe.addAction(finger.createPointerUp(0));
					androidDriver.perform(Arrays.asList(swipe));
					try {
						androidDriver.findElement(By.xpath(xpath));
						break;
					} catch (Exception e) {

					}
					count++;

				}
				androidDriver.manage().timeouts().implicitlyWait(time);

			}
			else {
			
					Duration time = iosDriver.manage().timeouts().getImplicitWaitTimeout();
					iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
					org.openqa.selenium.Dimension screenSize = iosDriver.manage().window().getSize();//Identify screen dimension
					WebElement el = iosDriver.findElement(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther"));
					int start=(el.getLocation().getY())+(el.getRect().height/2);
					int screenCenter = (int) (screenSize.getWidth()*0.5);//Identify center point of screen for Y axis
					int startPoint = (int) (screenSize.getWidth()*0.8);//Identify beginning point of scroll for X axis
					int endPoint = (int) (screenSize.getWidth()*0.6);
					int count = 0;
					int max=10;

					PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

					//Search Element until it is available on screen. If no, then perform below operations
					while (count<=10) {
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
					iosDriver.manage().timeouts().implicitlyWait(time);

				}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully performed swipe Action ");

		}
		catch (Exception e) {
			// TODO: handle exception
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform swipe Action "+e.getMessage());
		}
		return nlpResponseModel;
	}
} 