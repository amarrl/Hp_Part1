
package logics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReturnValueFromJS implements Nlp {
	@InputParams({@InputParam(name = "Enter Xpath", type = "java.lang.String")})
	@ReturnType(name = "Assign step to return value", type = "java.lang.Boolean")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		WebElement element = (WebElement) programElementsInput.get("Element");
		String path = (String) programElementsInput.get("Enter Xpath");
		boolean isDisplayed=false;

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		try {
			// Your program element business logic starts here ...
			WebDriver driver = nlpResponseModel.getWebDriver();
			isDisplayed=driver.findElement(By.xpath(path)).isDisplayed();
			if(isDisplayed==true) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			//Uncomment the below line and modify the message, only if you want to override the pass message defined for the program element.
			nlpResponseModel.setMessage("Element is displayed");
			}else {
				nlpResponseModel.setStatus(CommonConstants.pass);
				//Uncomment the below line and modify the message, only if you want to override the pass message defined for the program element.
				nlpResponseModel.setMessage("Element is not displayed");
			}
		}
		catch(Exception e) {
			// Your program element Exception handling goes here ...
			nlpResponseModel.setStatus(CommonConstants.fail);

			//Uncomment the below line and modify the message, only if you want to override the fail message defined for the program element.
			nlpResponseModel.setMessage("Failed to execute :"+e.getMessage());
		}

		// Your program element business logic ends here ...
		nlpResponseModel.getAttributes().put("Assign step to return value", isDisplayed);
		return nlpResponseModel;
	}

} 