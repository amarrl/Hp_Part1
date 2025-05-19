package business_Logics;
 
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
 
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
 
public class EnterInput implements Nlp {
	@InputParams({ @InputParam(name = "Input", type = "java.lang.String") })
 
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
		String otp = (String) attributes.get("Input");
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
 
		try {
 
			for (char c : otp.toCharArray()) {
				if (Character.isDigit(c)) {
					// Generate KeyEvent dynamically for digits
					int digit = Character.getNumericValue(c);
					driver.pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + digit)));
				} else {
					// Handle other characters if needed (e.g., letters, special characters)
					throw new IllegalArgumentException("Unsupported character: " + c);
				}
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is selected and Input entered successfully");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Error while selecting element or entering Input: " + e.getMessage());
		}
 
		return nlpResponseModel;
	}
}