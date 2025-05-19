package Bussiness_Logics;





import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.AppiumDriver;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
























public class Is_Contains implements Nlp {
	@InputParams({ @InputParam(name = "List", type = "java.util.List"),@InputParam(name = "Value", type = "java.lang.String")})
	@ReturnType(name = "Assign Step Return Value To", type = "java.lang.Boolean")

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
		List list = (List) attributes.get("List");
		String value=(String) attributes.get("Value");
		Boolean contain = false;
		try {
				if(list.isEmpty()==false) {
				if(list.contains(value)==true) {
			    contain=true;
									
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage( "List contains the given value "+value);
							
				}else {
					contain=false;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage( "List not contains the given value "+value);
				}
				}else {
					contain=false;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage( "List is emptyy");
					
				}
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage( "Failed to execute");
			contain=false;
		}

		nlpResponseModel.getAttributes().put("Assign Step Return Value To",contain);
		return nlpResponseModel;
	}
}

