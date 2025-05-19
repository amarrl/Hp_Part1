package bussiness_Logics;



import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;



@Component("LIC18077_PJT1001_PE_NLPb18b2aef-0305-4190-9d97-efe68227bc69")
public class Date_Generator implements Nlp {
	@InputParams({ @InputParam(name = "offset", type = "java.lang.Integer"),
		@InputParam(name = "format", type = "java.lang.String")})
	@ReturnType(name = "generatedDate", type = "java.lang.String")

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
		Integer offset = (Integer) attributes.get("offset");
		String format = (String) attributes.get("format");
		
		String generatedDate=null;
		// Your program element business logic goes here ...
		try {
			 Calendar calendar = Calendar.getInstance();
		        calendar.setTime(new Date());
		        calendar.add(Calendar.DAY_OF_MONTH, offset);

		        generatedDate= new SimpleDateFormat(format).format(calendar.getTime());
		        nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Date Generated Successfully "+ generatedDate);
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Date not gerenated ");
		}

		nlpResponseModel.getAttributes().put("generatedDate", generatedDate);
		return nlpResponseModel;
	}
}

