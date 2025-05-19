package The_Hosteller_logics;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;




public class Scroll_Element implements Nlp {
	@InputParams({ @InputParam(name = "Scroll Element", type = "org.openqa.selenium.WebElement")})

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
		WebElement ele = (WebElement) attributes.get("Scroll Element");
		WebDriver driver = nlpRequestModel.getWebDriver();
		try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",ele);
			nlpResponseModel.setMessage("Scrolled to the element");
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			nlpResponseModel.setMessage("Could not scroll to the element "+e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		return nlpResponseModel;
	}
}