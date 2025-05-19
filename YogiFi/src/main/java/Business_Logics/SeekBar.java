package Business_Logics;



import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;




public class SeekBar implements Nlp {
  
    @InputParams({@InputParam(name = "Locator Name", type = "java.lang.String"), @InputParam(name = "Percentage to Skip", type = "java.lang.Double"),@InputParam(name = "Locator Value", type = "java.lang.String")})
    
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
        
          String name = (String) attributes.get("Locator Name");
          Double percentage = (Double) attributes.get("Percentage to Skip");
          String value = (String) attributes.get("Locator Value");
          // Your program element business logic goes here ...
          AndroidDriver driver=nlpRequestModel.getAndroidDriver();
          
          try {
        	  TouchAction ac=new TouchAction(driver);
     		 ac.tap(PointOption.point(999,730)).perform();
     		 ac.longPress(PointOption.point(999,730)).release().perform();
     		 Method method = By.class.getDeclaredMethod(name, String.class);
     		 By by=(By)method.invoke(null, value);
//     		 driver.findElement(by).click();
     		 WebElement seekBar = driver.findElement(by);

     		 
     		 	      
              
               // Your program element business logic goes here ...
//               AndroidDriver driver=nlpRequestModel.getAndroidDriver();
               
               
               	ac.tap(PointOption.point(999,730)).perform();
             	int seekBarWidth = seekBar.getSize().getWidth();
             	  
           		int seekBarHeight = seekBar.getSize().getHeight();
           		System.out.println(seekBarWidth);
           		System.out.println(seekBarHeight);
           		percentage = percentage/100;
           		System.out.println(percentage);
           		int startX = seekBar.getLocation().getX() + (int) (seekBarWidth * percentage);
           	//	int endX = seekBar.getLocation().getX() + (int) (seekBarWidth * 0.93);
           		
           		
           		int y = (int) (seekBar.getLocation().getY() + seekBarHeight *0.5);
           		
           		
           		TouchAction touchaction = new TouchAction(driver);
        

                   touchaction.tap(ElementOption.point(startX, y)).perform();
        
         nlpResponseModel.setStatus(CommonConstants.pass);
         nlpResponseModel.setMessage("Audio is played continuously");
         
          } catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
              nlpResponseModel.setMessage("Failed to play Audio continuously  :"+ e.getLocalizedMessage());
             
  		}
          return nlpResponseModel;
      }
  } 
