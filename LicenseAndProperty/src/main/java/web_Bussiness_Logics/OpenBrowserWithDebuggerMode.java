
package web_Bussiness_Logics;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

public class OpenBrowserWithDebuggerMode implements Nlp {
	@InputParams({ @InputParam(name = "Folder Path", type = "java.lang.String") })

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		String folderPath = (String) programElementsInput.get("Folder Path");
		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		try {
			// Your program element business logic starts here ...
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-data-dir="+folderPath);
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-blink-features=AutomationControlled");
			List s = new ArrayList();
			s.add("enable-automation");
			options.setExperimentalOption("excludeSwitches", s);
			options.setExperimentalOption("useAutomationExtension", false);
			cap.merge(options);
			
			WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			nlpResponseModel.setWebDriver(driver);

			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			// Your program element Exception handling goes here ...
			nlpResponseModel.setStatus(CommonConstants.fail);

		}

		// Your program element business logic ends here ...
		return nlpResponseModel;
	}

}