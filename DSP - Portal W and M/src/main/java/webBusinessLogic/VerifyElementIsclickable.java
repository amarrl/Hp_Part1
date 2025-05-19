
package webBusinessLogic;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class VerifyElementIsclickable implements Nlp {
	@InputParams({ @InputParam(name = "Element", type = "org.openqa.selenium.WebElement") })
	@ReturnType(name = "Return Value", type = "java.lang.Boolean")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		WebElement element = (WebElement) programElementsInput.get("Element");
		Boolean returnValue = null;

		WebDriver driver = nlpRequestModel.getWebDriver();

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		try {
			// Your program element business logic starts here ...

			// Initialize WebDriverWait
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Updated

			// Check if the element is clickable
			WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));

			if (clickableElement != null) {

				returnValue = true;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Given Element is clickable");

			} else if (clickableElement == null) {
				returnValue = false;
				nlpResponseModel.setMessage("Given Element is not clickable");

				nlpResponseModel.setStatus(CommonConstants.pass);

			}
		} catch (Exception e) {

			returnValue = null;
			// Your program element Exception handling goes here ...
			nlpResponseModel.setMessage("Failed to Find the given element :" + e.getMessage());
			nlpResponseModel.setStatus(CommonConstants.fail);

		}

		// Your program element business logic ends here ...
		nlpResponseModel.getAttributes().put("Return Value", returnValue);
		return nlpResponseModel;
	}

}