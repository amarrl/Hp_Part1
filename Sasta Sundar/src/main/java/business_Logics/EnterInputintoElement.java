package business_Logics;
 
import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
 
public class EnterInputintoElement implements Nlp {
	@InputParams({@InputParam(name = "Xpath", type = "java.lang.String"),
		@InputParam(name = "Input", type = "java.lang.String")})
 
	

 
      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
 
          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          String xpath = (String) programElementsInput.get("Xpath");
          String input = (String) programElementsInput.get("Input");
         // Integer returnValue=null;
 
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          WebDriver driver=nlpRequestModel.getAndroidDriver();
          try {
        	 WebElement mobileNumber= driver.findElement(By.xpath(xpath));
      		 Actions act = new Actions(driver);
             act.sendKeys(mobileNumber, input).build().perform();
 
              nlpResponseModel.setStatus(CommonConstants.pass);
              //Uncomment the below line and modify the message, only if you want to override the pass message defined for the program element.
              //nlpResponseModel.setMessage("Added two numbers");
          }
          catch(Exception e) {
          	 // Your program element Exception handling goes here ...
             nlpResponseModel.setStatus(CommonConstants.fail);
 
             //Uncomment the below line and modify the message, only if you want to override the fail message defined for the program element.
             //nlpResponseModel.setMessage("Failed to add numbers");
          }
 
          // Your program element business logic ends here ...
        //  nlpResponseModel.getAttributes().put("Sum of Two numbers", returnValue);
          return nlpResponseModel;
      }
 
  }