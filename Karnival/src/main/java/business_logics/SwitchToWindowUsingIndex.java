package business_logics;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;






public class SwitchToWindowUsingIndex implements Nlp {
	@InputParams({ @InputParam(name = "Index", type = "java.lang.Integer") })
	@ReturnType(name = "isSwitched", type = "java.lang.Boolean")

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
		Integer Index = (Integer) attributes.get("Index");
		// Your program element business logic goes here ...
		WebDriver driver = nlpRequestModel.getWebDriver();
		Boolean isSwitched = null;
		try {
			Set<String> handle = driver.getWindowHandles();
			List<String> listFromSet = new ArrayList<String>(handle);
			String getIndex = listFromSet.get(Index);
			driver.switchTo().window(getIndex);
			isSwitched = true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Switched To " + Index + " index");
		} catch (NoSuchWindowException e) {
			isSwitched = false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Specidied Window Is Not Present");
		} catch (Exception g) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Switch to specified window " + g);
		}
		nlpResponseModel.getAttributes().put("isSwitched", isSwitched);
		return nlpResponseModel;
	}
}
