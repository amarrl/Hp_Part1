package default_package;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.ArrayList;
import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;


@Component("LIC18077_PJT1002_PE_NLP527d128b-9b37-4805-a1af-0cc7c85c463f")
public class FetchLogsWithFiltering implements Nlp {
	@InputParams({@InputParam(name = "URL with Endpoint", type = "java.lang.String")})
	@ReturnType(name = "Logs", type = "java.lang.String")
	
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
		String url = (String) attributes.get("URL with Endpoint");

		WebDriver driver = nlpRequestModel.getWebDriver();
		// Your program element business logic goes here ...

		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
		
		List<String> logs = new ArrayList<>();
		for (LogEntry entry : entries) {
			if (entry.getMessage().contains(url.trim())) {
				logs.add(entry.getMessage());
			}
		}
		
		if (!logs.isEmpty()) {
			nlpResponseModel.setMessage("Fetching Network logs is successful...");
			nlpResponseModel.setStatus(CommonConstants.pass);
		} else {
			nlpResponseModel.setMessage("Failed to fetch Network logs/No Api requests found for specified URL...");
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("RequestBody", logs);
		return nlpResponseModel;
	}
} 
