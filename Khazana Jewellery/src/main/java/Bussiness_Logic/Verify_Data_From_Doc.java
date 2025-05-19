package Bussiness_Logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

//@Component("LIC20134_PJT1001_PE_NLPe886bd0c-cfa4-4c0f-863a-c7d1c9dc9f12")
import org.springframework.stereotype.Component;

public class Verify_Data_From_Doc implements Nlp {
	@InputParams({ @InputParam(name = "File Path", type = "java.lang.String"),@InputParam(name = "Line", type = "java.lang.String")})
	@ReturnType(name = "returnText", type = "java.lang.String")
	
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
		
		String filePath = (String) attributes.get("File Path");
		String lineInput = (String) attributes.get("Line");
		String Text=null;
	    // Use try-with-resources to ensure that resources are closed after the program is finished
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        // Read the file line by line
	        while ((line = reader.readLine()) != null) {
	        	if(line.contains(lineInput))
	        	{
	        		int start=line.indexOf(lineInput);
	        		//int end=line.indexOf(":");
	        		Text=line.substring(start);
	        		System.out.println( );
	        		
	        		nlpResponseModel.setMessage("Data verified succesfully");
					nlpResponseModel.setStatus(CommonConstants.pass);
	        	}
//	            System.out.println(line);
	        }
	    } catch (IOException e) {
	    	nlpResponseModel.setMessage("Failed to Verify the data");
			nlpResponseModel.setStatus(CommonConstants.fail);
//	        System.err.println("Error reading the file: " + e.getMessage());
	    }
	    
		nlpResponseModel.getAttributes().put("returnText", Text);
		return nlpResponseModel;
	

	}
}