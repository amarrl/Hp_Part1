package Business_Logics;




import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;



public class Swipe_handle implements Nlp {
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
          AndroidDriver androidDriver=nlpRequestModel.getAndroidDriver();
          org.openqa.selenium.Dimension screenSize = androidDriver.manage().window().getSize();//Identify screen dimension
  		int screenCenter = (int) (screenSize.getHeight()*0.9);
  		int startPoint = (int) (screenSize.getWidth()*0.6);
  		int endPoint = (int) (screenSize.getWidth()*0.4);
  		String xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView";
  		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
  		 try {
  			 androidDriver.findElement(By.xpath(xpath));
  			 Sequence swipe =  new Sequence(finger, 1);
  				swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startPoint, screenCenter));
  				swipe.addAction(finger.createPointerDown(0));
  				swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), endPoint, screenCenter));
  				swipe.addAction(finger.createPointerUp(0));
  				androidDriver.perform(Arrays.asList(swipe));
  				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully performed swipe Action ");	
  		} catch (Exception e) {
  			while (true) {
  				try {
  					androidDriver.findElement(By.id("com.yogifi.application:id/exo_overlay")).click();
  					androidDriver.findElement(By.xpath(xpath));
  					 Sequence swipe =  new Sequence(finger, 1);
  						swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startPoint, screenCenter));
  						swipe.addAction(finger.createPointerDown(0));
  						swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), endPoint, screenCenter));
  						swipe.addAction(finger.createPointerUp(0));
  						androidDriver.perform(Arrays.asList(swipe));
  						nlpResponseModel.setStatus(CommonConstants.pass);
  						nlpResponseModel.setMessage("Successfully performed swipe Action ");	
  					break;
  					
  				} catch (Exception e2) {
  					//Exception
  					}
  			}
  			
  		}      
          return nlpResponseModel;
      }
} 