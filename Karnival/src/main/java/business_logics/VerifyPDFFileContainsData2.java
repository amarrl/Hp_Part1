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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;




public class VerifyPDFFileContainsData2 implements Nlp {
	@InputParams({ @InputParam(name = "Data", type = "java.lang.String"),
			@InputParam(name = "File Path", type = "java.lang.String") })
	@ReturnType(name = "pdfContains", type = "java.lang.Boolean")

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
		String data = (String) attributes.get("Data");
		String filePath = (String) attributes.get("File Path");
		Boolean b=false;
		// Your program element business logic goes here ...
		try {
			
			String pdfFilePath = filePath;
			PDDocument document = PDDocument.load(new File(pdfFilePath));
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			String pdfText = pdfTextStripper.getText(document).toLowerCase();
				if (pdfText.contains(data)) {
					b=true;
				} else {
					b=false;
				}
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("PDF file contains data "+data+"");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("PDF file does not contain data "+data+"");
		}

		nlpResponseModel.getAttributes().put("pdfContains", b);
		return nlpResponseModel;
	}
}
