package Bussiness_Logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;



@Component("LIC20134_PJT1001_PE_NLP5114d004-f7f5-4f28-8e64-6ba9ad6f5344")
public class LaunchExeWithoutCmd implements Nlp {
	@InputParams({ @InputParam(name = "exePath", type = "java.lang.String")})

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
		String EXEPATH = (String) attributes.get("exePath");

		String cmdCommand="Jewello.exe";
		try {
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + cmdCommand , null, new File(EXEPATH));
			Thread.sleep(3000);
			Runtime.getRuntime().exec("taskkill /IM cmd.exe /F");


			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("App opened Successfully");
		} 
		catch (Exception e) {

			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Open the App"+e);
			e.printStackTrace();


		}


		return nlpResponseModel;
	}
	
	}


