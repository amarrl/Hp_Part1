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



public class SeekBarSlider implements Nlp {
	@InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
		@InputParam(name = "percentage", type = "java.lang.Integer")})
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
		WebElement el = (WebElement) attributes.get("element");
		int perc = (int) attributes.get("percentage");
		AndroidDriver androidDriver=null;
		IOSDriver iosDriver=null;
		try {
			androidDriver=nlpRequestModel.getAndroidDriver();
			iosDriver=nlpRequestModel.getIosDriver();
		} catch (Exception e) {
			iosDriver=nlpRequestModel.getIosDriver();
		}
		try {
			
			if (androidDriver!=null) {
				 int x = el.getLocation().getX()+(el.getRect().getWidth())*perc/100;
				 int y = el.getLocation().getY();	
				 TouchAction touchAction=new TouchAction<>(androidDriver);
		          PointOption point=new PointOption<>();
		          touchAction.tap( point.point(x, y)).perform();
		          
		          nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Successfully performed Action");
				
			}else {
				org.openqa.selenium.Dimension screenSize = iosDriver.manage().window().getSize();//Identify screen dimension
//				WebElement el = iosDriver.findElement(By.xpath(cxpath));
				int start=(el.getLocation().getY())+(el.getRect().height/2);
				int screenCenter = (int) el.getLocation().getY();//Identify center point of screen for Y axis
				int startPoint = (int) (el.getLocation().getX()+5);//Identify beginning point of scroll for X axis
				int endPoint = (int) (el.getLocation().getX()+(el.getRect().getWidth())*perc/100);
				int count = 0;	
				int maxSwipeCount=1;
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

				//Search Element until it is available on screen. If no, then perform below operations
				while (count<maxSwipeCount) {
					Sequence swipe =  new Sequence(finger, 1);

					//Move finger into starting position
					swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startPoint, screenCenter));
					//Finger goes up into contact with screen
					swipe.addAction(finger.createPointerDown(0));
					//Finger moves to End Position
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endPoint, screenCenter));
					//Take out finger from screen
					swipe.addAction(finger.createPointerUp(0));

					iosDriver.perform(Arrays.asList(swipe));
					count++; 
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully performed Action");	
		}
		}catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform swipe Action "+e.getMessage());
		}      
		return nlpResponseModel;
	}
} 