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
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class Drag_And_Drop implements Nlp {
	@InputParams({@InputParam(name = "DragElement", type = "java.lang.String"), @InputParam(name = "DropElement", type = "java.lang.String")})
	

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
		String string1 = (String) attributes.get("DragElement");
		String string2 = (String) attributes.get("DropElement");
		WebDriver driver=nlpRequestModel.getWebDriver();

		try {
			WebElement element1 = driver.findElement(By.xpath(string1));
			WebElement element2 = driver.findElement(By.xpath(string2));
			Actions actions=new Actions(driver);
			actions.clickAndHold(element1).build().perform();
	        Thread.sleep(1000);
	        actions.moveByOffset(100,100).build().perform();
	        Thread.sleep(2000);
	        actions.click(element2).build().perform();
	        
	        actions.build();
	        actions.perform();
	        actions.release();
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully dragged the element");
		}
		catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to drag the element");
		}
		//String string3 = "Return Value";
		//nlpResponseModel.getAttributes().put("string3", string3);
		return nlpResponseModel;
	}
} 
