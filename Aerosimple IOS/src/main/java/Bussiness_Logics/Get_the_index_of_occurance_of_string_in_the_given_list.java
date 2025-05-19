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
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Get_the_index_of_occurance_of_string_in_the_given_list implements Nlp {
	@InputParams({ @InputParam(name = "Value", type = "java.lang.String") })
	@ReturnType(name = "Assign step to return value", type = "java.lang.Integer")

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

		String value = (String) attributes.get("Value");

		int pos = 0;

		try {
//				ArrayList arrlist=new ArrayList();
			String[] arr = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
					"September", "October", "November", "December" };

			for (int i = 0; i < arr.length; i++) {
				if (arr[i].equalsIgnoreCase(value)) {
					pos = i + 1;
				}
			}

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("The Index of occurance of the string " + value + " in the given List");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("String is not present in the List");

		}

		nlpResponseModel.getAttributes().put("Assign step to return value", pos);
		return nlpResponseModel;
	}
}
