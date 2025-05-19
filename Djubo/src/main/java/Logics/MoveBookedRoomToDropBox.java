
package Logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

//import groovy.util.logging.Log;
//import groovy.util.logging.Slf4j;



public class MoveBookedRoomToDropBox implements Nlp {
	@InputParams({ @InputParam(name = "Start Date", type = "java.lang.String"),
		@InputParam(name = "Start Room Name", type = "java.lang.String"),
		@InputParam(name = "DropBox Element", type = "org.openqa.selenium.WebElement"),
		@InputParam(name = "Room category", type = "java.lang.String") })

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
		String date = (String) attributes.get("Start Date");
		String roomName = (String) attributes.get("Start Room Name");
		WebElement dropBox = (WebElement) attributes.get("DropBox Element");
		String category = (String) attributes.get("Room category");
		WebDriver driver = nlpRequestModel.getWebDriver();
		try {
			driver.findElement(By.xpath("//a[.='"+category+"']/ancestor::div[@class='booking-data ng-scope closedSide']/descendant::table/tbody/tr/td[text()='"+roomName+"']"));
			String cat = driver.findElement(By.xpath("//*[text()='"+category+"']/parent::div[@class='action']")).getAttribute("id");
			int num = (Integer.parseInt(cat.substring(cat.length()-1,cat.length())));
			Actions ac = new Actions(driver);
			ac.clickAndHold(driver.findElement(By.xpath(
					"//div[@class='booking-details active bars"+num+"']/table/tbody/tr[count(//a[.='"+category+"']/ancestor::div[@class='booking-data ng-scope closedSide']/descendant::table/tbody/tr/td[text()='"+roomName+"']/parent::tr/preceding-sibling::tr)+1]/td[count(//*[text()='"+date+"']/ancestor::td/preceding-sibling::td)+1]/span")))
			.moveToElement(dropBox).release().perform();
			nlpResponseModel.setMessage("The provided room dropped into drop box");
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			nlpResponseModel.setMessage("The provided "+roomName+" doesn't exist in "+category+" Category "+e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		return nlpResponseModel;
	}
}