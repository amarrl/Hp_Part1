package default_package;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

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

@Component("LIC18077_PJT1002_PE_NLP7bee5804-14f0-491e-aa0f-c6e336f7bcc9")
public class NetworkLogsWithOutFiltering implements Nlp {
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

		WebDriver driver = nlpRequestModel.getWebDriver();
		// Your program element business logic goes here ...

		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
		
		List<String> logs = new ArrayList<>();
		for (LogEntry entry : entries) {
			logs.add(entry.getMessage());
			
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
