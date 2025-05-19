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
import net.bytebuddy.asm.Advice.Local;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
























public class Split_String implements Nlp {
	@InputParams({ @InputParam(name = "String", type = "java.lang.String"),@InputParam(name = "Regex", type = "java.lang.String")})
	@ReturnType(name = "Assign step to rerurn value", type = "java.lang.String")

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
		String string = (String) attributes.get("String");
		String regex = (String) attributes.get("Regex");
		
		String[] listOfString =null;
		String[] listOfString1= new String[1];
		
			try {
				
				listOfString=string.split(regex);
			
			    nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage(string +" is splitted successfully from given regex "+regex);
			
			    } catch (Exception e) {
			
			    	listOfString1[0]=string;
			    	listOfString=listOfString1;
			    	
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed to split the given string "+string);
		}

		nlpResponseModel.getAttributes().put("Assign step to rerurn value", listOfString);
		return nlpResponseModel;
	}
}

