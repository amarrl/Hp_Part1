
package Bussiness_Logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import org.springframework.stereotype.Component;







@Component("LIC15630_PJT1001_PE_NLP4a895831-ff4a-46e0-bcd8-19ea056f9204")
public class Select_Year_From_Calendar implements Nlp {
    @InputParams({@InputParam(name = "Select Year Path", type = "java.lang.String"),@InputParam(name = "Previous Year", type = "org.openqa.selenium.WebElement"),
    	@InputParam(name = "Next Year", type = "org.openqa.selenium.WebElement"),@InputParam(name = "Calendar Column Last Year", type = "org.openqa.selenium.WebElement")
    ,@InputParam(name = "Enter Year", type = "java.lang.String")})


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
          String selectYearPath = (String) attributes.get("Select Year Element");
          String enterYear= (String) attributes.get("Enter Year");
          WebElement previousYear = (WebElement) attributes.get("Previous Year");
          WebElement nextYear = (WebElement) attributes.get("Next Year");
          WebElement lastYear = (WebElement) attributes.get("Calendar Column Last Year");
          
          // Your program element business logic goes here ...
          try {
        	  WebDriver driver=(WebDriver)nlpRequestModel.getDriver().getSpecificIDriver();
        	  driver.findElement(By.xpath(selectYearPath)).click();
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("Failed to click on the selected year");
        	  
			}catch(Exception e) {
				
				String columnLastYear = lastYear.getText();
				Integer.parseInt(columnLastYear);
				if(Integer.parseInt(columnLastYear)> Integer.parseInt(enterYear)) {
					previousYear.click();
					
				}else {
					nextYear.click();
				}
				 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Successfully clicked on the selected year");
			}
          
          return nlpResponseModel;
      }
  } 