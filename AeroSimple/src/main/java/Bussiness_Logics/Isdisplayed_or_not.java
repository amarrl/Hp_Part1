package Bussiness_Logics;





import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;











@Component("LIC15630_PJT1001_PE_NLPa11ac24d-cec3-4a04-a9f0-21c3b0b11f23")
public class Isdisplayed_or_not implements Nlp {
	@InputParams({ @InputParam(name = "Enter Xpath Value", type = "java.lang.String")})
	@ReturnType(name = "IsDisplayed", type = "java.lang.Boolean")

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
		String locatorValue = (String) attributes.get("Enter Xpath Value");
		
	
		Boolean IsDisplayed = null;
		try {
			WebDriver driver = (WebDriver)nlpRequestModel.getDriver().getSpecificIDriver();
			WebElement element = driver.findElement(By.xpath(locatorValue));
			IsDisplayed = element.isDisplayed();
			
			if(IsDisplayed==true) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage( "Element is Displayed on the page  ");
			}else {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage( "Element is not Displayed on the page ");
				IsDisplayed=false;
			}
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage( "Element is not Displayed on the page ");
			IsDisplayed=false;
		}

		nlpResponseModel.getAttributes().put("IsDisplayed", IsDisplayed);
		return nlpResponseModel;
	}
}