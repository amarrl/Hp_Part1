
package Bussiness_logics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollIntoView implements Nlp {
    @InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement")})
//    @ReturnType(name = "Sum of Two numbers", type = "java.lang.Integer")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          WebElement element = (WebElement) programElementsInput.get("Element");
//          Integer number2 = (Integer) programElementsInput.get("Number 2");
//          Integer returnValue=null;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
              // Your program element business logic starts here ...
        	  WebDriver driver = (WebDriver)nlpRequestModel.getWebDriver();
        	  JavascriptExecutor js=(JavascriptExecutor)driver;
        	  js.executeScript("window.scrollBy("+element.getLocation().getX()+", "+element.getLocation().getY()+");");
              nlpResponseModel.setStatus(CommonConstants.pass);
              //Uncomment the below line and modify the message, only if you want to override the pass message defined for the program element.
              nlpResponseModel.setMessage("Scrolled to element successfully :"+element);
          }
          catch(Exception e) {
          	 // Your program element Exception handling goes here ...
             nlpResponseModel.setStatus(CommonConstants.fail);

             //Uncomment the below line and modify the message, only if you want to override the fail message defined for the program element.
             nlpResponseModel.setMessage("Failed to scroll");
          }

          // Your program element business logic ends here ...
//          nlpResponseModel.getAttributes().put("Sum of Two numbers", returnValue);
          return nlpResponseModel;
      }

  } 