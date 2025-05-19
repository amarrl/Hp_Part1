package default_package;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

@Component("LIC18077_PJT1002_PE_NLPcdbec083-f13c-4d22-818a-7116af4ac7ef")
public class SetLoginPreference implements Nlp {
	@InputParams({ @InputParam(name = "DesiredCapabilities_in", type = "org.openqa.selenium.remote.DesiredCapabilities")})
	@ReturnType(name = "DesiredCapabilities_out", type = "org.openqa.selenium.remote.DesiredCapabilities")
	
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
		DesiredCapabilities desiredCapabilities = (DesiredCapabilities) attributes.get("DesiredCapabilities_in");
		
		ChromeOptions options = new ChromeOptions();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		options.setCapability("goog:loggingPrefs", logPrefs);
		nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("");
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		nlpResponseModel.getAttributes().put("DesiredCapabilities_out", desiredCapabilities);
		return nlpResponseModel;
	}
}
