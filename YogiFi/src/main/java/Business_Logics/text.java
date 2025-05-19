
package Business_Logics;

import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;

public class text implements Nlp {
	@InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),@InputParam(name = "Attribute Name", type ="java.lang.String")  } )
	
		@ReturnType(name = "Sum of Two numbers", type = "java.lang.String")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		WebElement el = (WebElement) programElementsInput.get("element");
		String AttributeName = (String) programElementsInput.get("Attribute Name");
		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		String text=null;
		try{
			AndroidDriver androidDriver=null;
			IOSDriver iosDriver=null;
			try {
				androidDriver=nlpRequestModel.getAndroidDriver();
				iosDriver=nlpRequestModel.getIosDriver();
			} catch (Exception e) {
				iosDriver=nlpRequestModel.getIosDriver();
				// TODO: handle exception
			}
			if (androidDriver!=null) {
				text=el.getText();
			}else {
				text=el.getAttribute(AttributeName);
			}

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully fetched the text from the element");

		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
//			log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform action "+exceptionAsString);
		}

		// Your program element business logic ends here ...
        nlpResponseModel.getAttributes().put("Sum of Two numbers", text);
		return nlpResponseModel;
	}

}