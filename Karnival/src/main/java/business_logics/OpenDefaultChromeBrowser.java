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
import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;






public class OpenDefaultChromeBrowser implements Nlp {
	@InputParams({ @InputParam(name = "Desired Capabilities", type = "openqa.selenium.remote.DesiredCapabilities"),
			@InputParam(name = "File Path", type = "java.lang.String") })
	@ReturnType(name = "BrowserCapablity", type = "openqa.selenium.remote.DesiredCapabilities")

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
		DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capabilities");
		String filePath = (String) attributes.get("File Path");
		try {
			String dir = filePath;
			Integer port = 9222;
			String cmdCommand = "chrome.exe -remote-debugging-port=" + port
					+ " --no-first-run --no-default-browser-check --user-data-dir=" + dir;
			String chromePath = "C:\\Program Files\\Google\\Chrome\\Application";
			Runtime.getRuntime().exec("cmd /c "+"start/B"+" cmd.exe /K " + cmdCommand, null, new File(chromePath));
			Thread.sleep(2000);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "localhost:9222 ");
			cap.merge(options);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Browser is Launched Successfully");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to launch the browser ");
		}
		nlpResponseModel.setDesiredCapabilities(cap);
		nlpResponseModel.getAttributes().put("BrowserCapablity", cap);
		return nlpResponseModel;
	}
}
