
package Bussiness_logics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

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
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;
import net.sourceforge.tess4j.util.LoadLibs;

public class ClickUisngText implements Nlp {
	@InputParams({@InputParam(name = "Image Path", type = "java.lang.String"), @InputParam(name = "Text", type = "java.lang.String")})
	//	@ReturnType(name = "Sum of Two numbers", type = "java.lang.Integer")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		String path = (String) programElementsInput.get("Image Path");
		String text = (String) programElementsInput.get("Text");
		NlpResponseModel nlpResponseModel = new NlpResponseModel();
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

			ITesseract tesseract = new Tesseract();
			System.out.println(LoadLibs.extractTessResources("tessdata").getAbsolutePath());
			tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath());
			tesseract.setLanguage("eng");
			InputStream ip = new FileInputStream(path);
			BufferedImage bufferedImage = ImageIO.read(ip);
			String result = tesseract.doOCR(bufferedImage);
			List<net.sourceforge.tess4j.Word> words = tesseract.getWords(bufferedImage, TessAPI.TessPageSegMode.PSM_AUTO);
			// Your program element business logic starts here ...
			for (Word word : words) {
				if (word.toString().toLowerCase().contains(text) || true) {
					int x1 = word.getBoundingBox().x;
					int y1 = word.getBoundingBox().y;
					int width = word.getBoundingBox().width;
					int height = word.getBoundingBox().height;
					TouchAction action = null;
					int x = x1+(width/2);
					int y = y1+(height/2);
					if (androidDriver!=null) {
						action = new TouchAction(androidDriver);
					}
					else {
						action = new TouchAction(iosDriver);
					}
					action.tap(PointOption.point(x, y)).perform();
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Successfully Clicked on the text");
				}
			}
		}
		catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to click");
		}

		// Your program element business logic ends here ...
		return nlpResponseModel;
	}

} 