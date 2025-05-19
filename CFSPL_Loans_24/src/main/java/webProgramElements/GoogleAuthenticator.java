
package webProgramElements;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.jboss.aerogear.security.otp.Totp;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class GoogleAuthenticator implements Nlp {
	@InputParams({ @InputParam(name = "SecretKey", type = "java.lang.String") })
	@ReturnType(name = "PassKey", type = "java.lang.String")
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		String SecretKey = (String) programElementsInput.get("SecretKey");

		NlpResponseModel nlpResponseModel = new NlpResponseModel();

		String passKey = null;
		try {
			//Class.forName("org.jboss.aerogear.security.otp.Totp");

			// using org.jboss.aerogear.security.otp.Totp class
			Totp totp = new Totp(SecretKey);
			passKey = totp.now();
			System.out.println("Passkey is: " + passKey);

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Google Authenticator 2FA Code is " + passKey);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			nlpResponseModel.setMessage("Failed to fetch the 2FA code " + e.getMessage() + " " + exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);

		}
		nlpResponseModel.getAttributes().put("PassKey", passKey);
		return nlpResponseModel;
	}

	public static void main(String[] args) throws Throwable {
		GoogleAuthenticator convert = new GoogleAuthenticator();
		NlpRequestModel req = new NlpRequestModel();

		req.getAttributes().put("SecretKey", "w4vc u6vz jqgq jpou vs4s h2sr tvew xtzn");
		// req.getAttributes().put("Download Directory", "C:\\Users\\User\\Downloads");

		try {
			System.out.println(convert.execute(req));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}