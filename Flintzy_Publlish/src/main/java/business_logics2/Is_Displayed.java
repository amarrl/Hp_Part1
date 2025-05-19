
package business_logics2;

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

import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component("LIC18077_PJT1001_PE_NLPa93e4d6e-44cd-4389-8f34-d492bc5bd13a")
public class Is_Displayed implements Nlp {
    @InputParams({@InputParam(name = "string1", type = "org.openqa.selenium.WebElement")})
    @ReturnType(name = "string3", type = "java.lang.Boolean")

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
          
          WebElement  element= (WebElement) attributes.get("string1");
          boolean ele=false;
          try {
        	 
        	   ele = element.isDisplayed();
        	   nlpResponseModel.setStatus(CommonConstants.pass);
   			nlpResponseModel.setMessage("Element Is Dispalyed In WebPage");
          }catch(Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
     			nlpResponseModel.setMessage("Element Is not Dispalyed In WebPage" + e);
          }

          
          nlpResponseModel.getAttributes().put("string3", ele);
          return nlpResponseModel;
      }
  } 
