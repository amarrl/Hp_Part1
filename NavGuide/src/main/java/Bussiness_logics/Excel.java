
package Bussiness_logics;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Excel implements Nlp {
	@InputParams({@InputParam(name = "Android Validation", type = "java.util.Map"), @InputParam(name = "iOS Validation", type = "java.util.Map"),
		@InputParam(name = "Key Name", type = "java.lang.String")})
	    @ReturnType(name = "Sum of Two numbers", type = "java.lang.String")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		Map<String, String> map = (Map) programElementsInput.get("Android Validation");
		Map<String, String> map1 = (Map) programElementsInput.get("iOS Validation");
		String keyName = (String) programElementsInput.get("Key Name");
        Map<String, String> tableMap = new LinkedHashMap<>();
		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		try {
			AndroidDriver androidDriver=null;
			IOSDriver iOsDriver=null;
			try {
				androidDriver=nlpRequestModel.getAndroidDriver();
				iOsDriver=nlpRequestModel.getIosDriver();
			} catch (Exception e) {
				iOsDriver=nlpRequestModel.getIosDriver();
			}
			Map main=null;
			if (androidDriver!=null) {
				tableMap=map;
			} else {
				tableMap=map1;
			}
			keyName=tableMap.get(keyName);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("The value has been successfully retrieved from the map");
		}
		catch(Exception e) {
			// Your program element Exception handling goes here ...
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			//  			log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to fetch "+exceptionAsString);
		}

		// Your program element business logic ends here ...
		          nlpResponseModel.getAttributes().put("Sum of Two numbers", keyName);
		return nlpResponseModel;
	}

} 