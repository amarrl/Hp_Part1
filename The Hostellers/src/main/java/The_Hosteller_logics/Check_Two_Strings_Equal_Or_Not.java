package The_Hosteller_logics;



import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;



public class Check_Two_Strings_Equal_Or_Not implements Nlp {
	@InputParams({ @InputParam(name = "String1", type = "java.lang.String"),
			@InputParam(name = "String2", type = "java.lang.String") })
	@ReturnType(name = "Return Value", type = "java.lang.boolean")

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
		String string1 = (String) attributes.get("String1");
		String string2 = (String) attributes.get("String2");
		boolean result = false;

		try {
			if (string1.equals(string2)) {
				result = true;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Both the strings are equal");
			} else {
				result = false;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Not Equal");
			}

		} catch (Exception e) {

			e.getMessage();
//			nlpResponseModel.setStatus(CommonConstants.pass);
//			nlpResponseModel.setMessage("Not Equal");
		}

		nlpResponseModel.getAttributes().put("Return Value", result);
		return nlpResponseModel;
	}
}

