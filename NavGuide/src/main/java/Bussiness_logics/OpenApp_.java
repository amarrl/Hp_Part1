
package Bussiness_logics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

import org.openqa.selenium.remote.DesiredCapabilities;

public class OpenApp_ implements Nlp {
	@InputParams({@InputParam(name = "Number 1", type = "java.lang.Integer"), @InputParam(name = "Number 2", type = "java.lang.Integer")})
	@ReturnType(name = "Sum of Two numbers", type = "java.lang.String")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		Integer number1 = (Integer) programElementsInput.get("Number 1");
		Integer number2 = (Integer) programElementsInput.get("Number 2");
		Integer returnValue=null;

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		try {
			// Your program element business logic starts here ...
			DesiredCapabilities c = nlpRequestModel.getDesiredCapabilities();
			c.getPlatformName();

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
		nlpResponseModel.getAttributes().put("Sum of Two numbers", nlpRequestModel.getDesiredCapabilities().getPlatformName());
		return nlpResponseModel;
	}
	public static void main(String[] args) {
		NlpRequestModel l=new NlpRequestModel();
		System.out.println(l.getDesiredCapabilities().getCapability(""));
		
	}

} 